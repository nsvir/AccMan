package fr.accman.app.model.stream;


import fr.accman.app.model.Category;
import fr.accman.app.model.Entry;
import fr.accman.app.model.stream.file.File;
import fr.accman.app.model.utils.MyDateFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class CSV extends Editor
{
    private static final String EXTENSION = ".csv";
    private static final java.lang.String SEPARATOR = ",";

    public CSV(java.io.File directory, String fileName){
		super(directory, fileName + EXTENSION);
	}

    protected void addCategories(List<Category> categories) {
        categories.add(new Category("none", this));
        categories.add(new Category("food", this));
        categories.add(new Category("business", this));
        categories.add(new Category("entertainment", this));
    }

	@Override
	public List<Category> getCategories() {
		List<Category> result = new ArrayList<Category>();
        this.addCategories(result);
        String line;
        while ((line = readLine()) != null) {
            final String[] row = line.split(CSV.SEPARATOR);
            if (row.length >= 3) {
                Category concernedCategory = getConcernedCategory(result, row[2]);
                concernedCategory.addEntry(Entry.newEntry(MyDateFormat.getTime(row[0]), row[1]));
            }
        }
		return result;
	}

    @Override
    public void setCategories(List<Category> categories) {

    }

    protected static Category getConcernedCategory(List<Category> result, final String categoryName) {
        return CollectionUtils.find(result, new Predicate<Category>() {
            @Override
            public boolean evaluate(Category category) {
                return category.getName().equals(categoryName);
            }
        });
    }

	public void addEntry(Category category, Entry entry) {
        this.addLine(this.createCSV(entry.date, entry.value, category.getName()));
	}

    private String createCSV(long date, double value, String name) {
        return formatCSV(MyDateFormat.getDate(date), String.valueOf(value), name);
    }

    protected static String formatCSV(String... elements) {
        String result = "";
        boolean isFirst = true;
        for (String each: elements) {
            if (isFirst) {
                isFirst = false;
            } else {
                result += ",";
            }
            result += each;
        }
        return result;
    }

    public static void createCSV(java.io.File directory, String filename) {
        try {
            new File(directory,  filename + EXTENSION).createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

