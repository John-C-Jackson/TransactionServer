package transaction_client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TransactionServerProxy
{
    String host;
    int port;

    private Socket dbConnection = null;
    private ObjectOutputStream writeTo = null;
    private ObjectInputStream readFrom = null;
    private int transId = 0;

    public TransactionServerProxy(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    // do a socket.accept and establish input/ output streams.
    // figure out which transaction were connecting with
    public int openTransaction()
    {
      try
      {
        // ServerSocket serverSocket = new ServerSocket. //something like getport ("PORT");

        // keeps track of the number of client connections
        int clientCounter = 0;

        System.out.println("Server established ..");

        while(true)
        {
          clientCounter ++;

        //  Socket clientSocket = serverSocket.accept();


        }



      }
      catch( Exception ex)
      {
        System.out.println("Error occur in TransactionServerProxy");
        ex.printStackTrace();
      }

        return 0;
    }

    // close socket connection
    public void closeTransaction()
    {

    }

    public int read(int accountNumber)
    {
      Integer balance = null;
  //    Message readMessage = new Message(READ_TRANSACTION,accountNumber);
      try
      {
      //  writeTo .writeObject(readMessage);

      }
      catch(Exception ex)
      {
        System.out.println("Error occur in TransactionServerProxy");
        ex.printStackTrace();
      }
      // null balance as of right now
        return balance;
    }

    public int write(int accountNumber, int amount)
    {

    //  Message writeMessage = new Message( WRITE_TRANSACTION);
      Integer balance = null;

      try
      {
        // TO do
      }
      catch (Exception ex)
      {
        System.out.println( "Error occur in TransactionServerProxy");
        ex.printStackTrace();
      }

      return balance;
      }


}
