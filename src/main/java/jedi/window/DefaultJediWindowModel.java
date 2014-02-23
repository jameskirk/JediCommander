package jedi.window;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by golovin on 22.02.14.
 */
public class DefaultJediWindowModel implements JediWindowModel {

    private List<JediFile> jediFiles = new ArrayList<JediFile>();
    private int selectedElement;
    private int firstElement;

    @Override
    public List<JediFile> getJediFiles() {
        return jediFiles;
    }

    @Override
    public void setJediFiles(List<JediFile> jediFiles) {
        this.jediFiles = jediFiles;
    }

    @Override
    public void setSelectedElement(int selectedElement) {
        this.selectedElement = selectedElement;
    }

    @Override
    public int getSelectedElement() {
        return selectedElement;
    }

    @Override
    public void setFirstElement(int firstElement) {
        this.firstElement = firstElement;
    }

    @Override
    public int getFirstElement() {
        return firstElement;
    }
}
