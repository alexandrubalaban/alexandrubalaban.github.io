package gui;

import model.Movie;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Sunny on 28.03.2015.
 */
public class MovieTableModel extends AbstractTableModel {

    private List<Movie> db;

    private String[] colNames = {"Name", "Categorry", "Data", "ID IMDB", "Raiting IMDB", "Regizor", "Actor"};

    public MovieTableModel(){
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

    public void setData(List<Movie> db){
        this.db = db;
    }

    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Movie movie = db.get(row);

        switch (col){
            case 0:
                return  movie.getName();
            case 1:
                return  movie.getMovieCat();
            case 2:
                return  movie.getData();
            case 3:
                return  movie.getIdImdb();
            case 4:
                return  movie.getRaitingImdb();
            case 5:
                return  movie.getRegizor();
            case 6:
                return  movie.getActor();
        }

        return null;
    }
}
