package br.edu.ifba.inf008.interfaces;

import java.io.Serializable;

public interface IBook extends Serializable {

    public abstract String getTitle();

    public abstract String getISBN();

    public abstract String getGenre();

    public abstract String getAuthor();

    public abstract String getPublicationYear();

    public abstract boolean isAvailable();

    public abstract void setAvailable(boolean value);
}
