package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.shell.PluginController;

import br.edu.ifba.inf008.plugins.User;
import br.edu.ifba.inf008.plugins.Book;
import br.edu.ifba.inf008.plugins.Loan;

import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.geometry.HPos;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class UIController extends Application implements IUIController {
    private ICore core;
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

        var bordePaneCenter = new BorderPane(tabPane);
        var borderPaneLeft = new BorderPane();
        borderPaneLeft.setPrefWidth(250);
        borderPaneLeft.setStyle(String.join("\n", "-fx-background-color: rgb(230, 230, 230);",
                "-fx-border-color: rgb(184, 184, 184);",
                "-fx-border-width: 1px;"));

        borderPane.setTop(menuBar);
        borderPane.setCenter(bordePaneCenter);
        borderPane.setLeft(borderPaneLeft);

        Scene scene = new Scene(borderPane, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        Core.getInstance().getPluginController().init();
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

        for (var tab : tabPane.getTabs()) {
            if (tab.getText().equals(tabText)) {
                return false;
            }
        }

        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
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

        Button createButton = createAndSetButtonAction("create", 80, buttonAction -> {
            validateInput(
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
            validateInput(
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

    public void validateInput(String name, String email) {
        if (!User.createUser(name, email)) {
            generateWarning("Invalid credentials for user!");
            return;
        }
        generateWarning("User created successfully!");
    }

    public void validateInput(String title, String ISBN, String author, String genre, String publicationYear) {
        if (!Book.createBook(title, ISBN, author, genre, publicationYear)) {
            generateWarning("Invalid credentials for book!");
            return;
        }
        generateWarning("Book created successfully!");
    }

    public void validateInput(String email) {
        var user = User.getUser(email);

        if (user == null) {
            generateWarning("Invalid credentials for user or user does not exist!");
            return;
        }
        tabPane.setVisible(false);

        createLoanWindow(user, Book.getListOfBooks());
    }

    public void generateWarning(String warningMessage) {
        var warningStage = new Stage();
        warningStage.setTitle("Warning");
        warningStage.setResizable(false);

        var button = new Button("ok");
        button.setPrefWidth(80);
        button.setOnAction(event -> warningStage.close());

        var buttonBar = new ButtonBar();
        // buttonBar.setPrefHeight(15);
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
            validateInput(emailField.getText());
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

    public void createLoanWindow(User user, HashMap<String, Book> Books) {
        BorderPane left = (BorderPane) borderPane.getLeft();

        var vBox = new VBox(10);
        vBox.setPadding(new Insets(10));
        vBox.setStyle(String.join("\n", "-fx-background-color: rgb(230, 230, 230);",
                "-fx-border-color: rgb(184, 184, 184);",
                "-fx-border-width: 1px;"));

        var info = new Text("User Credentials");
        info.setFill(Color.BLACK);
        var userName = new Text(user.getName());
        var userEmail = new Text(user.getEmail());
        var infoBooks = new Text("Rented Books");
        infoBooks.setFill(Color.BLACK);

        var infoSeparator = new Separator();
        var userInfoSeparator = new Separator();
        var infoBookSeparator = new Separator();

        vBox.getChildren().addAll(
                info,
                infoSeparator,
                userName,
                userEmail,
                userInfoSeparator,
                infoBooks,
                infoBookSeparator);

        if (user.getRentedBooks() != null && !user.getRentedBooks().isEmpty()) {
            for (Loan loan : user.getRentedBooks()) {
                for (Book book : loan.getlistOflentBooks()) {
                    var title = new Text(book.getTitle());
                    vBox.getChildren().add(title);
                }
                var dueDate = new Text(loan.getReturnDate());
                var separator = new Separator();
                vBox.getChildren().addAll(dueDate, separator);
            }
        } else {
            vBox.getChildren().add(new Text("User has no rented books!"));
        }

        left.setCenter(vBox);

        BorderPane center = (BorderPane) borderPane.getCenter();

        var loanButton = new Button("Loan Book");
        loanButton.setPrefWidth(80);
        loanButton.setOnAction(action -> System.out.println("you clicked me!"));
        ButtonBar.setButtonData(loanButton, ButtonBar.ButtonData.RIGHT);

        var buttonBar = new ButtonBar();
        buttonBar.setPrefHeight(50);
        buttonBar.setPadding(new Insets(15));
        buttonBar.setStyle(String.join("\n", "-fx-background-color: rgb(230, 230, 230);",
                "-fx-border-color: rgb(184, 184, 184);",
                "-fx-border-width: 1px;"));

        buttonBar.getButtons().add(loanButton);

        center.setBottom(buttonBar);

        var scrollBar = new ScrollBar();
        scrollBar.setOrientation(Orientation.VERTICAL);
        center.setRight(scrollBar);
    }
}
