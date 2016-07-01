package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;

public class BaseBuilder {

    protected final StringBuffer stringBuffer;

    protected final QueryHelper queryHelper;

    public BaseBuilder(final QueryHelper queryHelper, final String sqlHeader) {
	this.queryHelper = queryHelper;
	this.stringBuffer = new StringBuffer(sqlHeader);
    }

    public String generateSQL() {
	return this.queryHelper.toString();
    }

}
