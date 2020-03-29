package transaction_client;

public class TransactionClient implements Runnable
{
    private static TransactionServerProxy serverProxy;
    public int numberOfTransactions;
    public static int numberOfAccounts;
    
    public static int initialBalance;
    public String host;
    public int port;
    
    public TransactionClient()
    {
        
    }
    
    @Override
    public void run ()
    {
        // TODO Auto-generated method stub
        
    }

}
