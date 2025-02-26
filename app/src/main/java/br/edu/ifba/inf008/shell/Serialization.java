package br.edu.ifba.inf008.shell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IUser;

public class Serialization implements Serializable {
    public ArrayList<IBook> observableRentedBooks;
    public ArrayList<ILoan> observableOverdueLoans;
    public HashMap<String, IUser> usersMap;
    public HashMap<String, IBook> booksMap;
    public int loanIdCounter;
    public int userIdCounter;
}
