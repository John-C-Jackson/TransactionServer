package transaction_server;
import accounts.AccountManager;
import locks.LockManager;
import transactions.TransactionManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.*;

/*
 * TransactionServer Class
 *  - [] runs server loop
 *  - [] takes requests from client side
 */
public class TransactionServer extends Thread
{
    public static AccountManager accountMgr = null;
    public static LockManager lockMgr = null;
    public static TransactionManager transMgr = null;
    public static  Boolean transView;
    public ServerSocket serverSocket = null;

    // implement server loop here

/*
    public TransactionServer()
    {
      // somehwere here read the property handler


      // create trans LockManager
      TransactionServer.TransactionManager = new TransactionManager();
      // create account lockMgr
      // create lock lockMgr
      //

      try
        {
          serverSocket = new ServerSocket.getProperty("PORT");
          System.out.println("TransactionServer ServerSocket created");
        }
        catch (IOException ex)
        {
          System.out.println("TransactionServer could not create server socket");
          ex.printStackTrace();
          System.exit(1);
        }


    }

    @Override
    public void run()
    {
      while(true)
      {
      try
      {
        TransactionManager.runTransaction(serverSocket.accept());
      }
      catch(IOException e)
      {
      System.out.println("TransactionServer could not runTransaction");
      ex.printStackTrace();

      }

      }
    }

  **/
}
