package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IBook;

public class Book implements IBook {
    private String title;
    private String ISBN;
    private String author;
    private String genre;
    private String publicationYear;
    private boolean isAvailable;

    public Book() {
    }

    public Book(String title, String ISBN, String author, String genre, String publicationYear) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean value) {
        isAvailable = value;
    }
}
