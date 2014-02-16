package jedi.window;

import jedi.main.DefaultFilesCollectorStrategy;
import jedi.main.FilesCollectorStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

/**
 * Created by golovin on 15.02.14.
 */
public class JediFileList extends JComponent implements KeyListener{
    public static int HEIGHT = 283;
    public static int WIDTH = 280;
    public int MAX_FILES = HEIGHT / JediFile.HEIGHT;

    protected List<JediFile> jediFiles = new ArrayList<JediFile>();
    protected FilesCollectorStrategy strategy = new DefaultFilesCollectorStrategy();

    protected int selectedElement;
    private int firstIndex;
    protected String currentPath = "C:\\Dev\\";
    protected boolean isActive;

    public JediFileList() {
        setSize(WIDTH, HEIGHT);
        addKeyListener(this);
        strategy.initJediFiles(currentPath, jediFiles);
    }

    protected void paintComponent(Graphics g) {

        setDoubleBuffered(true);
        setSize(WIDTH, HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g2.fillRect(0,0,WIDTH, HEIGHT);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (isActive) {
            g.setColor(Color.LIGHT_GRAY);
            g2.fillRect(0, (selectedElement - firstIndex)  * JediFile.HEIGHT + 2, JediFile.WIDTH, JediFile.HEIGHT +2);
        }



        for (int i = firstIndex; i < jediFiles.size(); i++) {
            if (!jediFiles.get(i).isFile()) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.BLACK);
            }

            g2.setFont(new Font("Consolas", Font.PLAIN , JediFile.HEIGHT - 2));
            g2.drawString(jediFiles.get(i).getName(), 30, (JediFile.HEIGHT) * (i - firstIndex + 1));
            g2.drawImage(jediFiles.get(i).getIcon(), 0, JediFile.HEIGHT * (i - firstIndex), null);
        }
        g.setColor(Color.GRAY);
        g2.draw(new Rectangle(0, 0, getWidth() - 1, getHeight() - 1));
        g.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (jediFiles.size() > selectedElement + 1) {
                selectedElement++;
            }
            if (selectedElement > firstIndex + MAX_FILES -1) {
                firstIndex++;

            }
            paint(getGraphics());

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (selectedElement > 0 ) {
                selectedElement--;
                paint(getGraphics());
            }
            if (selectedElement < firstIndex) {
                firstIndex--;
            }
            paint(getGraphics());

        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (!jediFiles.get(selectedElement).isFile()) {
                currentPath = jediFiles.get(selectedElement).getPath();
            }
            selectedElement = 0;
            firstIndex = 0;
            strategy.initJediFiles(currentPath, jediFiles);
            paint(getGraphics());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
