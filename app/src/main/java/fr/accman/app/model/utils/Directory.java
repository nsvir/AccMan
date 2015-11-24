package fr.accman.app.model.utils;

import fr.accman.app.AccMan;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by nanosvir on 24 Nov 15.
 */
public class Directory {

    public static File getRootDir() {
        return AccMan.getAppContext().getExternalFilesDir(null);
    }

    public static Collection<File> getDirectories(File dir) {
        Collection<File> result = new ArrayList<File>();
        for (File each : dir.listFiles()) {
            if (each.isDirectory()) {
                result.add(each);
            }
        }
        return result;
    }

    public static ArrayList<String> getDirectoriesName(File fileDir) {
        Collection<File> files = getDirectories(fileDir);
        ArrayList<String> result = new ArrayList<String>();
        for (File each: files) {
            result.add(each.getName());
        }
        return result;
    }

    public static void addDirectory(File fileDir, String username) {
        new File(fileDir, username).mkdir();
    }

    public static List<String> getRootDirectoriesName() {
        return getDirectoriesName(getRootDir());
    }
}
