package accounts;
import java.util.ArrayList;

/*
 * Account Manager Class:
 *  - [X] Works on accounts (read & write method)
 *  - [X] initializes full set of needed accounts
 *  - [X] provides access to accounts
 *  
 */
public class AccountManager
{
    private ArrayList<Account> accounts;
    
    public AccountManager(int numAccounts, int initialBalance)
    {
        accounts = new ArrayList<Account>();
        for (int index = 0; index < numAccounts; index++)
        {
            accounts.add(new Account(index, initialBalance));
        }
    }
    
    public Account getAccount(int accountNumber)
    {
        return accounts.get(accountNumber);
    }
    
    // reads the balance of an account
    public int readBalance(int accountNumber)
    {
        return getAccount(accountNumber).getAccountBalance();
    }
    
    //sets the balance of an account (accountNumber) to newBalance
    public void writeBalance(int accountNumber, int newBalance)
    {
        getAccount(accountNumber).setAccountBalance(newBalance);
    }
    
}
