package sortingAlgoritms;

import view.View;
import view.ViewImplementation;

public class SelectionSort {
    private View theView = new ViewImplementation();
    public int[] doSelectionSort(int[] arr,View theView) throws InterruptedException {
boolean continui = true;
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[index])
                    index = j;
            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
            theView.updateThird(arr,i,index,continui);
        }
        theView.updateThird(arr,1,1,!continui);
        return arr;
    }

}
