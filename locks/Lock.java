package locks;
import java.util.ArrayList;
import java.util.HashMap;

import accounts.Account;
import constants.LockTypes;
import transactions.Transaction;

public class Lock implements LockTypes
{
    
    private final Account account;                          // object being protected by lock
    private final ArrayList<Transaction> holders;         // Transactions holding the object
    private LockType lockType;
    
    // I think this hashmap is optional (not in book psuedocode.) commenting out for now.
//    private final HashMap<Transaction, Object[]> lockRequestors;
    
    public Lock(Account account) 
    {
        this.account = account;
        this.holders = new ArrayList<Transaction>();
        this.lockType = new LockType(NO_LOCK);
        
//        this.lockRequestors = new HashMap<Transaction, Object[]>();
        
    }
    
    public synchronized void acquire(Transaction trans, LockType lockType)
    {
        while(isConflict(trans, lockType))
        {
            try 
            {
//                addRequestor(trans, lockType);
                wait();
//                removeRequestor(trans);
            }
            catch(InterruptedException e)
            {
                System.err.println(e);
            }
        }
        // if no other transactions, hold lock
        if(holders.isEmpty()) {
            holders.add(trans);
            this.lockType = lockType;
            trans.addLock(this);
        }
        // if another transaction has a read lock, share it
        else if(this.lockType.getType() == READ_LOCK)
        {
            if (!isHolder(trans))
            {
                holders.add(trans);
                trans.addLock(this);
            }
        }
        // if this transaction is a holder but needs a more exclusive lock
        // TO-DO: This probably needs a stronger condition
        else if (isHolder(trans))
        {
            this.lockType.promote();
        }
    }
    
    public synchronized void release(Transaction trans)
    {
        holders.remove(trans);
        
        if (holders.isEmpty())
        {
            lockType.setNone();
        }
        
        notifyAll();
    }
    
    // TO-DO: implement this function
    public boolean isConflict(Transaction trans, LockType type)
    {
        if (holders.isEmpty())
        {
            // no lock holders => no conflict
            return false;
        }
        else if (holders.size() == 1 && holders.contains(trans))
        {
            // if this transaction is the only holder, return false.
            return false;
        }
        else if (this.lockType.getType() == WRITE_LOCK || type.getType() == WRITE_LOCK)
        {
            // if the existing lock or the new lock is a WRITE lock, then there is a conflict. 
            return true;
        }
    
        return false;
    }
    
    public boolean isHolder(Transaction trans)
    {
        if (holders.contains(trans))
        {
            return true;
        }
        return false;
    }
    
    public Account getAccount()
    {
        return account;
    }
//    
//    public void addRequestor(Transaction trans, LockType locktype)
//    {
//        
//    }
//    
//    public void removeRequestor(Transaction trans)
//    {
//        
//    }
    
}   

