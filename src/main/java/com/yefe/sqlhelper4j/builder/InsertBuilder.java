package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Column;
import com.yefe.sqlhelper4j.model.Table;

public class InsertBuilder extends BaseBuilder {

    private boolean columnAdded = false;
    private boolean valueAdded = false;
    private int columnCount = 0;

    public InsertBuilder(final QueryHelper queryHelper) {
	super(queryHelper, "INSERT INTO");
    }

    public InsertBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    public InsertBuilder table(final Table table) {
	return this.table(table.getNameAndAlias());
    }

    public InsertBuilder table(final String tableName) {
	this.stringBuffer.append(" ").append(tableName);
	return this;
    }

    public InsertBuilder column(final Column column) {
	return this.column(column.getNameAndAlias());
    }

    public InsertBuilder column(final String columnName) {
	this.columnCount++;
	this.checkColumnAdded();
	this.stringBuffer.append(" ").append(columnName);
	return this;
    }

    public InsertBuilder values() {
	this.stringBuffer.append(" ) VALUES");
	return this;
    }

    public InsertBuilder value(final String value) {
	this.checkValueAdded();
	this.stringBuffer.append(" '").append(value).append("'");
	return this;
    }

    public InsertBuilder value(final Integer value) {
	this.checkValueAdded();
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public InsertBuilder value(final Long value) {
	this.checkValueAdded();
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public InsertBuilder value(final Double value) {
	this.checkValueAdded();
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public InsertBuilder questionMark() {
	this.checkValueAdded();
	this.stringBuffer.append(" ?");
	return this;
    }

    public InsertBuilder allQuestionMark() {
	for (int i = 0; i < this.columnCount; i++) {
	    this.questionMark();
	}
	return this;
    }

    public InsertBuilder questionMark(final int count) {
	for (int i = 0; i < count; i++) {
	    this.questionMark();
	}
	return this;
    }

    private void checkColumnAdded() {
	if (this.columnAdded) {
	    this.stringBuffer.append(",");
	} else {
	    this.stringBuffer.append(" (");
	}
	this.columnAdded = true;
    }

    private void checkValueAdded() {
	if (this.valueAdded) {
	    this.stringBuffer.append(",");
	} else {
	    this.stringBuffer.append(" (");
	}
	this.valueAdded = true;
    }

    @Override
    public String toString() {
	return this.stringBuffer.toString() + " )";
    }

}
