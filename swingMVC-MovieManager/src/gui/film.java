package gui;

import javax.swing.*;

/**
 * Created by Sunny on 19.03.2015.
 */
public class film {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });

    }
}
