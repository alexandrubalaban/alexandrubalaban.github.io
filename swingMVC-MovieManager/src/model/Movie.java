package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Sunny on 28.03.2015.
 */
@XmlRootElement(name = "movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    private String name;
    private MovieCategory empCat;
    private String data;
    private String idImdb;
    private String raitingImdb;
    private String regizor;
    private String actor;
    private String path;


    public Movie(){};
    public Movie(String name, MovieCategory empCat, String data,
                 String idImdb, String raitingImdb, String regizor, String actor, String path){
        this.name = name;
        this.empCat = empCat;
        this.data = data;
        this.idImdb = idImdb;
        this.raitingImdb = raitingImdb;
        this.regizor = regizor;
        this.actor = actor;
        this.path = path;

    }


    public String getName() {
        return name;
    }

    public MovieCategory getMovieCat() {
        return empCat;
    }

    public String getData() {
        return data;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public String getRaitingImdb() {
        return raitingImdb;
    }

    public String getRegizor() {
        return regizor;
    }

    public String getActor() {
        return actor;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmpCat(MovieCategory empCat) {
        this.empCat = empCat;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    public void setRaitingImdb(String raitingImdb) {
        this.raitingImdb = raitingImdb;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
