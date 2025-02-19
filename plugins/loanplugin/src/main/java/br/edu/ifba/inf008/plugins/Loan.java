package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import br.edu.ifba.inf008.plugins.Book;

public class Loan implements IPlugin {
    private String startDate;
    private Book[] listOflentBooks;

    public boolean init() {
        return true;
    }

    public Loan() {
    }

    private Loan(Book book) {
        this.startDate = new String("");
        this.listOflentBooks = new Book[5];

        this.listOflentBooks[0] = book;
    }

    private Loan(Book... books) {
        this.startDate = new String("");
        this.listOflentBooks = new Book[5];

        for (int i = 0; i < books.length; i++) {
            listOflentBooks[i] = books[i];
        }
    }
}
