package jedi.main;

import jedi.window.JediSimpleWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by golovin on 15.02.14.
 */
public class JediFrame extends JFrame implements KeyListener {

    private JediSimpleWindow window1 = new JediSimpleWindow();
    private JediSimpleWindow window2 = new JediSimpleWindow();
    private boolean window1Active = true;

    public JediFrame() {
        super();
        initUI();
        addKeyListener(this);
    }
    
    private void initUI() {
        //setLayout(new GridLayout(2, 2, 10, 10));

        //getContentPane().add(window1, 0, 0);
        //window2.setBounds(30, 200, window2.getWidth(), window2.getHeight());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        window1.setBounds(0, 0, window1.getWidth(), window1.getHeight());
        window1.setActive(true);
        panel.add(window1);


        window2.setBounds(window1.getWidth(), 0, window2.getWidth(), window2.getHeight());
        panel.add(window2);

        getContentPane().add(panel);

        setTitle("Jedi Commander");
        setSize(650, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            window1Active = !window1Active;
            window1.setActive(window1Active);
            window2.setActive(!window1Active);
            window1.redraw();
            window2.redraw();
        } else {
            if (window1Active) {
                window1.keyPressed(e);
            } else {
                window2.keyPressed(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
