package br.edu.ifba.inf008.plugins;

import br.edu.ifba.inf008.interfaces.IPlugin;
import br.edu.ifba.inf008.interfaces.ICore;
import br.edu.ifba.inf008.interfaces.IUIController;

public class MenuItens implements IPlugin {
    public boolean init() {
        IUIController uiController = ICore.getInstance().getUIController();
        return true;
    }
}
