package br.edu.ifba.inf008.interfaces;

import java.util.HashMap;

public interface IUserController {
    public abstract boolean isValidUserData(String name, String email);

    public abstract void addUser(IUser newUser);

    public abstract IUser getUser(String userEmail);

    public abstract HashMap<String, IUser> getUsersList();

    public abstract void test();
}
