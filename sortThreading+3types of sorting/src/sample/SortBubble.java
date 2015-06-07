package sample;

import javafx.application.Platform;

/**
 * Created by maximbureac on 4/26/15.
 */
public class SortBubble implements Runnable{
    private int timeOut = 100;
    private int [] forSort;
    private Controller controller;

    public SortBubble(int[] forSort, Controller controller){
        this.forSort = forSort;
        this.controller = controller;
    }
    public void sort() throws InterruptedException {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < forSort.length - j; i++) {
                if (forSort[i] > forSort[i + 1]) {
                    tmp = forSort[i];

                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            controller.lineToSwap(finalI, forSort, controller.getGcBubble());
                            controller.lineToSwap(finalI + 1, forSort, controller.getGcBubble());
                        }
                    });

                    Thread.sleep(timeOut);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            controller.lineDraw(finalI, forSort, controller.getGcBubble());
                            controller.lineDraw(finalI + 1, forSort, controller.getGcBubble());
                        }
                    });


                    forSort[i] = forSort[i + 1];
                    forSort[i + 1] = tmp;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            controller.lineToSwap(finalI, forSort, controller.getGcBubble());
                            controller.lineToSwap(finalI + 1, forSort, controller.getGcBubble());
                        }
                    });


                    Thread.sleep(timeOut);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            controller.lineDraw(finalI, forSort, controller.getGcBubble());
                            controller.lineDraw(finalI + 1, forSort, controller.getGcBubble());
                        }
                    });

                    swapped = true;
                }
            }
        }
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        System.out.println(this.forSort.length);
        try {
            sort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < forSort.length; i++) {
            System.out.println(forSort[i]);
        }
    }
}
