package br.edu.ifba.inf008.interfaces;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public interface IUIController {
    public abstract MenuItem createMenuItem(String menuText, String menuItemText, Node newGrid);

    public abstract boolean createTab(String tabText, Node contents);

    public abstract GridPane createUserGrid();

    public abstract GridPane enrollNewBookGrid();

    public abstract GridPane selectUserGrid(String action);

    public abstract void generateWarning(String warningMassage);

    public abstract MenuBar getMenuBar();

    public abstract TabPane getTabPane();

    public abstract Tab getTabByTitle(String title);
}
