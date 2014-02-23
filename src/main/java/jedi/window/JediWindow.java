package jedi.window;

import com.sun.java.swing.plaf.windows.resources.windows;
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
public class JediWindow extends JComponent {

    private static final String uiClassID = "JediWindowUI";

    protected JediWindowModel model;
    public int HEIGHT = 283;
    public int WIDTH = 280;
    public int ELEMENT_HEIGHT = 18;
    public int MAX_FILES = HEIGHT / ELEMENT_HEIGHT;
    protected FilesCollectorStrategy strategy;
    protected String currentPath = "C:\\Dev\\";
    protected boolean isActive = true;

    public JediWindow(FilesCollectorStrategy strategy) {
        setSize(WIDTH, HEIGHT);
        model = new DefaultJediWindowModel();
        this.strategy = strategy;
        strategy.initJediFiles(currentPath, model.getJediFiles());

        updateUI();
    }

    public void setUI(JediWindowUI ui) {
        super.setUI(ui);
    }

    public JediWindowUI getUI() {
        return (JediWindowUI) ui;
    }

    public void updateUI() {
        if (UIManager.get(getUIClassID()) != null) {
            setUI((JediWindowUI) UIManager.getUI(this));
        } else {
            setUI(new JediWindowUI());
        }
    }

    public String getUIClassID() {
        return uiClassID;
    }

    public JediWindowModel getModel() {
        return this.model;
    }

    public String getCurrentPath() {
        return currentPath;
    }
}
