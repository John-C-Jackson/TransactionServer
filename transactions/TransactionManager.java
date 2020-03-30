package transactions;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import constants.MessageTypes;
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

            switch (message.getType())
            {
// -------------------------------------------------------------------------------------------------------------
              case OPEN_TRANSACTION:
                synchronized(transactions)
                {
                  transaction = new Transaction(transactionCounter++);
                  transactions.add(transaction);
                }
                try
                {
                  writeTo.writeObject(transaction.getID());

                }
                catch(IOException e )
                {
                  System.out.println("TransactionManagerWorker OPEN_TRANSACTION experienced error when reading");
                }
                break;
// -------------------------------------------------------------------------------------------------------------
              case READ_TRANSACTION:
                accountNumber = (Integer) message.getContent();
              //  balance = TransactionServer.accountManager.read(accountNumber, transaction);
                try
                {
                  writeTo.writeObject((Integer) balance);
                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker READ_TRANSACTION experienced error when reading");

                }


                break;
// -------------------------------------------------------------------------------------------------------------
              case WRITE_TRANSACTION:
                Object[] content = (Object[]) message.getContent();
                accountNumber = ( (Integer) content[0]);
                balance = ((Integer) content[1]);
            //    balance = TransactionServer.accountManager.write(accountMgr, transaction, balance);

                try
                {
                  writeTo.writeObject((Integer) balance);
                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker WRITE_TRANSACTION experienced error when writing");

                }

                break;
//--------------------------------------------------------------------------------------------------------------
              case CLOSE_TRANSACTION:
                //    TransactionServer.LockManager.unlock(transaction);
                transactions.remove(transaction);
                try
                {
                  readFrom.close();
                  writeTo.close();
                  client.close();
                  continueLooping = false;

                }
                catch(IOException e)
                {
                  System.out.println("TransactionManagerWorker CLOSE_TRANSACTION experienced error when closing");
                }
                break;

//--------------------------------------------------------------------------------------------------------------

          }

      }

    }
  }
}
