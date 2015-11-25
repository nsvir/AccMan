package fr.accman.app.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import fr.accman.app.AccMan;
import fr.accman.app.R;
import fr.accman.app.controller.AddEntry;
import fr.accman.app.model.Account;
import fr.accman.app.model.User;

import java.util.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AddEntry.Callback {

    protected ArrayList<Account> accounts;
    protected View view;
    private ExpandableListAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.account_fragment, container, false);
        User.current.addAccount("first");
        this.accounts = User.current.getAccounts();
        Account currentAccount = this.accounts.get(0);
        this.adapter = new ExpandableListAdapter(this.accounts);
        this.adapter.notifyDataSetChanged();
        ((ExpandableListView)this.view.findViewById(R.id.expandable_list_view)).setAdapter(this.adapter);
        view.findViewById(R.id.button).setOnClickListener(new AddEntry(currentAccount, this));
        return view;
    }


    @Override
    public void addEntryCallback() {
        this.updateExpandableListView();
    }

    private void updateExpandableListView() {
        this.adapter.notifyDataSetChanged();
    }

    private class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final ArrayList<Account> accounts;

        public ExpandableListAdapter(ArrayList<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public int getGroupCount() {
            return this.accounts.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return this.accounts.get(i).getCategories().size();
        }

        @Override
        public Object getGroup(int i) {
            return this.accounts.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return this.accounts.get(i).getCategories().get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                view = AccMan.inflate(R.layout.account_group);
            }
            textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(this.accounts.get(i).getName());
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView;
            if (view == null) {
                view = AccMan.inflate(R.layout.account_child);
            }
            textView = (TextView) view.findViewById(R.id.text_view);
            textView.setText(this.accounts.get(i).getCategories().get(i1).toString());
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }
}
