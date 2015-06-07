#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QFileDialog>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <errno.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <netdb.h>
#include <string.h>
#include <arpa/inet.h>
#include <iostream>

QString file1Name;
using namespace std;
/* codul de eroare returnat de anumite apeluri */
extern int errno;
int sock;
/* portul de conectare la server*/
int port;

int connectare(char* ip, char* port1)
{
    int sd;			// descriptorul de socket
    struct sockaddr_in server;	// structura folosita pentru conectare
    // mesajul trimis

    /* stabilim portul */
    port = atoi (port1);

    /* cream socketul */
    if ((sd = socket (AF_INET, SOCK_STREAM, 0)) == -1)
    {
        perror ("Eroare la socket().\n");
        return errno;
    }

    /* umplem structura folosita pentru realizarea conexiunii cu serverul */
    /* familia socket-ului */
    server.sin_family = AF_INET;
    /* adresa IP a serverului */
    server.sin_addr.s_addr = inet_addr(ip);
    /* portul de conectare */
    server.sin_port = htons (port);

    /* ne conectam la server */
    if (connect (sd, (struct sockaddr *) &server,sizeof (struct sockaddr)) == -1)
    {
        perror ("[client]Eroare la connect().\n");
        return errno;
    }
    return sd;
}

int log(char* log1, char* pass1, int sd)
{
    if (write (sd,log1,100) <= 0)
    {
        perror ("[client]Eroare la write() spre server.\n");
        return errno;
    }
    if (write (sd,pass1,100) <= 0)
    {
        perror ("[client]Eroare la write() spre server.\n");
        return errno;
    }
    int rez;
    read (sock, &rez ,sizeof(int));
    //this->ui->consola->setText(rez);
    if (rez==0)
        return 0;
    else if (rez==1)
        return 1;
}
int reg(char* log1, char* pass1, int sd)
{
    if (write (sd,log1,100) <= 0)
    {
        perror ("[client]Eroare la write() spre server.\n");
        return errno;
    }
    if (write (sd,pass1,100) <= 0)
    {
        perror ("[client]Eroare la write() spre server.\n");
        return errno;
    }
    int rez;
    read (sd, &rez ,sizeof(int));
    if (rez==0)
        return 0;
    else if (rez==1)
        return 1;
    return 0;
}


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_Conect_clicked()
{
    char* ip="127.0.0.1";
    char* port="2908";
    sock=connectare(ip,port);
    this->ui->logare->setEnabled(true);
    this->ui->Register->setEnabled(true);
}

void MainWindow::on_Browse_clicked()
{
    file1Name = QFileDialog::getOpenFileName(this,
                                             tr("Open  File 1"), "/home", tr("Files (*.*)"));
    this->ui->lineEdit->setText(file1Name);
}

void MainWindow::on_Register_clicked()
{
    this->ui->Inregistrare->setEnabled(true);

}
void MainWindow::on_Inregistrare_clicked()
{
    int menue=2;
    write (sock,&menue,sizeof(int));
    QString Rlogin = this->ui->R_log->text();
    QString Rpassw = this->ui->R_pass->text();
    QByteArray b_Rlogin = Rlogin.toLocal8Bit();
    char *c_Rlogin = b_Rlogin.data();
    QByteArray b_Rpassw = Rpassw.toLocal8Bit();
    char *c_Rpassw = b_Rpassw.data();
    int raspuns=reg(c_Rlogin, c_Rpassw, sock);
    if (raspuns==1)
    {
        this->ui->consola->setText("inregistrare cu SUCCES!!!");
    }
    else
        this->ui->consola->setText("inregistrare nereusita!!!");
    menue=0;
}

/*dup2(tdL.cl,1);
system("ls");
if (write(tdL.cl,buffer,200)<=0)
{perror("eroare de scriere");
}*/


void MainWindow::on_logare_clicked()
{
    int menue=1;
    char links[50001];
    write (sock,&menue,sizeof(int));
    QString login = this->ui->login->text();
    QString passw = this->ui->password->text();
    QByteArray b_login = login.toLocal8Bit();
    char *c_login = b_login.data();

    QByteArray b_passw = passw.toLocal8Bit();
    char *c_passw = b_passw.data();
    int raspuns=log(c_login, c_passw, sock);
    if (raspuns==1){
        this->ui->upload->setEnabled(true);
        this->ui->Download->setEnabled(true);
        this->ui->consola->setText("Logare cu SUCCES!!!");
        read (sock, links ,50000);
        this->ui->adrese->setText(links);
        //char xyz[100]="";
        //read(sock, xyz,99);
        //this->ui->consola->setText(xyz);

    }
    else
    {
        this->ui->consola->setText("Login sau parola incorect!");
    }

    menue=0;
}




void MainWindow::on_upload_clicked()
{
    int menue=3;
    int LENGTH=512;
    write (sock,&menue,sizeof(int));
    QStringList pieces = file1Name.split( "/" );
    QString neededWord = pieces.value( pieces.length() - 1 );
    QByteArray b_neededWord = neededWord.toLocal8Bit();
    char *c_neededWord = b_neededWord.data();
    this->ui->consola->setText(c_neededWord);
    write (sock,c_neededWord,100);
    QByteArray b_fileName = file1Name.toLocal8Bit();
    char *fs_name = b_fileName.data();
    char sdbuf[LENGTH]; // Send buffer
    printf("[Server] Sending %s to the Client...", fs_name);
    FILE *fs = fopen(fs_name, "r");
    if(fs == NULL)
    {
        fprintf(stderr, "ERROR: File %s not found on server. (errno = %d)\n", fs_name, errno);
        exit(1);
    }

    bzero(sdbuf, LENGTH);
    int fs_block_sz;
    while((fs_block_sz = fread(sdbuf, sizeof(char), LENGTH, fs))>0)
    {
        if(send(sock, sdbuf, fs_block_sz, 0) < 0)
        {
            fprintf(stderr, "ERROR: Failed to send file %s. (errno = %d)\n", fs_name, errno);
            exit(1);
        }
        bzero(sdbuf, LENGTH);
    }
    printf("Ok sent to client!\n");

}

void MainWindow::on_Download_clicked()
{
    int menue=4;
    int LENGTH=512;
    char revbuf[LENGTH];
    write (sock,&menue,sizeof(int));
    QString fis_doritd = this->ui->download_path->text();
    this->ui->consola->setText(fis_doritd);
    QStringList pieces = fis_doritd.split( "/" );
    QString neededWord = pieces.value( pieces.length() - 1 );
    QByteArray b_neededWord = neededWord.toLocal8Bit();
    char *c_neededWord = b_neededWord.data();
    QByteArray b_fis_dorit = fis_doritd.toLocal8Bit();
    char *c_fis_dorit = b_fis_dorit.data();
    write (sock,c_fis_dorit,1000);
    write (sock,c_neededWord,100);
    printf("[Client] Receiveing file from Server and saving it as final.txt...");
    char fr_name[101]="/home/sunny/Desktop/QT/incarca/";
    strcat(fr_name,c_neededWord);
    this->ui->consola->setText(fr_name);
    FILE *fr = fopen(fr_name, "a");
    if(fr == NULL)
        printf("File %s Cannot be opened.\n", fr_name);
    else
    {
        bzero(revbuf, LENGTH);
        int fr_block_sz = 0;
        while((fr_block_sz = recv(sock, revbuf, LENGTH, 0)) > 0)
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

}
