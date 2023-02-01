package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public Account addAccount(Account account){
        if (account.username != "" && account.password.length() >= 4) {
            return accountDAO.insertAcccount(account);
        }
        return null;
    }
    
    public Account checkLogin(Account account) {
        return accountDAO.checkLogin(account);
    }
}
