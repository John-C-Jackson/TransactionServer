package locks;
import java.util.ArrayList;

import accounts.Account;
import constants.LockTypes;
import transactions.Transaction;

public class LockManager implements LockTypes
{
    private ArrayList<Lock> locks;
    
    // taken from book pseudocode... I'm thinking Object should be changed to Account.
    public void setLock(Account account, Transaction trans, LockType lockType)
    {
        boolean lockExists = false;
        Lock foundLock = new Lock(account);
        synchronized(this)
        {
            // find the lock associated with object
            // if there isn't one, create it and add it to list
            for (Lock lockIndex : locks)
            {
                if (lockIndex.getAccount().getAccountNumber() == account.getAccountNumber())
                {
                    foundLock = lockIndex;
                    lockExists = true;
                }
            }
            
            if (!lockExists)
            {
                locks.add(foundLock);
            }
            
        }
        foundLock.acquire(trans, lockType);
    }
    
    // synchronized this one because we want to remove all entries
    public synchronized void unlock(Transaction trans)
    {
        for (Lock lock : trans.getLocks())
        {
            lock.release(trans);
        }
    }
}
