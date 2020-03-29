package utils;

import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PropertyHandler extends Properties
{
    /**
     * 
     */
    private static final long serialVersionUID = 5731181758919549697L;

    public PropertyHandler(String filepath)
    {
        String propertyLine = "";
        String[] keyValue;
        try
        {
            Scanner scanner = new Scanner( new File(filepath));
            
            // loop through property file and read each line.
            while (scanner.hasNextLine())
            {
                // get the next line
                propertyLine = scanner.nextLine();
                keyValue = propertyLine.split(" ");
                
                this.put(keyValue[0], keyValue[1]);
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println(e);
        }
    }
}
