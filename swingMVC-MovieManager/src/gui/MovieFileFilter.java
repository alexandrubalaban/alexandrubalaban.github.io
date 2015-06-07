package gui;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Sunny on 28.03.2015.
 */
public class MovieFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {

        String name = f.getName();

        String extension = Utils.getFileExtension(name);

        if (f.isDirectory()){
            return true;
        }

        if (extension == null){
            return  false;
        }

        if (extension.equals("mkv")){
            return true;
        }

        if (extension.equals("flv")){
            return true;
        }

        if (extension.equals("avi")){
            return true;
        }

        if (extension.equals("rm")){
            return true;
        }

        if (extension.equals("mp4")){
            return true;
        }

        if (extension.equals("mpg")){
            return true;
        }

        if (extension.equals("mov")){
            return true;
        }

        if (extension.equals("wmv")){
            return true;
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Movie files (*.avi, *mkv, *flv, *mp4...)";
    }
}
