package messages;

public class ReadMessage extends Message
{
    private static final long serialVersionUID = -1904731288335071591L;

    public ReadMessage(int type, Object msgContent)
    {
        super(type, msgContent);
        this.msgContent = msgContent;
        this.type = READ_TRANSACTION;
    }

}
