package locks;
import java.util.ArrayList;

import constants.LockTypes;
import transactions.Transaction;

public class Lock implements LockTypes
{
    // object needs to be replaced with Account??
    private Object object;                          // object being protected by lock
    private ArrayList<Transaction> holders;         // Transactions holding the object
    private int currentLockType;
    private Account account;

    public Lock( Account account)
    {
      this.holders = new ArrayList();
      this.account = account ;
      this.currentLockType = EMPTY_LOCK;
    }

    public synchronized void acquire(Transaction trans, LockType lockType)
    {
        while(isConflict(transaction, lockType))
        {
            try
            {
                wait();
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
        }
        // if another transaction has a read lock, share it
        else if(this.lockType.getType() == READ_LOCK)
        {
            if (!isHolder(trans))
            {
                holders.add(trans);
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
        // set locktype to none -- From book pseudocode..
        // not sure why we would set to none... can't there still be
        // other transactions waiting on this object?

        if (holders.isEmpty())
        {
          this.currentLockType = EMPTY_LOCK;

        }
      //  lockType.setNone();
        notifyAll();
    }

    // TO-DO: implement this function
    public boolean isConflict( Transaction trans, int lockType)
    {
        if(holders.isEmpty())
        {
          return false;
        }
        else if(holders.size() == 1 && holders.contains(tranaction))
        {
          return false;
        }
        return true;
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

    /*

    public synchronized int getType()
    {
      return currentLockType;
    }
    **/
}
