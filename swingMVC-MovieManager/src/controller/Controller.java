package controller;

import gui.FormEvent;
import model.*;

import java.util.List;

/**
 * Created by Sunny on 28.03.2015.
 */
public class Controller {
    FilmColection db = new FilmColection();

    public List<Movie> getFilm(){
        return db.getMovies();
    }

    public void addMovie(FormEvent e){
        String name = e.getName();
        String movieCat = e.getMovieCat();
        String data = e.getData();
        String idImdb = e.getIdImdb();
        String raitingImdb = e.getRaitingImdb();
        String regizor = e.getRegizor();
        String actor = e.getActor();
        String path = e.getPath();



        MovieCategory movieCategory;

        if (movieCat.equals("Action")){
            movieCategory = MovieCategory.Action;
        }
        else if (movieCat.equals("Adventure")){
            movieCategory = MovieCategory.Adventure;
        }
        else if (movieCat.equals("Animation")){
            movieCategory = MovieCategory.Animation;
        }
        else if (movieCat.equals("Comedy")){
            movieCategory = MovieCategory.Comedy;
        }
        else if (movieCat.equals("Crime")){
            movieCategory = MovieCategory.Crime;
        }
        else if (movieCat.equals("Drama")){
            movieCategory = MovieCategory.Drama;
        }
        else if (movieCat.equals("Fantasy")){
            movieCategory = MovieCategory.Fantasy;
        }
        else if (movieCat.equals("Horror")){
            movieCategory = MovieCategory.Horror;
        }
        else if (movieCat.equals("Thriller")){
            movieCategory = MovieCategory.Thriller;
        }
        else {
            movieCategory = MovieCategory.other;
            System.err.println(movieCat);
        }
        

        Movie movie = new Movie(name, movieCategory, data, idImdb, raitingImdb, regizor, actor, path );

        db.addMovie(movie);

    }
    public void saveToFile(String fileName){
        db.saveToFile(fileName);
    }

}
