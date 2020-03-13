package transaction_server;

import java.io.*;
import java.net.*;
import java.util.*;


import accounts.AccountManager;
import locks.LockManager;
import transactions.TransactionManager;

/*
 * TransactionServer Class
 *  - [] runs server loop
 *  - [] takes requests from client side
 */
public class TransactionServer extends Thread
{
    private static AccountManager accountMgr;
    private static LockManager lockMgr;
    private static TransactionManager transMgr;

    /*
       he mentioned that the following have to be known by the server
       i assuming that he is referring to the TransactionServer
    **/

    int port;
    String host;
    int numberOfAccouts;
    int initialBalance;

    // implement server loop here

    public TransactionServer()
    {



      // server loop
      while (true)
      {

        public void run()
        {

      //    transMgr.runTrans(serverSocket.accept())
        }

      }

    }



}
