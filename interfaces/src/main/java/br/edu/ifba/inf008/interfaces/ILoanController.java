package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

public interface ILoanController { // deals with loan and returns

    public abstract boolean transaction(IUser user, ArrayList<IBook> books);

    public abstract boolean isValidTrasaction(IUser user, int amountOfBooks);
}
