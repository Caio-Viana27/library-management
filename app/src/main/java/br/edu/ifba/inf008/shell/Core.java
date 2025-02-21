package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.*;

public class Core extends ICore {
    private Core() {
    }

    public static boolean init() {
        if (instance != null) {
            System.out.println("Fatal error: core is already initialized!");
            System.exit(-1);
        }
        instance = new Core();

        UIController.launch(UIController.class);
        return true;
    }

    public IUIController getUIController() {
        return UIController.getInstance();
    }

    public IAuthenticationController getAuthenticationController() {
        return authenticationController;
    }

    public IIOController getIOController() {
        return ioController;
    }

    public IPluginController getPluginController() {
        return pluginController;
    }

    public IUserController getUserController() {
        return userController;
    }

    public IBookController getBookController() {
        return bookController;
    }

    public ILoanController getLoanController() {
        return businessController;
    }

    private IAuthenticationController authenticationController = new AuthenticationController();
    private IIOController ioController = new IOController();
    private IPluginController pluginController = new PluginController();
    private ILoanController businessController = new LoanController();
    private IUserController userController = new UserController();
    private IBookController bookController = new BookController();
}
