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
    ArrayList<Lock> locks;
    
    // not sure if needed?
    public ArrayList<Lock> getLocks()
    {
        return locks;
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
