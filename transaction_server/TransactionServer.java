package transaction_server;
import accounts.AccountManager;
import locks.LockManager;
import transactions.TransactionManager;
import utils.PropertyHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Properties;


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


    public TransactionServer(String propertiesFile)
    {
      Properties handler = null;

      try
      {
          handler = new PropertyHandler(propertiesFile);
      } catch (Exception e)
      {
          System.out.println("Not found: properties file \"" + propertiesFile + "\"");
          e.printStackTrace();
          System.exit(1);
      }


      // create transaction manager
      TransactionServer.transMgr = new TransactionManager();
      transView = Boolean.valueOf(handler.getProperty("TRANSACTION_VIEW"));

      // create lock manager
      TransactionServer.lockMgr = new LockManager();
      boolean applyLocking = Boolean.valueOf(handler.getProperty("APPLY_LOCKING"));

      // create account manager
      int numberAccounts = 0;
      numberAccounts = Integer.parseInt(handler.getProperty("NUMBER_ACCOUNTS"));
      int initialBalance = 0;
      initialBalance = Integer.parseInt(handler.getProperty("INITIAL_BALANCE"));

      TransactionServer.accountMgr = new AccountManager(numberAccounts, initialBalance);


      try
        {
          int portNum = Integer.parseInt(handler.getProperty("PORT"));
          serverSocket = new ServerSocket(portNum);
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
        transMgr.runTransaction(serverSocket.accept());
      }
      catch(IOException e)
      {
      System.out.println("TransactionServer could not runTransaction");
      e.printStackTrace();

      }

      }
    }

}
