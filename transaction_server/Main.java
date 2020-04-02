package transaction_server;

import utils.PropertyHandler;
import java.util.Properties;

public class Main
{
    public static void main ( String[] args )
    {
		(new TransactionServer("config/serverProps.txt")).start();
    }
}
