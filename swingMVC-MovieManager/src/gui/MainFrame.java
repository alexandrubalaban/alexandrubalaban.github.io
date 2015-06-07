package gui;

import controller.Controller;
import model.FilmColection;
import model.Movie;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.awt.*;
import java.awt.event.*;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Sunny on 19.03.2015.
 */
public class MainFrame extends JFrame {

    private TextPanel textPanel;
    //private  ToolBar toolbar;
    private FilmPanel filmPanel;
    private TreePanel treePanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private DialogMovie dialogMovie;
    private JSplitPane spliter;
    private  JButton del;

    public MainFrame(){
        super("Hello World");

        setLayout(new BorderLayout());



        //toolbar = new ToolBar();
        filmPanel = new FilmPanel();
        textPanel = new TextPanel();
        treePanel = new TreePanel();
        tablePanel = new TablePanel();
        dialogMovie = new DialogMovie(this);
        del = filmPanel.getDelMovie();

        spliter = new JSplitPane();
        spliter.setLeftComponent(treePanel);
        spliter.setRightComponent(null);
        controller = new Controller();

        tablePanel.setData(controller.getFilm());

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new MovieFileFilter());

        setJMenuBar(createMenuBar());

       /* toolbar.setStringListener(new StringListener() {
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });*/

        dialogMovie.setFormListener(new FormListener() {
            public void formEventOccurred(FormEvent e) {
                controller.addMovie(e);
                tablePanel.refresh();
                treePanel.searchNode(e.getMovieCat(), e.getName());
            }
        });

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("lalalla");
                for (int i = 0; i < controller.getFilm().size(); i++) {
                    if (filmPanel.getMovie().getName().equals(controller.getFilm().get(i).getName())){
                        treePanel.removeNode(filmPanel.getMovie().getMovieCat().toString(), filmPanel.getMovie().getName());
                        controller.getFilm().remove(i);
                    }
                }
            }
        });

        treePanel.getMovieTree().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePanel.getMovieTree().getLastSelectedPathComponent();

                Object categorryObject = node.getUserObject();


                if (categorryObject instanceof CategoryInfo) {
                    spliter.setRightComponent(tablePanel);
                    ArrayList<Movie> categoryMovies = new ArrayList<Movie>();
                    for (int i = 0; i < controller.getFilm().size(); i++) {
                        if (categorryObject.toString().equals(controller.getFilm().get(i).getMovieCat().toString())){
                            categoryMovies.add(controller.getFilm().get(i));
                        }
                    }
                    tablePanel.setData(categoryMovies);
                    tablePanel.refresh();
                } else if (categorryObject instanceof NodeCategorii) {
                    spliter.setRightComponent(tablePanel);
                    tablePanel.setData(controller.getFilm());
                    tablePanel.refresh();
                } else {
                    spliter.setRightComponent(filmPanel);
                    for (int i = 0; i < controller.getFilm().size(); i++) {
                        if (categorryObject.toString().equals(controller.getFilm().get(i).getName())){
                            filmPanel.recieveMovie(controller.getFilm().get(i));
                            filmPanel.appendText();
                        }
                    }

                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing");
                dispose();
                System.gc();
            }
        });

        //add(textPanel, BorderLayout.CENTER);
        add(spliter,BorderLayout.CENTER);
        //add(tablePanel, BorderLayout.CENTER);
        //add(textPanel, BorderLayout.CENTER);
        //add(treePanel, BorderLayout.WEST);



        setSize(700, 450);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem addMovie = new JMenuItem("Add movie");

        fileMenu.add(exportDataItem);
        fileMenu.add(addMovie);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Movie From");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem)e.getSource();

                if (menuItem.isSelected()){
                    spliter.setDividerLocation((int)treePanel.getMinimumSize().getWidth());
                }

                treePanel.setVisible(menuItem.isSelected());
            }
        });



        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        addMovie.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));



        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                    controller.saveToFile(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        addMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogMovie.setVisible(true);
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               int action = JOptionPane.showConfirmDialog(MainFrame.this,
                       "Do you really want to exit?",
                        "Confirm exit", JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    WindowListener [] listeners = getWindowListeners();

                    for (WindowListener listener: listeners){
                        listener.windowClosing(new WindowEvent(MainFrame.this, 0));
                    }
                }

            }
        });

        return menuBar;
    }
}
