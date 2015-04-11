package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class ShortTag extends Tag
{
    private final short value;

    public ShortTag(final String name, final short value)
    {
        super(name);
        this.value = value;
    }

    /**
     * 获取标签值
     *
     * @return
     */
    @Override
    public Short getValue()
    {
        return value;
    }

    /**
     * (non-Javadoc)
     *
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
        return "TAG_Short" + append + ": " + value;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = super.hashCode();
        result = (prime * result) + value;
        return result;
    }

    /**
     * (non-Javadoc)
     *
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
        if (!(obj instanceof ShortTag))
        {
            return false;
        }
        final ShortTag other = (ShortTag) obj;
        if (value != other.value)
        {
            return false;
        }
        return true;
    }
}
