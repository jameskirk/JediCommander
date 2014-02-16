package jedi.main;

import jedi.window.JediFile;

import java.util.List;

/**
 * Created by golovin on 16.02.14.
 */
public interface FilesCollectorStrategy {

    public void initJediFiles(String path, List<JediFile> jediFiles);
}
