package controller;

import sortingAlgoritms.BubbleSort;
import sortingAlgoritms.InsertionSort;
import sortingAlgoritms.SelectionSort;
import view.View;
import view.ViewImplementation;


/**
 * Created by Ionut on 25.04.2015.
 */
public class ThreadSortingAlgoritm extends Thread implements Runnable {
    private int[] vectorDeSortat;
    private String numeThread;
    private BubbleSort bubbleSort = new BubbleSort();
    private InsertionSort insertionSort = new InsertionSort();
    private View theView = new ViewImplementation();
    private SelectionSort selectionSort = new SelectionSort();
    public ThreadSortingAlgoritm(String numeThread, View view, BubbleSort bubbleSort, int[] vectorDeSortat){
        this.theView  = view;
        this.bubbleSort = bubbleSort;
        this.numeThread = numeThread;
        this.vectorDeSortat = vectorDeSortat;
    }
    public ThreadSortingAlgoritm(String numeThread,View theView, InsertionSort insertionSort, int[] vectorDeSortat){
        this.theView = theView;
        this.insertionSort = insertionSort;
        this.numeThread = numeThread;
        this.vectorDeSortat = vectorDeSortat;
    }
    public ThreadSortingAlgoritm(String numeThread, View theView, SelectionSort selectionSort,int[] vectorDeSortat){
        this.numeThread = numeThread;
        this.theView = theView;
        this.selectionSort = selectionSort;
        this.vectorDeSortat= vectorDeSortat;
    }
    @Override
    public void run(){
            System.out.println("\n Nume thread:  " + numeThread) ;
            selectThread();

    }
    private void selectThread(){
        if(this.numeThread.equals("SelectionSortThread")){
            try {

                this.selectionSort.doSelectionSort(vectorDeSortat,theView);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(this.numeThread.equals("BubbleSortThread")){
            try {
                this.bubbleSort.bubbleSort(vectorDeSortat,theView,this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(numeThread.equals("InsertionThread")){
            try {
                insertionSort.insertionSort(vectorDeSortat,theView);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int[] getVectorDeSortat() {
        return this.vectorDeSortat;
    }


}
