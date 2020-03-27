package client;
import java.io.*;
import java.net.*;
import java.util.*;

/*
import accounts.AccountManager;
import locks.LockManager;
import transactions.TransactionManager;
**/
/*
 * creates a client proxy
 * unsure who calls a transaction client
 */
public class TransactionClient implements Runnable
{
    private static TransactionServerProxy serverProxy;
    public int numberOfTransactions ;
    public static int numberOfAccounts;

    public static int initialBalance ;
    public String host;
    public int port;

    



    public TransactionClient()
    {
      try
      {

      }
      catch( Exception ex)
      {
        ex.printStackTrace();
      }

    }


      public void runTrans()
      {
        for ( int index = 0 ; index< numberOfTransactions; index++)
        {
            new Thread()
            {
              @Override
              public void run()
              {
                TransactionServerProxy transaction = new TransactionServer(host,port);
                int transactionID = transaction.openTransaction();


                int accountFrom = (int) Math.floor(Math.random() + numberOfAccounts)

              }

            }
        }
      }






}
    /**
    * represents a tranaction
    * keeps connection to server open
    * for the first connection it alreadys issues a open message
    */
  class TransactionServerProxy
    {
      String host;
      int port;

      public TransactionServerProxy( String host , int port)
      {

      }

      public openTransaction()
      {

      }

    }
