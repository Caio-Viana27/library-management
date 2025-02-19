package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import br.edu.ifba.inf008.plugins.Loan;

import java.util.HashMap;
import java.util.ArrayList;

public class User implements IPlugin {
    private int id;
    private String name;
    private String email;
    private ArrayList<Loan> rentBooks;

    private static HashMap<String, User> listOfUsers = new HashMap<String, User>();

    public boolean init() {
        // load the books to the static atribute
        return true;
    }

    public User() {
    }

    private User(String name, String email) {
        this.id = 1;
        this.name = name;
        this.email = email;
    }

    public static boolean createUser(String name, String email) {
        if (!isValidName(name))
            return false;
        if (!isValidEmail(email))
            return false;

        listOfUsers.put(email, new User(name, email));

        return true;
    }

    public static boolean isValidName(String name) {
        if (name == null || "".equals(name))
            return false;
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || "".equals(email) || listOfUsers.containsKey(email))
            return false;
        return true;
    }

    public static User getUser(String email) {
        return listOfUsers.get(email);
    }
}