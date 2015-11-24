package fr.accman.app.model;


import fr.accman.app.model.stream.Editor;

import java.util.List;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ParentCategory extends Category
{
	public Set<Category> categories;

	public ParentCategory(String name, Editor editor) {
		super(name, editor);
	}

	public List<Category> getCategories() {
		// TODO implement me
		return null;	
	}
	
}

