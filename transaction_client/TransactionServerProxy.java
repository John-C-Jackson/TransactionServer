package transaction_client;

import java.io.*;
import java.net.*;
import java.util.*;
import messages.Message;
import constants.MessageTypes;


public class TransactionServerProxy implements MessageTypes
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
        dbConnection = new Socket( host, port);



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
      Message readMessage = new Message(READ_TRANSACTION,accountNumber);

      try
      {
       writeTo .writeObject(readMessage);
       balance = (Integer) readFrom.readObject();

      }
      catch(Exception ex)
      {
        System.out.println("Error occur in TransactionServerProxy");
        ex.printStackTrace();
      }

        return balance;
    }

    public int write(int accountNumber, int amount)
    {

      Message writeMessage = new Message( WRITE_TRANSACTION , accountNumber);
      Integer balance = null;

      try
      {
        writeTo.writeObject(writeMessage);

        // TODO
      }
      catch (Exception ex)
      {
        System.out.println( "Error occur in TransactionServerProxy");
        ex.printStackTrace();
      }

      return balance;
      }


}
