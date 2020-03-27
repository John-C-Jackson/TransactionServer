package transactions;
import java.util.ArrayList;

import locks.Lock;


public class Transaction
{
    ArrayList<Lock> locks = null;
    int transactionID;



    Transaction(int transactionID)
    {
      this.transactionID= transactionID;
      this.locks = new ArrayList();
    }


    public int getID()
    {
      return transactionID;
    }

    // not sure if needed? --- i think it is .....
    public ArrayList<Lock> getLocks()
    {
        return locks;
    }

    public void addLock(Lock lock)
    {
      locks.add(lock);
    }

    // temporary skeleton code & stub return
    public int read(int accountId)
    {
        return 0;
    }

    // temporary skeleton code & stub return
    public void write(int accountId, int balance)
    {

    }

    // not entirely sure what close is meant to do...
    public void close()
    {

    }
}
