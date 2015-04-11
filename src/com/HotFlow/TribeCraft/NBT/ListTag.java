package com.HotFlow.TribeCraft.NBT;

import java.util.List;

/**
 * @author HotFlow
 */
public final class ListTag extends Tag
{
    private final Class<? extends Tag> type;

    private final List<Tag> value;

    public ListTag(final String name, final Class<? extends Tag> type, final List<Tag> value)
    {
        super(name);
        this.type = type;
        this.value = value;
    }

    /**
     * 获取标签类型
     *
     * @return
     */
    public Class<? extends Tag> getType()
    {
        return type;
    }

    /**
     * 获取标签值
     *
     * @return
     */
    @Override
    public List<Tag> getValue()
    {
        return value;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
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
        final StringBuilder bldr = new StringBuilder();
        bldr.append("TAG_List").append(append).append(": ").append(value.size()).append(" entries of type ").append(NBTUtils.getTypeName(type)).append("\r\n{\r\n");
        for (final Tag t : value)
        {
            bldr.append("   ").append(t.toString().replaceAll("\r\n", "\r\n   ")).append("\r\n");
        }
        bldr.append("}");
        return bldr.toString();
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
        result = (prime * result) + ((value == null) ? 0 : value.hashCode());
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
        if (!(obj instanceof ListTag))
        {
            return false;
        }
        final ListTag other = (ListTag) obj;
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
