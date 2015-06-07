package com.company;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Sunny on 08.03.2015.
 */

/**
 *Clasa RareMatrix implementeaza operatiile uzuale ale lucrului cu matrici:
 * 1.adunarea a doua matrici
 * 2.inmultirea unei matrici cu un scalar
 * 3.inmultirea a doua matrici
 * 4.transpunerea unei matrici
 * Constructorul clasei nu reprezinta generarea unei matrici m x n ci cu ajutorul structurii HashMap existenta in Java,
 * deoarece proprietatea de baza a matricilor rare este aceea ca au majoritatea elementelor nule.
 * Elementele matricii pot fi adaugate aleator sau setate unul cate unul;
 *Sunt suprascrise si metodele toString, HashCode si equals
 */
public class RareMatrix {
    //private int nzElements;
    /**
     * Numarul de linii ale matricii
     */
    private int rows;
    /**
     * Numarul de coloane ale matricii
     */
    private int colums;
    /**
     * Structura de date in care se retin elementele in matrice
     */
    HashMap<Integer, Double> matrix;

    /**
     * Constructorul explicit a unei matrici rare avind ca parametru un double[][]
     * vom umple matricea noastra rara element cu element memorind pozitia dupa formula i*rows+j
     * iar elementul il v-om memora ca tip double
     * In asa fel v-om crea structura noastra de HashMap
     * @parma x numarul de linii
     * @parma x[0] numarul de coloane
     */
    public RareMatrix(double[][] x) {
        matrix = new HashMap<Integer, Double>();
        this.rows = x.length;
        this.colums = x[0].length;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                if (x[i][j] != 0) {
                    matrix.put(i * rows + j, x[i][j]);
                }
            }

        }

    }

    /**
     * Constructorul in cazul primirii ca parametru a unui vector
     * @param x array-ul din care vom extrage matricea rara
     */

    public RareMatrix(double[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] != 0) {
                matrix.put(i, x[i]);
            }
        }
    }

    /**
     * Costructorul explicit pentru RareMatrix
     * @param m setam numarul de linii
     * @param n setam numarul de coloane
     */
    public RareMatrix(int m, int n) {
        matrix = new HashMap<Integer, Double>();
        this.rows = m;
        this.colums = n;
    }

    /**
     * Metoda ne genereaza matricea rara, avind ca parametri doar numarul de elemente nenue si intervalul in care acestia se gasesc
     * avind urmatorii parametri:
     * @param nrValNeNul parametru intreg ce reprezinta nr elementelor nenule
     * @param StartInterval parametru intreg ce reprezinta startul intervalului in care se gasesc parametrii nostri
     * @param EndInterval parametrul intreg ce reprezinta finalul intervalului
     */
    public void GenerateRareMatrix(int nrValNeNul, double StartInterval, double EndInterval) {
        for (int i = 0; i < nrValNeNul; i++) {
            double random = new Random().nextDouble();
            double result = StartInterval + (random * (EndInterval - StartInterval));
            Random rand = new Random();
            int randomNumI = rand.nextInt((nrValNeNul - 0) + 1) + 0;
            int randomNumJ = rand.nextInt((nrValNeNul - 0) + 1) + 0;
            matrix.put(randomNumI * rows + randomNumJ, result);

        }


    }

    /**
     * Metoda ce o vom folos pentru a afisa la ecran matricea noastra rara
     * extragerea matricei rare din structura noastra Hashmap si adaugarea zerourilor necesare.
     */
    public void Print() {

        for (int i = 0; i < rows; i++) {
            System.out.println();
            for (int j = 0; j < colums; j++) {
                if (matrix.get(i * rows + j) != null)
                    System.out.print(matrix.get(i * rows + j + ' '));
                else
                    System.out.print(0 + ' ');
            }
        }
    }

    /**
     * Geter function pentru un index calculat cu ajutorul i,j
     * @param i indexul folosit pentru a parcurge liniile unei matrice
     * @param j indexul folosit pentru a parcurge rindurile unei matrici
     * @return in caz de existenta a cheiei cautate vom returna pozitia
     */
    public double getIJ(Integer i, Integer j) {
        if (matrix.containsKey(i * rows + j))
            return matrix.get(i * rows + j);
        return 0;
    }

    /**
     * Construirea unui array[][] dintr-o matrice rara stocata intr-un hashMap
     * @return vom returna vectorul nou construit
     */
    public double[][] getMatrix() {
        double[][] m = new double[rows][colums];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                if (matrix.containsKey(i * rows + j))
                    m[i][j] = matrix.get(i * rows + j);
                else
                    m[i][j] = 0.0;
            }

        }
        return m;
    }

    /**
     * putIJ adaugarea valorii in HashMap-ul nostru
     * @param i folosim indexul i pentru linii
     * @param j folosim indexul j pentru coloane avind acesti doi indecsi putem calcula integerul asociat pozitiei
     * @param val parametrul ce avem de gind sa il tocam in HashMap
     */
    public void putIJ(int i, int j, double val) {
        if (val != 0)
            matrix.put(i * rows + j, val);
    }

    /**
     * Metoda ce implementeaza adunarea matricii curente cu alta matrice
     * @param a parametru de tip RareMatrix ce reprezinta matricea cu care se aduna matricea curenta
     * @return returneaza suma matricilor
     */
    public RareMatrix suma(RareMatrix a) {
        int maxRows;
        if (a.rows > this.rows)
            maxRows = a.rows;
        else
            maxRows = this.rows;
        int maxColums;
        if (a.colums > this.colums)
            maxColums = a.colums;
        else
            maxColums = this.colums;
        RareMatrix y = new RareMatrix(maxRows, maxColums);
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxColums; j++) {
                y.putIJ(i, j, (this.getIJ(i, j) + a.getIJ(i, j)));
            }

        }
        return y;
    }
    /**
     * Metoda ce implementeaza inmultirea unei matrici rare cu un scalar
     * @param scalar parametru intreg ce reprezinta valoarea scalarul-ui
     * @return returneaza matricea curenta inmultita cu scalarul dat
     */
    public RareMatrix scalarMult(double scalar) {
        RareMatrix aux = new RareMatrix(this.rows, this.colums);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                aux.putIJ(i, j, scalar * this.getIJ(i, j));
            }
        }
        return aux;
    }
    /**
     * Metoda ce implementeaza inmultirea matricii curente cu alta matrice
     * @param x parametru intreg ce reprezinta matricea cu care se inmulteste matricea curenta
     * @return returneaza produsul celor doua matrici
     */
    public RareMatrix matrixMult(RareMatrix x) {
        RareMatrix aux = new RareMatrix(this.rows, x.colums);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < x.colums; j++) {
                double suma = 0;
                for (int k = 0; k < this.colums; k++) {
                    if (this.matrix.containsKey(i * aux.rows + k) && x.matrix.containsKey(k * aux.rows + j))
                        suma += this.matrix.get(i * aux.rows + k) * x.matrix.get(k * aux.rows + j);
                }
                if (suma != 0) {
                    aux.matrix.put(i * rows + j, suma);
                }
            }
        }

        return aux;
    }
    /**
     * Metoda ce implementeaza transpunerea matricii curente
     * @return returneaza matricea curenta transpusa
     */
    public RareMatrix transpus() {
        RareMatrix aux = new RareMatrix(this.colums, this.rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                aux.putIJ(i, j, this.getIJ(j, i));
            }
        }
        return aux;
    }
    /**
     * Suprascrierea metodei toString se face reprezentand intr-un string numarul de linii al matricii, numarul de
     * coloane al matricii si elementele nenule in formatul linie|coloana - valoare
     * @return returneaza stringul
     */
    @Override
    public String toString() {
        String matrix = new String();
        matrix = matrix + " numar linii: " + Integer.toString(this.rows) + " \n ";
        matrix = matrix + "numar coloane: " + Integer.toString(this.colums) + " \n ";
        matrix += "linie|coloana - valoare" + " \n ";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                matrix = matrix + Integer.toString(i * rows + j) + " - " + Double.toString(this.matrix.get(i * rows + j)) + " \n ";
            }
        }
        return matrix;
    }
    /**
     * Suprascrierea metodei equals se face daca obiectul curent este egal cu cel primit ca parametru. Verificarea se
     * face asupra numarului de linii si numarului de coloane iar apoi element cu element se verifica pentru egalitate
     * @param x obiectul cu care se compara obiectul curent
     * @return returneaza true in caz de cele doua obiecte sunt egale, false in caz contrar
     */
    public boolean Equals(RareMatrix x) {
        if ((this.rows == x.rows) && (this.colums == x.colums))
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < colums; j++) {
                    if (this.getIJ(i, j) != x.getIJ(i, j))
                        return false;
                }
            }
        else
            return false;
        return true;
    }
    /**
     * Suprascrierea metodei hashCode.
     * @return returneaza un numar de tip int care se obtine din suma numarului de linii cu numarul de coloane si cu
     * valoarea fiecarui element nenul din matrice
     */
    @Override
    public int hashCode()
    {
        double suma=0;
        suma+=this.colums;
        suma+=this.rows;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                suma+=this.matrix.get(i*rows+j);
            }
        }
        return (int)suma;
    }
}

