package com.company;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
	// write your code here\
        double[][] b = {{0, 1}, {2, 2}};
        RareMatrix a = new RareMatrix(b);
        RareMatrix c = new RareMatrix(8,8);
        c.GenerateRareMatrix(4,4,8);
        c.Print();

    }
}
