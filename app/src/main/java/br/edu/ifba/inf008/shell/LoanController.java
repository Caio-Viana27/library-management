package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.interfaces.IUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.ILoan;

import java.time.LocalDate;
import java.util.ArrayList;

public class LoanController implements ILoanController {
    private final ObservableList<IBook> rentedBooksList = FXCollections.observableArrayList();
    private final ObservableList<ILoan> overdueLoans = FXCollections.observableArrayList();
    public static int MAX_VALUE = 5;
    public int loanIdCounter = 0;

    public LoanController() {
    }

    public boolean transaction(IUser user, ArrayList<IBook> books, LocalDate date) {
        if (!isValidTrasaction(user, books.size(), date)) {
            return false;
        }

        ILoan newLoan = new Loan(loanIdCounter++, books, date);

        if (date.plusDays(14).isBefore(LocalDate.now())) {
            // overdueBooks.addAll(books);
            overdueLoans.add(newLoan);
        }

        rentedBooksList.addAll(books);

        user.addLoan(newLoan);
        return true;
    }

    public int getLoanIdCounter() {
        return loanIdCounter;
    }

    public void setLoanIdCounter(int counter) {
        loanIdCounter = counter;
    }

    public void ReturnTransaction(ILoan loan) {

        for (IBook book : loan.getRentedBooks()) {
            Core.getInstance().getBookController().getBook(book.getISBN()).setAvailable(true);

            int i = 0;
            for (IBook rentedBook : rentedBooksList) {
                if (book.getISBN().equals(rentedBook.getISBN())) {
                    rentedBooksList.remove(i);
                    break;
                }
                i++;
            }

            i = 0;
            for (ILoan ovLoan : overdueLoans) {
                if (loan.getId() == ovLoan.getId()) {
                    overdueLoans.remove(i);
                    break;
                }
                i++;
            }
        }
    }

    public boolean isValidTrasaction(IUser user, int amountOfBooks, LocalDate date) {
        if (user.getRentedBooks().isEmpty()) {
            return true;
        }
        if (date == null) {
            return false;
        }
        int numOfRentedBooks = 0;
        for (ILoan loan : user.getRentedBooks()) {
            numOfRentedBooks += loan.getRentedBooks().size();
        }
        if (numOfRentedBooks + amountOfBooks > MAX_VALUE) {
            return false;
        }
        return true;
    }

    public ObservableList<IBook> getRentedBooksList() {
        return rentedBooksList;
    }

    public void loadRentedBooksList(ArrayList<IBook> rentedBooks) {
        this.rentedBooksList.addAll(rentedBooks);
    }

    public void loadOverdueLoansList(ArrayList<ILoan> overdueLoans) {
        this.overdueLoans.addAll(overdueLoans);
    }

    public ObservableList<ILoan> getOverdueLoansList() {

        for (IUser user : Core.getInstance().getUserController().getUsersMap().values()) {
            if (!user.getRentedBooks().isEmpty()) {
                for (ILoan loan : user.getRentedBooks()) {
                    LocalDate dueDate = loan.getStartDate().plusDays(14);

                    if (!loan.getRentedBooks().isEmpty() && dueDate.isBefore(LocalDate.now())) {
                        overdueLoans.add(loan);
                    }
                }
            }
        }
        System.out.println(overdueLoans.size());
        return overdueLoans;
    }
}
