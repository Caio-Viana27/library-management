package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.IBook;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.ILoan;
import br.edu.ifba.inf008.interfaces.IUserController;
import br.edu.ifba.inf008.interfaces.IBookController;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UIController extends Application implements IUIController {
    private MenuBar menuBar;
    private TabPane tabPane;
    private BorderPane borderPane;
    private static UIController uiController;

    public UIController() {
    }

    @Override
    public void init() {
        uiController = this;
    }

    public static UIController getInstance() {
        return uiController;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library Management");

        menuBar = new MenuBar();

        borderPane = new BorderPane();

        tabPane = new TabPane();
        tabPane.setSide(Side.TOP);

        createMenuItem("User", "Create User", createUserGrid());
        createMenuItem("Books", "Enroll Book", enrollNewBookGrid());
        createMenuItem("Books", "Loan Book", loanBookGrid());
        // createMenuItem("Books", "Return Book", "Return Method");

        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);

        Scene scene = new Scene(borderPane, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        Core.getInstance().getPluginController().init();

        Core.getInstance().getUserController().test(); // add test data
        Core.getInstance().getBookController().test(); // add test data
    }

    public MenuItem createMenuItem(String menuText, String menuItemText, GridPane newGrid) {
        // Criar o menu caso ele nao exista
        Menu newMenu = null;
        for (Menu menu : menuBar.getMenus()) {
            if (menu.getText() == menuText) {
                newMenu = menu;
                break;
            }
        }
        if (newMenu == null) {
            newMenu = new Menu(menuText);
            menuBar.getMenus().add(newMenu);
        }

        // Criar o menu item neste menu
        MenuItem menuItem = new MenuItem(menuItemText);
        menuItem.setOnAction(event -> createTab(menuItemText, newGrid));
        newMenu.getItems().add(menuItem);

        return menuItem;
    }

    public boolean createTab(String tabText, Node contents) {
        if (!tabPane.isVisible()) {
            tabPane.setVisible(true);
        }

        if (tabAlreadyExist(tabText))
            return false;

        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
    }

    public Tab getTabByTitle(String tabTitle) {
        for (Tab tab : tabPane.getTabs()) {
            if (tab.getText().equals(tabTitle)) {
                return tab;
            }
        }
        return null;
    }

    public boolean tabAlreadyExist(String tabText) {
        for (var tab : tabPane.getTabs()) {
            if (tab.getText().equals(tabText)) {
                return true;
            }
        }
        return false;
    }

    public GridPane createBasicGridPane() {
        var grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    public GridPane createBasicGridPane(int topRightBottonLeft) {
        var grid = new GridPane();
        grid.setPadding(new Insets(topRightBottonLeft));
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    public Button createAndSetButtonAction(String label, int prefWidth, EventHandler<ActionEvent> buttonAction) {
        var button = new Button(label);
        button.setPrefWidth(prefWidth);
        button.setOnAction(buttonAction);
        return button;
    }

    public GridPane createUserGrid() {
        var grid = createBasicGridPane();

        var nameLabel = new Label("Name");
        var nameField = new TextField();
        nameField.setPrefWidth(200);
        var emailLabel = new Label("E-mail");
        var emailField = new TextField();
        emailField.setPrefWidth(200);

        Button createButton = createAndSetButtonAction("Create", 80, buttonAction -> {
            validateUserData(
                    nameField.getText(),
                    emailField.getText());

            nameField.clear();
            emailField.clear();
        });

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(createButton, 1, 3);

        GridPane.setConstraints(nameLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(nameField, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(emailLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(emailField, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(createButton, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    public GridPane enrollNewBookGrid() {
        GridPane grid = createBasicGridPane();

        var titleLabel = new Label("Title");
        var titleField = new TextField();
        titleField.setPrefWidth(200);
        var ISBNLabel = new Label("ISBN");
        var ISBNField = new TextField();
        ISBNField.setPrefWidth(200);
        var authorLabel = new Label("Author");
        var authorField = new TextField();
        authorField.setPrefWidth(200);
        var publicationLabel = new Label("Publication year");
        var publicationField = new TextField();
        publicationField.setPrefWidth(200);

        var genreLabel = new Label("Genre");
        var comboBoxGenre = new ComboBox<String>();
        comboBoxGenre.getItems().addAll("Fantasy",
                "Science Fiction",
                "Mystery",
                "Thriller",
                "Romance",
                "Non-fiction",
                "Science",
                "Horror");

        comboBoxGenre.setPromptText("Select genre");
        comboBoxGenre.setPrefWidth(200);

        var enrollButton = new Button("Enroll");
        enrollButton.setPrefWidth(80);
        enrollButton.setOnAction(event -> {
            validateBookData(
                    titleField.getText(),
                    ISBNField.getText(),
                    authorField.getText(),
                    publicationField.getText(),
                    comboBoxGenre.getValue());

            titleField.clear();
            ISBNField.clear();
            authorField.clear();
            publicationField.clear();
        });

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(ISBNLabel, 0, 1);
        grid.add(ISBNField, 1, 1);
        grid.add(authorLabel, 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(publicationLabel, 0, 4);
        grid.add(publicationField, 1, 4);
        grid.add(genreLabel, 0, 3);
        grid.add(comboBoxGenre, 1, 3);
        grid.add(enrollButton, 1, 5);

        GridPane.setConstraints(titleLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(titleField, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ISBNLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ISBNField, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(authorLabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(authorField, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(publicationLabel, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(publicationField, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(genreLabel, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(comboBoxGenre, 1, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(enrollButton, 1, 6, 1, 1, HPos.RIGHT, VPos.CENTER);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    public void validateUserData(String name, String email) {
        IUserController userController = Core.getInstance().getUserController();

        if (!userController.isValidUserData(name, email)) {
            generateWarning("Invalid credentials for user or a user with this e-mail already exists!");
            return;
        }
        generateWarning("User created successfully!");
    }

    public void validateBookData(String title, String ISBN, String author, String genre, String publicationYear) {
        IBookController bookController = Core.getInstance().getBookController();

        if (!bookController.isValidBookData(title, ISBN, author, genre, publicationYear)) {
            generateWarning("Invalid credentials for book!");
            return;
        }
        generateWarning("Book enrolled successfully!");
    }

    public void validateUserCredential(String email) {
        IUser user = Core.getInstance().getUserController().getUser(email);

        if (user == null) {
            generateWarning("Invalid e-mail or e-mail doesn't exist!");
            return;
        }
        tabPane.setVisible(false);

        createLoanWindow(user);
    }

    public void generateWarning(String warningMessage) {
        var warningStage = new Stage();
        warningStage.setTitle("Warning");
        warningStage.setResizable(false);

        var button = new Button("ok");
        button.setPrefWidth(80);
        button.setOnAction(event -> warningStage.close());

        var buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(2));
        buttonBar.getButtons().add(button);

        var warningLabel = new Label(warningMessage);
        warningLabel.setWrapText(true);
        warningLabel.setTextAlignment(TextAlignment.CENTER);
        warningLabel.setFont(Font.font("System", FontWeight.MEDIUM, 20));

        var borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setCenter(warningLabel);
        borderPane.setBottom(buttonBar);

        Scene scene = new Scene(borderPane, 300, 120);

        warningStage.setScene(scene);
        warningStage.showAndWait();
    }

    public GridPane loanBookGrid() {
        var grid = createBasicGridPane();

        var emailLabel = new Label("E-mail");
        var emailField = new TextField();
        emailField.setPrefWidth(200);

        Button button = createAndSetButtonAction("select", 80, Action -> {
            if (tabAlreadyExist("Select Book")) {
                generateWarning("Please finish the previous loan!");
            } else {
                validateUserCredential(emailField.getText());
            }
            emailField.clear();
        });

        grid.add(emailLabel, 0, 0);
        grid.add(emailField, 1, 0);
        grid.add(button, 1, 2);

        GridPane.setConstraints(emailLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(emailField, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(button, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    public void createLoanWindow(IUser user) {
        BorderPane left = new BorderPane();

        var grid = createBasicGridPane(10);
        grid.setMinWidth(500);

        var col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(col1);
        var col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(col2);

        var info = new Text("User Credentials");
        var nameLable = new Text("User Name:");
        var userName = new Text(user.getName());
        var emailLable = new Text("User E-mail:");
        var userEmail = new Text(user.getEmail());
        var infoBooks = new Text("Rented Books");

        var infoSeparator = new Separator();
        var userInfoSeparator = new Separator();
        var infoBookSeparator = new Separator();

        grid.add(info, 1, 0);
        grid.add(infoSeparator, 0, 1);
        grid.add(nameLable, 0, 2);
        grid.add(userName, 1, 2);
        grid.add(emailLable, 0, 3);
        grid.add(userEmail, 1, 3);
        grid.add(userInfoSeparator, 0, 4);
        grid.add(infoBooks, 1, 5);
        grid.add(infoBookSeparator, 0, 6);

        GridPane.setConstraints(info, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(infoSeparator, 0, 1, 3, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(nameLable, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(userName, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(emailLable, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(userEmail, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(userInfoSeparator, 0, 4, 3, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(infoBooks, 1, 5, 1, 1, HPos.LEFT, VPos.CENTER);
        GridPane.setConstraints(infoBookSeparator, 0, 6, 3, 1, HPos.RIGHT, VPos.CENTER);

        if (user.getRentedBooks() != null && !user.getRentedBooks().isEmpty()) {
            int i = 7;
            for (ILoan loan : user.getRentedBooks()) {
                for (String book : loan.getMapOfRentedBooks().values()) {
                    var lable = new Text("Title:");
                    var title = new Text(book);

                    grid.add(lable, 0, i);
                    GridPane.setConstraints(lable, 0, i, 1, 1, HPos.RIGHT, VPos.CENTER);
                    grid.add(title, 1, i);
                    GridPane.setConstraints(title, 1, i++, 2, 1, HPos.LEFT, VPos.CENTER);
                }
                var lable = new Text("Return date:");
                var returnDate = new Text(loan.getReturnDate());
                var separator = new Separator();

                grid.add(lable, 0, i);
                GridPane.setConstraints(lable, 0, i, 1, 1, HPos.RIGHT, VPos.CENTER);
                grid.add(returnDate, 1, i);
                GridPane.setConstraints(returnDate, 1, i++, 2, 1, HPos.LEFT, VPos.CENTER);
                grid.add(separator, 0, i);
                GridPane.setConstraints(separator, 0, i++, 2, 1, HPos.RIGHT, VPos.CENTER);
            }
        } else {
            var lable = new Text("User hasn't rented any books!");
            grid.add(lable, 1, 7);
            GridPane.setConstraints(lable, 1, 7, 1, 1, HPos.LEFT, VPos.CENTER);
        }

        VBox vBoxListOfBooks = createToggableList(Core.getInstance().getBookController().getBooksMap().values());
        vBoxListOfBooks.setPrefWidth(400);

        var scrollPane = new ScrollPane(vBoxListOfBooks);
        scrollPane.setMinWidth(400);
        scrollPane.setFitToWidth(true);

        var borderPanebooklist = new BorderPane();
        borderPanebooklist.setMinWidth(400);
        borderPanebooklist.setCenter(scrollPane);

        var searchField = new TextField();
        searchField.setPrefWidth(200);

        var searchTrigger = createAndSetButtonAction("search", 80, action -> {
            scrollPane.setContent(
                    createToggableList(
                            Core.getInstance().getBookController().getMatchingPatternBooks(searchField.getText())));
        });

        var hBox = new HBox();
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.BASELINE_CENTER);
        hBox.getChildren().addAll(searchField, searchTrigger);

        borderPanebooklist.setTop(hBox);

        var loanButton = createAndSetButtonAction("Loan Book", 80, action -> {
            Map<String, IBook> booksList = Core.getInstance().getBookController().getBooksMap();
            var books = new ArrayList<IBook>();

            for (ToggleButton button : UIHelper.selectedButtons) {
                if (booksList.containsKey(button.getId())) {
                    var book = booksList.get(button.getId());
                    book.setAvailable(false);
                    books.add(book);
                }
            }
            if (Core.getInstance().getLoanController().transaction(user, books)) {
                generateWarning("Success");
                tabPane.getTabs().remove(getTabByTitle("Select Book"));
            } else {
                generateWarning("Transaction Failed! Maximun of five loans per user");
            }
        });

        ButtonBar.setButtonData(loanButton, ButtonBar.ButtonData.RIGHT);

        var buttonBar = new ButtonBar();
        buttonBar.setPrefHeight(50);
        buttonBar.setPadding(new Insets(15));

        buttonBar.getButtons().add(loanButton);
        left.setCenter(grid);
        left.setBottom(buttonBar);

        var splitPane = new SplitPane(left, borderPanebooklist);
        createTab("Select Book", splitPane);
    }

    public VBox createToggableList(Collection<IBook> Books) {
        VBox vBoxListOfBooks = new VBox();

        UIHelper.buttonList = new ArrayList<ToggleButton>();
        UIHelper.selectedButtons = new ArrayList<ToggleButton>();

        for (IBook book : Books) {
            if (book.isAvailable()) {
                var toggleButton = new ToggleButton(book.getTitle());
                toggleButton.setId(book.getISBN());
                toggleButton.setMinHeight(50);
                toggleButton.setMaxWidth(Double.MAX_VALUE);
                toggleButton.setOnAction(action -> {
                    if (toggleButton.isSelected()) {
                        if (UIHelper.selectedButtons.size() < UIHelper.MAX_VALUE) {
                            UIHelper.selectedButtons.add(toggleButton);
                        } else {
                            toggleButton.setSelected(false);
                        }
                    } else {
                        UIHelper.selectedButtons.remove(toggleButton);
                    }
                    UIHelper.updateButtonStates();
                });
                vBoxListOfBooks.getChildren().add(toggleButton);
            }
        }
        return vBoxListOfBooks;
    }
}
