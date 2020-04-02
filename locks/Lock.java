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
    private LockType currentLockType;
    private LockType lockType;



    public Lock(Account account)
    {
        this.account = account;
        this.holders = new ArrayList<Transaction>();
        this.lockType = new LockType(NO_LOCK);


    }

    public synchronized void acquire(Transaction trans, LockType lockType)
    {
        while(isConflict(trans, lockType))
        {
			trans.log("[Lock.acquire] try " + lockType.typeToString() + " on account number " + account.getAccountNumber());
            try
            {
				trans.log("[Lock.acquire] wait to set " + lockType.typeToString() + " on account number " + account.getAccountNumber());
                wait();
				trans.log("[Lock.acquire] finished wait, setting " + lockType.typeToString() + " on account number " + account.getAccountNumber());
            }
            catch(InterruptedException e)
            {
                System.err.println(e);
            }
        }
        // if no other transactions, hold lock
        if(holders.isEmpty()) {
            holders.add(trans);
            this.currentLockType = lockType;
            trans.addLock(this);
			trans.log("[Lock.acquire] set " + lockType.typeToString() + " on account number " + account.getAccountNumber());
		}
        // if another transaction has a read lock, share it
        else if(!holders.contains(trans))
        {
            holders.add(trans);
            trans.addLock(this);
			trans.log("[Lock.acquire] sharing " + lockType.typeToString() + " on account number " + account.getAccountNumber());
        }
        // if this transaction is a holder but needs a more exclusive lock
        // TO-DO: This probably needs a stronger condition
        else if (holders.size() == 1 && currentLockType.getType() == READ_LOCK && lockType.getType() == WRITE_LOCK)
        {
			trans.log("[Lock.acquire] promote " + this.lockType.typeToString() + " to " + lockType.typeToString() + " on account " + account.getAccountNumber());
        //    this.lockType.promote();
        this.currentLockType = lockType;

        }
  		else
  		{
  			trans.log("[Lock.acquire] ignore setting " + this.lockType.typeToString() + " to " + lockType.typeToString()  + " on account " + account.getAccountNumber());
  		}
    }

    public synchronized void release(Transaction trans)
    {
        holders.remove(trans);
		trans.log("[Lock.release] releasing lock on account " + account.getAccountNumber());

        if (holders.isEmpty())
        {
            lockType.setNone();
			trans.log("[Lock.release] account " + account.getAccountNumber() + " now has no locks");
        }

        notifyAll();
    }

    public boolean isConflict(Transaction trans, LockType type)
    {
        if (holders.isEmpty())
        {
			trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " does not conflict with " + type.typeToString());
            // no lock holders => no conflict
            return false;
        }
        else if (holders.size() == 1 && holders.contains(trans))
        {
            // if this transaction is the only holder, return false.
			trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " does not conflict with " + type.typeToString());
            return false;
        }
        else if ( this.currentLockType.getType()  == READ_LOCK && type.getType()  == READ_LOCK)
        {
          return false;
        }
        else
        {
          trans.log("[Lock.isConflict] | current lock :  " + currentLockType.typeToString() + " hold by transaction");
          return true;
        }

/*
        else if (this.lockType.getType() == WRITE_LOCK)
        {
            // if there is a write lock already, then theres a conflict.
			trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " conflicts with " + type.typeToString());
            return true;
        }
		trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " does not conflict with " + type.typeToString());
        return false;


**/
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
