package fr.accman.app.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import fr.accman.app.R;
import fr.accman.app.controller.AddEntry;
import fr.accman.app.model.Account;
import fr.accman.app.model.Category;
import fr.accman.app.model.User;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements AddEntry.Callback  {

    public CategoryFragmentCallback categoryFragmentCallback = new CategoryFragmentCallback();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new AddEntry(User.current.getAccounts().get(0), this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addEntryCallback() {
        getAccountFragment().updateExpandableListView();
    }

    protected AccountFragment getAccountFragment() {
        return (AccountFragment) getSupportFragmentManager().findFragmentByTag(AccountFragment.TAG);
    }

    protected CategoryFragment getCategoryFragment() {
        CategoryFragment categoryFragment = (CategoryFragment) getSupportFragmentManager().findFragmentByTag(CategoryFragment.TAG);
        if (categoryFragment == null) {
            categoryFragment = new CategoryFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.view_group, categoryFragment, CategoryFragment.TAG);
            fragmentTransaction.commit();
        }
        return categoryFragment;
    }

    public class CategoryFragmentCallback implements CategoryFragment.Callback {

        @Override
        public void setCategory(Category category) {
            getCategoryFragment().setCategory(category);
        }
    }
}
