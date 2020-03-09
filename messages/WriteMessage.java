package messages;

public class WriteMessage extends Message
{
    private static final long serialVersionUID = -1091532039564451152L;
    
    public WriteMessage()
    {
        super();
        this.type = WRITE_TRANSACTION;
    }

}
