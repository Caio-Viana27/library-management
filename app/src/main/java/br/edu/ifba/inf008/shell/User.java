package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.ILoan;

import java.util.ArrayList;

public class User implements IUser {
    private int id;
    private String name;
    private String email;
    private ArrayList<ILoan> rentedBooks = null;

    public User() {
    }

    public User(String name, String email) {
        this.id = 1;
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<ILoan> getRentedBooks() {
        return rentedBooks;
    }
}