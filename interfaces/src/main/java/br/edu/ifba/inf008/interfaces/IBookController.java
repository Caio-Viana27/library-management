package br.edu.ifba.inf008.interfaces;

import java.util.HashMap;
import java.util.Collection;

public interface IBookController {
    public abstract boolean isValidBookData(
            String title, String ISBN, String author, String genre, String publicationYear);

    public void addBook(IBook newBook);

    public IBook getBook(String BookISBN);

    public abstract HashMap<String, IBook> getBooksMap();

    public abstract Collection<IBook> getMatchingPatternBooks(String pattern);

    public abstract void test();
}
