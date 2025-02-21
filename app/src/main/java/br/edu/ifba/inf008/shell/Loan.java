package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IBook;

import java.util.ArrayList;

public class Loan implements ILoan {
    private String startDate;
    private ArrayList<IBook> listOflentBooks = null;

    public Loan() {
    }

    public Loan(Book book) {
        this.startDate = new String("started");
        this.listOflentBooks = new ArrayList<IBook>();

        this.listOflentBooks.add(book);
    }

    public Loan(Book... books) {
        this.startDate = new String("started");
        this.listOflentBooks = new ArrayList<IBook>();

        for (int i = 0; i < books.length; i++) {
            listOflentBooks.add(books[i]);
        }
    }

    public String getReturnDate() {
        return startDate;
    }

    public ArrayList<IBook> getlistOflentBooks() {
        return listOflentBooks;
    }
}
