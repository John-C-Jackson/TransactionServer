package transaction_client;
import java.util.Properties;
import utils.PropertyHandler;

public class TransactionClient implements Runnable
{
    private static TransactionServerProxy serverProxy;
    public int numberOfTransactions;
    public static int numberOfAccounts;
    public static int initialBalance;
    public int port;
    public String host;
    
    public TransactionClient(String clientPropertiesFile, String serverPropertiesFile)
    {
        Properties serverProps = new PropertyHandler(serverPropertiesFile);
        Properties clientProps = new PropertyHandler(clientPropertiesFile);
        
        host = serverProps.getProperty("HOST");
        port = Integer.parseInt(serverProps.getProperty("PORT"));
        numberOfAccounts = Integer.parseInt(serverProps.getProperty("NUMBER_ACCOUNTS"));
        initialBalance = Integer.parseInt(serverProps.getProperty("INITIAL_BALANCE"));
        
        numberOfTransactions = Integer.parseInt(serverProps.getProperty("NUMBER_TRANSACTIONS"));
    }
    
    @Override
    public void run ()
    {
        // TODO Auto-generated method stub
        
    }

}
