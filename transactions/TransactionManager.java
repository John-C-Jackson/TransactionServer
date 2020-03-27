package transactions;

import java.io.*;
import java.net.*;
import java.util.*;

import java.util.ArrayList;

public class TransactionManager  implements MessageTypes
{
    /*
     * Transaction Manager Class
     *  - [] handles all read/write requests
     *  - [] per transaction, open one connection until transaction is closed
     */

    private static final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private static int transactionCounter = 0;
    boolean flag;

    public TransactionManager()
    {

    }

    public ArrayList<Transaction> getTransactions()
    {
      return transactions;
    }

    public void runTrans(Socket client)
    {
      (new TransactionManagerWorker(client).start());
    }

    private class TransactionManagerWorker extends Thread
    {
      Socket client = null;
      ObjectInputStream readFromNetwork = null;
      ObjectInputStream writeToNetwork = null;

      Message message = null;

      Transaction transaction = null;
      int accountNumber = 0 ;
      int balance = 0 ;

      boolean continueProcess;

      private TransactionManagerWorker(Socket client)
      {
        this.client = client;
        try
        {
          readFromNetwork = new ObjectInputStream(client.getInputStream());
          writeToNetwork = new ObjectInputStream(client.getInputStream());
        }
        catch(IOException ex)
        {
          System.exit(1);
        }

      }

      @Override
      public void run()
      {
        while(continueProcess)
        {
          try
          {

          }
          catch(IOException e)
          {

          }

          switch(message.getType())
          {
            case OPEN_TRANSACTION:
              break;
            case WRITE_REQUEST:
              break;
            case READ_TRANSACTION:
              break;
            case CLOSE_TRANSACTION:
              break;
          }

        }

      }


    }


}
