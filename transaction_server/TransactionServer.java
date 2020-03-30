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


    public TransactionServer(String serverPropertiesFile)
    {
	  Properties serverProps = new PropertyHandler(serverPropertiesFile);

      // create transaction manager
      TransactionServer.transMgr = new TransactionManager();
      transView = Boolean.valueOf(serverProps.getProperty("TRANSACTION_VIEW"));

      // create lock manager
      TransactionServer.lockMgr = new LockManager();
      Boolean applyLocking = Boolean.valueOf(serverProps.getProperty("APPLY_LOCKING"));

      // create account manager
      int numberAccounts = 0;
      numberAccounts = Integer.parseInt(serverProps.getProperty("NUMBER_ACCOUNTS"));
      int initialBalance = 0;
      initialBalance = Integer.parseInt(serverProps.getProperty("INITIAL_BALANCE"));

      TransactionServer.accountMgr = new AccountManager(numberAccounts, initialBalance);


      try
	  {
	     int portNum = Integer.parseInt(serverProps.getProperty("PORT"));
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
		System.out.println("running...");
		int index = 0;
      while(true)
      {
	      try
	      {
	        transMgr.runTransaction(serverSocket.accept());
			System.out.println(index);
	      }
	      catch(IOException e)
	      {
	        System.out.println("TransactionServer could not runTransaction");
	        e.printStackTrace();
	      }
		  index ++;

      }
    }

}
