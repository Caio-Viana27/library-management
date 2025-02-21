package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface ILoan {

    public abstract String getReturnDate();

    public abstract ArrayList<IBook> getlistOflentBooks();
}
