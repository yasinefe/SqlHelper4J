package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Column;

public class OrderBuilder extends BaseBuilder {

    private boolean columnAdded = false;

    public OrderBuilder(final QueryHelper queryBuilder) {
	super(queryBuilder, "ORDER BY");
    }

    public OrderBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    @Override
    public String toString() {
	return this.stringBuffer.toString();
    }

    public OrderBuilder asc() {
	this.stringBuffer.append(" ASC");
	return this;
    }

    public OrderBuilder desc() {
	this.stringBuffer.append(" DESC");
	return this;
    }

    public OrderBuilder column(final Column column) {
	return this.column(column.getNameOrAlias());
    }

    public OrderBuilder column(final String columnName) {
	this.checkColumnAdded();
	this.stringBuffer.append(" ").append(columnName);
	return this;
    }

    private void checkColumnAdded() {
	if (this.columnAdded) {
	    this.stringBuffer.append(",");
	}
	this.columnAdded = true;
    }

}
