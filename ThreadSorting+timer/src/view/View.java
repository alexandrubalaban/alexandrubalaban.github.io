package view;

import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Ionut on 24.04.2015.
 */
public interface View {
    void startView();
    Button getStartFirstAlgButton();
    Button getStartSecondAlgButton();
    Button getStartThirdAlgButton();
    Button getStartAllButton();
    void setFirstAlgGridPane(Rectangle[] rectangleOfFirstPane);
    Rectangle[] getRectangleOfFirstPane();
    void setThirdRectangle(int[] vectorSortedRepresentation);
    void setSecondRectangle(int[] vectorSortedRepresentation);
    void setFirstRectangle(int[] vectorToSort);
    void initThirdRectangle(int[] vectorSortedRepresentation);
    void initSecondRectangle(int[] vectorSortedRepresentation);
    void initFirstRectangle(int[] vectorToSort);
    GridPane getFirstAlgPane();
    GridPane getSecondAlgPane();
    GridPane getThirdAlgPane();
    void setSecondAlgGridPane(Rectangle[] rectangleOfSecondPane);
    void setThirdAlgGridPane(Rectangle[] rectangleOfSecondPane);
    Rectangle[] getRectangleOfSecondPane();
    Rectangle[] getRectangleOfTirdPane();
    void updateFirst(int[] vector, int i) throws InterruptedException;
    void updateFirstIndicator(int d, int[] array,boolean c) throws InterruptedException;
    void updateSecond(final int[] vector, int i,int j,boolean c) throws InterruptedException;
    void updateThird(final int[] vector,int i, int j,boolean b) throws InterruptedException;
    void setChronometerOfFirst();
    Timeline getTimelineForFirst();
    Duration getTimeForFirst();
    void setTimeForFirst(Duration time);
    void setTimeSecondsForFirst(double timeSeconds);
    void setTimelineForFirst(Timeline timelineForFirst);
    void stopWatch();
    Timeline getTimelineForSecond();
    void setTimelineForSecond(Timeline timelineForSecond);
    void setTimeSecondsForSecond(double timeSecondsForSecond);
    Duration getTimeForSecond();
    void setTimeForSecond(Duration timeForSecond);
    Timeline getTimelineForThird();
    void setTimelineForThird(Timeline timelineForThird);
    void setTimeSecondForThird(double timeSecondForThird);
    Duration getTimeThird();
    void setTimeThird(Duration timeThird);
    Button getGenerateNewButton();
    boolean getStatusOfPane();
    void getNotNow();
    boolean getStatusOfFirtsChronometer();
    boolean getStatusOfSecondChronometer();
    boolean getStatusOfThirdChronometer();
}
