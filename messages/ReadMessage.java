package messages;

public class ReadMessage extends Message
{
    private static final long serialVersionUID = -1904731288335071591L;
    
    public ReadMessage()
    {
        super();
        this.type = READ_TRANSACTION;
    }

}
