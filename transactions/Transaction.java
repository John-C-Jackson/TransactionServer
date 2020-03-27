package transactions;

import java.util.ArrayList;
import transaction_server.TransactionServer;
import locks.Lock;



public class Transaction
{
    ArrayList<Lock> locks = null;
    int transactionID;
    StringBuffer log = new StringBuffer("");



    Transaction(int transactionID)
    {
      this.transactionID= transactionID;
      this.locks = new ArrayList();
    }


    public int getID()
    {
      return transactionID;
    }

    // temporary skeleton code & stub return
    public int read(int accountId)
    {
    }

    // temporary skeleton code & stub return
    public void write(int accountId, int balance)
    {

    }

    // not entirely sure what close is meant to do...
    public void close()
    {

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
