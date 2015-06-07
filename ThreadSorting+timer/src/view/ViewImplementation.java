package view;


import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * Created by Ionut on 24.04.2015.
 */
public class ViewImplementation implements View {


    private Stage stage = null;
    private Scene scene = null;

    private Button startAllButton;
    private Button startFirstAlgButton;
    private Button startSecondAlgButton;
    private Button startThirdAlgButton;
    private Button generateButton;

    private Rectangle[] rectangleOfFirstPane;
    private Rectangle[] rectangleOfSecondPane;
    private Rectangle[] rectangleOfTirdPane;

    private GridPane firstAlgPane;
    private GridPane secondAlgPane;
    private GridPane thirdAlgPane;

    final ExecutorService execForFirstAlg = Executors.newSingleThreadExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
    final ExecutorService execForSecondAlg = Executors.newSingleThreadExecutor(new ThreadFactory() {
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });
    final ExecutorService execForThirdAlg = Executors.newSingleThreadExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t;
        }
    });

    private HBox paneWithAlg;
    private HBox buttonBox;
    private BorderPane principalPane;
    private HBox chronometerPane;

    private Timeline timelineForFirst;
    private Label timerLabelForFirst;
    private DoubleProperty timeSecondsForFirst = new SimpleDoubleProperty();
    private Duration timeForFirst = Duration.ZERO;

    private Timeline timelineForSecond;
    private Label timerLabelForSecond;
    private DoubleProperty timeSecondsForSecond = new SimpleDoubleProperty();
    private Duration timeForSecond = Duration.ZERO;

    private Timeline timelineForThird;
    private Label timerLabelForThird;
    private DoubleProperty timeSecondForThird = new SimpleDoubleProperty();
    private Duration timeThird = Duration.ZERO;

    public ViewImplementation(){}
    public ViewImplementation(Stage stage) {
        this.stage = stage;
    }

    public void startView() {
        initBoxButton();
        setButtonBox();
        initFirsAlgGridPane();
        initSecondAlgGridPane();
        initThirdAlgGridPane();
        initAndSetChonometer();
        initPaneWithAlg();
        setPaneWithAlg();
        initPrincipalePane();
        setPrincipalPane();
        initScene();
        setStage();
    }

    //PRINCIPAL
    private void setStage(){
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.setMaxWidth(1000);
        stage.setMaxHeight(700);
        stage.show();
    }
    private void initScene() {
        scene = new Scene(principalPane);
        scene.getStylesheets().add("style/stylesheet.css");
    }

    private void initPrincipalePane() {
        principalPane = new BorderPane();
    }

    private void initPaneWithAlg() {
        paneWithAlg = new HBox();
        paneWithAlg.setSpacing(20);
        paneWithAlg.getStyleClass().add("paneWithAlg");
        paneWithAlg.setPadding(new Insets(20, 20, 20, 20));
    }

    private void initBoxButton() {
        buttonBox = new HBox();
        buttonBox.setSpacing(30);
        buttonBox.setPadding(new Insets(0, 0, 0, 250));
    }

    private void setPrincipalPane() {
        principalPane.setTop(buttonBox);
        principalPane.setCenter(paneWithAlg);
        principalPane.setBottom(chronometerPane);

    }

    private void setPaneWithAlg() {
        paneWithAlg.getChildren().addAll(getFirstAlgPane(), getSecondAlgPane(), getThirdAlgPane());
    }

    private void setButtonBox() {
        initStartAllButton();
        initStartFirstAlgButton();
        initStartSecondAlgButton();
        initStartThirdAlgButton();
        generateButton = new Button("Get New Vector");
        buttonBox.getChildren().addAll(startAllButton, startFirstAlgButton, startSecondAlgButton, startThirdAlgButton, generateButton);
    }

    //ALG PANEL
    private void initFirsAlgGridPane() {
        firstAlgPane = new GridPane();
        firstAlgPane.getStyleClass().add("firstAlgPane");
    }

    public void setFirstAlgGridPane(Rectangle[] rectangleOfFirstPane) {
        for (int i = 0; i < rectangleOfFirstPane.length; i++) {
            rectangleOfFirstPane[i].setFill(Color.BLACK);
            firstAlgPane.add(rectangleOfFirstPane[i], 2, i + 10);
        }

    }

    private void initSecondAlgGridPane() {
        secondAlgPane = new GridPane();
        secondAlgPane.getStyleClass().add("secondAlgPane");
    }

    public void setSecondAlgGridPane(Rectangle[] rectangleOfSecondPane) {

        for (int i = 0; i < rectangleOfSecondPane.length; i++) {
            rectangleOfSecondPane[i].setFill(Color.BISQUE);
            secondAlgPane.add(rectangleOfSecondPane[i], 3, i + 10);
        }

    }

    private void initThirdAlgGridPane() {
        thirdAlgPane = new GridPane();
        thirdAlgPane.getStyleClass().add("thirdAlgPane");
    }

    public void setThirdAlgGridPane(Rectangle[] rectangleOfTirdPane) {
        for (int i = 0; i < rectangleOfTirdPane.length; i++) {
            rectangleOfTirdPane[i].setFill(Color.GREEN);
            thirdAlgPane.add(rectangleOfTirdPane[i], 2, i + 10);
        }
    }

    //BUTTON
    private void initStartAllButton() {
        startAllButton = new Button("START ALL");
    }

    private void initStartFirstAlgButton() {
        startFirstAlgButton = new Button("Start First");
    }

    private void initStartSecondAlgButton() {
        startSecondAlgButton = new Button("Start Second");

    }

    private void initStartThirdAlgButton() {
        startThirdAlgButton = new Button("Start Third");
    }

    //REPRESENTATION OF VECTOR
    public void initSecondRectangle(int[] vectorSortedRepresentation) {
        rectangleOfSecondPane = new Rectangle[vectorSortedRepresentation.length];

    }

    @Override
    public void initFirstRectangle(int[] vectorToSort) {
        rectangleOfFirstPane = new Rectangle[vectorToSort.length];
    }

    public void initThirdRectangle(int[] vectorSortedRepresentation) {
        rectangleOfTirdPane = new Rectangle[vectorSortedRepresentation.length];

    }

    @Override
    public void setThirdRectangle(int[] vectorSortedRepresentation) {
        for (int i = 0; i < vectorSortedRepresentation.length; i++) {
            int width = vectorSortedRepresentation[i];
            rectangleOfTirdPane[i] = RectangleBuilder.create()
                    .x(50)
                    .y(50)
                    .width(width)
                    .height(20)
                    .build();
        }
    }

    @Override
    public void setSecondRectangle(int[] vectorSortedRepresentation) {
        for (int i = 0; i < vectorSortedRepresentation.length; i++) {
            int width = vectorSortedRepresentation[i];
            rectangleOfSecondPane[i] = RectangleBuilder.create()
                    .x(50)
                    .y(50)
                    .width(width)
                    .height(20)
                    .build();
        }
    }

    @Override
    public void setFirstRectangle(int[] vectorSortedRepresentation) {
        for (int i = 0; i < vectorSortedRepresentation.length; i++) {
            int width = vectorSortedRepresentation[i];
            rectangleOfFirstPane[i] = RectangleBuilder.create()
                    .x(50)
                    .y(50)
                    .width(width)
                    .height(20)
                    .build();
        }
    }

    //CHRONOMETER

    private void initAndSetChonometer(){
        initChronometerPane();
        initChronometer();
        initSecondChronometer();
        initThirdChronometer();
        setChronometerOfFirst();
        setChronometerOfSecond();
        setChronometerOfThird();
        setChronometerPane();

    }
    private void initChronometerPane() {
        chronometerPane = new HBox();
    }

    private void setChronometerPane() {
        chronometerPane.setPadding(new Insets(10, 10, 10, 50));
        chronometerPane.setSpacing(300);
        chronometerPane.getChildren().addAll(timerLabelForFirst, timerLabelForSecond, timerLabelForThird);
    }

    private void initChronometer() {
        timerLabelForFirst = new Label();
    }

    public void setChronometerOfFirst() {
        timerLabelForFirst.textProperty().bind(timeSecondsForFirst.asString());
        timerLabelForFirst.setTextFill(Color.RED);
        timerLabelForFirst.setStyle("-fx-font-size: 4em;");
    }

    public void setChronometerOfSecond() {
        timerLabelForSecond.textProperty().bind(timeSecondsForSecond.asString());
        timerLabelForSecond.setTextFill(Color.BLUE);
        timerLabelForSecond.setStyle("-fx-font-size: 4em;");
    }

    public void setChronometerOfThird() {
        timerLabelForThird.textProperty().bind(timeSecondForThird.asString());
        timerLabelForThird.setTextFill(Color.RED);
        timerLabelForThird.setStyle("-fx-font-size: 4em;");
    }

    private void initSecondChronometer() {
        timerLabelForSecond = new Label();
    }

    private void initThirdChronometer() {
        timerLabelForThird = new Label();
    }

    //UPDATE FIRST
    public void updateFirst(final int[] vector, int i) throws InterruptedException {
        final int j = i;
        final int vectorVal = vector[i + 1];
        final int vectorValPrec = vector[i];
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(100);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setFill(Color.BLUE);
                Rectangle rectangle2 = new Rectangle();
                rectangle2.setHeight(20);
                rectangle2.setWidth(20);
                rectangle2.setFill(Color.GREEN);
                firstAlgPane.getChildren().clear();
                firstAlgPane.getChildren().removeAll(rectangle);
                firstAlgPane.add(rectangle, 1, j + 1 + 10);
                firstAlgPane.add(rectangle2, 1, j + 10);
                rectangleOfFirstPane[j + 1].setWidth(vectorValPrec);
                rectangleOfFirstPane[j].setWidth(vectorVal);
                setFirstAlgGridPane(rectangleOfFirstPane);
            }
        });
        execForFirstAlg.submit(task);
    }

    public void updateFirstIndicator(int i, final int[] vector, boolean continui) throws InterruptedException {
        final int j = i;
        final int vectorVal = vector[i];
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(100);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setFill(Color.BLUE);
                Rectangle rectangle2 = new Rectangle();
                rectangle2.setHeight(20);
                rectangle2.setWidth(20);
                rectangle2.setFill(Color.BLUE);
                firstAlgPane.getChildren().clear();
                firstAlgPane.add(rectangle, 1, j + 1 + 10);
                firstAlgPane.add(rectangle2, 1, j + 10);
                rectangleOfFirstPane[j].setWidth(vectorVal);
                setFirstAlgGridPane(rectangleOfFirstPane);
                if (!continui) {
                    stopWatch();


                }
            }
        });

        execForFirstAlg.submit(task);

    }
    //UPDATE SECOND
    public void updateSecond(final int[] vector, int i, int k, boolean continui) throws InterruptedException {
        final int j = i;
        final int posOfi = k;
        final int vectorVal = vector[j];
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(100);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Rectangle rectangle2 = new Rectangle();
                rectangle2.setHeight(20);
                rectangle2.setWidth(20);
                rectangle2.setFill(Color.RED);
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setFill(Color.BLUE);

                secondAlgPane.getChildren().clear();
                secondAlgPane.add(rectangle2, 1, posOfi + 10);
                secondAlgPane.add(rectangle, 1, j + 10);


                rectangleOfSecondPane[j].setWidth(vectorVal);
                setSecondAlgGridPane(rectangleOfSecondPane);
                if (!continui) {
                    stopWatchSecond();
                }
            }
        });
        execForSecondAlg.submit(task);
    }
    //UPDATE THIRD
    public void updateThird(final int[] vector, int i, int j,boolean continui) throws InterruptedException {
        final int posOfi = i;
        final int posOfj = j;
        final int vectorVali = vector[i];
        final int vectorValj = vector[j];
        final Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Thread.sleep(100);
                return null;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Rectangle rectangle2 = new Rectangle();
                rectangle2.setHeight(20);
                rectangle2.setWidth(20);
                rectangle2.setFill(Color.RED);
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(20);
                rectangle.setWidth(20);
                rectangle.setFill(Color.BLUE);

                thirdAlgPane.getChildren().clear();

                thirdAlgPane.add(rectangle2, 1, posOfi + 10);
                thirdAlgPane.add(rectangle, 1, j + 10);
                rectangleOfTirdPane[posOfi].setWidth(vectorVali);
                rectangleOfTirdPane[posOfj].setWidth(vectorValj);
                setThirdAlgGridPane(rectangleOfTirdPane);
                if (!continui) {
                    stopWatchThird();
                }
            }
        });
        execForThirdAlg.submit(task);
    }
    //GETTER
    public GridPane getFirstAlgPane() {
        return firstAlgPane;
    }

    public GridPane getSecondAlgPane() {
        return secondAlgPane;
    }

    public GridPane getThirdAlgPane() {
        return thirdAlgPane;
    }

    public Button getStartFirstAlgButton() {
        return startFirstAlgButton;
    }

    public Button getStartSecondAlgButton() {
        return startSecondAlgButton;
    }

    public Button getStartAllButton() {
        return startAllButton;
    }

    public Button getStartThirdAlgButton() {
        return startThirdAlgButton;
    }

    public Rectangle[] getRectangleOfFirstPane() {
        return rectangleOfFirstPane;
    }

    public Rectangle[] getRectangleOfSecondPane() {
        return rectangleOfSecondPane;
    }

    public Rectangle[] getRectangleOfTirdPane() {
        return rectangleOfTirdPane;
    }

    public Timeline getTimelineForFirst() {
        return timelineForFirst;
    }

    public Duration getTimeForFirst() {
        return timeForFirst;
    }

    public void setTimeForFirst(Duration timeForFirst) {
        this.timeForFirst = timeForFirst;
    }

    public void setTimeSecondsForFirst(double timeSecondsForFirst) {
        this.timeSecondsForFirst.set(timeSecondsForFirst);
    }

    public void setTimelineForFirst(Timeline timelineForFirst) {
        this.timelineForFirst = timelineForFirst;
    }

    public void stopWatch() {

        timelineForFirst.stop();
    }

    public void stopWatchSecond() {

        timelineForSecond.stop();
    }

    public void stopWatchThird() {

        timelineForThird.stop();
    }

    public Timeline getTimelineForSecond() {
        return timelineForSecond;
    }

    public void setTimelineForSecond(Timeline timelineForSecond) {
        this.timelineForSecond = timelineForSecond;
    }

    public void setTimeSecondsForSecond(double timeSecondsForSecond) {
        this.timeSecondsForSecond.set(timeSecondsForSecond);
    }

    public Duration getTimeForSecond() {
        return timeForSecond;
    }

    public void setTimeForSecond(Duration timeForSecond) {
        this.timeForSecond = timeForSecond;
    }

    public Timeline getTimelineForThird() {
        return timelineForThird;
    }

    public void setTimelineForThird(Timeline timelineForThird) {
        this.timelineForThird = timelineForThird;
    }

    public void setTimeSecondForThird(double timeSecondForThird) {
        this.timeSecondForThird.set(timeSecondForThird);
    }

    public Duration getTimeThird() {
        return timeThird;
    }

    public void setTimeThird(Duration timeThird) {
        this.timeThird = timeThird;
    }

    @Override
    public Button getGenerateNewButton() {
        return generateButton;
    }

    public boolean getStatusOfPane() {
            if(getStatusOfFirtsChronometer() && getStatusOfSecondChronometer() && getStatusOfThirdChronometer())
           return true;
        else return false;

    }
    public boolean getStatusOfFirtsChronometer(){
        if(timelineForFirst == null)
        return true;
        else  if (timelineForFirst.getStatus().toString().equals("STOPPED"))
            return true;
        return false;

    }
    public boolean getStatusOfSecondChronometer(){
        if(timelineForSecond == null)
            return true;
        else  if (timelineForSecond.getStatus().toString().equals("STOPPED"))
            return true;
        return false;
    }
    public boolean getStatusOfThirdChronometer(){
        if(timelineForThird == null)
            return true;
        else  if (timelineForThird.getStatus().toString().equals("STOPPED"))
            return true;
        return false;
    }
    public void getNotNow() {

        Button button = new Button("OK");
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(VBoxBuilder.create().
                children(new Text("Trebuie asteptati terminarea algoritmului curent"),button).
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


