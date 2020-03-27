package transactions;

import java.util.ArrayList;
import transaction_server.TransactionServer;
import locks.Lock;

/*
 * Transaction Class
 *  - [] getID
 *  - [] getLocks
 *  - [] addLock
 *  - [] log
 *  - [] getLog
 */
public class Transaction
{
    ArrayList<Lock> locks = null;
    int transID;
    
    StringBuffer log = new StringBuffer("");
    
    // gets transaction ID
    public int getID()
    {
        return transID;
    }
    
    // gets locks
	public ArrayList<Lock> getLocks()
    {
        return locks;
    }
    
	// add new lock
	public void addLock(Lock lock)
	{
        locks.add
	}
	
	// append to transaction log
	public void log (String logString)
	{
        log.append("\n").append(logString);
        
        if (!TransactionServer.transactionView) {
            System.out.println("Transaction # " + this.getID() + ((this.getID() < 10) ? " " : "") + logString);
        }
	}
	
	public StringBuffer getLog()
	{
        return log;
	}
}
