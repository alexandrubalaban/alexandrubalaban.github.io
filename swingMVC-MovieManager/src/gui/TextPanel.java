package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Sunny on 20.03.2015.
 * */

public class TextPanel extends JPanel {

    private  JTextArea textArea;

    public TextPanel(){
        textArea = new JTextArea();

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    public void  appendText(String text){
        textArea.append(text);
    }
}
