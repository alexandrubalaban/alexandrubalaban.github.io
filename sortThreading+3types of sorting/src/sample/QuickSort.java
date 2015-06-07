package sample;

import javafx.application.Platform;

/**
 * Created by maximbureac on 4/26/15.
 */
public class QuickSort implements Runnable{
    private int timeOut = 100;
    private int [] forSort;
    private Controller controller;

    public QuickSort(int[] forSort, Controller controller){
        this.forSort = forSort;
        this.controller = controller;
    }
    public int partition(int arr[], int left, int right) throws InterruptedException {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];

        while (i <= j) {
            while (arr[i] < pivot)
                i++;
            while (arr[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arr[i];
                final int finalI = i;
                final int finalJ = j;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineToSwap(finalI, forSort, controller.getGcQuick());
                        controller.lineToSwap(finalJ, forSort, controller.getGcQuick());
                    }
                });

                Thread.sleep(timeOut);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineDraw(finalI, forSort, controller.getGcQuick());
                        controller.lineDraw(finalJ, forSort, controller.getGcQuick());
                    }
                });
                arr[i] = arr[j];
                arr[j] = tmp;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineToSwap(finalI, forSort, controller.getGcQuick());
                        controller.lineToSwap(finalJ, forSort, controller.getGcQuick());
                    }
                });


                Thread.sleep(timeOut);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        controller.lineDraw(finalI, forSort, controller.getGcQuick());
                        controller.lineDraw(finalJ, forSort, controller.getGcQuick());
                    }
                });
                i++;
                j--;
            }
        };

        return i;
    }

    public void sort(int arr[], int left, int right) {
        int index = 0;
        try {
            index = partition(arr, left, right);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (left < index - 1)
            sort(arr, left, index - 1);
        if (index < right)
            sort(arr, index, right);
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        sort(this.forSort, 0, forSort.length-1);
        for (int i = 0; i < forSort.length; i++) {
            System.out.println(forSort[i]);
        }
    }
}
