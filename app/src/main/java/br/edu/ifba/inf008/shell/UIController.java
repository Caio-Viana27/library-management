package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IUIController;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.shell.PluginController;

import br.edu.ifba.inf008.plugins.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.geometry.VPos;
import javafx.scene.Node;

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

        borderPane.setTop(menuBar);
        borderPane.setCenter(tabPane);

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

        Tab tab = new Tab();
        tab.setText(tabText);
        tab.setContent(contents);
        tabPane.getTabs().add(tab);

        return true;
    }

    public GridPane createNewUserGrid() {
        var grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        var nameLabel = new Label("Name");
        var nameField = new TextField();
        var emailLabel = new Label("E-mail");
        var emailField = new TextField();
        var createButton = new Button("Create");

        // criar outro metodo para lidar melhor com a tela de confirmação
        createButton.setOnAction(event -> User.createUser(nameField.getText(), emailField.getText()));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(createButton, 1, 4);

        GridPane.setConstraints(nameLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(nameField, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(emailLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(emailField, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(createButton, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }

    public GridPane enrollNewBookGrid() {
        var grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        var titleLabel = new Label("Title");
        var titleField = new TextField();
        var ISBNLabel = new Label("ISBN");
        var ISBNField = new TextField();
        var authorLabel = new Label("Author");
        var authorField = new TextField();
        var genreLabel = new Label("Genre");
        var genreField = new TextField();
        var publicationLabel = new Label("Publication year");
        var publicationField = new TextField();
        var enrollButton = new Button("Enroll");

        // enrollButton.setOnAction(event -> User.createUser(titleField.getText()));

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(ISBNLabel, 0, 1);
        grid.add(ISBNField, 1, 1);
        grid.add(authorLabel, 0, 2);
        grid.add(authorField, 1, 2);
        grid.add(genreLabel, 0, 3);
        grid.add(genreField, 1, 3);
        grid.add(publicationLabel, 0, 4);
        grid.add(publicationField, 1, 4);
        grid.add(enrollButton, 1, 5);

        GridPane.setConstraints(titleLabel, 0, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(titleField, 1, 0, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ISBNLabel, 0, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(ISBNField, 1, 1, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(authorLabel, 0, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(authorField, 1, 2, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(genreLabel, 0, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(genreField, 1, 3, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(publicationLabel, 0, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(publicationField, 1, 4, 1, 1, HPos.RIGHT, VPos.CENTER);
        GridPane.setConstraints(enrollButton, 1, 6, 1, 1, HPos.RIGHT, VPos.CENTER);

        grid.setAlignment(Pos.CENTER);

        return grid;
    }
}
