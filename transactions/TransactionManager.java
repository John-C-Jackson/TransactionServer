package transactions;
import java.util.ArrayList;

public class TransactionManager
{
    /*
     * Transaction Manager Class
     *  - [] handles all read/write requests
     *  - [] per transaction, open one connection until transaction is closed
     */
    
    static final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static int transactionCounter = 0;
    
    private class TransactionManagerWorker
    {
        
    }
}
