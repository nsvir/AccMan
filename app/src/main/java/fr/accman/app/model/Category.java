package fr.accman.app.model;
import fr.accman.app.model.stream.Editor;

import java.util.ArrayList;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Category
{

	List<Entry> entries;
	private String name;
    private Editor editor;

	public Category(String name, Editor editor) {
		this.name = name;
        this.editor = editor;
        this.entries = new ArrayList<Entry>();
	}

	public List<Category> getCategories() {
		return null;
	}

	public String getName() {
		return this.name;
	}

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

	public void addEntryAndSave(Entry entry) {
        this.addEntry(entry);
        editor.addEntry(this, entry);
	}

	public int getBalance() {
		int result = 0;
		for (Entry each: this.entries) {
			result += each.value;
		}
		return result;
	}

	@Override
	public String toString() {
		return this.getName() + ": " + getBalance();
	}
}

