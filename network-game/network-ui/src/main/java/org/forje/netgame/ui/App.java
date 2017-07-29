package org.forje.netgame.ui;

import javax.swing.*;

/**
 * Created by Brian on 8/6/15.
 */
public class App {

    public static void main(String[] args) {


        try {
            String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(lookAndFeelClassName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Frame frame = new Frame();
        frame.repaint();
        frame.setVisible(true);

    }

}
