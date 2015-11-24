package fr.accman.app.model.stream;

import fr.accman.app.model.Category;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by nanosvir on 24 Nov 15.
 */
public class CSVTest {

    @Test
    public void testFormatCSV() throws Exception {
        String[] input = {"nicolas", "svirchevky", "22ans"};
        String expected = input[0] + "," + input[1] + "," + input[2];
        assertEquals(expected, CSV.formatCSV(input));
    }

    @Test
    public void testGetConcernedCategorySuccess() throws Exception {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category("none"));
        assertEquals(categories.get(0), CSV.getConcernedCategory(categories, "none"));
    }

    @Test
    public void testGetConcernedCategoryFailed() throws Exception {
        ArrayList<Category> categories = new ArrayList<Category>();
        categories.add(new Category("notnone"));
        assertEquals(null, CSV.getConcernedCategory(categories, "none"));
    }
}