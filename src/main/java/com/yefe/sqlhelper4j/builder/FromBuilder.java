package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Table;

public class FromBuilder extends BaseBuilder {

    private boolean tableAdded = false;

    private WhereBuilder whereBuilder;

    private OrderBuilder orderBuilder;

    public FromBuilder(final QueryHelper queryHelper) {
	super(queryHelper, "FROM");
    }

    public FromBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    public FromBuilder table(final Table table) {
	return this.table(table.getNameAndAlias());
    }

    public FromBuilder table(final String tableName) {
	this.checkTableAdded();
	this.stringBuffer.append(" ").append(tableName);
	return this;
    }

    private void checkTableAdded() {
	if (this.tableAdded) {
	    this.stringBuffer.append(",");
	}
	this.tableAdded = true;
    }

    public WhereBuilder where() {
	this.stringBuffer.append(" ");
	this.whereBuilder = new WhereBuilder(this.queryHelper);
	return this.whereBuilder;
    }

    public OrderBuilder order() {
	this.stringBuffer.append(" ");
	this.orderBuilder = new OrderBuilder(this.queryHelper);
	return this.orderBuilder;
    }

    @Override
    public String toString() {
	if (this.whereBuilder != null && this.orderBuilder == null) {
	    return this.stringBuffer.toString() + this.whereBuilder.toString();
	} else if (this.whereBuilder == null && this.orderBuilder != null) {
	    return this.stringBuffer.toString() + this.orderBuilder.toString();
	} else {
	    return this.stringBuffer.toString();
	}
    }

}
