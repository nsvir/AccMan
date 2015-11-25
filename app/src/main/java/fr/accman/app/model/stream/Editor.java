package fr.accman.app.model.stream;


import fr.accman.app.model.Category;
import fr.accman.app.model.Entry;
import fr.accman.app.model.stream.file.ExternalFile;
import fr.accman.app.model.stream.file.File;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public abstract class Editor implements Serializable
{
	protected File file;
	protected Iterator<String> iterator;

	public Editor(java.io.File directory, String fileName) {
		this.file = new File(directory, fileName);
		if (!this.file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected Editor(File file) {this.file = file;}

	public abstract List<Category> getCategories();

	public abstract void setCategories(List<Category> categories);

	public abstract void addEntry(Category category, Entry entry);

	protected String readLine() {
		if (iterator == null) {
			this.iterator = file.getIterator();
		}
		if (iterator.hasNext()) {
			return this.iterator.next();
		} else {
			this.iterator = null;
			return null;
		}
	}

	public void addLine(String line) {
		this.file.addLine(line + "\n");
	}
}

