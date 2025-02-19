package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import br.edu.ifba.inf008.plugins.Book;

import java.util.ArrayList;

public class Loan implements IPlugin {
    private String startDate;
    private ArrayList<Book> listOflentBooks = null;

    public boolean init() {
        return true;
    }

    public Loan() {
    }

    private Loan(Book book) {
        this.startDate = new String("started");
        this.listOflentBooks = new ArrayList<Book>();

        this.listOflentBooks.add(book);
    }

    private Loan(Book... books) {
        this.startDate = new String("started");
        this.listOflentBooks = new ArrayList<Book>();

        for (int i = 0; i < books.length; i++) {
            listOflentBooks.add(books[i]);
        }
    }

    public String getReturnDate() {
        return startDate;
    }

    public ArrayList<Book> getlistOflentBooks() {
        return listOflentBooks;
    }
}
