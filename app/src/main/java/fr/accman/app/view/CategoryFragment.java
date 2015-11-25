package fr.accman.app.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.accman.app.AccMan;
import fr.accman.app.R;
import fr.accman.app.model.Category;
import fr.accman.app.model.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nanosvir on 25 Nov 15.
 */
public class CategoryFragment extends Fragment {

    public static final String CATEGORY_KEY = "Category";
    public static final String TAG = "CategoryFragment";
    private Category category;
    private CategoryAdapter adapter;
    private Bundle bundle = new Bundle();

    public interface Callback {
        void setCategory(Category category);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.bundle = savedInstanceState;
            this.category = (Category) savedInstanceState.getSerializable(CATEGORY_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putAll(this.bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        this.adapter = new CategoryAdapter(this.category);
        listView.setAdapter(this.adapter);
        return view;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.bundle.putSerializable(CATEGORY_KEY, this.category);
        updateView();
    }

    private void updateView() {
        if (this.adapter != null) {
            this.adapter.setCategory(this.category);
            this.adapter.notifyDataSetChanged();
        }
    }

    private class CategoryAdapter extends BaseAdapter {

        private Category category;
        private ArrayList<Entry> entries;

        public CategoryAdapter(Category category) {
            this.category = category;
            this.entries = category.getEntries();
        }

        public void setCategory(Category category) {
            this.category = category;
            this.entries = category.getEntries();
        }

        @Override
        public int getCount() {
            return this.entries.size();
        }

        @Override
        public Object getItem(int i) {
            return this.entries.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = AccMan.inflate(R.layout.category_adapter);
            }
            TextView date = (TextView) view.findViewById(R.id.date);
            TextView amount = (TextView) view.findViewById(R.id.amount);
            date.setText(this.entries.get(i).getDate());
            amount.setText(this.entries.get(i).getAmount());
            return view;
        }
    }
}
