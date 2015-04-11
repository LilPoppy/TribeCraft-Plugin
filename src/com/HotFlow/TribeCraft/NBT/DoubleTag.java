package com.HotFlow.TribeCraft.NBT;

/**
 * @author HotFlow
 */
public final class DoubleTag extends Tag {
	private final double value;

	public DoubleTag(final String name, final double value) {
		super(name);
		this.value = value;
	}

	/**
	 * 获取标签值
	 * 
	 * @return
	 */
	@Override
	public Double getValue() {
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
		return "TAG_Double" + append + ": " + value;
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
		long temp;
		temp = Double.doubleToLongBits(value);
		result = (prime * result) + (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof DoubleTag)) {
			return false;
		}
		final DoubleTag other = (DoubleTag) obj;
		if (Double.doubleToLongBits(value) != Double
				.doubleToLongBits(other.value)) {
			return false;
		}
		return true;
	}
}
