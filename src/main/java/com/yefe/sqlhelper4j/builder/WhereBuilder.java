package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Column;

public class WhereBuilder extends BaseBuilder {

    private OrderBuilder orderBuilder;

    public WhereBuilder(final QueryHelper queryBuilder) {
	super(queryBuilder, "WHERE");
    }

    public WhereBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Columns
    // --------------------------------------------------------------------------------------------

    public WhereBuilder column(final Column column) {
	return this.column(column.getTable().getNameOrAlias() + "."
		+ column.getName());
    }

    public WhereBuilder column(final String columnName) {
	this.stringBuffer.append(" ").append(columnName);
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Conditions
    // --------------------------------------------------------------------------------------------

    public WhereBuilder and() {
	return this.add("AND");
    }

    public WhereBuilder or() {
	return this.add("OR");
    }

    public WhereBuilder group() {
	return this.add("(");
    }

    public WhereBuilder closeGroup() {
	return this.add(")");
    }

    public WhereBuilder equal() {
	return this.add("=");
    }

    public WhereBuilder equalQuestionMark() {
	return this.add("= ?");
    }

    public WhereBuilder notEqual() {
	return this.add("<>");
    }

    public WhereBuilder notEqualQuestionMark() {
	return this.add("<> ?");
    }

    public WhereBuilder like() {
	return this.add("LIKE");
    }

    public WhereBuilder less() {
	return this.add("<");
    }

    public WhereBuilder lessQuestionMark() {
	return this.add("< ?");
    }

    public WhereBuilder lessEqual() {
	return this.add("<=");
    }

    public WhereBuilder lessEqualQuestionMark() {
	return this.add("<= ?");
    }

    public WhereBuilder greater() {
	return this.add(">");
    }

    public WhereBuilder greaterQuestionMark() {
	return this.add("> ?");
    }

    public WhereBuilder greaterEqual() {
	return this.add(">=");
    }

    public WhereBuilder greaterEqualQuestionMark() {
	return this.add(">= ?");
    }

    private WhereBuilder add(final String value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Value methods
    // --------------------------------------------------------------------------------------------

    public WhereBuilder value(final String value) {
	this.stringBuffer.append(" '").append(value).append("'");
	return this;
    }

    public WhereBuilder value(final Integer value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public WhereBuilder value(final Long value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public WhereBuilder value(final Double value) {
	this.stringBuffer.append(" ").append(value);
	return this;
    }

    public WhereBuilder questionMark() {
	this.stringBuffer.append(" ?");
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // In methods
    // --------------------------------------------------------------------------------------------

    public WhereBuilder in(final String... values) {
	this.stringBuffer.append(" IN (");
	if (values != null) {
	    int i = 0;
	    for (String value : values) {
		if (i != 0) {
		    this.stringBuffer.append(", ");
		}
		i++;
		this.stringBuffer.append("'").append(value).append("'");
	    }
	}
	this.stringBuffer.append(")");
	return this;
    }

    public WhereBuilder in(final Integer... values) {
	this.stringBuffer.append(" IN (");
	if (values != null) {
	    int i = 0;
	    for (int value : values) {
		if (i != 0) {
		    this.stringBuffer.append(", ");
		}
		i++;
		this.stringBuffer.append(value);
	    }
	}
	this.stringBuffer.append(")");
	return this;
    }

    public WhereBuilder in(final Double... values) {
	this.stringBuffer.append(" IN (");
	if (values != null) {
	    int i = 0;
	    for (double value : values) {
		if (i != 0) {
		    this.stringBuffer.append(", ");
		}
		i++;
		this.stringBuffer.append(value);
	    }
	}
	this.stringBuffer.append(")");
	return this;
    }

    public WhereBuilder in(final Long... values) {
	this.stringBuffer.append(" IN (");
	if (values != null) {
	    int i = 0;
	    for (long value : values) {
		if (i != 0) {
		    this.stringBuffer.append(", ");
		}
		i++;
		this.stringBuffer.append(value);
	    }
	}
	this.stringBuffer.append(")");
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Order
    // --------------------------------------------------------------------------------------------

    public OrderBuilder order() {
	this.stringBuffer.append(" ");
	this.orderBuilder = new OrderBuilder(this.queryHelper);
	return this.orderBuilder;
    }

    @Override
    public String toString() {
	if (this.orderBuilder != null) {
	    return this.stringBuffer.toString() + this.orderBuilder.toString();
	} else {
	    return this.stringBuffer.toString();
	}
    }

}
