package jedi.window;

import java.util.List;

public interface JediWindowModel {

    public List<JediFile> getJediFiles();

    public void setJediFiles(List<JediFile> jediFiles);

    public void setSelectedElement(int selectedElement);

    public int getSelectedElement();

    public void setFirstElement(int firstElement);

    public int getFirstElement();

}
