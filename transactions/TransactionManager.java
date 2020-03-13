package transactions;

import java.io.*;
import java.net.*;
import java.util.*;

import java.util.ArrayList;

public class TransactionManager // implements MessageTypes
{
    /*
     * Transaction Manager Class
     *  - [] handles all read/write requests
     *  - [] per transaction, open one connection until transaction is closed
     */

    static final ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    static int transactionCounter = 0;
    boolean flag;



    /*
     * read
     * write
     * close
     * open
    **/

    private class TransactionManagerWorker extends Thread
    {



    }


}
