package gui;

import model.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Sunny on 28.03.2015.
 */
public class TablePanel extends JPanel {

    private  JTable table;
    private MovieTableModel tableModel;

    public TablePanel(){

        tableModel = new MovieTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Movie> db){
        tableModel.setData(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }
}

