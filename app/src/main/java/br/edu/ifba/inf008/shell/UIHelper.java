package br.edu.ifba.inf008.shell;

import javafx.scene.control.ToggleButton;

import java.util.ArrayList;

public class UIHelper {
    public static int MAX_VALUE = 5;
    public static ArrayList<ToggleButton> buttonList = null;
    public static ArrayList<ToggleButton> selectedButtons = null;

    public static void updateButtonStates() {
        boolean maxReached = buttonList.size() >= MAX_VALUE;

        for (var button : buttonList) {
            if (!selectedButtons.contains(button)) {
                button.setDisable(maxReached);
            }
        }
    }
}
