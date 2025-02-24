package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public interface ILoanController { // deals with loan and returns

    public abstract boolean transaction(IUser user, ArrayList<IBook> books, LocalDate date);

    public abstract boolean isValidTrasaction(IUser user, int amountOfBooks, LocalDate date);

    public void ReturnTransaction(HashMap<String, String> rentedBooks);
}
