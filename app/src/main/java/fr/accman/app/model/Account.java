package fr.accman.app.model;
import fr.accman.app.model.stream.CSV;
import fr.accman.app.model.stream.Editor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Account
{
    public Editor editor;
	public List<Category> categories;
	private String name;

	public Account(User user, File name) {
		String filename = getNoExtensionFilename(name);
        this.name = filename;
		this.editor = new CSV(user.getDirectory(), filename);
        this.categories = this.editor.getCategories();
	}

    private String getNoExtensionFilename(File name) {
        String filename = name.getName();
        int index = filename.indexOf(".");
        if (index <= 0) {
            return filename;
        }
        return filename.substring(0, index);
    }

    @Override
	public String toString() {
		return name;
	}

    protected static void createAccount(User user, String name) {
        CSV.createCSV(user.getDirectory(), name);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }
}

