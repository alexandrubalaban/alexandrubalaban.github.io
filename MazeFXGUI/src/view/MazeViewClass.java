package view;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.event.ActionEvent;
import java.io.Serializable;

public class MazeViewClass implements MazeView,Serializable{

    private Image wallImage;
    private Image freeImage;
    private Image startImage;
    private Image finishImage;
    private Image buburuza;

    private Polyline polyline;

    private Box myBox;

    private Button create;
    private  Button generate;
    private  Button saveJPG;
    private  Button getSavedGame;
    private Button getSolve;
    private Button saveGame;


    private HBox buttonBox;
    private GridPane imagePane;
    private  BorderPane panouPrincipal;

    private ImageView wallImageView;
    private ImageView freeImageView;
    private ImageView startImageView;
    private String cssDefault;
    private  String css;
    private String cssForImage;
    private ObjectProperty<Material> material ;
    private ObjectProperty<DrawMode> drawMode;
    private ObjectProperty<CullFace> cullFace;
    private DoubleProperty rotate;

    private Scene scene;
    private DrawMazeStage drawMazePanel;



    public MazeViewClass(){
        drawMazePanel = new DrawMazeStage();
        create =  new Button("Creare");
        generate = new Button("Generare");
        saveJPG = new Button("SalvareJPG");
        getSavedGame = new Button("Continuare");
        getSolve = new Button("Solve");
        saveGame = new Button("SaveGame");
        freeImageView = new ImageView();
        wallImageView = new ImageView();
        startImageView = new ImageView();
        cssForImage = new String();
        setCssStyle();
        writeImages();
        buttonBox = new HBox();
        setBoxButton();
        imagePane = new GridPane();
        setImagePane();
        set3DPropreties();
        myBox =  new Box(15, 15, 15);
        set3dBox();
        polyline = new Polyline();
        setPanouPrinvipal();

        scene = new Scene(panouPrincipal,610,610);

    }

    private void writeImages(){
        freeImage = new Image("images/free.png");
        wallImage = new Image("images/wall.png");
        startImage = new Image("images/images.jpg");
        finishImage = new Image("images/finish.png");
        buburuza =new Image("images/buburuzajpg.jpg");
    }
    private void setImagePane(){
        imagePane.setMinSize(250, 250);
        imagePane.setVgap(0);
        imagePane.setHgap(0);
        imagePane.setPadding(new Insets(20,20,20,20));
        imagePane.setGridLinesVisible(true);
        imagePane.setGridLinesVisible(true);
        imagePane.setMaxSize(590d,590d);

       // imagePane.setStyle(cssForImage);
    }
    private void set3DPropreties(){
        material= new SimpleObjectProperty<>(this, "material", new PhongMaterial(Color.BLUE));
        drawMode = new SimpleObjectProperty<>(this, "drawMode", DrawMode.FILL);
        cullFace  = new SimpleObjectProperty<>(this, "cullFace", CullFace.BACK);
        rotate =  new SimpleDoubleProperty(this, "rotate", 14.0d);
    }
    private void set3dBox(){
        myBox.setTranslateX(0);
        myBox.setTranslateY(0);
        myBox.materialProperty().bind(material);
        myBox.setRotationAxis(new Point3D(1.0d,1.0d,1.0d));
        myBox.drawModeProperty().bindBidirectional(drawMode);
        myBox.cullFaceProperty().bindBidirectional(cullFace);
        myBox.rotateProperty().bind(rotate);
        myBox.setVisible(true);
    }
    private void setCssStyle(){
        css=  "-fx-alignment: baseline-center;\n" +"-fx-fit-to-height: true;\n" + "-fx-fit-to-width: true;";
        cssDefault = "-fx-border-color: blue;\n" + "-fx-border-insets: 50;\n" + "-fx-border-width: 1;\n" + "-fx-border-style: dashed;\n";
        cssForImage = "-fx-max-width: 100%;\n" +
                "    height: auto;\n" +
                "    width: auto\\9;";
    }
    private void setBoxButton(){
        buttonBox.getChildren().addAll(create, generate, saveJPG, getSavedGame,getSolve,saveGame);
        buttonBox.setSpacing(40);
    }
    private void setPanouPrinvipal(){
        panouPrincipal  = new BorderPane();
        panouPrincipal.setTop(buttonBox);
        panouPrincipal.setCenter(imagePane);
        panouPrincipal.getCenter().setStyle(css);
        panouPrincipal.setStyle(css);


    }

    public Image getWallImage() {
        return wallImage;
    }

    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    public Image getFreeImage() {
        return freeImage;
    }

    public void setFreeImage(Image freeImage) {
        this.freeImage = freeImage;
    }

    public Button getCreate() {


        return create;

    }

    public void setCreate(Button create) {
        this.create = create;
    }

    public Button getGenerate() {
        return generate;
    }

    public void setGenerate(Button generate) {
        this.generate = generate;
    }

    public Button getSaveJPG() {
        return saveJPG;
    }

    public void setSaveJPG(Button saveJPG) {
        this.saveJPG = saveJPG;
    }

    public Button getGetSavedGame() {
        return getSavedGame;
    }

    public HBox getButtonBox() {
        return buttonBox;
    }

    public GridPane getImagePane() {
        return imagePane;
    }

    public void setImagePane(GridPane imagePane) {
        this.imagePane = imagePane;
    }

    public BorderPane getPanouPrincipal() {
        return panouPrincipal;
    }

    public void setPanouPrincipal(BorderPane panouPrincipal) {
        this.panouPrincipal = panouPrincipal;
    }

    public ImageView getWallImageView() {
        return wallImageView;
    }

    public void setWallImageView(ImageView wallImageView) {
        this.wallImageView = wallImageView;
    }

    public ImageView getFreeImageView() {
        return freeImageView;
    }

    public void setFreeImageView(ImageView freeImageView) {
        this.freeImageView = freeImageView;
    }

    public void startApp(Stage stage){
        stage.setScene(scene);
        stage.show();

    }

    public DrawMazeStage getDrawMazePanel() {
        return drawMazePanel;
    }

    public void setDrawMazePanel(DrawMazeStage drawMazePanel) {
        this.drawMazePanel = drawMazePanel;
    }

    public void getNewStage(){
        drawMazePanel.ss(10,10);
    }

    public Image getStartImage() {
        return startImage;
    }

    public Image getFinishImage() {
        return finishImage;
    }

    public void switchScene(Scene scene){
        this.scene = scene;
    }

    public Box getMyBox() {
        return myBox;
    }

    public Button getSolver() {
        return getSolve;
    }

    public DoubleProperty getRotateProperty() {
        return rotate;
    }

    public Scene getScene() {
        return scene;
    }

    public Stage getStage(){
        return this.getStage();
    }

    public Polyline getPolyline() {
        return polyline;
    }

    public Button getSaveGame() {
        return saveGame;
    }

    @Override
    public void getNotCorrectMaze() {

    Button button = new Button("OK");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Trebuie sa setati start si finish"),button).
                alignment(Pos.CENTER).padding(new Insets(5)).build()));
        dialogStage.show();

        button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                dialogStage.close();
            }
        });
    }




}