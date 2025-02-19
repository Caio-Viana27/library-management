package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

import java.util.HashMap;

public class Book implements IPlugin {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private String publicationYear;
    private boolean isLent = false;

    private static HashMap<String, Book> listOfBooks = new HashMap<String, Book>();

    public boolean init() {

        // load the books to the static atribute

        return true;
    }

    public Book() {
    }

    private Book(String title, String ISBN, String author, String genre, String publicationYear) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public static boolean createBook(String title, String ISBN, String author, String genre, String publicationYear) {
        if (!isValidTitle(title)) {
            return false;
        }
        if (!isValidISBN(ISBN)) {
            return false;
        }
        if (!isValidAuthor(author)) {
            return false;
        }
        if (!isValidPublicationYear(publicationYear)) {
            return false;
        }

        listOfBooks.put(ISBN, new Book(title, ISBN, author, genre, publicationYear));

        return true;
    }

    public static boolean isValidTitle(String title) {
        if ("".equals(title) || title == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidISBN(String ISBN) {
        if ("".equals(ISBN) || ISBN == null || listOfBooks.containsKey(ISBN)) {
            return false;
        }
        return true;
    }

    public static boolean isValidAuthor(String author) {
        if ("".equals(author) || author == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidGenre(String genre) {
        if ("".equals(genre) || genre == null) {
            return false;
        }
        return true;
    }

    public static boolean isValidPublicationYear(String publicationYear) {
        return true;
    }
}
