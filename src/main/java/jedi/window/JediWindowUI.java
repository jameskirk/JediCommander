package jedi.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.*;

public class JediWindowUI extends ComponentUI{

	protected JediWindow window;

    protected KeyListener keyListener;

    public static ComponentUI createUI(JComponent c) {
		return new JediWindowUI();
	}

	public void installUI(JComponent c) {
        System.out.println("install UI");
		window = (JediWindow) c;
		installDefaults();
		installComponents();
		installListeners();

		c.setLayout(new FlowLayout());
		c.setBorder(new EmptyBorder(1, 1, 1, 1));
	}

	public void uninstallUI(JComponent c) {
		c.setLayout(null);
		uninstallListeners();
		uninstallComponents();
		uninstallDefaults();

		this.window = null;
	}

	public void installDefaults() {

	}

	public void installComponents() {


	}

	public void installListeners() {
        keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                JediWindowModel model = window.model;
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (model.getJediFiles().size() > model.getSelectedElement() + 1) {
                        model.setSelectedElement(model.getSelectedElement() + 1);
                    }
                    if (model.getSelectedElement() > model.getFirstElement() + window.MAX_FILES -1) {
                        model.setFirstElement(model.getFirstElement()+1);

                    }
                }

                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (model.getSelectedElement() > 0 ) {
                        model.setSelectedElement(model.getSelectedElement() - 1);
                    }
                    if (model.getSelectedElement() < model.getFirstElement()) {
                        model.setFirstElement(model.getFirstElement() - 1);
                    }
                }

                else if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (!model.getJediFiles().get(model.getSelectedElement()).isFile()) {
                        window.currentPath = model.getJediFiles().get(model.getSelectedElement()).getPath();
                    }
                    model.setSelectedElement(0);
                    model.setFirstElement(0);
                    window.strategy.initJediFiles(window.currentPath, model.getJediFiles());
                }
                window.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        window.addKeyListener(keyListener);

	}

	public void uninstallDefaults() {

	}

	public void uninstallComponents() {

	}

	public void uninstallListeners() {

	}

	@Override
	public void paint(Graphics g, JComponent c) {
		super.paint(g, c);
		this.paintWindow(g);
	}

	protected void paintWindow(Graphics g) {
        window.setDoubleBuffered(true);
        window.setSize(window.WIDTH, window.HEIGHT);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g2.fillRect(0,0,window.WIDTH, window.HEIGHT);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (window.isActive) {
            g.setColor(Color.LIGHT_GRAY);
            g2.fillRect(0, (window.model.getSelectedElement() - window.model.getFirstElement())  * window.ELEMENT_HEIGHT + 2, window.WIDTH, window.ELEMENT_HEIGHT + 2);
        }



        for (int i = window.model.getFirstElement(); i < window.model.getJediFiles().size(); i++) {
            if (!window.model.getJediFiles().get(i).isFile()) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.BLACK);
            }

            g2.setFont(new Font("Consolas", Font.PLAIN , window.ELEMENT_HEIGHT - 2));
            g2.drawString(window.model.getJediFiles().get(i).getName(), 30, (window.ELEMENT_HEIGHT) * (i - window.model.getFirstElement() + 1));
            g2.drawImage(window.model.getJediFiles().get(i).getIcon(), 0, window.ELEMENT_HEIGHT * (i - window.model.getFirstElement()), null);
        }
        g.setColor(Color.GRAY);
        g2.draw(new Rectangle(0, 0, window.getWidth() - 1, window.getHeight() - 1));
        g.dispose();
	}





}
