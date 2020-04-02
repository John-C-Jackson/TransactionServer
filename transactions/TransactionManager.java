package transactions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import constants.MessageTypes;
import transaction_server.TransactionServer;
import messages.Message;

import java.util.*;
import java.io.IOException;

public class TransactionManager implements MessageTypes
{
    /*
     * Transaction Manager Class
     *  - [] handles all read/write requests
     *  - [] per transaction, open one connection until transaction is closed
     */

    static final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static int transactionCounter = 0;

    public TransactionManager() {}

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
        ObjectOutputStream writeTo = null;

        Transaction transaction = null;
        int accountNumber = 0;
        int balance = 0;

        boolean continueLooping = true;
        Message message = null;

        public TransactionManagerWorker(Socket client)
        {
            this.client = client;

            // streams set up
            try
            {
              readFrom = new ObjectInputStream(client.getInputStream());
              writeTo = new ObjectOutputStream(client.getOutputStream());
            }
            catch(IOException e)
            {
              System.out.println("TransactionManagerWorker failed to open streams");
              e.printStackTrace();
              System.exit(1);
            }


        }


        @Override
        public void run()
        {
          while(continueLooping)
          {
            try
            {
              message = (Message) readFrom.readObject();

            }
            catch(IOException | ClassNotFoundException e)
            {
              System.out.println(" TransactionManagerWorker run() cannot read object");
              System.exit(1);

            }
            // gets type of message
            // comparesw to what kind of message
            switch (message.getType())
            {
// -------------------------------------------------------------------------------------------------------------
              case OPEN_TRANSACTION:
                synchronized(transactions)
                {
                  // create a new transaction
                  transaction = new Transaction(transactionCounter++);
                  //add it to the rest fo the transactions
                  transactions.add(transaction);
                }

                try
                {
                  // write to the object
                  writeTo.writeObject(transaction.getID());

                }
                catch(IOException e )
                {
                  System.out.println("TransactionManagerWorker OPEN_TRANSACTION experienced error when reading");
                }

                transaction.log("[TransactionManagerWorker.run] OPEN_TRANSACTION >>>>>> Transaction Number: "+ transaction.getID());
                break;
// -------------------------------------------------------------------------------------------------------------
              case READ_TRANSACTION:
                // get the content of the message
                accountNumber = (Integer) message.getContent();
                // read the balance from the acccount manager
                balance = TransactionServer.accountMgr.readBalance(accountNumber, transaction);
                try
                {
                  // the the balance
                  writeTo.writeObject((Integer) balance);
                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker READ_TRANSACTION experienced error when reading");

                }

                transaction.log("[TransactionManagerWorker.run] READ_TRANSACTION >>>>>> Account Number: "+ accountNumber + " Balance: " + balance );

                break;
// -------------------------------------------------------------------------------------------------------------
              case WRITE_TRANSACTION:
              // create an object
                int[] content = (int[]) message.getContent();
                // read account number from content
                accountNumber = content[0];
                // read the balance from content
                balance = content[1];
                // get balancefrom the account
                balance = TransactionServer.accountMgr.writeBalance(accountNumber, transaction, balance);

                try
                {
                  // write the balance
                  writeTo.writeObject((Integer) balance);
                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker WRITE_TRANSACTION experienced error when writing");

                }

                transaction.log("[TransactionManagerWorker.run] WRITE_TRANSACTION  >>>>>> Account Number: "+accountNumber
                                    +" New Balance: "+ balance);

                break;
//--------------------------------------------------------------------------------------------------------------
              case CLOSE_TRANSACTION:
              // unlock the transaction
                TransactionServer.lockMgr.unlock(transaction);
                // remove the tranaction
                transactions.remove(transaction);
                try
                {
                  // close everything opened
                  readFrom.close();
                  writeTo.close();
                  client.close();
                  continueLooping = false;
                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker CLOSE_TRANSACTION experienced error when closing");
                }
                transaction.log("[TransactionManagerWorker.run] CLOSE_TRANSACTION  >>>>>> Transaction: "+ transaction.getID());

                if(TransactionServer.transView)
                {
                  System.out.println(transaction.getLog());
                }

                break;

//--------------------------------------------------------------------------------------------------------------

          }

      }

    }
  }
}
