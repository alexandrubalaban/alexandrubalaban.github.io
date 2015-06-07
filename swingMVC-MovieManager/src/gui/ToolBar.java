package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Sunny on 20.03.2015.

public class ToolBar extends JPanel implements ActionListener {

    private JButton btn1;
    private  JButton btn2;

    private StringListener textListiner;

    public  ToolBar(){
        setBorder(BorderFactory.createEtchedBorder());
        btn1 = new JButton("Salut");
        btn2 =new JButton("Pa-Pa");

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(btn1);
        add(btn2);
    }

    public void setStringListener(StringListener listener){
        this.textListiner = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked ==  btn1){
            if (textListiner != null){
                textListiner.textEmitted("Salut\n");
            }
            //textPanel.appendText("Salut\n");
        }
        else if (clicked == btn2)
            if (textListiner != null){
                textListiner.textEmitted("Pa-Pa\n");
        }
    }
}
*/