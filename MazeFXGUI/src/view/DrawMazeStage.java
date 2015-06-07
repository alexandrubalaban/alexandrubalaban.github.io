
package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.Serializable;

import static javafx.scene.paint.Color.BLACK;


/**
 * Created by Ionut on 20.04.2015.
 */
public class DrawMazeStage extends GridPane implements Serializable{

    private BorderPane panou;

    private int[][] maze;
    private Button creare;

    private ColorPicker wallColor;
    private ColorPicker startColor;
    private ColorPicker finishColor;
    private ColorPicker freeColor;

    private RadioButton freeRadioButton;
    private RadioButton startRadioButton;
    private RadioButton finishRadioButton;
    private RadioButton wallRadiioButton;

    private   Label wallName;
    private    Label freeName;
    private    Label startName;
    private    Label finishName;

    private ObservableList<Color> data;

    private Stage stage;
    private GridPane drawGridPane;
    private Rectangle[][] rectangle;
    private ToggleGroup group;

    private VBox comboBox;
    private HBox buttonBox;
    private VBox radioButtonBox;


    public void ss(int x,int y) {
        //initializare
        freeColor = new ColorPicker(Color.WHITE);
        wallColor = new ColorPicker(Color.BLACK);
        finishColor = new ColorPicker(Color.RED);
        startColor = new ColorPicker(Color.YELLOW);

        finishRadioButton = new RadioButton("FINISH");
        freeRadioButton = new RadioButton("FREE");
        startRadioButton = new RadioButton("START");
        wallRadiioButton = new RadioButton("WALL");
        finishRadioButton.setUserData("finish");
        freeRadioButton.setUserData("free");
        startRadioButton.setUserData("start");
        wallRadiioButton.setUserData("wall");


        stage = new Stage();
        drawGridPane = new GridPane();
        panou = new BorderPane();
        maze = new int[x][y];
        rectangle = new Rectangle[x][y];
        creare = new Button("Creare");
        comboBox = new VBox();
        radioButtonBox = new VBox();


        buttonBox = new HBox();
        wallName = new Label("WALL COLOR");
        freeName = new Label("FREE COLOR");
        finishName =new Label("FINISH COLOR");
        startName = new Label("START COLOR");

        drawGridPane.setVgap(2);
        drawGridPane.setHgap(2);


        final String cssDefault = "-fx-border-color: blue;\n"
                + "-fx-border-insets: 50;\n"
                + "-fx-border-width: 1;\n"
                + "-fx-border-style: dashed;\n";

        panou.setPadding(new Insets(5));
        //initializare IMAGE
        initialize(x, y);

        buttonBox.getChildren().addAll(creare);
        comboBox.getChildren().addAll(freeName,freeColor,wallName,wallColor,startName,startColor,finishName, finishColor);
         group = new ToggleGroup();

        freeRadioButton.setToggleGroup(group);
        startRadioButton.setToggleGroup(group);
        finishRadioButton.setToggleGroup(group);
        wallRadiioButton.setToggleGroup(group);


        radioButtonBox.getChildren().addAll(freeRadioButton,finishRadioButton,startRadioButton,wallRadiioButton);


        panou.setCenter(drawGridPane);
        panou.setLeft(comboBox);
        panou.setBottom(buttonBox);
        panou.setRight(radioButtonBox);
        panou.getCenter().setStyle(cssDefault);


        panou.setPadding(new Insets(30,30,30,30));

            Scene page2 = new Scene(panou);
            stage.setScene(page2);
            stage.show();
        }


    private void initialize(int x,int y){

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                rectangle[i][j] = RectangleBuilder.create()
                        .x(50)
                        .y(50)
                        .width(20)
                        .height(20)
                        .build();
            }
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                rectangle[i][j].setFill(BLACK);
                drawGridPane.add(rectangle[i][j], j, i);
            }
        }

    }
    public Button getCreare() {
        return creare;
    }
    public ObservableList<Color> getData() {
        return data;
    }
    public Rectangle[][] getRectangle() {
        return rectangle;
    }
    public Rectangle getRectangleCell(int i, int j) {
        return this.rectangle[i][j];
    }
    public ColorPicker getFreeColor() {
        return freeColor;
    }
    public ColorPicker getFinishColor() {
        return finishColor;
    }
    public ColorPicker getStartColor() {
        return startColor;
    }
    public ColorPicker getWallColor() {
        return wallColor;
    }

    public BorderPane getPanou() {
        return panou;
    }

    public RadioButton getFreeRadioButton() {
        return freeRadioButton;
    }

    public RadioButton getStartRadioButton() {
        return startRadioButton;
    }

    public RadioButton getFinishRadioButton() {
        return finishRadioButton;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public RadioButton getWallRadiioButton() {
        return wallRadiioButton;
    }

    public Stage getStage() {
        return stage;
    }
}
