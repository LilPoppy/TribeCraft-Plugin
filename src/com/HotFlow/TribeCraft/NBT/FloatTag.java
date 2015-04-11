package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class FloatTag extends Tag {
	private final float value;

	public FloatTag(final String name, final float value) {
		super(name);
		this.value = value;
	}

	/**
	 * 获取标签值
	 * 
	 * @return
	 */
	@Override
	public Float getValue() {
		return value;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.String#toString()
	 */
	@Override
	public String toString() {
		final String name = this.getName();
		String append = "";
		if ((name != null) && !name.equals("")) {
			append = "(\"" + this.getName() + "\")";
		}
		return "TAG_Float" + append + ": " + value;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + Float.floatToIntBits(value);
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof FloatTag)) {
			return false;
		}
		final FloatTag other = (FloatTag) obj;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}
}
