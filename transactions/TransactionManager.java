package transactions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import constants.MessageTypes;

public class TransactionManager implements MessageTypes
{
    /*
     * Transaction Manager Class
     *  - [] handles all read/write requests
     *  - [] per transaction, open one connection until transaction is closed
     */
    
    static final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static int transactionCounter = 0;
    
    public TransactionManager() {};
    
    public ArrayList<Transaction> getTransactions()
    {
        return transactions;
    }
    
    public void runTransaction(Socket client)
    {
        (new TransactionManagerWorker(client)).start();
    }
    
    
//    needs a lot more logic in this class (i.e. handle READ, WRITE, OPEN and CLOSE messages)
    public class TransactionManagerWorker extends Thread
    {
        Socket client = null;
        ObjectInputStream readFrom = null;
        ObjectOutputStream readoTo = null;
        
        Transaction transaction = null;
        int accountNumber = 0;
        int balance = 0;
        
        public TransactionManagerWorker(Socket client)
        {
            this.client = client;
            
            // open input and output streams here.
        }
        
        
        @Override
        public void run()
        {
            
        }
    }
}
