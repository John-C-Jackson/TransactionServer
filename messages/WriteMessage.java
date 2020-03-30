package messages;

public class WriteMessage extends Message
{
    private static final long serialVersionUID = -1091532039564451152L;

    public WriteMessage(int type, Object msgContent)
    {
        super(type, msgContent);
        this.msgContent = msgContent;
        this.type = WRITE_TRANSACTION;
    }

}
