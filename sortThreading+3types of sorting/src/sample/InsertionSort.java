package sample;

import javafx.application.Platform;

/**
 * Created by maximbureac on 4/26/15.
 */
public class InsertionSort implements Runnable {

    private int timeOut = 100;
    private int [] forSort;
    private Controller controller;

    public InsertionSort(int[] forSort, Controller controller){
        this.forSort = forSort;
        this.controller = controller;
    }
    public void sort() throws InterruptedException {
        int i, j, newValue;
        for (i = 1; i < forSort.length; i++) {

            newValue = forSort[i];
            final int finalI = i;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.lineToSwap(finalI, forSort, controller.getGcInsert());
                }
            });
            j = i;

            while (j > 0 && forSort[j - 1] > newValue) {
                final int finalJ = j;
                forSort[j] = forSort[j - 1];
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineToSwap(finalJ, forSort, controller.getGcInsert());
                        controller.lineToSwap(finalJ - 1, forSort, controller.getGcInsert());
                    }
                });

                Thread.sleep(timeOut);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineDraw(finalJ, forSort, controller.getGcInsert());
                        controller.lineDraw(finalJ - 1, forSort, controller.getGcInsert());
                    }
                });
                j--;
            }
            final int finalK = j;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.lineToSwap(finalI, forSort, controller.getGcInsert());
                    controller.lineToSwap(finalK, forSort, controller.getGcInsert());
                }
            });
            forSort[j] = newValue;
            Thread.sleep(timeOut);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    controller.lineDraw(finalI, forSort, controller.getGcInsert());
                    controller.lineDraw(finalK, forSort, controller.getGcInsert());
                }
            });
        }
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        try {
            sort();
            for (int i = 0; i < forSort.length; i++) {
                System.out.println(forSort[i]);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
