package br.edu.ifba.inf008.interfaces;

import java.util.HashMap;

public interface ILoan {

    public abstract int getId();

    public abstract String getReturnDate();

    public abstract HashMap<String, String> getMapOfRentedBooks();
}
