package model;

import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 * Created by Ionut on 20.04.2015.
 */
public class MazeModelClass implements MazeModel,Serializable{

    private StringBuilder movements;
    private ArrayList<Pair> cellsVisited;

    private ArrayList<Double> polyLine = new ArrayList<Double>();

    private Stage stage = null;
    private int maze[][];

    public MazeModelClass(){};

    public MazeModelClass(Stage stage){
        this.stage = stage;
        movements = new StringBuilder();
        cellsVisited = new ArrayList<Pair>();
    };

    public Stage getStage() {
        return stage;
    }
    @Override
    public boolean isFreeAt(double line, double col) {

        if(line >= 0 && col >= 0)
            if(line < this.maze.length && col < this.maze[(int)line].length)
            {
                if( this.maze[(int)line][(int)col] == 0 )return true;
                else return false;
            }
            else return false;
        return false;
    }
    @Override
    public boolean isWallAt(int line, int col) {

        if(line >= 0 && col >= 0) {
            if (line < this.maze.length && col < this.maze[line].length) {
                if (maze[line][col] == 1) return true;
            }
            else {
                System.out.print("\nLinie sau coloana prea mare\n");
                return false;
            }
        }
        else {
            System.out.print("Linie sau coloana mai mica decat 0");
            return false;
        }
        return false;

    }
    @Override
    public boolean isFinishCel(int line, int col) {

        if(line >= 0 && col >= 0)
            if(line <= this.maze.length && col <= this.maze[line].length)
                if(this.getValue(line,col) == 2)return true;
                else return false;
        return false;
    }
    @Override
    public boolean isStartCel(int line, int col) {
        if(this.getValue(  line,    col) == -1)return true;
        else return false;
    }

    @Override
    public boolean isVisited(int line, int col){
        if(line >= 0 && col >= 0)
            if(line < this.maze.length && col < this.maze[line].length)
            {
                if( this.maze[line][col] == 3 )return true;
                else return false;
            }
            else return false;
        return false;
    }

    public int[][] citesteFisier(String fis) {
        FileReader f = null;
        int a[][] = new int[0][0];
        try {
// Deschidem fisierul

            f = new FileReader(fis);
            BufferedReader bfr = new BufferedReader(f);
            int mazeDim = Integer.parseInt(bfr.readLine());
            int tempMaze[][] = new int[mazeDim][mazeDim];
            for(int i = 0;i <  mazeDim;i++) {
                String[] temp = bfr.readLine().split(" ");
                for(int j = 0;j< temp.length;j++)
                    tempMaze[i][j] = Integer.parseInt(temp[j]);
            }
            a = tempMaze;

        } catch (FileNotFoundException e) {
// Tratam un tip de exceptie
            System.err.println(" Fisierul nu a fost gasit ! ");
            System.err.println(" Exceptie : " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
// Tratam alt tip de exceptie
            System.out.println(" Eroare la citirea din fisier ! ");
            e.printStackTrace();
        } finally {
            if (f != null) {
// Inchidem fisierul
                System.out.println(" \n Inchidem fisierul . ");
                try {
                    f.close();
                } catch (IOException e) {
                    System.err.println(" Fisierul nu poate fi inchis ! ");
                    e.printStackTrace();
                }
            }
        }
        return a;
    }
    private int getValue(int line, int col){
        return this.maze[line][col];
    }
    public int[][] getMaze() {
        return this.maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    public int[][] getMazeModel(){
        return this.maze;
    }

    public void setCell(int line, int col,int val){
        maze[line][col] = val;
    }
    public int getMazeModelSize(){
        return this.maze.length;
    }

    private final int x = 0;
    private final int y = 0;

    public void generateMazeRandom(int x,int y){

        generateMaze(0,0);

    }

    private void generateMaze(int cx, int cy) {
        Directions[] dirs;
        dirs = Directions.values();
        Collections.shuffle(Arrays.asList(dirs));//permuta random colectia "dirs"...
        for (Directions dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, x) && between(ny, y)
                    && (maze[nx][ny] == 0)) {
                maze[cx][cy] |= dir.bit;//OR pe biti
                maze[nx][ny] |= dir.opposite.bit;
                generateMaze(nx, ny);//apel recursiv
            }
        }
    }
    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }
    private enum Directions {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private Directions opposite;

        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }
        private Directions(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };
    public void writeMazeIntoFile(int[][]maze){
        try{
            FileOutputStream file = new FileOutputStream("mazeSolved.txt");
            PrintStream ps = new PrintStream(file);
            ps.println(maze.length);
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze.length; j++)
                    ps.print(maze[i][j] + " ");
                ps.println();
            }
            ps.close();
            file.close();
        }
        catch (IOException ex){
            System.out.println("Nu sa creat fisierul");
        }
    }

    @Override
    public boolean haveStartCell() {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == -1) return true;
            }
        return false;
    }

    @Override
    public int getStartCellX() {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == -1)return i;
            }
        System.out.print("EROARE - >  NU SA GASIT START ");
        return -1;
    }

    @Override
    public int getStartCellY()  {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == -1) return j;
            }
        System.out.print("EROARE - >  NU SA GASIT START ");
        return -1;
    }

    @Override
    public boolean haveFinishCell() {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == 2) return true;
            }
        return false;
    }

    @Override
    public int getFinishCellX() {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == 2)return j;
            }
        System.out.print("EROARE - >  NU SA GASIT START ");
        return -1;
    }

    @Override
    public int getFinishCellY() {
        for(int i = 0; i< this.maze.length; i++)
            for (int j = 0 ; j < this.maze.length; j++)
            {
                if (maze[i][j] == 2) return i;
            }
        System.out.print("EROARE - >  NU SA GASIT START ");
        return -1;
    }
    public void solveMaze(int[][] maze,int startCellX,int startCellY) {

        for (int i = 0; i <maze.length; i++)
            for (int j = 0; j < maze.length; j++)
                if(maze[i][j] == 2 )
                {
                    System.out.print("AM INTRAT AICI");
                    if(solveMazeRecursively(maze,i, j, -1,startCellX,startCellY))
                        System.out.print("A REUSIT REZOLVAREA");
                    else System.out.print("\nNU A REUSIT REZOLVAREA\n");

                }
    }
    private boolean solveMazeRecursively(int[][] maze, int x, int y, int d, int startCellX, int startCellY) {
        this.getPolyLine().clear();
        boolean ok = false;
        if (x == startCellX && y == startCellY)
            ok = true;
        for (int i = 0; i < 4 && !ok; i++)
            if (i != d)
                switch (i) {
                    // 0 = up, 1 = right, 2 = down, 3 = left
                    case 0:
                        if (y - 1 >= 0)
                            if (maze[y - 1][x] == 0 ||maze[y - 1][x] == -1 || maze[y-1][x] == 3)
                                ok = solveMazeRecursively(maze, x, y - 1, 2,startCellX,startCellY);
                        break;
                    case 1:
                        if (x + 1 <= maze.length -1 )
                            if (maze[y][x + 1] == 0 ||maze[y][x + 1] == -1 || maze[y][x+1] == 3)
                                ok = solveMazeRecursively(maze, x + 1, y, 3,startCellX,startCellY);
                        break;
                    case 2:
                        if (y + 1 <= maze.length - 1)
                            if (maze[y + 1][x] == 0 || maze[y + 1][x] == -1 || maze[y+1][x] == 3)

                                ok = solveMazeRecursively(maze, x, y + 1, 0,startCellX,startCellY);
                        break;
                    case 3:
                        if (x > 0)
                            if (maze[y][x - 1] == 0 || maze[y][x - 1] == -1 || maze[y][x-1] == 3)
                                ok = solveMazeRecursively(maze, x - 1, y, 1,startCellX,startCellY);
                        break;
                }

        // once we have found a solution, draw it as we unwind the recursion
        if (ok) {
            maze[y][x] = 3;
            // 0 = up, 1 = right, 2 = down, 3 = left
            switch (d) {
                case 0:
                    this.polyLine.add((double)x * 30 + 10);
                    this.polyLine.add(  ((double)y-1) *30 +10 );

                    maze[y - 1][x] = 3;
                    break;
                case 1:
                    this.polyLine.add(((double)x + 1) * 30 +10);
                    this.polyLine.add((double)y * 30 +10);

                    maze[y][x + 1] = 3;
                    break;
                case 2:
                    this.polyLine.add((double)x * 30 +10);
                    this.polyLine.add(((double)y + 1) *30 +10 );

                    maze[y + 1][x] = 3;
                    break;
                case 3:
                    this.polyLine.add(((double)x - 1) * 30 + 10);
                    this.polyLine.add((double)y * 30 + 10);

                    maze[y][x - 1] = 3;
                    break;
            }
        }
        return ok;
    }
    public boolean findPath(int startColoana,int startLinie,int[][] maze,ArrayList<Pair> wasThere)
    {
        Pair startPosition = new Pair(startLinie,startColoana);
        // mark visited cell
        wasThere.add(startPosition);

        //Pair finish = labirint.getFinishCell();

        Pair finish = new Pair(getFinishCellY(),getFinishCellX());


        //if (x,y is goal) return true
        if(startPosition.equals(finish))return true;
        //if (x,y not open) return false
        if(isWallAt(startPosition.getLinie(), startPosition.getColoana())) return false;
        //mark x,y as part of solution path
        cellsVisited.add(startPosition);

        //if (FIND-PATH(North of x,y) == true) return true
        movements.append("w");
        if(findPath(startColoana,startLinie -1,maze,wasThere) ==  true) return true;
        movements.deleteCharAt(movements.lastIndexOf("w"));
        movements.append("d");
        //if (FIND-PATH(East of x,y) == true) return true
        if(findPath(startColoana + 1,startLinie,maze,wasThere) ==  true) return true;
        movements.deleteCharAt(movements.lastIndexOf("d"));
        movements.append("s");
        //if (FIND-PATH(South of x,y) == true) return true
        if(findPath(startColoana,startLinie+ 1,maze,wasThere) ==  true) return true;
        movements.deleteCharAt(movements.lastIndexOf("s"));
        movements.append("a");
        //if (FIND-PATH(West of x,y) == true) return true
        if(findPath(startColoana - 1, startLinie,maze,wasThere) ==  true) return true;
        movements.deleteCharAt(movements.lastIndexOf("a"));

        //unmark x,y as part of solution path
        cellsVisited.remove(startPosition);
        //return false
        return false;

    }
    public StringBuilder getMovements() {
        return movements;
    }

    public ArrayList<Pair> getCellsVisited() {
        return cellsVisited;
    }

    public ArrayList<Double> getPolyLine() {
        return polyLine;
    }
}
