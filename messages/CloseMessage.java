package messages;

public class CloseMessage extends Message
{
    private static final long serialVersionUID = 6810458536691981522L;
    
    public CloseMessage()
    {
        super();
        this.type = CLOSE_TRANSACTION;
    }

}
