package model;

import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Ionut on 20.04.2015.
 */
public interface MazeModel {

    boolean isFreeAt(double line, double col);
    boolean isVisited(int line, int col);
    boolean isWallAt(int line, int col);
    boolean isFinishCel(int line, int col);
    boolean isStartCel(int line, int col);
    void setCell(int line, int col,int val);
    int[][] citesteFisier( String fis );
    int getMazeModelSize();
    void solveMaze(int[][] m,int startCellX,int startCellY);
    void writeMazeIntoFile(int[][] maze);
    boolean findPath(int startColoana,int startLinie,int[][] maze,ArrayList<Pair> wasThere);
    StringBuilder getMovements();
    ArrayList<Pair> getCellsVisited();

    void setMaze(int[][] maze);
    Stage getStage();


    int[][] getMaze();
    boolean haveStartCell();
    int getStartCellX();
    int getStartCellY();
    boolean haveFinishCell();
    int getFinishCellX();
    int getFinishCellY();
    ArrayList<Double> getPolyLine();


}
