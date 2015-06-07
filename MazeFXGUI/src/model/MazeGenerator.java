package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

public class MazeGenerator{
    private final int x;
    private final int y;
    private final int[][] maze;
    public MazeGenerator(int x, int y) {
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        generateMaze(0, 0);
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
    public void writeMazeIntoFile(){
        try{
            FileOutputStream file = new FileOutputStream("mazeGenerat.txt");
            PrintStream ps = new PrintStream(file);

            ps.println(maze.length*2-1);
            for (int i = 0; i < y; i++) {
                // draw the north edge
                if(i > 0) {
                    for (int j = 0; j < x; j++) {
                        if(j  == 0)ps.print((maze[j][i] & 1) == 0 ? "1 " : "0 ");
                        else
                            ps.print((maze[j][i] & 1) == 0 ? "1 1 " : "1 0 ");
                    }
                   ps.println();//trec la linia urmatoare
                    // draw the west edge
                    for (int j = 0; j < x; j++) {
                        if(j == 0)ps.print((maze[j][i] & 8) == 0 ? "0 " : "0 0 ");
                        else
                        if(i == y-1 && j == x -1)ps.print((maze[j][i] & 8) == 0 ? "1 2 " : "0 2 ");
                        else
                            ps.print((maze[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                    //numarul de linii
                    ps.println();
                }
                else{
                    for (int j = 0; j < x; j++) {
                        if(j == 0) ps.print((maze[j][i] & 8) == 0 ? "-1 " : "   ");
                        else
                            ps.print((maze[j][i] & 8) == 0 ? "1 0 " : "0 0 ");
                    }
                    ps.println("");//numarul de linii
                }
            }
            ps.close();
            file.close();
        }
        catch (IOException ex){
            System.out.println("Nu sa creat fisierul");
        }
    }
}