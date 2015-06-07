package gui;

import java.util.EventObject;
/**
 * Created by Sunny on 26.03.2015.
 */
public class FormEvent extends EventObject {

    private String name;
    private String empCat;
    private String data;
    private String idImdb;
    private String raitingImdb;
    private String regizor;
    private String actor;
    private String path;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String name,
                     String empCat, String data, String idImdb,
                     String raitingImdb, String regizor, String actor, String path) {
        super(source);

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

    public void setName(String name) {
        this.name = name;
    }

    public  String getMovieCat(){
        return  empCat;
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
}
