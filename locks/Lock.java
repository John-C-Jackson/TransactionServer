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
            this.lockType = lockType;
            trans.addLock(this);
			trans.log("[Lock.acquire] set " + lockType.typeToString() + " on account number " + account.getAccountNumber());
		}
		else if (this.lockType.getType() == NO_LOCK)
		{
			this.lockType = lockType;
			if (!isHolder(trans))
			{
				holders.add(trans);
			}
			trans.log("[Lock.acquire] set " + lockType.typeToString() + " on account number " + account.getAccountNumber());
		}
        // if another transaction has a read lock, share it
        else if(!holders.isEmpty() && this.lockType.getType() == READ_LOCK && lockType.getType() == READ_LOCK && !holders.contains(trans))
        {
			holders.add(trans);
			trans.log("[Lock.acquire] sharing " + lockType.typeToString() + " on account number " + account.getAccountNumber());
        }
        // if this transaction is a holder but needs a more exclusive lock
        else if (holders.contains(trans) && this.lockType.getType() == READ_LOCK && lockType.getType() == WRITE_LOCK)
        {
			trans.log("[Lock.acquire] promote " + this.lockType.typeToString() + " to " + lockType.typeToString() + " on account " + account.getAccountNumber());
            this.lockType.promote();
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

        lockType.setNone();
		trans.log("[Lock.release] account " + account.getAccountNumber() + " now has no locks");

        notifyAll();
    }

    // TO-DO: implement this function
    public boolean isConflict(Transaction trans, LockType type)
    {
		if (type.getType() == NO_LOCK)
		{
			return false;
		}
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
        else if (this.lockType.getType() == WRITE_LOCK || this.lockType.getType() == WRITE_LOCK)
        {
            // if there is a write lock already, then theres a conflict.
			trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " conflicts with " + type.typeToString());
            return true;
        }
		trans.log("[Lock.isConflict] current Lock: " + this.lockType.typeToString() + " on account number " + account.getAccountNumber() + " does not conflict with " + type.typeToString());
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
