package br.edu.ifba.inf008.interfaces;

import java.io.Serializable;
import java.util.ArrayList;

public interface IUser extends Serializable {
    public abstract String getName();

    public abstract String getEmail();

    public abstract ArrayList<ILoan> getRentedBooks();

    public abstract void addLoan(ILoan newLoan);
}
