package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IBook;

import java.util.HashMap;
import java.util.ArrayList;

public class Loan implements ILoan {
    private int id;
    private String startDate;
    private HashMap<String, String> mapOfRentedBooks = null;

    public Loan() {
    }

    public Loan(int loanId, ArrayList<IBook> books) {
        id = loanId;
        startDate = new String("started");
        mapOfRentedBooks = new HashMap<String, String>();

        for (IBook book : books) {
            mapOfRentedBooks.put(book.getISBN(), book.getTitle());
        }
    }

    public int getId() {
        return id;
    }

    public String getReturnDate() {
        return startDate;
    }

    public HashMap<String, String> getMapOfRentedBooks() {
        return mapOfRentedBooks;
    }
}
