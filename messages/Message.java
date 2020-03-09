package messages;
import java.io.Serializable;

import constants.MessageTypes;

public class Message implements Serializable, MessageTypes
{

    private static final long serialVersionUID = 6029174950700546138L;
    int type;
    
    public Message(){}
    
}
