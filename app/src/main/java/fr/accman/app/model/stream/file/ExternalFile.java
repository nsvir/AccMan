package fr.accman.app.model.stream.file;

import android.content.Context;
import android.os.Environment;
import fr.accman.app.AccMan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by nsvir on 19/10/15.
 * n.svirchevsky@gmail.com
 */
public class ExternalFile extends File {

    public ExternalFile(String fileName) {
        super(AccMan.getAppContext().getExternalFilesDir(null), fileName);
    }

}