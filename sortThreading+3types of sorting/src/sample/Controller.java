package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML public AnchorPane anchorPane;
    @FXML public Pane pane;
    @FXML public Canvas canvasBuble;
    @FXML public Canvas canvasQuick;
    @FXML public Canvas canvasInsert;
    @FXML public Label lungVector;
    @FXML public TextField vectLungTextField;
    @FXML public Button startBtn;
    @FXML public Slider animationSpeed;
    @FXML public Label speedLabel;
    private int[] forSort;
    private GraphicsContext gcBubble;
    private GraphicsContext gcQuick;
    private GraphicsContext gcInsert;
    private SortBubble bubleSort;
    private Thread bubleThread;
    private InsertionSort insertSort;
    private Thread insertThread;
    private QuickSort quicktSort;
    private Thread quickThread;
    private int[] forQuickSort;
    private int[] forInsertSort;

    public int [] randomVec(int length){

        forSort = new int[length];
        for (int i = 0; i < forSort.length; i++){
            forSort[i] = i + 1;
        }
        ShuffleVector(forSort);

        return forSort;

    }


    private void ShuffleVector(int[] array)
    {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            if (index != i)
            {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gcBubble = canvasBuble.getGraphicsContext2D();
        gcBubble.setFill(Color.ALICEBLUE);
        gcBubble.fillRect(0, 0, 700, 200);

        gcQuick = canvasQuick.getGraphicsContext2D();
        gcQuick.setFill(Color.ALICEBLUE);
        gcQuick.fillRect(0, 0, 700, 200);

        gcInsert = canvasInsert.getGraphicsContext2D();
        gcInsert.setFill(Color.ALICEBLUE);
        gcInsert.fillRect(0, 0, 700, 200);

    }
    @FXML public void startButton(Event event){

        int lungVect = Integer.parseInt(vectLungTextField.getText());
        forSort = randomVec(lungVect);
        forInsertSort = copyVect(forSort);
        forQuickSort = copyVect(forSort);
        for (int i = 0; i < lungVect; i++) {
            lineDraw(i, forSort, getGcBubble());
        }
        for (int i = 0; i < lungVect; i++) {
            lineDraw(i, forSort, getGcQuick());
        }
        for (int i = 0; i < lungVect; i++) {
            lineDraw(i, forSort, getGcInsert());
        }
        bubleSort = new SortBubble(forSort, this);
        bubleThread = new Thread(bubleSort);
        bubleThread.start();
        quicktSort = new QuickSort(forQuickSort, this);
        quickThread = new Thread(quicktSort);
        quickThread.start();
        insertSort = new InsertionSort(forInsertSort, this);
        insertThread = new Thread(insertSort);
        insertThread.start();

    }
    public void lineDraw(int i, int [] vector, GraphicsContext gc){
        gc.setFill(Color.BLACK);
        double heightOfOne = 200/vector.length;
        double weightOfOne = 500/vector.length;
        double weightOfOneSpace = 200/vector.length;
        gc.fillRect(i*weightOfOne+i*weightOfOneSpace, 200-heightOfOne*vector[i], weightOfOne, heightOfOne*vector[i]);
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(i*weightOfOne+i*weightOfOneSpace, 0, weightOfOne, 200-heightOfOne*vector[i]);

    }
    public int [] copyVect(int[] vect){
        int[] tempvect = new int[vect.length];
        for (int i = 0; i < vect.length ; i++) {
            tempvect[i] = vect[i];
        }
        return tempvect;
    }
    public void lineToSwap(int i, int [] vector, GraphicsContext gc){
        gc.setFill(Color.RED);
        double heightOfOne = 200/vector.length;
        double weightOfOne = 500/vector.length;
        double weightOfOneSpace = 200/vector.length;
        gc.fillRect(i*weightOfOne+i*weightOfOneSpace, 200-heightOfOne*vector[i], weightOfOne, heightOfOne*vector[i]);
        gc.setFill(Color.ALICEBLUE);
        gc.fillRect(i*weightOfOne+i*weightOfOneSpace, 0, weightOfOne, 200-heightOfOne*vector[i]);

    }

    public GraphicsContext getGcBubble() {
        return gcBubble;
    }

    public GraphicsContext getGcQuick() {
        return gcQuick;
    }

    public GraphicsContext getGcInsert() {
        return gcInsert;
    }

    @FXML public void changeSpeed(Event event){
        this.bubleSort.setTimeOut((int)animationSpeed.getValue());
        this.insertSort.setTimeOut((int) animationSpeed.getValue());
        this.quicktSort.setTimeOut((int) animationSpeed.getValue());
    }
}
