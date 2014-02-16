package jedi.window;

import jedi.main.FilesCollectorStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by golovin on 16.02.14.
 */
public class JediSimpleWindow extends JPanel implements KeyListener {

    public static int HEIGHT = 300;
    public static int WIDTH = 300;

    private JediFileList window = new JediFileList();
    private JLabel label = new JLabel();
    private JScrollPane scroll = new JScrollPane();
    private JScrollBar bar = scroll.getVerticalScrollBar();


    public JediSimpleWindow() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        label.setFont(new Font("SansSerif", Font.PLAIN , JediFile.HEIGHT));
        label.setText(window.currentPath);
        add(window, BorderLayout.WEST);
        add(label, BorderLayout.NORTH);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll, BorderLayout.CENTER);
        initScroll();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        window.keyPressed(e);
        label.setText(window.currentPath);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            initScroll();
        } else {
            bar.setValue(window.selectedElement);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setStrategy(FilesCollectorStrategy strategy) {
        window.strategy = strategy;
    }

    public JediFile getCurrentFile() {
        return window.jediFiles.get(window.selectedElement);
    }

    public void setActive(boolean isActive) {
        window.isActive = isActive;
    }

    public void redraw() {
        paint(getGraphics());
        window.paint(window.getGraphics());
    }

    private void initScroll() {
        bar.setMinimum(0);
        bar.setMaximum(window.jediFiles.size()+window.MAX_FILES - 1);
        bar.setValue(window.selectedElement);
        bar.setVisibleAmount(window.MAX_FILES);

    }
}
