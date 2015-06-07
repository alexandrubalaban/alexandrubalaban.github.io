package model;

import java.io.Serializable;


public class Pair implements Serializable
{
    private int coloana , linie;
    Pair(int linieVal, int coloanaVal)
    {
        linie=linieVal;
        coloana=coloanaVal;
    }
    public int getColoana() { return coloana; }
    public int getLinie() { return linie; }

    public void setColoana(int coloana) {
        this.coloana = coloana;
    }

    public void setLinie(int linie) {
        this.linie = linie;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Pair)) return false;
        if(coloana==((Pair)obj).getColoana() && linie==((Pair)obj).getLinie()) return true;
        else return false;
    }
    public String toString()
    {
        return coloana+" "+linie;
    }
}
