package com.HotFlow.TribeCraft.NBT;

import java.util.Map;

/**
 * @author HotFlow
 */
public final class CompoundTag extends Tag {
	private final Map<String, Tag> value;

	public CompoundTag(final String name, final Map<String, Tag> value) {
		super(name);
		this.value = value;
	}

	/**
	 * 获取标签值
	 * 
	 * @return
	 */
	@Override
	public Map<String, Tag> getValue() {
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

		final StringBuilder bldr = new StringBuilder();
		bldr.append("TAG_Compound").append(append).append(": ")
				.append(value.size()).append(" entries\r\n{\r\n");

		for (final Map.Entry<String, Tag> entry : value.entrySet()) {
			bldr.append("   ")
					.append(entry.getValue().toString()
							.replaceAll("\r\n", "\r\n   ")).append("\r\n");
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
		if (!(obj instanceof CompoundTag)) {
			return false;
		}
		final CompoundTag other = (CompoundTag) obj;
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
