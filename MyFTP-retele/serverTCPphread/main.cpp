/* servTCPConcTh2.c - Exemplu de server TCP concurent care deserveste clientii
   prin crearea unui thread pentru fiecare client.
   Asteapta un numar de la clienti si intoarce clientilor numarul incrementat.
    Intoarce corect identificatorul din program al thread-ului.


   Autor: Lenuta Alboaie  <adria@infoiasi.ro> (c)2009
*/

#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <string>
#include <stdlib.h>
#include <signal.h>
#include <pthread.h>
#include <arpa/inet.h>
#include <sqlite3.h>
#include <iostream>
#include <QDebug>
#include <QDir>
#include <QFileInfo>
#include <QString>

using namespace std;
static int select(void *data, int argc, char **argv, char **azColName){
    if (argv[0]==NULL)  return 0;
    else return 1;
}
static int insert(void *NotUsed, int argc, char **argv, char **azColName){
   return 0;
}
static int callback(void *NotUsed, int argc, char **argv, char **azColName){
    int i;
    for(i=0; i<argc; i++){
        printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
    }
    printf("\n");
    return 0;
}
QString printDir2(QString path, int level)
{
    int check =1;
    QDir folder(path);

    QString space;
    QString buffer;
    qDebug() <<buffer;
    for(int i=0; 1<level; i++)
        space +="    ";
    qDebug() <<buffer;
    foreach(QFileInfo temp , folder.entryInfoList())
    {
        if(check > 2)
        {
            qDebug() <<buffer;
            buffer += space + temp.absoluteFilePath() + "\n";
            if(temp.isDir())
                buffer += printDir2(temp.absoluteFilePath(), ++level);
        }
        check++;
        qDebug() <<buffer;
    }
    return buffer;
}
/*void printDir(const QString, &filePath)
{

}*/




/* portul folosit */
#define PORT 2908

/* codul de eroare returnat de anumite apeluri */
extern int errno;

typedef struct thData{
    int idThread; //id-ul thread-ului tinut in evidenta de acest program
    int cl; //descriptorul intors de accept
}thData;

static void *treat(void *); /* functia executata de fiecare thread ce realizeaza comunicarea cu clientii */
void raspunde(void *);

int main ()
{
    struct sockaddr_in server;	// structura folosita de server
    struct sockaddr_in from;
    int nr;		//mesajul primit de trimis la client
    int sd;		//descriptorul de socket
    int pid;
    pthread_t th[100];    //Identificatorii thread-urilor care se vor crea
    int i=0;


    /* crearea unui socket */
    if ((sd = socket (AF_INET, SOCK_STREAM, 0)) == -1)
    {
        perror ("[server]Eroare la socket().\n");
        return errno;
    }
    /* utilizarea optiunii SO_REUSEADDR */
    int on=1;
    setsockopt(sd,SOL_SOCKET,SO_REUSEADDR,&on,sizeof(on));

    /* pregatirea structurilor de date */
    bzero (&server, sizeof (server));
    bzero (&from, sizeof (from));

    /* umplem structura folosita de server */
    /* stabilirea familiei de socket-uri */
    server.sin_family = AF_INET;
    /* acceptam orice adresa */
    server.sin_addr.s_addr = htonl (INADDR_ANY);
    /* utilizam un port utilizator */
    server.sin_port = htons (PORT);

    /* atasam socketul */
    if (bind (sd, (struct sockaddr *) &server, sizeof (struct sockaddr)) == -1)
    {
        perror ("[server]Eroare la bind().\n");
        return errno;
    }

    /* punem serverul sa asculte daca vin clienti sa se conecteze */
    if (listen (sd, 2) == -1)
    {
        perror ("[server]Eroare la listen().\n");
        return errno;
    }
    /* servim in mod concurent clientii...folosind thread-uri */

    while (1)
    {
        int client;
        thData * td; //parametru functia executata de thread
        socklen_t length = sizeof (from);

        printf ("[server]Asteptam la portul %d...\n",PORT);
        fflush (stdout);

        //client= malloc(sizeof(int));
        /* acceptam un client (stare blocanta pina la realizarea conexiunii) */
        if ( (client = accept (sd, (struct sockaddr *) &from, &length)) < 0)
        {
            perror ("[server]Eroare la accept().\n");
            continue;
        }

        /* s-a realizat conexiunea, se astepta mesajul */

        int idThread; //id-ul threadului
        int cl; //descriptorul intors de accept

        td=(struct thData*)malloc(sizeof(struct thData));
        td->idThread=i++;
        td->cl=client;

        pthread_create(&th[i], NULL, &treat, td);

    }//while
};
static void *treat(void * arg)
{
    struct thData tdL;
    tdL= *((struct thData*)arg);
    printf ("[thread]- %d - Asteptam mesajul...\n", tdL.idThread);
    fflush (stdout);
    pthread_detach(pthread_self());


    sqlite3 *db;
    char *zErrMsg = 0;
    int rc;

    const char* data = "Callback function called";

    rc = sqlite3_open("accounts.sqlite", &db);

    if( rc ){
        fprintf(stderr, "Can't open database: %s\n", sqlite3_errmsg(db));
        exit(0);
    }else{
        fprintf(stderr, "Opened database successfully\n");
    }
    //sqlite3_close(db);


    while (1) {

        int nr, i=0;
        int menue;
        char login[101];
        char pass[101];
        if (read (tdL.cl, &menue,sizeof(int)) <= 0)
        {
            printf("[Thread %d]\n",tdL.idThread);
            perror (" el vrea sa CITEASCA Eroare la read() de la client.\n");
            break;
        }
        if (menue==1)
        {
            if (read (tdL.cl, login,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() de la client.\n");

            }
            if (read (tdL.cl, pass,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() de la client.\n");

            }

            printf ("[Thread %d]Mesajul a fost receptionat...%s\n",tdL.idThread, login);

            printf("[Thread %d]Trimitem mesajul inapoi...%s\n",tdL.idThread, pass);
            string sql;



            sql.append("SELECT login, password FROM acc WHERE login='").append(login).append("' AND password='").append(pass).append("'");
            //cout << sql<<"bla bla bla:";
            rc = sqlite3_exec(db, sql.c_str(), select, NULL, &zErrMsg);
            if( rc != SQLITE_OK && rc!=4 ){
                fprintf(stderr, "SQL error: %s\n", zErrMsg);
                sqlite3_free(zErrMsg);
            }else{
                fprintf(stdout, "Operation done successfully\n");
                cout<<"rc:  " <<rc<<"rc=";
                if (rc==0)
                {
                    int x=0;
                    cout<< "logare nereusita";
                    if (write (tdL.cl, &x, 100) <= 0)
                    {
                        printf("[Thread %d] ",tdL.idThread);
                        perror ("[Thread]Eroare la write() catre client.\n");
                    }
                    else
                        printf ("[Thread %d]Mesajul a fost trasmis cu succes.\n",tdL.idThread);


                }
                else
                {
                    cout<<"logare reusita";
                    if (rc!=0)
                    {
                        cout<<"\n mega super logare!";
                        int x=1;
                        if (write (tdL.cl, &x, sizeof(int)) <= 0)
                        {
                            printf("[Thread %d] ",tdL.idThread);
                            perror ("[Thread]Eroare la write() catre client.\n");
                        }
                        else
                        {
                            printf ("[Thread %d]Mesajul a fost trasmis cu succes.\n",tdL.idThread);
                        }

                        QString str;
                        //QString drum="/home/sunny/Desktop/QT/descarca";
                        QDir mDir("/home/sunny/Desktop/QT/descarca");
                        foreach (QFileInfo mitm, mDir.entryInfoList())
                        {
                            if(mitm.isDir())
                                str.append("Dir: ").append(mitm.absoluteFilePath().append("\n"));
                                //qDebug() << "Dir: "<< mitm.absoluteFilePath();
                            if(mitm.isFile())
                                str.append("File: ").append(mitm.absoluteFilePath().append("\n"));
                                //qDebug() << "File: "<< mitm.absoluteFilePath();
                        }

                        //str=printDir2(drum,0);
                        //qDebug()<<str<<"acesta este drumul";
                        QByteArray b_str = str.toLocal8Bit();
                        char *c_str = b_str.data();
                        if (write (tdL.cl, c_str, 50000) <= 0)
                        {
                            printf("[Thread %d] ",tdL.idThread);
                            perror ("[Thread]Eroare la write() catre client.\n");
                        }
//                        sql="";
//                        sql.append("SELECT statut FROM acc WHERE login='").append(login).append("' AND statut=0");
//                        rc = sqlite3_exec(db, sql.c_str(), select, NULL, &zErrMsg);
//                        if( rc != SQLITE_OK && rc!=4 ){
//                            fprintf(stderr, "SQL error: %s\n", zErrMsg);
//                            sqlite3_free(zErrMsg);
//                        }else{
//                            fprintf(stdout, "Operation done successfully\n");
//                            fflush(stdout);

//                        }
//                        if(rc==4){
//                            qDebug() <<"rc:  " <<rc<<"rc=";
//                            char xyz[2]="q";
//                            if (write (tdL.cl, xyz, 10) <= 0)
//                            {
//                                qDebug() <<"eroare";
//                            }else{
//                                qDebug() <<"ok";
//                            }
   //                     }

                    }
                }

            }
            //sqlite3_close(db);
            menue=0;

        }
        else if (menue==2)
        {
            if (read (tdL.cl, login,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() de la client.\n");

            }
            if (read (tdL.cl, pass,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() de la client.\n");

            }

            printf ("[Thread %d]Mesajul a fost receptionat...%s\n",tdL.idThread, login);

            printf("[Thread %d]Trimitem mesajul inapoi...%s\n",tdL.idThread, pass);
            string sql1;
            int ax;
            sql1.append("INSERT into acc(login,password,statut) values('").append(login).append("','").append(pass).append("',").append("1").append(");");
            rc = sqlite3_exec(db, sql1.c_str(), insert, 0, &zErrMsg);
            if( rc != SQLITE_OK){
                fprintf(stderr, "SQL error: %s\n", zErrMsg);
                sqlite3_free(zErrMsg);
                ax=0;
                if (write (tdL.cl, &ax, sizeof(int)) <= 0)
                   {
                    printf("[Thread %d] ",tdL.idThread);
                    perror ("[Thread]Eroare la write() catre client.\n");
                   }
            }else{
                fprintf(stdout, "Records created successfully\n");
                ax=1;
                if (write (tdL.cl, &ax, sizeof(int)) <= 0)
                   {
                    printf("[Thread %d] ",tdL.idThread);
                    perror ("[Thread]Eroare la write() catre client.\n");
                   }
            }
            //sqlite3_close(db);
            menue=0;
        }
        else if (menue==3)
        {
            int LENGTH=512;
            char revbuf[LENGTH];
            printf("[Client] Receiveing file from Server and saving it as final.txt...");
            char fr_name1[101];
            if (read (tdL.cl, fr_name1,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() nume de la client.\n");

            }
            char fr_name[101]="/home/sunny/Desktop/QT/descarca/";
            strcat(fr_name,fr_name1);

            FILE *fr = fopen(fr_name, "a");
            if(fr == NULL)
                printf("File %s Cannot be opened.\n", fr_name);
            else
            {
                bzero(revbuf, LENGTH);
                int fr_block_sz = 0;
                while((fr_block_sz = recv(tdL.cl, revbuf, LENGTH, 0)) > 0)
                {
                    int write_sz = fwrite(revbuf, sizeof(char), fr_block_sz, fr);
                    if(write_sz < fr_block_sz)
                    {
                        printf("File write failed.\n");
                    }
                    bzero(revbuf, LENGTH);
                    if (fr_block_sz == 0 || fr_block_sz != 512)
                    {
                        break;
                    }
                }
                if(fr_block_sz < 0)
                {
                    if (errno == EAGAIN)
                    {
                        printf("recv() timed out.\n");
                    }
                    else
                    {
                        fprintf(stderr, "recv() failed due to errno = %d\n", errno);
                    }
                }
                printf("Ok received from server!\n");
                fclose(fr);
            }
            menue=0;
        }
        else if (menue==4)
        {
            //cout<<"/n bla bla bla......";
            int LENGTH=512;
            char fis_dorit[1001];
            if (read (tdL.cl, fis_dorit,1000) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() nume de la client.\n");

            }
            qDebug() <<"/n fisierul dorit este:"<<fis_dorit;
            char fr_name2[101];
            if (read (tdL.cl, fr_name2,100) <= 0)
            {
                printf("[Thread %d]\n",tdL.idThread);
                perror ("Eroare la read() nume de la client.\n");

            }
            qDebug() <<"\n denumirea fisierului dorit este:"<<fr_name2;
            char sdbuf[LENGTH]; // Send buffer
            printf("[Server] Sending %s to the Client...", fis_dorit);
            FILE *fs = fopen(fis_dorit, "r");
            if(fs == NULL)
            {
                fprintf(stderr, "ERROR: File %s not found on server. (errno = %d)\n", fis_dorit, errno);
                exit(1);
            }

            bzero(sdbuf, LENGTH);
            int fs_block_sz;
            while((fs_block_sz = fread(sdbuf, sizeof(char), LENGTH, fs))>0)
            {
                if(send(tdL.cl, sdbuf, fs_block_sz, 0) < 0)
                {
                    fprintf(stderr, "ERROR: Failed to send file %s. (errno = %d)\n", fis_dorit, errno);
                    exit(1);
                }
                bzero(sdbuf, LENGTH);
            }
            printf("Ok sent to client!\n");

        }





        /* returnam mesajul clientului */
        /*char mesaj[1001]="Sugi Pula MAX";
     if (write (tdL.cl, mesaj, 1000) <= 0)
        {
         printf("[Thread %d] ",tdL.idThread);
         perror ("[Thread]Eroare la write() catre client.\n");
        }
    else
        printf ("[Thread %d]Mesajul a fost trasmis cu succes.\n",tdL.idThread);*/

    }
    /* am terminat cu acest client, inchidem conexiunea */
    close ((intptr_t) arg);
    return(NULL);

};
