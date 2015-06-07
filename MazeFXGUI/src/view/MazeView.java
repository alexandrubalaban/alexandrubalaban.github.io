package view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.io.Serializable;

/**
 * Created by Ionut on 20.04.2015.
 */
public interface MazeView  {


    void startApp(Stage stage);
    Image getWallImage();
    void setWallImage(Image wallImage);
    Image getFreeImage();
    void setFreeImage(Image freeImage);
    Button getCreate();
    void setCreate(Button create);
    void setGenerate(Button generate);
    Button getGenerate();
    Button getSaveJPG();
    void setSaveJPG(Button save);
    Button getGetSavedGame();

    HBox getButtonBox();
    GridPane getImagePane();
    void setImagePane(GridPane imagePane);
    BorderPane getPanouPrincipal();
    void setPanouPrincipal(BorderPane panouPrincipal);
    ImageView getWallImageView();
    void setWallImageView(ImageView wallImageView);
    ImageView getFreeImageView();
    void setFreeImageView(ImageView freeImageView);

    void getNewStage();

    DrawMazeStage getDrawMazePanel();
    void setDrawMazePanel(DrawMazeStage drawMazePanel);
    Image getStartImage();
    Image getFinishImage();
    void switchScene(Scene scene);
    Box getMyBox();
    DoubleProperty getRotateProperty();
    Button getSolver();
    Scene getScene();
    Stage getStage();
    Polyline getPolyline();
    Button getSaveGame();
    void getNotCorrectMaze();


}
