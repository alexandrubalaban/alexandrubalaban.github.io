package gui;

import controller.Controller;
import model.FilmColection;
import model.Movie;
import model.MovieCategory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by Sunny on 29.03.2015.
 */
public class DialogMovie extends JDialog {

    private  JLabel nameLabel;
    private  JTextField nameField;
    private  JLabel dataLabel;
    private  JTextField dataField;
    private  JLabel idImdbLabel;
    private  JTextField idImdbField;
    private  JLabel notaImdbLabel;
    private  JTextField notaImdbField;
    private FormListener formListener;
    private  JLabel regizorLabel;
    private  JTextField regizorField;
    private  JLabel actorLabel;
    private  JTextField actorField;
    private JComboBox empCombo;
    private JButton addMovieButton;
    private JButton browseMovieButton;
    private  JTextField browseMovieField;
    private JButton closeButton;
    private JFileChooser adresa;

    private Movie movie;
    private Controller controller;

    public  DialogMovie(JFrame parent){
        super(parent, "addMovie", false);


        setPreferredSize(new Dimension(500,600));
        setMinimumSize(new Dimension(300, 400));
        setMaximumSize(new Dimension(720,600));

        controller = new Controller();

        nameLabel = new JLabel("Name movie: ");
        nameField = new JTextField(10);
        dataLabel = new JLabel("Data lansarii: ");
        dataField = new JTextField(10);
        idImdbLabel = new JLabel("ID IMDB: ");
        idImdbField = new JTextField(10);
        notaImdbLabel = new JLabel("nota IMDB: ");
        notaImdbField = new JTextField(10);
        regizorLabel = new JLabel("Regizor: ");
        regizorField = new JTextField(12);
        actorLabel = new JLabel("Actori: ");
        actorField = new JTextField(12);
        empCombo = new JComboBox();
        addMovieButton = new JButton("addMovie");
        browseMovieButton = new JButton("Browse");
        browseMovieField = new JTextField(12);
        closeButton = new JButton("Close");


        adresa = new JFileChooser();
        adresa.addChoosableFileFilter(new MovieFileFilter());

        //SET up mnemonics
        addMovieButton.setMnemonic(KeyEvent.VK_A);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        //Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Action");
        empModel.addElement("Adventure");
        empModel.addElement("Animation");
        empModel.addElement("Comedy");
        empModel.addElement("Crime");
        empModel.addElement("Drama");
        empModel.addElement("Fantasy");
        empModel.addElement("Horror");
        empModel.addElement("Thriller");

        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String empCat = (String)empCombo.getSelectedItem();
                //MovieCategory empCat1 = (MovieCategory) empCombo.getSelectedItem();
                String data = dataField.getText();
                String idImdb = idImdbField.getText();
                String raitingImdb = notaImdbField.getText();
                String regizor = regizorField.getText();
                String actor = actorField.getText();
                String path = browseMovieField.getText();

                //movie = new Movie(name, empCat1, data, idImdb, raitingImdb, regizor, actor);

                //controller.addMovie(movie);



                FormEvent ev = new FormEvent(this, name,
                        empCat, data, idImdb, raitingImdb, regizor, actor, path);

                controller.addMovie(ev);

                if (formListener != null){
                    formListener.formEventOccurred(ev);
                }
                setVisible(false);
            }
        });

        browseMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adresa.showOpenDialog(DialogMovie.this) == JFileChooser.APPROVE_OPTION) {
                    browseMovieField.setText(adresa.getSelectedFile().toString());
                    System.out.println(adresa.getSelectedFile());
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add movie");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5 );
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public  void layoutComponents(){
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
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField,gc);

        ////////////Next ROW/////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Categorie "), gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        ///////////NEXT ROW////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(dataLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dataField,gc);

        ///////////NEXT ROW////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(idImdbLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(idImdbField,gc);

        ///////////NEXT ROW////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(notaImdbLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(notaImdbField,gc);

        ///////////NEXT ROW////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(regizorLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(regizorField,gc);

        ///////////NEXT ROW////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(actorLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(actorField,gc);

        ////////////Next ROW/////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(browseMovieButton, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(browseMovieField,gc);

        ////////////Next ROW/////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(addMovieButton, gc);

        ////////////Next ROW/////////////

        //gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(closeButton, gc);
    }

    public void setFormListener (FormListener listener){
        this.formListener = listener;
    }

}
