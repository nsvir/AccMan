package fr.accman.app.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.accman.app.R;
import fr.accman.app.controller.AddEntry;
import fr.accman.app.model.Account;
import fr.accman.app.model.Category;
import fr.accman.app.model.User;

import java.util.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AddEntry.Callback {

    protected ArrayList<Account> accounts;
    protected View view;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_main, container, false);
        User.current.addAccount("first");
        this.accounts = User.current.getAccounts();
        Account currentAccount = this.accounts.get(0);
        this.updateContent();
        view.findViewById(R.id.button).setOnClickListener(new AddEntry(currentAccount, this));
        return view;
    }


    @Override
    public void addEntryCallback() {
        this.updateContent();
    }

    private void updateContent() {
        String content = "Account: " + this.accounts.get(0).toString() + "\n";
        for (Category each : this.accounts.get(0).getCategories()) {
            content += "\t" + each.getName() + ": " + each.getBalance() + "\n";
        }
        ((TextView)this.view.findViewById(R.id.text_view)).setText(content);
    }
}
