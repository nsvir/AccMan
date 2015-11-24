package fr.accman.app.controller;

import android.view.View;
import fr.accman.app.model.Account;
import fr.accman.app.model.Entry;

/**
 * Created by nanosvir on 24 Nov 15.
 */
public class AddEntry implements View.OnClickListener {
    private final Callback callback;
    Account currentAccount;

    public AddEntry(Account currentAccount, AddEntry.Callback callback) {
        this.currentAccount = currentAccount;
        this.callback = callback;
    }

    @Override
    public void onClick(View view) {
        currentAccount.getCategories().get(0).addEntryAndSave(Entry.newEntry("10"));
        this.callback.addEntryCallback();
    }

    public interface Callback {
        void addEntryCallback();
    }
}
