package br.edu.ifba.inf008.interfaces;

public interface IBook {

    public abstract String getTitle();

    public abstract String getISBN();

    public abstract String getGenre();

    public abstract boolean isAvailable();
}
