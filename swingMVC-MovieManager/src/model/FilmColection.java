package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunny on 28.03.2015.
 */
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "FilmColection")
@XmlAccessorType (XmlAccessType.FIELD)
public class FilmColection {

    @XmlElement(name = "movie")
    private List<Movie> movies;



    public FilmColection(){
        movies = new ArrayList<Movie>();
        Movie m = new Movie("Jora Voinicu", MovieCategory.Comedy ,"20.01.200", "125", "10", "Sandu", "Max", "D:\\Torrents\\Amerikanski.pirog.vse.v.sbore.2012.D.HDRip.1400MB.avi");
        Movie m1 = new Movie("Super Viking", MovieCategory.Comedy ,"20.01.200", "125", "10", "Sandu", "Max", "D:\\Torrents\\Amerikanski.pirog.vse.v.sbore.2012.D.HDRip.1400MB.avi");
        Movie m2 = new Movie("Gigel", MovieCategory.Action ,"20.01.200", "125", "10", "Sandu", "Max", "D:\\Torrents\\Amerikanski.pirog.vse.v.sbore.2012.D.HDRip.1400MB.avi");
        Movie m3 = new Movie("Extraterestri", MovieCategory.Thriller ,"20.01.200", "125", "10", "Sandu", "Max", "D:\\Torrents\\Amerikanski.pirog.vse.v.sbore.2012.D.HDRip.1400MB.avi");
        addMovie(m);
        addMovie(m1);
        addMovie(m2);
        addMovie(m3);
    }

    public  void addMovie(Movie movie){
        movies.add(movie);
    }

    public List<Movie> getMovies(){
        return movies;
    }
    public void saveToFile(String fileName){
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(FilmColection.class);
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        Marshaller jaxbMarshaller = null;
        try {
            if (jaxbContext != null) {
                jaxbMarshaller = jaxbContext.createMarshaller();
            }
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }

        try {
            if (jaxbMarshaller != null) {
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            }
        } catch (PropertyException e1) {
            e1.printStackTrace();
        }

        //Marshal the employees list in file
        try {
            if (jaxbMarshaller != null) {
                jaxbMarshaller.marshal(this, new File(fileName));
            }
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
    }

}
