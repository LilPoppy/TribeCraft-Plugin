package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public abstract class Tag
{
    private final String name;

    public Tag(final String name)
    {
        this.name = name;
    }

    /**
     * 获取名称
     *
     * @return
     */
    public final String getName()
    {
        return name;
    }

    /**
     * 获取标签值
     *
     * @return
     */
    public abstract Object getValue();

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((name == null) ? 0 : name.hashCode());
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
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof Tag))
        {
            return false;
        }
        final Tag other = (Tag) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }
}
