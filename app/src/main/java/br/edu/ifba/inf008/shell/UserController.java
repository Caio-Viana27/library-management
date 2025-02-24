package br.edu.ifba.inf008.shell;

import java.util.HashMap;

import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IUserController;

public class UserController implements IUserController {
    private int idCounter = 0;
    private HashMap<String, IUser> usersList = new HashMap<String, IUser>();

    public UserController() {
    }

    public boolean isValidUserData(String name, String email) {
        if (!isValidName(name))
            return false;
        if (!isValidEmail(email))
            return false;

        addUser(new User(idCounter++, name, email));

        return true;
    }

    public boolean isValidName(String name) {
        if (name == null || "".equals(name))
            return false;
        return true;
    }

    public boolean isValidEmail(String email) {
        IUser user = getUser(email);

        if (user != null || email == null || "".equals(email))
            return false;
        return true;
    }

    public void addUser(IUser newUser) {
        usersList.put(newUser.getEmail(), newUser);
    }

    public IUser getUser(String userEmail) {
        return usersList.get(userEmail);
    }

    public HashMap<String, IUser> getUsersList() {
        return usersList;
    }

    public void test() {
        isValidUserData("caio viana teixeira carmo", "caio@gmail.com");
        isValidUserData("Ravena Dafne", "ravena@gmail.com");
        isValidUserData("camille luz", "camille@gmail.com");
        isValidUserData("Raphael Gramosa", "rafa@gmail.com");
    }
}