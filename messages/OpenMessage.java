package messages;

public class OpenMessage extends Message
{

    private static final long serialVersionUID = 8760959655079276653L;

    public OpenMessage(int type, Object msgContent)
    {
        super(type, msgContent);
        this.msgContent = msgContent;
        this.type = OPEN_TRANSACTION;
    }

}
