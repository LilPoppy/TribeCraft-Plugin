package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class LongTag extends Tag
{
    private final long value;

    public LongTag(final String name, final long value) 
    {
        super(name);
        this.value = value;
    }

    /**
     * 获取标签值
     * @return 
     */
    @Override
    public Long getValue() 
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
        final String name = this.getName();
        String append = "";
        if ((name != null) && !name.equals(""))
        {
            append = "(\"" + this.getName() + "\")";
        }
        return "TAG_Long" + append + ": " + value;
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
        result = (prime * result) + (int) (value ^ (value >>> 32));
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
        if (!(obj instanceof LongTag)) 
        { 
            return false; 
        }
        final LongTag other = (LongTag) obj;
        if (value != other.value) 
        { 
            return false; 
        }
        return true;
    }
}
