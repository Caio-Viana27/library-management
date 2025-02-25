package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IUIController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ReportPlugin implements IPlugin {
    static ObservableList<OverdueBook> overdueBooks = FXCollections.observableArrayList();

    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();

        uiController.createMenuItem("Reports", "Rented Books",
                createRentedBooksReport(ICore.getInstance().getLoanController().getRentedBooksList()));

        ObservableList<ILoan> ob = ICore.getInstance().getLoanController().getOverdueLoansList();

        uiController.createMenuItem("Reports", "Overdue Books",
                createOverdueBooksReport(
                        updateOverdueBooksList(ob), ob));

        return true;
    }

    public VBox createRentedBooksReport(ObservableList<IBook> rentedBooks) {
        TableView<IBook> tableView = new TableView<IBook>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rentedBooks.addListener((ListChangeListener<IBook>) change -> {
            tableView.refresh();
        });

        String style = new String("-fx-alignment: CENTER;");

        TableColumn<IBook, String> titleColumn = new TableColumn<IBook, String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setStyle(style);

        TableColumn<IBook, String> genreColumn = new TableColumn<IBook, String>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setStyle(style);

        TableColumn<IBook, String> authorColumn = new TableColumn<IBook, String>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        authorColumn.setStyle(style);

        TableColumn<IBook, String> ISBNColumn = new TableColumn<IBook, String>("ISBN");
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        ISBNColumn.setStyle(style);

        TableColumn<IBook, String> publicationColumn = new TableColumn<IBook, String>("Publication Year");
        publicationColumn.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        publicationColumn.setStyle(style);

        tableView.getColumns().addAll(titleColumn, genreColumn, authorColumn, ISBNColumn, publicationColumn);

        tableView.setItems(rentedBooks);

        var scrollPane = new ScrollPane(new VBox(tableView));
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox vBox = new VBox(tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        return vBox;
    }

    public VBox createOverdueBooksReport(ObservableList<OverdueBook> overdueLoans, ObservableList<ILoan> loans) {
        TableView<OverdueBook> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        overdueLoans.addListener((ListChangeListener<OverdueBook>) change -> {
            tableView.refresh();
        });

        loans.addListener((ListChangeListener<ILoan>) change -> {
            updateOverdueBooksList(loans);
            tableView.refresh();
        });

        String style = new String("-fx-alignment: CENTER;");

        TableColumn<OverdueBook, String> titleColumn = new TableColumn<OverdueBook, String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setStyle(style);

        TableColumn<OverdueBook, String> genreColumn = new TableColumn<OverdueBook, String>("Genre");
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        genreColumn.setStyle(style);

        TableColumn<OverdueBook, String> ISBNColumn = new TableColumn<OverdueBook, String>("ISBN");
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        ISBNColumn.setStyle(style);

        TableColumn<OverdueBook, String> publicationColumn = new TableColumn<OverdueBook, String>("Fine");
        publicationColumn.setCellValueFactory(new PropertyValueFactory<>("fine"));
        publicationColumn.setStyle(style);

        tableView.getColumns().addAll(titleColumn, genreColumn, ISBNColumn, publicationColumn);

        tableView.setItems(overdueLoans);

        var scrollPane = new ScrollPane(new VBox(tableView));
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        VBox vBox = new VBox(tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);

        return vBox;
    }

    public class OverdueBook {
        private double fine;
        private String title;
        private String ISBN;
        private String genre;

        public OverdueBook(IBook book, Double fine) {
            this.fine = fine;
            this.title = book.getTitle();
            this.ISBN = book.getISBN();
            this.genre = book.getGenre();
        }

        public double getFine() {
            return fine;
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
    }

    public ObservableList<OverdueBook> updateOverdueBooksList(ObservableList<ILoan> loans) {
        for (ILoan loan : loans) {
            for (IBook book : loan.getRentedBooks()) {
                overdueBooks.add(new OverdueBook(book, calculateFine(loan.getStartDate())));
            }
        }
        return overdueBooks;
    }

    public double calculateFine(LocalDate startDate) {
        int dayDiff = (int) ChronoUnit.DAYS.between(startDate, LocalDate.now());

        return 0.50 * dayDiff;
    }
}
