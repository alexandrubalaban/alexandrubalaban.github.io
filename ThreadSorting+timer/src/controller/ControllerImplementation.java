package controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.Model;
import model.ModelImplementation;
import sortingAlgoritms.BubbleSort;
import sortingAlgoritms.InsertionSort;
import sortingAlgoritms.SelectionSort;
import view.View;
import view.ViewImplementation;

import java.util.Random;

public class ControllerImplementation implements Controller {
      private View theView = new ViewImplementation();
      private Model theModel  =new ModelImplementation();
      private BubbleSort bubbleSort = new BubbleSort();
      private InsertionSort insertionSort = new InsertionSort();
      private SelectionSort selectionSort = new SelectionSort();
      private int[] a = {100,10,220,40,150,80,110,200,240,10,1,157,12,45};
      private int[] b = {100,10,220,40,150,80,110,200,240,10,1,157,12,45};
      private int[] c = {100,10,220,40,150,80,110,200,240,10,1,157,12,45};
      private ThreadSortingAlgoritm bubbleSortThread;
      private ThreadSortingAlgoritm insertionSortThread;
      private ThreadSortingAlgoritm selectionSortThread;
    public ControllerImplementation(View theView, Model theModel) {
            this.theView = theView;
            this.theModel = theModel;
        }
    public void startApp(){
        initAndSetAllVectors();
        theView.startView();
        setAllAlgPane();
        addListenerToAllButtons();
    }
    private void addListenerToAllButtons(){
        this.theView.getStartFirstAlgButton().setOnAction(new setFirstAlgButton());
        this.theView.getStartSecondAlgButton().setOnAction(new setSecondAlgButton());
        this.theView.getStartThirdAlgButton().setOnAction(new setThirdAlgButton());
        this.theView.getStartAllButton().setOnAction(new setStartAllThread());
        this.theView.getGenerateNewButton().setOnAction(new generateButton());
    }
    private void initAndSetAllVectors(){
        theView.initFirstRectangle(a);
        theView.setFirstRectangle(a);


        theView.initSecondRectangle(b);
        theView.setSecondRectangle(b);


        theView.initThirdRectangle(c);
        theView.setThirdRectangle(c);
    }
    private void setAllAlgPane(){theView.setFirstAlgGridPane(theView.getRectangleOfFirstPane());
        theView.setSecondAlgGridPane(theView.getRectangleOfSecondPane());
        theView.setThirdAlgGridPane(theView.getRectangleOfTirdPane());

    }
    public void setFirstThreadView(Model model){
        theView.initFirstRectangle(model.getVector());
    }
    public void setSecondThreadView(Model model){
        theView.initSecondRectangle(model.getVector());
    }
    public void setThirdThreadView(Model model){
        theView.initThirdRectangle(model.getVector());
    }
    public Model getTheModel() {
        return theModel;
    }
    //LISTENER FOR FIRST ALG BUTTON
    private class setFirstAlgButton implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            if(theView.getStatusOfFirtsChronometer()){
           startFirstThread();
           startFirstWatch();
        }
        else theView.getNotNow();
        }
    }
    private void startFirstThread(){
    bubbleSortThread = new ThreadSortingAlgoritm("BubbleSortThread",theView, bubbleSort,a);
    bubbleSortThread.start();
    theView.setFirstRectangle(bubbleSortThread.getVectorDeSortat());
    theView.getFirstAlgPane().getChildren().clear();
    theView.setFirstAlgGridPane(theView.getRectangleOfFirstPane());
}
    private void startFirstWatch(){
        if (theView.getTimeForFirst() != null) {
            theView.setTimeForFirst(Duration.ZERO);
            theView.setTimeSecondsForFirst(theView.getTimeForFirst().toSeconds());
            theView.setTimelineForFirst(new Timeline(
                    new KeyFrame(Duration.millis(100),
                            new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent t) {
                                    Duration duration = ((KeyFrame) t.getSource()).getTime();
                                    theView.setTimeForFirst(theView.getTimeForFirst().add(duration));
                                    theView.setTimeSecondsForFirst(theView.getTimeForFirst().toSeconds());
                                }
                            })
            ));
        }
        theView.getTimelineForFirst().setCycleCount(Timeline.INDEFINITE);
        theView.getTimelineForFirst().play();
    }
    private class setSecondAlgButton implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {

            if (theView.getStatusOfSecondChronometer()) {
                startSecondThread();
                startSecondWatch();
            }
        else theView.getNotNow();
        }
    }
    private void startSecondWatch(){
        theView.setTimelineForSecond(new Timeline(
                new KeyFrame(Duration.millis(100),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                Duration duration = ((KeyFrame) t.getSource()).getTime();
                                theView.setTimeForSecond(theView.getTimeForSecond().add(duration));
                                theView.setTimeSecondsForSecond(theView.getTimeForSecond().toSeconds());


                            }
                        })
        ));

        theView.getTimelineForSecond().setCycleCount(Timeline.INDEFINITE);
        theView.getTimelineForSecond().play();
    }
    private void startSecondThread(){insertionSortThread = new ThreadSortingAlgoritm("InsertionThread", theView, insertionSort, b);
        insertionSortThread.start();
        theView.setSecondRectangle(insertionSortThread.getVectorDeSortat());
        theView.getSecondAlgPane().getChildren().clear();
        theView.setSecondAlgGridPane(theView.getRectangleOfSecondPane());}
    private class setThirdAlgButton implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event) {
            if (theView.getStatusOfThirdChronometer()) {
                startThirdThread();
               startThirdWatch();
        }
        else theView.getNotNow();
        }
    }
    private void startThirdWatch(){
        theView.setTimelineForThird(new Timeline(
                new KeyFrame(Duration.millis(100),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                Duration duration = ((KeyFrame) t.getSource()).getTime();
                                theView.setTimeThird(theView.getTimeThird().add(duration));
                                theView.setTimeSecondForThird(theView.getTimeThird().toSeconds());


                            }
                        })
        ));

        theView.getTimelineForThird().setCycleCount(Timeline.INDEFINITE);
        theView.getTimelineForThird().play();
    }
    private void startThirdThread(){
        selectionSortThread = new ThreadSortingAlgoritm("SelectionSortThread", theView, selectionSort, c);
        selectionSortThread.start();
        theView.setThirdRectangle(selectionSortThread.getVectorDeSortat());
        theView.getThirdAlgPane().getChildren().clear();
        theView.setThirdAlgGridPane(theView.getRectangleOfTirdPane());
    }
    private class setStartAllThread implements EventHandler<ActionEvent>{
            @Override
            public void handle(ActionEvent event) {

                if(theView.getStatusOfPane()){
                    startAllThread();
                }
                else theView.getNotNow();
            }
        }
    private void startAllThread(){
        startFirstThread();
        startFirstWatch();
        startSecondThread();
        startSecondWatch();
        startThirdThread();
        startThirdWatch();
    }
    private class generateButton implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if(theView.getStatusOfPane())
            {
                createNewVector();
                startApp();
            }
            else theView.getNotNow();
        }
    }
    void createNewVector(){
              Random random = new Random();
              for(int i = 0;i < 13;i++){
                this.a[i] = random.nextInt(22)*10 + 10;
                  this.b[i] = a[i];
                  this.c[i] = a[i];
              }
          }
}
