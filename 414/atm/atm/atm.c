#include "atm.h"
#include "ports.h"
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>

ATM *atm_create()
{
    ATM *atm = (ATM *)malloc(sizeof(ATM));
    if (atm == NULL)
    {
        perror("Could not allocate ATM");
        exit(1);
    }

    // Set up the network state
    atm->sockfd = socket(AF_INET, SOCK_DGRAM, 0);

    bzero(&atm->rtr_addr, sizeof(atm->rtr_addr));
    atm->rtr_addr.sin_family = AF_INET;
    atm->rtr_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    atm->rtr_addr.sin_port = htons(ROUTER_PORT);

    bzero(&atm->atm_addr, sizeof(atm->atm_addr));
    atm->atm_addr.sin_family = AF_INET;
    atm->atm_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    atm->atm_addr.sin_port = htons(ATM_PORT);
    bind(atm->sockfd, (struct sockaddr *)&atm->atm_addr, sizeof(atm->atm_addr));

    // Set up the protocol state
    // TODO set up more, as needed

    return atm;
}

void atm_free(ATM *atm)
{
    if (atm != NULL)
    {
        close(atm->sockfd);
        free(atm);
    }
}

ssize_t atm_send(ATM *atm, char *data, size_t data_len)
{
    // Returns the number of bytes sent; negative on error
    return sendto(atm->sockfd, data, data_len, 0,
                  (struct sockaddr *)&atm->rtr_addr, sizeof(atm->rtr_addr));
}

ssize_t atm_recv(ATM *atm, char *data, size_t max_data_len)
{
    // Returns the number of bytes received; negative on error
    return recvfrom(atm->sockfd, data, max_data_len, 0, NULL, NULL);
}

int has_digit(char *name)
{
    while (name)
    {
        if (isdigit(*name++) != 0)
            return 1;
    }

    return 0;
}
void XORCipher(char *data, char *key, int dataLen, int keyLen)
{
    for (int i = 0; i < dataLen; ++i)
    {
        data[i] = data[i] ^ key[i % keyLen];
    }
}

Tuple atm_process_command(ATM *atm, char *command, int loggedIn, char *sessionName, char key[1000])
{
    for (int i = 0; i < strlen(command); i++)
    {
        if (command[i] == ' ' || command[i] == '\n' || command[i] == '\0')
        {
            command[i] = '?';
        }
    }
    // DO NOT MESS WITH FINAL NAME
    char finalName[250];
    char recvline[10000] = "";
    char recvline2[10000] = "";
    char pin[5];
    char sendMessage[10000] = "";
    char sendMessage2[10000] = "";
    char *argumentOne = strtok(command, "?");
    if (strcmp(command, "") == 0 || strcmp(command, "\n") == 0)
    {
        printf("Invalid command\n");
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        setbuf(stdin, NULL);
        return tuple;
    }

    if (strcmp(argumentOne, "begin-session") == 0)
    {
        char *name = strtok(NULL, "?");
        if (name == NULL)
        {
            printf("Usage: begin-session <user-name>\n");
            Tuple tuple;
            tuple.loggedIn = loggedIn;
            tuple.name = finalName;
            setbuf(stdin, NULL);
            return tuple;
        }
        char *rest = strtok(NULL, "");
        if (rest != NULL)
        {
            printf("Usage: begin-session <user-name>\n");
            Tuple tuple;
            tuple.loggedIn = loggedIn;
            tuple.name = finalName;
            setbuf(stdin, NULL);
            return tuple;
        }
        if (loggedIn == 1) {
            printf("A user is already logged in\n");
        }
        else if (strlen(name) > 250)
        {
            printf("Usage: begin-session <user-name>\n");
        }
        else {
            strncpy(finalName, name, (sizeof finalName));
            strcat(finalName, ".card");
            strcpy(sendMessage2, "exist?");
            strcat(sendMessage2, name);
            strcat(sendMessage2, "?");
            XORCipher(sendMessage2, key, strlen(sendMessage2), strlen(key));
            atm_send(atm, sendMessage2, strlen(sendMessage2));
            atm_recv(atm, recvline2, 10000);
            if (strcmp(recvline2, "no") == 0)
            {
                printf("No such user\n");
            }
            else
            {
                FILE *file;
                file = fopen(finalName, "r");
                if (file == NULL)
                {
                    printf("Unable to access %s's card\n", name);
                }
                else
                {
                    printf("PIN? ");
                    fflush(stdout);
                    fgets(pin, 5, stdin);
                    setbuf(stdin, NULL); //-----
                    strncpy(finalName, name, (sizeof finalName));
                    strcpy(sendMessage, "authorize?");
                    strcat(sendMessage, finalName);
                    strcat(sendMessage, "?");
                    strcat(sendMessage, pin);
                    strcat(sendMessage, "?");
                    XORCipher(sendMessage, key, strlen(sendMessage), strlen(key));
                    atm_send(atm, sendMessage, strlen(sendMessage));
                    atm_recv(atm, recvline, 10000);
                    if (strcmp(recvline, "authorized") == 0)
                    {
                        printf("Authorized\n");
                        loggedIn = 1;
                    }
                    else
                    {
                        printf("Not authorized\n");
                    }
                    fclose(file);
                }
            }
        }
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        setbuf(stdin, NULL);
        return tuple;
    }
    else if (strcmp(argumentOne, "withdraw") == 0)
    {
        if (loggedIn == 0)
        {
            printf("No user logged in\n");
        }
        else
        {
            char *amt = strtok(NULL, "?");
            char *rest = strtok(NULL, "");
            if (amt == NULL || rest != NULL)
            {
                printf("Usage: withdraw <amt>\n");
                Tuple tuple;
                tuple.loggedIn = loggedIn;
                tuple.name = finalName;
                setbuf(stdin, NULL);
                return tuple;
            }
            if (atoi(amt) > 2147483647 || atoi(amt) <= 0)
            {
                printf("Usage: withdraw <amt>\n");
            }
            else
            {
                strcpy(sendMessage, "withdraw?");
                strcat(sendMessage, finalName);
                strcat(sendMessage, "?");
                strcat(sendMessage, amt);
                strcat(sendMessage, "?");
                XORCipher(sendMessage, key, strlen(sendMessage), strlen(key));
                atm_send(atm, sendMessage, strlen(sendMessage));
                atm_recv(atm, recvline, 10000);
                if (strcmp(recvline, "unsuccessful") == 0)
                {
                    printf("Insufficient funds\n");
                }
                else
                {
                    printf("$%d dispensed\n", atoi(amt));
                }
            }
        }
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        setbuf(stdin, NULL);
        return tuple;
    }
    else if (strcmp(argumentOne, "balance") == 0)
    {
        if (loggedIn == 0)
        {
            printf("No user logged in\n");
        }
        else
        {
            char *rest = strtok(NULL, "");
            if (rest != NULL)
            {
                printf("Usage: balance\n");
                Tuple tuple;
                tuple.loggedIn = loggedIn;
                tuple.name = finalName;
                setbuf(stdin, NULL);
                return tuple;
            }
            strcpy(sendMessage, "balance?");
            strcat(sendMessage, sessionName);
            strcat(sendMessage, "?");
            XORCipher(sendMessage, key, strlen(sendMessage), strlen(key));
            atm_send(atm, sendMessage, strlen(sendMessage));
            atm_recv(atm, recvline, 10000);
            printf("$%s\n", recvline);
        }
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        return tuple;
    }
    else if (strcmp(argumentOne, "end-session") == 0)
    {
        if (loggedIn == 0)
        {
            printf("No user logged in\n");
        }
        else
        {
            char *rest = strtok(NULL, "");
            if (rest != NULL)
            {
                printf("Usage: end-session\n");
                Tuple tuple;
                tuple.loggedIn = loggedIn;
                tuple.name = finalName;
                setbuf(stdin, NULL);
                return tuple;
            }
            printf("User logged out\n");
            loggedIn = 0;
        }
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        setbuf(stdin, NULL);
        return tuple;
    }
    else
    {
        printf("Invalid command\n");
        Tuple tuple;
        tuple.loggedIn = loggedIn;
        tuple.name = finalName;
        setbuf(stdin, NULL);
        return tuple;
    }
}
// TODO: Implement the ATM's side of the ATM-bank protocol

/*
 * The following is a toy example that simply sends the
 * user's command to the bank, receives a message from the
 * bank, and then prints it to stdout.
 */

/*
char recvline[10000];
int n;

atm_send(atm, command, strlen(command));
n = atm_recv(atm,recvline,10000);
recvline[n]=0;
fputs(recvline,stdout);
*/