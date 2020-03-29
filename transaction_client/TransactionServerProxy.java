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
        return 0;
    }
    
    // close socket connection
    public void closeTransaction()
    {
        
    }
    
    public int read(int accountNumber)
    {
        return 0;
    }
    
    public int write(int accountNumber, int amount)
    {
        return 0;
    }
}
