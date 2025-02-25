package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IBook;

import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;

public class Loan implements ILoan {
    private int id;
    private LocalDate startDate;
    private ArrayList<IBook> rentedBooks = null;

    public Loan() {
    }

    public Loan(int loanId, ArrayList<IBook> books, LocalDate date) {
        id = loanId;
        startDate = date;
        rentedBooks = new ArrayList<IBook>();

        for (IBook book : books) {
            rentedBooks.add(book);
        }
    }

    public int getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ArrayList<IBook> getRentedBooks() {
        return rentedBooks;
    }
}
