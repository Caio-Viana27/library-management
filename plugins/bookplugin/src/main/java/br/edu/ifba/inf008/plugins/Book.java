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

    private static HashMap<String, Book> listOfBooks;

    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        // load the books to the static atribute

        // uiController.createMenuItem("Books", "Enroll Book",
        // uiController.enrollNewBookGrid());
        // uiController.createMenuItem("Books", "Loan Book", "Loan Method");
        // uiController.createMenuItem("Books", "Return Book", "Return Method");

        return true;
    }
}
