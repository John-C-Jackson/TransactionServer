package transaction_client;
import java.util.Properties;
import utils.PropertyHandler;

public class TransactionClient extends Thread
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
      int index;
      for( index = 0 ; index < numberOfTransactions; index++ )
      {

        new Thread(){
          @Override
          public void run(){

            TransactionServerProxy transaction = new TransactionServerProxy(host, port);
            int transId = transaction.openTransaction();
            System.out.println("Transaction Number: " + transId + "has started");

            int accountFrom = (int) Math.floor(Math.random() * numberOfAccounts);
            int accountTo = (int) Math.floor(Math.random() * numberOfAccounts);
            int amount = (int) Math.ceil(Math.random() * initialBalance);
            int balance;

            System.out.println("Transaction Number : " + transId + " Amount: $"
                          + amount + " Transfered from: " + accountFrom +"to " + accountTo);
           balance= transaction.read(accountFrom);
           transaction.write(accountFrom , balance - amount);

           balance = transaction.read(accountTo);
           transaction.write( accountTo, balance + amount);

           transaction.closeTransaction();

           System.out.println("Transaction Number: " + transId + " completed");
      }
    }.start();
  }
}



// ottte had this and im unsure extactly why...

public static void main(String[] args)
{
  (new TransactionClient("../../config/TransactionClient.properties", "../../config/TransactionServer.properties")).start();
}
}
