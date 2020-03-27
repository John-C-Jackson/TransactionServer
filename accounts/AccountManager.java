package accounts;
import java.util.ArrayList;
import transactions.Transaction;
import transaction_server.TransactionServer;
import locks.LockType;

/*
 * Account Manager Class:
 *  - [X] Works on accounts (read & write method)
 *  - [X] initializes full set of needed accounts
 *  - [X] provides access to accounts
 *
 */
public class AccountManager
{
    private static ArrayList<Account> accounts;
    private static int numberAccounts;
    private static int initialBalance;

    public AccountManager(int numAccounts, int initialBalance)
    {
        accounts = new ArrayList();
        AccountManager.numberAccounts = numberAccounts;
        AccountManager.initialBalance = initialBalance;

        for (int index = 0; index < numberAccounts; index++)
        {
            accounts.add(index, new Account(index, initialBalance));
        }
    }

    // returns the account object with this number
    public Account getAccount(int accountNumber)
    {
        return accounts.get(accountNumber);
    }

    // returns list of accounts
    public ArrayList<Account> getAccounts()
    {
        return accounts;
    }

    // reads the balance of an account
    public int readBalance(int accountNumber, Transaction transaction)
    {
    	// get the account
        Account account = getAccount(accountNumber);

        // set the read lock
        (TransactionServer.lockManager).lock(account, transaction, READ_LOCK);

        // above call may wait (or deadlock), until it continues here
        return getAccount(accountNumber).getAccountBalance();
    }

    // sets the balance of an account (accountNumber) to newBalance
    public void writeBalance(int accountNumber, Transaction transaction, int newBalance)
    {
    	// get the account
        Account account = getAccount(accountNumber);

        // set the read lock
        (TransactionServer.lockManager).lock(account, transaction, READ_LOCK);

        // above call may wait (or deadlock), until it continues here
        account.setAccountBalance(newBalance);
        return balance;
    }

}
