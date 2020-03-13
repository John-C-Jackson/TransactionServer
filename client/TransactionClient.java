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
    private int numberOfTransactions ;



    public TransactionClient()
    {

    }


      public void runTrans()
      {
        for ( int index = 0 ; index< numberOfTransactions; index++)
        {
            // run the run()???
        }
      }


      @Override
      public void run()
      {
        // assuming here we call to thread transaction proxy?

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

    }
