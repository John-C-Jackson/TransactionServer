package transactions;
import java.util.ArrayList;

import locks.Lock;

/*
 * Transaction Class
 *  - [] read
 *  - [] write
 *  - [] close
 */
public class Transaction
{
    int transID;
    ArrayList<Lock> locks = null;
    
    Transaction(int transID) 
    {
        this.transID = transID;
        this.locks = new ArrayList<Lock>();
    }
    
    public ArrayList<Lock> getLocks()
    {
        return locks;
    }
    
    public int getID()
    {
        return transID;
    }
    
    public void addLock(Lock lock)
    {
        locks.add(lock);
    }
}
