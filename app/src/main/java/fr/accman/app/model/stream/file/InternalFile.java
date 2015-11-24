package fr.accman.app.model.stream.file;

import android.content.Context;
import fr.accman.app.AccMan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by nsvir on 19/10/15.
 * n.svirchevsky@gmail.com
 */
public class InternalFile extends File {

    public InternalFile(String fileName) {
        super(AccMan.getAppContext().getFilesDir(), fileName);
    }

    public InternalFile(String path, String fileName) {
        super(AccMan.getAppContext().getDir(path, Context.MODE_PRIVATE), fileName);
    }

    public InternalFile(String dir, int directory) {
        super(dir, directory);
    }
}

