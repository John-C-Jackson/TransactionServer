package accounts;
import java.util.ArrayList;

import transaction_server.TransactionServer;
import transactions.Transaction;
import constants.LockTypes;
import locks.LockType; 

/*
 * Account Manager Class:
 *  - [X] Works on accounts (read & write method)
 *  - [X] initializes full set of needed accounts
 *  - [X] provides access to accounts
 *  
 */
public class AccountManager implements LockTypes
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
    public int readBalance(int accountNumber, Transaction trans)
    {
        // grab corresponding account
        Account acc = getAccount(accountNumber);
        
        // acquire a read lock.
        (TransactionServer.lockMgr).setLock(acc, trans, new LockType(READ_LOCK));
        
        // return the account balance
        return acc.getAccountBalance();
    }
    
    //sets the balance of an account (accountNumber) to newBalance
    public int writeBalance(int accountNumber, Transaction trans, int newBalance)
    {
        // grab corresponding account
        Account acc = getAccount(accountNumber);
        
        // acquire a read lock.
        (TransactionServer.lockMgr).setLock(acc, trans, new LockType(WRITE_LOCK));
        
        //set acc's balance to the new balance
        acc.setAccountBalance(newBalance);
        
        // return the account balance
        return newBalance;
    }
    
}
