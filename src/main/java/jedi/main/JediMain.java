package jedi.main;

import jedi.window.JediWindowScroll;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by golovin on 15.02.14.
 */
public class JediMain extends JFrame implements KeyListener {

    private JediWindowScroll window1 = new JediWindowScroll(new DefaultFilesCollectorStrategy());
    private JediWindowScroll window2 = new JediWindowScroll(new DefaultFilesCollectorStrategy());
    private JediButtonsPanel buttonsPanel = new JediButtonsPanel();
    private boolean window1Active = true;

    public JediMain() {
        super();
        initUI();
        addKeyListener(this);
    }
    
    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        window1.setBounds(0, 0, window1.getWidth(), window1.getHeight());
        panel.add(window1);


        window2.setBounds(window1.getWidth(), 0, window2.getWidth(), window2.getHeight());
        panel.add(window2);

        buttonsPanel.setBounds(0, window1.getHeight(), 650, 50);
        panel.add(buttonsPanel);


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

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JediMain ex = new JediMain();
            }
        });
    }
}
