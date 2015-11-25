package fr.accman.app.model.stream.file;

import android.content.Context;
import fr.accman.app.AccMan;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * Created by nsvir on 05/10/15.
 * n.svirchevsky@gmail.com
 */
public class File extends java.io.File {

    public static final Charset charset = Charsets.UTF_8;

    public File(java.io.File dir, String name) {
        super(dir, name);
    }

    public File(String path) {
        super(path);
    }

    public File(String dirPath, String name) {
        super(AccMan.getAppContext().getDir(dirPath, Context.MODE_PRIVATE), name);
    }

    public File(String dirPath, int type) {
        super(AccMan.getAppContext().getDir(dirPath, Context.MODE_PRIVATE).getAbsolutePath());

    }

    public File(URI uri) {
        super(uri);
    }

    public void write(String string) throws FileNotFoundException {
        FileOutputStream outputStream = null;
        try {
            outputStream = openWrite();
            writeString(string, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    public String read() throws FileNotFoundException {
        FileInputStream fis = null;
        String result = "";
        try {
            fis = openRead();
            result = readString(fis);
        } finally {
            IOUtils.closeQuietly(fis);
        }
        return result;
    }


    public FileIterator getIterator() {
        try {
            return new FileIterator(readString(openRead()).split("\n"));
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    private void writeString(String string, FileOutputStream outputStream) throws IOException {
        IOUtils.write(string.getBytes(this.charset), outputStream);
    }

    protected String readString(FileInputStream fis) {
        String result = null;
        try {
            byte[] bytes = IOUtils.toByteArray(fis);
            result = new String(bytes, this.charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected FileInputStream openRead() throws FileNotFoundException {
        return new FileInputStream(this);
    }

    protected FileOutputStream openWrite() throws FileNotFoundException {
        return new FileOutputStream(this);
    }

    protected FileOutputStream openAppendWrite() throws FileNotFoundException {
        return new FileOutputStream(this, true);
    }

    public void addLine(String line) {
        try {
            FileOutputStream fos = openAppendWrite();
            writeString(line, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public class FileIterator implements Iterator<String> {

        protected String[] content;
        protected int index;

        protected FileIterator(String[] content) {
            this.content = content;
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return this.content.length > index;
        }

        @Override
        public String next() {
            return this.content[this.index++];
        }

        @Override
        public void remove() {

        }
    }
}
