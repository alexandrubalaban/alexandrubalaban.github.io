package sortingAlgoritms;

import view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class BubbleSort {

    private int swap, c, d;

    final ExecutorService exec = Executors.newSingleThreadExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        }
    });


    public void bubbleSort(int[] array,View theView,Thread thread) throws InterruptedException {
        //Trebuie afisat pe pozitia D, si array[d];
        int n = array.length;
        boolean continui = true;
        for (c = 0; c < (n - 1); c++) {
            for (d = 0; d < n - c - 1; d++) {
                if (array[d] > array[d + 1]) /* For descending order use < */ {
                    theView.updateFirst(array,d);
                    swap = array[d];
                    array[d] = array[d + 1];
                    array[d + 1] = swap;
                }
                else theView.updateFirstIndicator(d,array,continui);

            }
        }
        continui = false;
        theView.updateFirstIndicator(d,array,continui);
    }



}