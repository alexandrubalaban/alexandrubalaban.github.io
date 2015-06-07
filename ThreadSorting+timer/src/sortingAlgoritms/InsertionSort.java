package sortingAlgoritms;

import view.View;

/**
 * Created by Ionut on 25.04.2015.
 */
public class InsertionSort {
  public void insertionSort(int[] arr, View theView) throws InterruptedException {
        int i, j, newValue;
      boolean continui = true;
        for (i = 1; i < arr.length; i++) {
            newValue = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > newValue) {
                arr[j] = arr[j - 1];
                theView.updateSecond(arr,j-1,i,continui);
                theView.updateSecond(arr,j,i,continui);
                j--;
            }
            arr[j] = newValue;
            theView.updateSecond(arr,j,i,continui);
        }
      theView.updateSecond(arr,i-1,i-1,!continui);
    }
}
