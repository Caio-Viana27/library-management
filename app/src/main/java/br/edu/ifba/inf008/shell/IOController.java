package br.edu.ifba.inf008.shell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IIOController;
import br.edu.ifba.inf008.interfaces.ILoan;
import javafx.collections.ObservableList;

public class IOController implements IIOController {
    private Serialization systemData;
    private String dirname = new String("./serialization/");

    public IOController() {
        this.systemData = new Serialization();
    }

    public boolean save() {

        File directory = new File(dirname);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        systemData.observableRentedBooks = transformToArrayListBook(
                Core.getInstance().getLoanController().getRentedBooksList());
        systemData.observableOverdueLoans = transformToArrayListLoan(
                Core.getInstance().getLoanController().getOverdueLoansList());

        systemData.loanIdCounter = Core.getInstance().getLoanController().getLoanIdCounter();
        systemData.userIdCounter = Core.getInstance().getUserController().getUserIdCounter();

        systemData.usersMap = Core.getInstance().getUserController().getUsersMap();
        systemData.booksMap = Core.getInstance().getBookController().getBooksMap();
        try (FileOutputStream file = new FileOutputStream(dirname + "data.bin");
                ObjectOutputStream out = new ObjectOutputStream(file);) {

            out.writeObject(systemData);
            out.close();
            file.close();

            System.out.println("Data saved!");
        } catch (IOException e) {
            System.out.println("Data not saved, IOException");
            e.printStackTrace();
        }
        return true;
    }

    public boolean load() {
        try {
            FileInputStream file = new FileInputStream(dirname + "data.bin");
            ObjectInputStream in = new ObjectInputStream(file);

            this.systemData = (Serialization) in.readObject();

            Core.getInstance().getLoanController().loadRentedBooksList(systemData.observableRentedBooks);
            Core.getInstance().getLoanController().loadOverdueLoansList(systemData.observableOverdueLoans);
            Core.getInstance().getUserController().loadUsersMap(systemData.usersMap);
            Core.getInstance().getBookController().loadBookMap(systemData.booksMap);
            Core.getInstance().getUserController().setUserIdCounter(systemData.userIdCounter);
            Core.getInstance().getLoanController().setLoanIdCounter(systemData.loanIdCounter);

            in.close();
            file.close();

            System.out.println("Data loaded!");
        } catch (IOException ex) {
            this.systemData = new Serialization();
            System.out.println("Data not loaded, IOexception");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            this.systemData = new Serialization();
            System.out.println("Data not loaded, Class not found Exception");
            ex.printStackTrace();
        }
        return true;
    }

    public ArrayList<IBook> transformToArrayListBook(ObservableList<IBook> rentedBooks) {
        return new ArrayList<IBook>(rentedBooks);
    }

    public ArrayList<ILoan> transformToArrayListLoan(ObservableList<ILoan> overdueLoans) {
        return new ArrayList<ILoan>(overdueLoans);
    }
}