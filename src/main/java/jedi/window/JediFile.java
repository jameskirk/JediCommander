package jedi.window;

import java.awt.*;

/**
 * Created by golovin on 15.02.14.
 */
public class JediFile  implements Comparable{
    public static int HEIGHT = 18;
    public static int WIDTH = JediFileList.WIDTH;

    private String name;
    private String path;
    private Image icon;
    private boolean isFile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean isFile) {
        this.isFile = isFile;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    @Override
    public int compareTo(Object o) {
        JediFile f = (JediFile) o;
        if (this.isFile() && !f.isFile()) {
            return 1;
        } else if (!this.isFile() && f.isFile()) {
            return -1;
        }
        return this.getName().compareTo(f.getName());
    }
}
