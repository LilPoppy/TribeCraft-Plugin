package com.HotFlow.TribeCraft.NBT;

import java.util.Arrays;

/**
 * @author HotFlow
 */
public final class ByteArrayTag extends Tag 
{
    private final byte[] value;

    public ByteArrayTag(final String name, final byte[] value) 
    {
        super(name);
        this.value = value;
    }

    /**
     * 获取标签值
     * @return 
     */
    @Override
    public byte[] getValue() 
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
        final StringBuilder hex = new StringBuilder();
        for (final byte b : value) 
        {
            final String hexDigits = Integer.toHexString(b).toUpperCase();
            if (hexDigits.length() == 1) 
            {
                hex.append("0");
            }
            hex.append(hexDigits).append(" ");
        }
        final String name = this.getName();
        String append = "";
        if ((name != null) && !name.equals("")) 
        {
            append = "(\"" + this.getName() + "\")";
        }
        return "TAG_Byte_Array" + append + ": " + hex.toString();
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
        result = (prime * result) + Arrays.hashCode(value);
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
        if (!(obj instanceof ByteArrayTag)) 
        { 
            return false; 
        }
        final ByteArrayTag other = (ByteArrayTag) obj;
        if (!Arrays.equals(value, other.value)) 
        {
            return false; 
        }
        return true;
    }
	
}
