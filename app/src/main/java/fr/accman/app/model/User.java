package fr.accman.app.model;


import fr.accman.app.model.stream.file.File;
import fr.accman.app.model.utils.Directory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class User
{
	public static User current = new User("default");
	protected ArrayList<Account> accounts = new ArrayList<Account>();
	protected java.io.File directory;

	public User(String name) {
		this.directory = new java.io.File(Directory.getRootDir(), name);
        if (!this.directory.exists()) {
            this.directory.mkdir();
        }
        flushAccounts();
	}

    private void flushAccounts() {
        this.accounts.clear();
        for (java.io.File each: this.directory.listFiles()) {
            this.accounts.add(new Account(this, each));
        }
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

	public static void addUser(String username) {
		Directory.addDirectory(Directory.getRootDir(), username);
	}

    public void addAccount(String accountName) {
        Account.createAccount(this, accountName);
        flushAccounts();
    }

    public java.io.File getDirectory() {
        return directory;
    }
}

