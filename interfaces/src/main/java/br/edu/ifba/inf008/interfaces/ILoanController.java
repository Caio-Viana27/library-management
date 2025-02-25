package br.edu.ifba.inf008.interfaces;

import java.util.ArrayList;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public interface ILoanController { // deals with loan and returns

    public abstract boolean transaction(IUser user, ArrayList<IBook> books, LocalDate date);

    public abstract boolean isValidTrasaction(IUser user, int amountOfBooks, LocalDate date);

    public abstract void ReturnTransaction(ILoan Loan);

    public abstract ObservableList<IBook> getRentedBooksList();

    public abstract ObservableList<ILoan> getOverdueLoansList();
}
