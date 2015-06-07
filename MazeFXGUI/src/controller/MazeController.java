package controller;

import com.sun.jmx.defaults.JmxProperties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.MazeGenerator;
import model.MazeModel;
import model.MazeModelClass;
import view.MazeView;
import view.MazeViewClass;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;



import javax.imageio.ImageIO;

public class MazeController implements Serializable{
    private MazeModel theModel = new MazeModelClass();
    private MazeView theView = new MazeViewClass();


    private BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
    private Color freeColor = Color.WHITE;
    private Color wallColor = Color.BLACK;
    private Color startColor= Color.RED;
    private Color finishColor = Color.YELLOW;
    private static String radioBoxChecked = "";
//Constructor
    public MazeController(MazeView theView, MazeModel theModel) {
        this.theModel = theModel;
        this.theView = theView;
        this.theView.getCreate().setOnAction(new createButtonEvent());
        this.theView.getGenerate().setOnAction(new generateButtonEvent());
        this.theView.getImagePane().setOnKeyPressed(new contorlEvent());
        this.theView.getPanouPrincipal().setOnKeyPressed(new contorlEvent());
        this.theView.getSolver().setOnAction(new solverButtonEvent());
        this.theView.getSaveJPG().setOnAction(new saveImageButtonEvent());
        this.theView.getSaveGame().setOnAction(new saveGameButtonEvent());
        this.theView.getGetSavedGame().setOnAction(new continueButtonEvent());
    }
//Crearea Labirint VIEW
    private void creare() {

       // theView.getImagePane().getChildren().clear();
        for (int i = 0; i < theModel.getMazeModelSize(); i++) {
            for (int j = 0; j < theModel.getMazeModelSize(); j++) {
              if(theModel.isFreeAt(i,j) || theModel.isVisited(i,j)){
                    ImageView imageView = new ImageView();
                    imageView.setImage(theView.getFreeImage());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    theView.getImagePane().add(imageView,j,i);
                }
                if(theModel.isWallAt(i, j)){
                    ImageView imageView = new ImageView();
                    imageView.setImage(theView.getWallImage());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    theView.getImagePane().add(imageView,j,i);
                }
                if(theModel.isStartCel(i,j)){
                    ImageView imageView = new ImageView();
                    imageView.setImage(theView.getStartImage());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    theView.getImagePane().add(imageView,j,i);
                }

                if(theModel.isFinishCel(i, j)){
                    ImageView imageView = new ImageView();
                    imageView.setImage(theView.getFinishImage());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    theView.getImagePane().add(imageView,j,i);
                }

                if(theModel.isVisited(i,j)){
                    ImageView imageView = new ImageView();
                    imageView.setImage(theView.getFinishImage());
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    theView.getImagePane().add(imageView,j,i);
                }

            }
        }
    }
//PORNIRE APLICATIE
    public void show(){
        this.theView.startApp(theModel.getStage());
        addListenerOnExit();
    }
//LISTENER CREARE
    class createButtonEvent implements EventHandler<ActionEvent>{
    public void handle(ActionEvent event) {
        theView.getDrawMazePanel().ss(19,19);
        addListenerToDrawMaze();
        addListenerToDrawButton();
        addListenerToRadioButtonFromDrawMazeStage();
        addListenerToColorPiker();

    }
}
//SAVE GAME LISTENER
    class saveGameButtonEvent implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {

        try {
            saveTheModel(theModel.getMaze());
            double[] boxPropr = new double[]{theView.getMyBox().getTranslateY(), theView.getMyBox().getTranslateX()};
            saveTheBox(boxPropr);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
//CONTINUE LISTENER
    class continueButtonEvent implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {
        try {
           // theView = (MazeViewClass)getTheModelSaved();

            theModel.setMaze((int[][]) getTheModelSaved());


            double[] temp = new double[2];
            temp = (double[])getTheBoxSaved();


            System.out.print(temp[1]);

            theView.getMyBox().setTranslateY(temp[0]);
            theView.getMyBox().setTranslateX(temp[1]);

            theView.getImagePane().getChildren().clear();

            creare();

            theView.getImagePane().getChildren().removeAll(theView.getMyBox());
            theView.getImagePane().getChildren().add(theView.getMyBox());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//GENERATE LISTENER
    class generateButtonEvent implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            generateMaze();
        }
    }
    private void generateMaze(){
        MazeGenerator mazeGenerator = new MazeGenerator(10,10);
        mazeGenerator.writeMazeIntoFile();
        theModel.setMaze(theModel.citesteFisier("mazeGenerat.txt"));
        theView.getImagePane().getChildren().removeAll(theView.getWallImageView(),theView.getFreeImageView());

        creare();

        theView.getImagePane().getChildren().remove(theView.getMyBox());
        theView.getMyBox().setTranslateX(theModel.getStartCellX());
        theView.getMyBox().setTranslateY(theModel.getStartCellY());
        theView.getImagePane().getChildren().add(theView.getMyBox());
        theView.getMyBox().setVisible(true);
    }
//SOLVER
    class solverButtonEvent implements EventHandler<ActionEvent> {
    public void handle(ActionEvent e) {


        if (theModel.getMaze() != null) {



        int tempX = theModel.getStartCellY();
        int tempY = theModel.getStartCellX();
        int tempXFinish = theModel.getFinishCellX();
        int tempYFinish = theModel.getFinishCellY();

        theModel.solveMaze(theModel.getMaze(), theModel.getStartCellX(), theModel.getStartCellY());

        theModel.setCell(tempY, tempX, -1);
        theModel.setCell(tempYFinish, tempXFinish, 2);

        theModel.writeMazeIntoFile(theModel.getMaze());
        theView.getImagePane().getChildren().clear();
            creare();
        theView.getImagePane().getChildren().remove(theView.getMyBox());
        theView.getImagePane().getChildren().add(theView.getMyBox());

        writePolyLine();
        theView.getMyBox().setVisible(true);
    }

    else

    {
        genNotGneratedMaze();
    }











    }
}
//ADD POINT FOR POLYLINE
    private void writePolyLine(){

        Line line = new Line(50,50,30,30);
        theView.getPolyline().getPoints().clear();

        Double[] vector;
        vector = new Double[theModel.getPolyLine().size()];
        for(int i = 0 ; i < theModel.getPolyLine().size();i++) {
            vector[i] = theModel.getPolyLine().get(i);
        }
        theView.getPolyline().getPoints().addAll(vector);

        Pane panel = new Pane(theView.getPolyline());
        theView.getImagePane().getChildren().removeAll(panel);
        theView.getImagePane().getChildren().add(panel);






    }

//SAVE IMAGE BUTTOn
    class saveImageButtonEvent implements EventHandler<ActionEvent>{

    @Override
    public void handle(ActionEvent event) {

            WritableImage snapshot = theView.getImagePane().snapshot(new SnapshotParameters(), null);
           saveImage(snapshot);
    }
}
//CONTROL EVENT
    class contorlEvent implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent event) {
            if(event.getCode().equals(KeyCode.S)) {
                downCommand();
            }
            if(event.getCode().equals(KeyCode.W)) {
                upCommand();
            }
            if(event.getCode().equals(KeyCode.A)) {
                leftCommand();
            }
            if(event.getCode().equals(KeyCode.D)) {
                rightCommand();

            }
        }
    }
    private void downCommand() {
        double posX = theView.getMyBox().getTranslateX();
        double posY = theView.getMyBox().getTranslateY();
        theView.getImagePane().getChildren().remove(theView.getMyBox());

        theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);

        if( posY + 30 < theModel.getMazeModelSize() * 30)
            if( theModel.isWallAt((int) posY / 30 + 1, (int)posX/30  )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
            }
            else
            if(theModel.isFinishCel( (int) posY / 30 + 1, (int)posX/30  )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateY(posY + 30);
                onFinish();
            }
            else{

                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateY(posY + 30);
            }

        theView.getImagePane().getChildren().addAll(theView.getMyBox());

        theView.getMyBox().setVisible(true);
    }
    private void upCommand(){
        double posX = theView.getMyBox().getTranslateX();
        double posY = theView.getMyBox().getTranslateY();

        theView.getImagePane().getChildren().remove(theView.getMyBox());
        if(posY > 0) {//daca nu poate iesi
            //verifica daca unde vreau sa mut este pereaawte
            if( theModel.isWallAt((int) posY / 30 - 1, (int)posX/30  )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
            }
            else
            if(theModel.isFinishCel( (int) posY / 30 - 1, (int)posX/30  )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateY(posY - 30);
                onFinish();
            }
            else{

                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateY(posY - 30);
            }
        }
        theView.getImagePane().getChildren().addAll(theView.getMyBox());
        theView.getMyBox().setVisible(true);
    }
    private void rightCommand(){
        double posX = theView.getMyBox().getTranslateX();
        double posY = theView.getMyBox().getTranslateY();


        theView.getImagePane().getChildren().remove(theView.getMyBox());


        if(posX  + 30 < theModel.getMazeModelSize() * 30)
            if( theModel.isWallAt( (int)posY/30 ,(int) posX / 30 + 1 )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
            }
            else
            if(theModel.isFinishCel( (int)posY/30 ,(int) posX / 30 + 1 )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateX(posX + 30);
                onFinish();
            }

            else{

                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateX(posX + 30);
            }

        theView.getImagePane().getChildren().addAll(theView.getMyBox());

        theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);

        theView.getMyBox().setVisible(true);
    }
    private void leftCommand(){
        double posX = theView.getMyBox().getTranslateX();
        double posY = theView.getMyBox().getTranslateY();
        theView.getImagePane().getChildren().remove(theView.getMyBox());
        theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
        if(posX > 0)
            if( theModel.isWallAt( (int)posY/30 ,(int) posX / 30 - 1 )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
            }
            else
            if(theModel.isFinishCel( (int)posY/30 ,(int) posX / 30 - 1 )){
                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateX(posX - 30);
                onFinish();
            }
            else
            {

                theView.getRotateProperty().set(theView.getRotateProperty().getValue() + 30);
                theView.getMyBox().setTranslateX(posX - 30);
            }

        theView.getImagePane().getChildren().addAll(theView.getMyBox());

        theView.getMyBox().setVisible(true);
    }
    private void onFinish(){

        getFinishMazeOption();
        }
//PIKER COLOR
    private void addListenerToColorPiker(){

        addListenerToFreeColorPiker();
        addListenerToWallColorPiker();
        addListenerToFinishColorPiker();
        addListenerToStartColorPiker();


    }
    private void addListenerToFreeColorPiker(){
        theView.getDrawMazePanel().getFreeColor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                freeColor = theView.getDrawMazePanel().getFreeColor().getValue();
                updateImageFromDrawMazeStage();
            }
        });
    }
    private void addListenerToWallColorPiker(){
        theView.getDrawMazePanel().getWallColor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wallColor = theView.getDrawMazePanel().getWallColor().getValue();
                updateImageFromDrawMazeStage();

            }
        });
    }
    private void addListenerToFinishColorPiker(){
        theView.getDrawMazePanel().getFinishColor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                finishColor = theView.getDrawMazePanel().getFinishColor().getValue();
                updateImageFromDrawMazeStage();
            }
        });
    }
    private void addListenerToStartColorPiker(){
        theView.getDrawMazePanel().getStartColor().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startColor = theView.getDrawMazePanel().getStartColor().getValue();
                updateImageFromDrawMazeStage();
            }
        });
    }
//DRAW MAZE LISTENER
    private void addListenerToDrawMaze(){
        int[][] mazeTempModel = new int[19][19];
        initializeTempMazeFromDrawMazeStage(mazeTempModel);
        theModel.setMaze(mazeTempModel);
        for (int i = 0;i<theView.getDrawMazePanel().getRectangle().length; i++)
            for(int j = 0; j < theView.getDrawMazePanel().getRectangle().length;j++)
            {
                final int finalJ = j;final int finalI = i;
                theView.getDrawMazePanel().getRectangle()[i][j].setOnMouseMoved(event -> {
                    if (radioBoxChecked.equals("finish")) {
                        finishRadioButtonPressedAction(finalI, finalJ);
                    } else if (radioBoxChecked.equals("free")) {
                        freeRadioButtonPressedAction(finalI, finalJ);
                    } else if (radioBoxChecked.equals("start")) {
                        startRadioButtonPressedAction(finalI, finalJ);
                    }
                    if (radioBoxChecked.equals("wall")) {
                        wallRadioButtonPressedAction(finalI, finalJ);
                    }
                });
            }
    }
//RADIO BUTTON
    private void initializeTempMazeFromDrawMazeStage(int[][] mazeTempModel){
        for (int i = 0;i<theView.getDrawMazePanel().getRectangle().length; i++)
            for(int j = 0; j < theView.getDrawMazePanel().getRectangle().length;j++)
                mazeTempModel[i][j] = 1;
    }
    private void updateImageFromDrawMazeStage(){
        for (int i = 0;i<theView.getDrawMazePanel().getRectangle().length; i++)
            for(int j = 0; j < theView.getDrawMazePanel().getRectangle().length;j++)
            {
                if(theModel.isFreeAt(i,j)){
                    theView.getDrawMazePanel().getRectangle()[i][j].setFill(freeColor);
                }
                if(theModel.isWallAt(i, j)){
                    theView.getDrawMazePanel().getRectangle()[i][j].setFill(wallColor);
                }
                if(theModel.isStartCel(i, j)){
                    theView.getDrawMazePanel().getRectangle()[i][j].setFill(startColor);
                }
                if(theModel.isFinishCel(i,j)){
                    theView.getDrawMazePanel().getRectangle()[i][j].setFill(finishColor);
                }
            }


    }
//BUTTON FROM DRAW STAGE
    private void addListenerToDrawButton(){
        theView.getDrawMazePanel().getCreare().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                theView.getImagePane().getChildren().clear();

                if(isCorect()){
                    creare();


                    theView.getImagePane().getChildren().remove(theView.getMyBox());

                    theView.getMyBox().setTranslateX(theModel.getStartCellY() * 30);
                    theView.getMyBox().setTranslateY(theModel.getStartCellX() * 30);

                    theView.getImagePane().getChildren().add(theView.getMyBox());
                    theView.getMyBox().setVisible(true);

                    theView.getDrawMazePanel().getStage().close();

                }
                else {

                    theView.getNotCorrectMaze();

                }


            }
        });
    }
    private boolean isCorect(){

        if(theModel.haveFinishCell())
            if(theModel.haveStartCell())
                return true;
        return false;

    }
//RADIO BUTTONS LISTENER
    private void addListenerToRadioButtonFromDrawMazeStage(){

        theView.getDrawMazePanel().getGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (theView.getDrawMazePanel().getGroup().getSelectedToggle() != null) {
                    radioBoxChecked = theView.getDrawMazePanel().getGroup().getSelectedToggle().getUserData().toString();
                }
            }
        });
    }
    private void finishRadioButtonPressedAction(int finalI,int finalJ){

        if(theModel.haveFinishCell()){
            int x =  theModel.getFinishCellY();
            int y = theModel.getFinishCellX();
            theModel.setCell(x,y,1);
        }

        theModel.setCell(finalI, finalJ, 2);

        updateImageFromDrawMazeStage();
    }
    private void freeRadioButtonPressedAction(int finalI,int finalJ){

        theModel.setCell(finalI, finalJ, 0);
        updateImageFromDrawMazeStage();
    }
    private void startRadioButtonPressedAction(int finalI,int finalJ){

        if(theModel.haveStartCell()){
            int x =  theModel.getStartCellX();
            int y = theModel.getStartCellY();
            theModel.setCell(x,y,1);
        }
        theModel.setCell(finalI, finalJ, -1);
        updateImageFromDrawMazeStage();

    }
    private void wallRadioButtonPressedAction(int finalI, int finalJ) {
        theModel.setCell(finalI, finalJ, 1);
        updateImageFromDrawMazeStage();

    }
    private void saveImage(WritableImage snapshot) {
        File file = new File("C:/Users/Ionut/Desktop/test2.jpg");
        BufferedImage image;
        image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
        try {
            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.translate(theView.getImagePane().getWidth(), theView.getImagePane().getHeight());
            ImageIO.write(image, "png", file);
        } catch (IOException ex) {
           System.out.print("EROARE A INTERVENIT");
        };
    }


    private static void saveTheModel(Object obj) throws IOException{
        FileOutputStream fos = new FileOutputStream("fisier.ser");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(obj);
        out.flush();
        fos.close();
        System.out.println("A fost salvat obiectul "+obj+"\n");

    }
    private static Object getTheModelSaved() throws IOException{

        FileInputStream fis = new FileInputStream("fisier.ser");
        ObjectInputStream in = new ObjectInputStream(fis);

        Object obiect = new Object();
        try {
            obiect = (int[][])in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fis.close();
        System.out.println("A fost restaurat obiectul: " + obiect + "\n");
        return obiect;
    }
    private static void saveTheBox(Object obj)throws IOException {
        FileOutputStream fos = new FileOutputStream("fisier2.ser");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(obj);
        out.flush();
        fos.close();
        System.out.println("A fost salvat obiectul "+obj+"\n");
    }
    private static Object getTheBoxSaved() throws IOException{
        FileInputStream fis = new FileInputStream("fisier2.ser");
        ObjectInputStream in = new ObjectInputStream(fis);

        Object obiect = new Object();
        try {
            obiect = (double[]) in.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        fis.close();
        System.out.println("A fost restaurat obiectul: " + obiect + "\n");
        return obiect;
    }
    private void addListenerOnExit(){
       theModel.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                if(theView.getDrawMazePanel().getStage() != null)
                            theView.getDrawMazePanel().getStage().close();

            }
        });
    }
    private void getFinishMazeOption(){
        javafx.scene.control.Button button = new javafx.scene.control.Button("NEW MAZE");
        javafx.scene.control.Button close = new javafx.scene.control.Button("CLOSE");

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Felicitari, a-ti terminat !"),button,close).
                alignment(Pos.CENTER).padding(new javafx.geometry.Insets(5)).build()));
        dialogStage.show();

        close.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                dialogStage.close();
                if(theView.getDrawMazePanel().getStage() != null)
                    theView.getDrawMazePanel().getStage().close();
                theModel.getStage().close();

            }
        });

        button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                generateMaze();
                dialogStage.close();
            }
        });
    }
    private void genNotGneratedMaze(){
        javafx.scene.control.Button button = new javafx.scene.control.Button("OK");

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Trebuie sa generati un labirint mai intai !"),button).
                alignment(Pos.CENTER).padding(new javafx.geometry.Insets(5)).build()));
        dialogStage.show();


        button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                generateMaze();
                dialogStage.close();
            }
        });


    }
}
