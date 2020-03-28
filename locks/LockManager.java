package locks;
import java.util.ArrayList;

import constants.LockTypes;
import transactions.Transaction;

public class LockManager implements LockTypes
{
    private ArrayList<Lock> locks;
    
    // taken from book pseudocode... I'm thinking Object should be changed to Account.
    public void setLock(Object object, Transaction trans, LockType lockType)
    {
        Lock foundLock = new Lock();
        synchronized(this)
        {
            // find the lock associated with object
            // if there isn't one, create it and add it to list
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
