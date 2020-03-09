package locks;

import constants.LockTypes;

public class LockType implements LockTypes
{
    private int type;
    
    public LockType(int type)
    {
        this.type = type;
    }
    
    public void promote()
    {
        if(type == READ_LOCK)
        {
            type = WRITE_LOCK;
        }
    }
    
    public void setNone()
    {
        type = NO_LOCK;
    }
    
    public int getType()
    {
        return type;
    }
    
   
}
