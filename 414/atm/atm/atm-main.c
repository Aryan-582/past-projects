/* 
 * The main program for the ATM.
 *
 * You are free to change this as necessary.
 */

#include "atm.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static const char prompt[] = "ATM: ";

int main(int argc, char**argv)
{
    char user_input[1000];
    char *sessionName;
    sessionName = "";
    ATM *atm = atm_create();

    char key[1000] = "";
    FILE *fp = fopen(argv[0], "r");
    fgets(key, 32, fp);
    fclose(fp);

    printf("%s", prompt);
    fflush(stdout);
    int loggedIn = 0;
    while (1) 
    {
        fgets(user_input, 10000,stdin);
        if (strcmp(user_input, "\n") != 0) {
            Tuple tuple = atm_process_command(atm, user_input, loggedIn, sessionName, key);
            loggedIn = tuple.loggedIn;
            sessionName = tuple.name;
        } 

        setbuf(stdin, NULL);
        if (loggedIn == 1) {
            printf("ATM (%s): ", sessionName);
        } else {
            printf("%s", prompt); 
        }
        
        fflush(stdout);
        
    }
    return EXIT_SUCCESS;
}