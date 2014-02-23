package jedi.window;

import jedi.main.FilesCollectorStrategy;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by golovin on 22.02.14.
 */
public class JediWindowScroll extends JPanel {

    private JediWindow window;
    protected JScrollPane scroll;
    private JLabel pathLabel;

    public JediWindowScroll(FilesCollectorStrategy strategy) {
        window = new JediWindow(strategy);
        scroll = new JScrollPane();
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pathLabel = new JLabel(window.getCurrentPath());
        pathLabel.setFont(new Font("Consolas", Font.PLAIN , window.ELEMENT_HEIGHT - 2));

        setSize(window.WIDTH + 15, window.HEIGHT + 10);
        setLayout(new BorderLayout());
        add(window, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
        add(pathLabel, BorderLayout.NORTH);

        initScroll();
    }

    private void initScroll() {
        JScrollBar bar = scroll.getVerticalScrollBar();
        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int val = e.getValue();
                if (val <= window.model.getJediFiles().size()) {
                    window.model.setFirstElement(val);
                }
                window.repaint();

            }
        };
        BoundedRangeModel brModel = new DefaultBoundedRangeModel() {
            @Override
            public int getExtent() {
                return window.MAX_FILES;
            }
        };
        bar.setModel(brModel);

        scroll.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
    }

    public void keyPressed(KeyEvent e) {
        window.getKeyListeners()[0].keyPressed(e);

        int max = window.model.getJediFiles().size() ;
        max = max > 0 ? max : 0;
        scroll.getVerticalScrollBar().setMaximum(max);

        int val = window.model.getFirstElement();
        val = val > 0 ? val : 0;
        scroll.getVerticalScrollBar().setValue(val);

        pathLabel.setText(window.getCurrentPath());
    }

}
