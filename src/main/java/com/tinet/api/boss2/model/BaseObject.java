package com.tinet.api.boss2.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 基础对象类。
 *<p>
 * 文件名： BaseObject.java
 *<p>
 * Copyright (c) 2006-2010 T&I Net Communication CO.,LTD.  All rights reserved.
 * @author 周营昭
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BaseObject implements java.io.Serializable {
	/**
	 * 定义对象以toString方式显示的样式
	 */
	protected final static ToStringStyle TO_STRING_STYLE = ToStringStyle.MULTI_LINE_STYLE;

	
	public String toString() {
		return ReflectionToStringBuilder.reflectionToString(this,
				TO_STRING_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
