#include "bank.h"
#include "ports.h"
#include "list.h"
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
///////////////////
#include <ctype.h>
#include <openssl/conf.h>
#include <openssl/evp.h>
#include <openssl/err.h>

Bank *bank_create()
{
    Bank *bank = (Bank *)malloc(sizeof(Bank));
    if (bank == NULL)
    {
        perror("Could not allocate Bank");
        exit(1);
    }

    // Set up the network state
    bank->sockfd = socket(AF_INET, SOCK_DGRAM, 0);

    bzero(&bank->rtr_addr, sizeof(bank->rtr_addr));
    bank->rtr_addr.sin_family = AF_INET;
    bank->rtr_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    bank->rtr_addr.sin_port = htons(ROUTER_PORT);

    bzero(&bank->bank_addr, sizeof(bank->bank_addr));
    bank->bank_addr.sin_family = AF_INET;
    bank->bank_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    bank->bank_addr.sin_port = htons(BANK_PORT);
    bind(bank->sockfd, (struct sockaddr *)&bank->bank_addr, sizeof(bank->bank_addr));

    // Set up the protocol state
    // TODO set up more, as needed
    bank->users = list_create();
    return bank;
}

void bank_free(Bank *bank)
{
    if (bank != NULL)
    {
        close(bank->sockfd);
        free(bank);
    }
}

ssize_t bank_send(Bank *bank, char *data, size_t data_len)
{
    // Returns the number of bytes sent; negative on error
    return sendto(bank->sockfd, data, data_len, 0,
                  (struct sockaddr *)&bank->rtr_addr, sizeof(bank->rtr_addr));
}

ssize_t bank_recv(Bank *bank, char *data, size_t max_data_len)
{
    // Returns the number of bytes received; negative on error
    return recvfrom(bank->sockfd, data, max_data_len, 0, NULL, NULL);
}

User *new_user(char user_name[250], char pin[4], int balance)
{
    User *user = (User *)malloc(sizeof(user));
    strcpy(user->user_name, user_name);
    user->balance = balance;
    strcpy(user->pin, pin);
    return user;
}

void gen_card(char user_name[250])
{
    // generate relatively random 10 number card using rand and
    // seeding it with the username
    char file_name[260];
    strcpy(file_name, user_name);
    u_int seed;
    int card_num[10];
    for (int i = 0; i < 10; i++)
    {
        seed = user_name[i];
        srand(seed);
        card_num[i] = rand();
    }
    // write to file <user_name>.card
    strcat(file_name, ".card");
    FILE *fp = fopen(file_name, "w");
    // check if file could be opened
    if (fp == NULL)
    {
        printf("Error creating card file for user %s", user_name);
        return;
    }

    for (int i = 0; i < 10; i++)
    {
        fprintf(fp, "%d", card_num[i]);
    }
    fclose(fp);
    return;
}

bool create_user(Bank *bank, char user_name[250], char pin[4], int balance)
{
    if (list_find(bank->users, user_name) != NULL)
    {
        printf("%s%s%s", "Error: user ", user_name, " already exists\n");
        return false;
    }

    User *user = new_user(user_name, pin, balance);
    list_add(bank->users, user_name, user);
    gen_card(user_name);
    return true;
}

bool deposit(Bank *bank, char user_name[250], int amt)
{
    User *user = list_find(bank->users, user_name);
    if (user == NULL)
    {
        printf("No such user\n");
        return false;
    }
    if ((user->balance + amt) > 2147483647 || (user->balance + amt) < 0)
    {
        printf("Too rich for this program\n");
        return false;
    }
    user->balance = user->balance + amt;
    return true;
}

bool check_num(char *str)
{
    for (int i = 0; i < strlen(str); i++)
    {
        if (isdigit(str[i]) == 0 && str[i] != '\n')
        {
            return false;
        }
    }
    return true;
}

void error_mess(char *comm_type)
{
    char *err_mess_create = "Usage: create-user <user-name> <pin> <balance>\n";
    char *err_mess_deposit = "Usage: deposit <user-name> <amt>\n";
    char *err_mess_balance = "Usage: balance <user-name>\n";
    if (strcmp(comm_type, "create-user") == 0)
    {
        printf("%s", err_mess_create);
    }
    else if (strcmp(comm_type, "deposit") == 0)
    {
        printf("%s", err_mess_deposit);
    }
    else if (strcmp(comm_type, "balance") == 0)
    {
        printf("%s", err_mess_balance);
    }
    else
    {
        printf("Invalid command\n");
    }
}

/*
 * we first want to parse the command into different variables so we can use it
 * to do whatever action it needs to.
 */
void bank_process_local_command(Bank *bank, char *command, size_t len)
{
    // initialize variables
    char *err_mess_create = "Usage: create-user <user-name> <pin> <balance>\n";
    char *err_mess_deposit = "Usage: deposit <user-name> <amt>\n";
    char *err_mess_balance = "Usage: balance <user-name>\n";
    char *name;
    char *comm_type;
    char user_name[250] = "";
    comm_type = strtok(command, " ");

    // check to make sure input is valid
    if (strcmp(comm_type, "balance") == 0)
    {
        name = strtok(NULL, "\n");
    }
    else
    {
        name = strtok(NULL, " ");
    }

    if (name == NULL)
    {
        error_mess(comm_type);
        return;
    }
    // initialize the rest
    char *balance_str = "";
    char *amt_str = "";
    char *pin_str = "";
    char pin[4] = "";
    int balance, amt;
    bool res;

    // check usernames
    if (strlen(name) > 250)
    {
        error_mess(comm_type);
        return;
    }
    for (int i = 0; i < strlen(name); i++)
    {
        if (isalpha(name[i]) == 0)
        {
            if (strcmp(comm_type, "balance") == 0)
            {
                if (i != strlen(name) - 1)
                {
                    printf("%s", err_mess_balance);
                    return;
                }
            }
            else
            {
                error_mess(comm_type);
                return;
            }
        }
    }

    // copy token to a standard size of 250
    strncpy(user_name, name, (sizeof user_name));

    // check command type and run local command
    if (strcmp(comm_type, "create-user") == 0)
    {
        pin_str = strtok(NULL, " ");
        for (int i = 0; i < 4; i++)
        {
            pin[i] = pin_str[i];
        }
        balance_str = strtok(NULL, " ");

        // verify balance and pin to be valid inputs
        if (strlen(pin_str) > 4 || strlen(pin_str) < 4 || atoi(balance_str) > 2147483647 || atoi(balance_str) < 0)
        {
            printf("%s", err_mess_create);
            return;
        }
        if (strcmp(balance_str, "0") == 0 && atoi(balance_str) == 0)
        {
            printf("%s", err_mess_create);
            return;
        }
        else if (check_num(balance_str) == false || check_num(pin_str) == false)
        {
            printf("%s", err_mess_create);
            return;
        }

        balance = atoi(balance_str);
        res = create_user(bank, user_name, pin, balance);
        if (res)
        {
            printf("%s%s%s", "Created user ", user_name, "\n");
        }
    }
    else if (strcmp(comm_type, "deposit") == 0)
    {
        amt_str = strtok(NULL, " ");
        // Check if amt is valid
        if (atoi(amt_str) > 2147483647)
        {
            printf("Too rich for this program\n");
            return;
        }
        if (atoi(amt_str) < 0)
        {
            printf("%s", err_mess_deposit);
            return;
        }
        if (check_num(amt_str) == false || check_num(balance_str) == false)
        {
            printf("%s", err_mess_deposit);
            return;
        }

        amt = atoi(amt_str);
        res = deposit(bank, user_name, amt);
        if (res)
        {
            printf("$%d added to %s's account\n", amt, user_name);
        }
    }
    else if (strcmp(comm_type, "balance") == 0)
    {
        if (list_find(bank->users, user_name) == NULL)
        {
            printf("No such user\n");
            return;
        }
        User *user = list_find(bank->users, user_name);
        printf("$%d\n", user->balance);
    }
    else
    {
        printf("Invalid command\n");
    }

    return;
}

void XORCipher(char *data, char *key, int dataLen, int keyLen)
{
    for (int i = 0; i < dataLen; ++i)
    {
        data[i] = data[i] ^ key[i % keyLen];
    }
}

void bank_process_remote_command(Bank *bank, char *command, size_t len, char key[1000])
{
    char *res = "";
    char res2[10] = "";
    char pin[4] = "";
    XORCipher(command, key, strlen(command), strlen(key));
    char *comm_type = strtok(command, "?");
    char *name = strtok(NULL, "?");
    char user_name[250];
    strncpy(user_name, name, (sizeof user_name));
    User *user = list_find(bank->users, user_name);

    if (strcmp(comm_type, "exist") == 0)
    {
        if (user == NULL)
        {
            res = "no";
        }
        else
        {
            res = "yes";
        }
    }
    else if (strcmp(comm_type, "authorize") == 0)
    {
        char *pin_str = strtok(NULL, "?");
        strncpy(pin, pin_str, (sizeof pin));
        if (atoi(user->pin) == atoi(pin))
        {
            res = "authorized";
        }
        else
        {
            res = "unauthorized";
        }
    }
    else if (strcmp(comm_type, "withdraw") == 0)
    {
        char *amt_str = strtok(NULL, "?");
        int amt = atoi(amt_str);

        if (user->balance < amt)
        {
            res = "unsuccessful";
        }
        else
        {
            user->balance = user->balance - amt;
            res = "successful";
        }
    }
    else if (strcmp(comm_type, "balance") == 0)
    {
        int bal = user->balance;
        sprintf(res2, "%d", bal);
        bank_send(bank, res2, strlen(res2));
        return;
    }
    bank_send(bank, res, strlen(res));
    return;

    /*
     * The following is a toy example that simply receives a
     * string from the ATM, prepends "Bank got: " and echoes
     * it back to the ATM before printing it to stdout.
     */

    /*
    char sendline[1000];
    command[len]=0;
    sprintf(sendline, "Bank got: %s", command);
    bank_send(bank, sendline, strlen(sendline));
    printf("Received the following:\n");
    fputs(command, stdout);
    */
}
