package br.edu.ifba.inf008.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ILoan extends Serializable {

    public abstract int getId();

    public abstract LocalDate getStartDate();

    public abstract ArrayList<IBook> getRentedBooks();
}
