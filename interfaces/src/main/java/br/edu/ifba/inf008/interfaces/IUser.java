package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface IUser {
    public abstract String getName();

    public abstract String getEmail();

    public abstract ArrayList<ILoan> getRentedBooks();
}
