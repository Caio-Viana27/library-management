package br.edu.ifba.inf008.shell;

import java.util.HashMap;

import br.edu.ifba.inf008.interfaces.ILoanController;
import br.edu.ifba.inf008.interfaces.IUser;
import br.edu.ifba.inf008.interfaces.IBook;

public class LoanController implements ILoanController {
    public static LoanController loanController;

    public LoanController() {
    }
}
