package messages;

public class CloseMessage extends Message
{
    private static final long serialVersionUID = 6810458536691981522L;

    public CloseMessage(int type, Object msgContent)
    {
        super(type, msgContent);
        this.msgContent = msgContent;
        this.type = CLOSE_TRANSACTION;
    }

}
