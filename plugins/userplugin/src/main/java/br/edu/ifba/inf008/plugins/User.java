package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.HashMap;

public class User implements IPlugin {
    private int id;
    private String name;
    private String email;

    private static HashMap<Integer, User> listOfUsers = new HashMap<>();

    public boolean init() {
        // load the books to the static atribute

        return true;
    }

    private User(String name, String email) {
        // this.id = id;
        // this.name = name;
        // this.email = email;
    }

    public static boolean createUser(String name, String email) {
        if (!isValidName(name))
            return false;
        if (!isValidEmail(email))
            return false;

        System.out.println(name + " " + email);

        User.listOfUsers.put(Integer.valueOf(1), new User(name, email));

        return true;
    }

    public static boolean isValidName(String name) {
        if (name == null || name.equals(""))
            return false;
        return true;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.equals(""))
            return false;
        return true;
    }

    public static boolean validadeData(String name, String email) {

        return true;
    }
}