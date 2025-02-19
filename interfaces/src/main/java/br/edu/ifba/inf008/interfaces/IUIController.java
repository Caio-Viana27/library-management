package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public interface IUIController {
    public abstract MenuItem createMenuItem(String menuText, String menuItemText, GridPane newGrid);

    public abstract boolean createTab(String tabText, Node contents);

    public abstract GridPane createUserGrid();

    public abstract GridPane enrollNewBookGrid();

    public abstract GridPane loanBookGrid();
}
