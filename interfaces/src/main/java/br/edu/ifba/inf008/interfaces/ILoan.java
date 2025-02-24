package br.edu.ifba.inf008.interfaces;

import java.time.LocalDate;
import java.util.HashMap;

public interface ILoan {

    public abstract int getId();

    public abstract LocalDate getStartDate();

    public abstract HashMap<String, String> getMapOfRentedBooks();
}
