package transaction_server;

import utils.PropertyHandler;
import java.util.Properties;

public class Main
{
    public static void main ( String[] args )
    {
        
        /*
         * All of this is temporary code to test PropertyHandler class...
         */
        String serverFile = "/Users/aarongiroux/Desktop/serverProps.txt";
        String clientFile = "/Users/aarongiroux/Desktop/clientProps.txt";
        
        Properties serverProps = new PropertyHandler(serverFile);
        Properties clientProps = new PropertyHandler(clientFile);
        
        System.out.println("Host: " + serverProps.getProperty("HOST"));
        System.out.println("Port: " + serverProps.getProperty("PORT"));
        System.out.println("Accounts: " + serverProps.getProperty("NUMBER_ACCOUNTS"));
        System.out.println("Balance: " + serverProps.getProperty("INITIAL_BALANCE"));
        
        System.out.println("Transactions: " + clientProps.getProperty("NUMBER_TRANSACTIONS"));
    }
}
