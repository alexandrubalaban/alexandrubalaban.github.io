package model;

/**
 * Created by Ionut on 24.04.2015.
 */
public class ModelImplementation implements Model {
    private int[] vector  = {10,20,50,20,30,100,2};

    public int[] getVector() {
        return vector;
    }

    public void setVector(int[] vector) {
        this.vector = vector;
    }
}
