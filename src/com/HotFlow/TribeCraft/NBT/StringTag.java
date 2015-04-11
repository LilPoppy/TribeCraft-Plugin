package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class StringTag extends Tag {
	private final String value;

	public StringTag(final String name, final String value) {
		super(name);
		this.value = value;
	}

	/**
	 * 获取标签值
	 * 
	 * @return
	 */
	@Override
	public String getValue() {
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
		return "TAG_String" + append + ": " + value;
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
		result = (prime * result) + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof StringTag)) {
			return false;
		}
		final StringTag other = (StringTag) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}
