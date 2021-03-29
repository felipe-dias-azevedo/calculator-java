package com.felipe;

import javax.swing.*;
import java.awt.*;

public class RunApp {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
        catch (Exception e) {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        JFrame frame = new JFrame("Calculator");
        App app = new App();
        frame.setContentPane(app.panelMain);
        frame.setPreferredSize(new Dimension(360, 420));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(app);
    }
}
