package gui;

import controller.Controller;
import model.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sunny on 30.03.2015.
 */

public class FilmPanel extends JPanel {
    private  JTextArea textArea;
    private JButton delMovie;
    private JButton playMovie;
    private JButton editMovie;
    private Movie movie;

    public JButton getDelMovie() {
        return delMovie;
    }

    public FilmPanel(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        setMinimumSize(dim);

        delMovie = new JButton("delMovie");
        playMovie = new JButton("playmovie");
        editMovie = new JButton("editMovie");





        playMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File(movie.getPath()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        layoutComponents();
    }

    public Movie getMovie() {
        return movie;
    }

    public void recieveMovie(Movie m){
        this.movie = m;
    }
    public  void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();


        ///////////FIRST ROW////////////////

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(textArea, gc);

        ////////////Next ROW/////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(playMovie,gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.CENTER;
        add(editMovie, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(delMovie, gc);
    }


    public void  appendText(){
        textArea.setText("");
        String text =  "MOVIE NAME  : "+movie.getName()+"\n"+
                "CATEGORY    : "+movie.getMovieCat()+"\n"+
                "RELEASE DATE: "+movie.getData()+"\n"+
                "ID IMDB     : "+movie.getIdImdb()+"\n"+
                "RATING      : "+movie.getRaitingImdb()+"\n"+
                "REGIZOR     : "+movie.getRegizor()+"\n"+
                "ACTORS      : "+movie.getActor()+"\n";
        textArea.append(text);
    }
}
