package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Table;

public class DeleteBuilder extends BaseBuilder {

    private WhereBuilder whereBuilder;

    public DeleteBuilder(final QueryHelper queryHelper) {
	super(queryHelper, "DELETE FROM");
    }

    public WhereBuilder where() {
	this.stringBuffer.append(" ");
	this.whereBuilder = new WhereBuilder(this.queryHelper);
	return this.whereBuilder;
    }

    public DeleteBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    public DeleteBuilder table(final Table table) {
	return this.table(table.getNameAndAlias());
    }

    public DeleteBuilder table(final String tableName) {
	this.stringBuffer.append(" ").append(tableName);
	return this;
    }

    @Override
    public String toString() {
	if (this.whereBuilder != null) {
	    return this.stringBuffer.toString() + this.whereBuilder.toString();
	} else {
	    return this.stringBuffer.toString();
	}
    }

}
