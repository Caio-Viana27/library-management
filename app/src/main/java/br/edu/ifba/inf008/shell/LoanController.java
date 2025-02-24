package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.ILoan;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanController implements ILoanController {
    public static int MAX_VALUE = 5;
    public int loanId = 0;

    public LoanController() {
    }

    public boolean transaction(IUser user, ArrayList<IBook> books) {
        if (!isValidTrasaction(user, books.size())) {
            return false;
        }
        user.addLoan(new Loan(loanId++, books));
        return true;
    }

    public void ReturnTransaction(HashMap<String, String> rentedBooks) {
        for (String key : rentedBooks.keySet()) {
            Core.getInstance().getBookController().getBooksMap().get(key).setAvailable(true);
        }
    }

    public boolean isValidTrasaction(IUser user, int amountOfBooks) {
        if (user.getRentedBooks().isEmpty()) {
            return true;
        }
        int numOfRentedBooks = 0;
        for (ILoan loan : user.getRentedBooks()) {
            numOfRentedBooks += loan.getMapOfRentedBooks().size();
        }
        if (numOfRentedBooks + amountOfBooks > MAX_VALUE) {
            return false;
        }
        return true;
    }
}
