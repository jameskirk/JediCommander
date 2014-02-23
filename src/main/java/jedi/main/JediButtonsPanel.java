package jedi.main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by golovin on 23.02.14.
 */
public class JediButtonsPanel extends JPanel {

    private JButton copyButton;

    public JediButtonsPanel() {
        copyButton = new JButton("F5 copy");
        copyButton.setFocusable(false);

        setLayout(new FlowLayout());
        add(copyButton);
    }
}
