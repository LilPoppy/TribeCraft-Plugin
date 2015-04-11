package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class EndTag extends Tag
{
    private final Object value = null;

    public EndTag()
    {
        super("");
    }

    /**
     * 获取标签值
     * @return 
     */
    @Override
    public Object getValue() 
    {
        return value;
    }

    /**
     * (non-Javadoc)
     * @see java.lang.String#toString() 
     */
    @Override
    public String toString()
    {
        return "TAG_End";
    }

    /**
     * (non-Javadoc)
     * @see java.lang.Object#hashCode() 
     */
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result) + ((value == null) ? 0 : value.hashCode());
        return result;
    }
    
    /**
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object) 
     */
    @Override
    public boolean equals(final Object obj) 
    {
        if (this == obj) 
        { 
            return true; 
        }
        if (!super.equals(obj)) 
        { 
            return false; 
        }
        if (!(obj instanceof EndTag)) 
        {
            return false; 
        }
        final EndTag other = (EndTag) obj;
        if (value == null)
        {
            if (other.value != null) 
            {
                return false; 
            }
        }
        else if (!value.equals(other.value)) 
        { 
            return false; 
        }
        return true;
    }
}
