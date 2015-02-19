package com.zl.abstracts;

import com.zl.utils.StringUtil;

abstract public class AResource {
	@Override
	public String toString() {
		return StringUtil.objToString(this);
	}
}
