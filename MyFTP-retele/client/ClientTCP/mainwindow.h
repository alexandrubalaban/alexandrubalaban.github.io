#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QDebug>
#include <QFileInfo>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_pushButton_clicked();

    void on_pushButton_2_clicked();

    void on_Conect_clicked();

    void on_Browse_clicked();

    void on_Register_clicked();

    void on_upload_clicked();

    void on_logare_clicked();

    void on_Inregistrare_clicked();

    void on_Download_clicked();

private:
    Ui::MainWindow *ui;

};

#endif // MAINWINDOW_H
