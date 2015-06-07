/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.2.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QFrame>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QPushButton *Browse;
    QPushButton *Conect;
    QLineEdit *lineEdit;
    QLineEdit *login;
    QLineEdit *password;
    QPushButton *upload;
    QPushButton *pushButton;
    QLineEdit *download_path;
    QPushButton *Download;
    QTextEdit *adrese;
    QPushButton *logare;
    QFrame *frame;
    QPushButton *Register;
    QLineEdit *R_log;
    QLineEdit *R_pass;
    QPushButton *Inregistrare;
    QTextEdit *consola;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(846, 473);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        Browse = new QPushButton(centralWidget);
        Browse->setObjectName(QStringLiteral("Browse"));
        Browse->setEnabled(true);
        Browse->setGeometry(QRect(0, 50, 99, 27));
        Conect = new QPushButton(centralWidget);
        Conect->setObjectName(QStringLiteral("Conect"));
        Conect->setGeometry(QRect(0, 10, 99, 27));
        lineEdit = new QLineEdit(centralWidget);
        lineEdit->setObjectName(QStringLiteral("lineEdit"));
        lineEdit->setGeometry(QRect(100, 50, 301, 31));
        lineEdit->setReadOnly(true);
        login = new QLineEdit(centralWidget);
        login->setObjectName(QStringLiteral("login"));
        login->setGeometry(QRect(700, 0, 113, 27));
        password = new QLineEdit(centralWidget);
        password->setObjectName(QStringLiteral("password"));
        password->setGeometry(QRect(700, 30, 113, 27));
        password->setEchoMode(QLineEdit::Password);
        upload = new QPushButton(centralWidget);
        upload->setObjectName(QStringLiteral("upload"));
        upload->setEnabled(true);
        upload->setGeometry(QRect(400, 50, 99, 27));
        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QStringLiteral("pushButton"));
        pushButton->setGeometry(QRect(0, 310, 99, 27));
        download_path = new QLineEdit(centralWidget);
        download_path->setObjectName(QStringLiteral("download_path"));
        download_path->setGeometry(QRect(100, 310, 301, 31));
        Download = new QPushButton(centralWidget);
        Download->setObjectName(QStringLiteral("Download"));
        Download->setEnabled(false);
        Download->setGeometry(QRect(400, 310, 99, 27));
        adrese = new QTextEdit(centralWidget);
        adrese->setObjectName(QStringLiteral("adrese"));
        adrese->setGeometry(QRect(103, 87, 441, 221));
        logare = new QPushButton(centralWidget);
        logare->setObjectName(QStringLiteral("logare"));
        logare->setEnabled(false);
        logare->setGeometry(QRect(700, 60, 111, 31));
        frame = new QFrame(centralWidget);
        frame->setObjectName(QStringLiteral("frame"));
        frame->setGeometry(QRect(670, 130, 151, 201));
        frame->setFrameShape(QFrame::StyledPanel);
        frame->setFrameShadow(QFrame::Raised);
        Register = new QPushButton(frame);
        Register->setObjectName(QStringLiteral("Register"));
        Register->setEnabled(false);
        Register->setGeometry(QRect(30, 10, 99, 27));
        R_log = new QLineEdit(frame);
        R_log->setObjectName(QStringLiteral("R_log"));
        R_log->setGeometry(QRect(20, 50, 113, 27));
        R_pass = new QLineEdit(frame);
        R_pass->setObjectName(QStringLiteral("R_pass"));
        R_pass->setGeometry(QRect(20, 100, 113, 27));
        Inregistrare = new QPushButton(frame);
        Inregistrare->setObjectName(QStringLiteral("Inregistrare"));
        Inregistrare->setEnabled(false);
        Inregistrare->setGeometry(QRect(30, 140, 99, 27));
        consola = new QTextEdit(centralWidget);
        consola->setObjectName(QStringLiteral("consola"));
        consola->setGeometry(QRect(10, 350, 821, 51));
        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 846, 25));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0));
        Browse->setText(QApplication::translate("MainWindow", "Browse", 0));
        Conect->setText(QApplication::translate("MainWindow", "Conectare", 0));
#ifndef QT_NO_TOOLTIP
        login->setToolTip(QApplication::translate("MainWindow", "login", 0));
#endif // QT_NO_TOOLTIP
        login->setPlaceholderText(QApplication::translate("MainWindow", "login", 0));
#ifndef QT_NO_TOOLTIP
        password->setToolTip(QApplication::translate("MainWindow", "password", 0));
#endif // QT_NO_TOOLTIP
        password->setPlaceholderText(QApplication::translate("MainWindow", "password", 0));
        upload->setText(QApplication::translate("MainWindow", "upload", 0));
        pushButton->setText(QApplication::translate("MainWindow", "Browse", 0));
        Download->setText(QApplication::translate("MainWindow", "Download", 0));
        logare->setText(QApplication::translate("MainWindow", "logare", 0));
        Register->setText(QApplication::translate("MainWindow", "Register", 0));
        R_log->setPlaceholderText(QApplication::translate("MainWindow", "login", 0));
        R_pass->setPlaceholderText(QApplication::translate("MainWindow", "password", 0));
        Inregistrare->setText(QApplication::translate("MainWindow", "Inregistrare", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
