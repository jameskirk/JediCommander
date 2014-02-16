package jedi.main;

import jedi.main.FilesCollectorStrategy;
import jedi.window.JediFile;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by golovin on 16.02.14.
 */
public class DefaultFilesCollectorStrategy implements FilesCollectorStrategy {
    @Override
    public void initJediFiles(String path, List<JediFile> jediFiles) {
        try {
            File file = new File(path);
            File[] files = file.listFiles();
            jediFiles.clear();

            for (File f: files) {
                JediFile jediFile = new JediFile();
                jediFile.setName(f.getName());
                jediFile.setPath(f.getCanonicalPath());

                Icon ico = FileSystemView.getFileSystemView().getSystemIcon(f);
                Image image = ((ImageIcon) ico).getImage();
                jediFile.setIcon(image);

                jediFile.setFile(f.isFile());
                if (Files.isSymbolicLink(Paths.get(f.getCanonicalPath()))
                        || !Files.isReadable(Paths.get(f.getCanonicalPath()))) {
                    jediFile.setFile(true);
                }
                jediFiles.add(jediFile);

            }
            Collections.sort(jediFiles);

            JediFile parent = new JediFile();

            parent.setName(File.separator + "..");
            parent.setPath(file.getCanonicalPath() + File.separator + "..");
            parent.setFile(false);
            jediFiles.set(0, parent);
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
