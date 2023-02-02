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
//     ## 2: Our API should be able to process User logins.

// As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a 
// JSON representation of an Account, not containing an account_id. In the future, this action may generate a Session token to allow 
// the user to securely use the site. We will not worry about this for now.

// - The login will be successful if and only if the username and password provided in the request body JSON match a real account 
// existing on the database. If successful, the response body should contain a JSON of the account in the response body, including its account_id. 
// The response status should be 200 OK, which is the default.
// - If the login is not successful, the response status should be 401. (Unauthorized)
    public Account checkLogin(Account account) {
         return accountDAO.checkLogin(account);
    }
}
