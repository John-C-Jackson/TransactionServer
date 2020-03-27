package locks;
import java.util.ArrayList;

import constants.LockTypes;
import transactions.Transaction;

public class LockManager implements LockTypes
{
    private ArrayList<Lock> locks;
    private static boolean lockIt;

    public LockManager(boolean lockIt)
    {
      this.lockIt = lockIt;
    }

    // taken from book pseudocode... I'm thinking Object should be changed to Account.
    public void setLock(Account account, Transaction trans, int lockType)
    {
        Lock foundLock = new Lock();
        if(!lockIt)
        {
          return;
        }

        Lock lock;

        synchronized(this)
        {
            // find the lock associated with object
            lock = locks.get(account);

            // if there isn't one, create it and add it to list
            if(lock = null)
            {
              lock = new Lock(account);

              // add the lock to the array ?????? ----- he used a hash map
              // something is missing here 
            }
        }

        lock.acquire(trans, lockType);
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
