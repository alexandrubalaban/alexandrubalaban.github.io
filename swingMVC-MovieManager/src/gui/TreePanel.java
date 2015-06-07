package gui;

import controller.Controller;
import model.FilmColection;
import model.MovieCategory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Enumeration;

/**
 * Created by Sunny on 25.03.2015.
*/

class CategoryInfo{
    private String name;
    private int id;

    public CategoryInfo(String name, int id){
        this.name = name;
        this.id =id;
    }

    public int getId(){
        return id;
    }
    public String toString(){
        return name;
    }
}

class NodeCategorii{
    private String name;
    private int id;

    public NodeCategorii(String name, int id){
        this.name = name;
        this.id =id;
    }

    public int getId(){
        return id;
    }
    public String toString(){
        return name;
    }
}

public class TreePanel extends JPanel {

    private MovieTableModel tableModel;

    private JTree movieTree;

    private Controller controller;

    public JTree getMovieTree() {
        return movieTree;
    }

    public TreePanel() {

        //colectie.getMovies();

        tableModel = new MovieTableModel();

        controller = new Controller();

        movieTree = new JTree(createTree());

        movieTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        setLayout(new BorderLayout());

        //add(new JSplitPane());

        add(new JScrollPane(movieTree), BorderLayout.CENTER);

        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        setMinimumSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("Ctegorii");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    private DefaultMutableTreeNode createTree() {

        //FilmColection

        DefaultMutableTreeNode topCategorii = new DefaultMutableTreeNode(new NodeCategorii("Categorii",99));


        DefaultMutableTreeNode branchAction = new DefaultMutableTreeNode(new CategoryInfo("Action",0));
        /*DefaultMutableTreeNode movie1 = new DefaultMutableTreeNode(new CategoryInfo("movie1"));
        DefaultMutableTreeNode movie2 = new DefaultMutableTreeNode(new CategoryInfo("movie2", 2));
        DefaultMutableTreeNode movie3 = new DefaultMutableTreeNode(new CategoryInfo("movie3", 3));*/

        DefaultMutableTreeNode branchAdventure = new DefaultMutableTreeNode(new CategoryInfo("Adventure",1));

        DefaultMutableTreeNode branchAnimation = new DefaultMutableTreeNode(new CategoryInfo("Animation",2));

        DefaultMutableTreeNode branchComedy = new DefaultMutableTreeNode(new CategoryInfo("Comedy",3));

        DefaultMutableTreeNode branchCrime = new DefaultMutableTreeNode(new CategoryInfo("Crime",4));

        DefaultMutableTreeNode branchDrama = new DefaultMutableTreeNode(new CategoryInfo("Drama",5));

        DefaultMutableTreeNode branchFantasy = new DefaultMutableTreeNode(new CategoryInfo("Fantasy",6));

        DefaultMutableTreeNode branchHorror = new DefaultMutableTreeNode(new CategoryInfo("Horror",7));

        DefaultMutableTreeNode branchThriller = new DefaultMutableTreeNode(new CategoryInfo("Thriller",8));

        DefaultMutableTreeNode branchOther = new DefaultMutableTreeNode(new CategoryInfo("Other",9));

        topCategorii.add(branchAction);
        topCategorii.add(branchAdventure);
        topCategorii.add(branchAnimation);
        topCategorii.add(branchComedy);
        topCategorii.add(branchCrime);
        topCategorii.add(branchDrama);
        topCategorii.add(branchFantasy);
        topCategorii.add(branchHorror);
        topCategorii.add(branchThriller);
        topCategorii.add(branchOther);

        for (int i = 0; i < controller.getFilm().size(); i++) {

            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Action){
                branchAction.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Adventure){
                branchAdventure.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Animation){
                branchAnimation.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Comedy){
                branchComedy.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Crime){
                branchCrime.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Drama){
                branchDrama.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Fantasy){
                branchFantasy.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Horror){
                branchHorror.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.Thriller){
                branchThriller.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }
            if (controller.getFilm().get(i).getMovieCat() == MovieCategory.other){
                branchOther.add(new DefaultMutableTreeNode (controller.getFilm().get(i).getName()));
            }

        }



        return topCategorii;


    }


    public void searchNode(String nodeStr, String name) {
        DefaultMutableTreeNode node = null;
        Enumeration e = ((DefaultMutableTreeNode)movieTree.getModel().getRoot()).breadthFirstEnumeration();
        while (e.hasMoreElements()) {
          node = (DefaultMutableTreeNode) e.nextElement();
          if (nodeStr.equals(node.getUserObject().toString())) {
              node.add(new DefaultMutableTreeNode (name));
          }
        }
    }
   public void removeNode(String nodeStr, String name) {
        DefaultMutableTreeNode node = null;
        Enumeration e = ((DefaultMutableTreeNode)movieTree.getModel().getRoot()).breadthFirstEnumeration();
        while (e.hasMoreElements()) {
            node = (DefaultMutableTreeNode) e.nextElement();
            if (nodeStr.equals(node.getUserObject().toString())) {
                //System.out.println("Lalalalalkhkjgjgj");
                DefaultMutableTreeNode movieNode = null;
                Enumeration en = node.breadthFirstEnumeration();
                while (en.hasMoreElements()){

                    movieNode = (DefaultMutableTreeNode)en.nextElement();

                    if(name.equals(movieNode.toString())){
                        System.out.println(movieNode.toString());
                        int index = node.getIndex(movieNode);
                        node.remove(index);
                        ((DefaultTreeModel) movieTree.getModel()).reload(node);
                        //((DefaultTreeModel)movieTree.getModel()).removeNodeFromParent(movieNode);
                    }
                }
            }
        }
    }

}

