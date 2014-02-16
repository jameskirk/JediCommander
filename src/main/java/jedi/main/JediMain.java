package jedi.main;

import javax.swing.*;

/**
 * Created by golovin on 15.02.14.
 */
public class JediMain {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JediFrame ex = new JediFrame();
            }
        });
    }
}
