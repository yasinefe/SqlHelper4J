package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Column;
import com.yefe.sqlhelper4j.model.Table;

public class UpdateBuilder extends BaseBuilder {

    private boolean columnAdded = false;

    private WhereBuilder whereBuilder;

    public UpdateBuilder(final QueryHelper queryHelper) {
	super(queryHelper, "UPDATE");
    }

    public WhereBuilder where() {
	this.stringBuffer.append(" ");
	this.whereBuilder = new WhereBuilder(this.queryHelper);
	return this.whereBuilder;
    }

    public UpdateBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    public UpdateBuilder table(final Table table) {
	return this.table(table.getNameAndAlias());
    }

    public UpdateBuilder table(final String tableName) {
	this.stringBuffer.append(" ").append(tableName);
	return this;
    }

    public UpdateBuilder set() {
	this.stringBuffer.append(" SET");
	return this;
    }

    public UpdateBuilder equal() {
	this.stringBuffer.append(" =");
	return this;
    }

    public UpdateBuilder column(final Column column) {
	return this.column(column.getNameAndAlias());
    }

    public UpdateBuilder column(final String columnName) {
	this.checkColumnAdded();
	this.stringBuffer.append(" ").append(columnName);
	return this;
    }

    public UpdateBuilder columnEqualQuestionMark(final Column column) {
	return this.column(column).equal().questionMark();
    }

    public UpdateBuilder columnEqualQuestionMark(final String columnName) {
	return this.column(columnName).equal().questionMark();
    }

    public UpdateBuilder value(final String value) {
	this.stringBuffer.append(" '").append(value).append("'");
	return this;
    }

    public UpdateBuilder value(final Integer value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public UpdateBuilder value(final Long value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public UpdateBuilder value(final Double value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public UpdateBuilder equalQuestionMark() {
	this.stringBuffer.append(" = ?");
	return this;
    }

    public UpdateBuilder questionMark() {
	this.stringBuffer.append(" ?");
	return this;
    }

    public UpdateBuilder notEqual() {
	this.stringBuffer.append(" <>");
	return this;
    }

    public UpdateBuilder notEqualQuestionMark() {
	this.stringBuffer.append(" <> ?");
	return this;
    }

    private void checkColumnAdded() {
	if (this.columnAdded) {
	    this.stringBuffer.append(",");
	}
	this.columnAdded = true;
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
