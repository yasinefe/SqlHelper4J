package com.yefe.sqlhelper4j.builder;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.model.Column;
import com.yefe.sqlhelper4j.model.Table;

public class SelectBuilder extends BaseBuilder {

    private boolean columnAdded = false;

    private FromBuilder fromBuilder;

    public SelectBuilder(final QueryHelper queryHelper) {
	super(queryHelper, "SELECT");
    }

    public SelectBuilder custom(final String value) {
	this.stringBuffer.append(value);
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // All column function
    // --------------------------------------------------------------------------------------------
    public SelectBuilder all() {
	this.stringBuffer.append(" *");
	return this;
    }

    public SelectBuilder allColumnOfTable(final Table table) {
	return this.column(table.getNameOrAlias() + ".*");
    }

    // --------------------------------------------------------------------------------------------
    // Column functions
    // --------------------------------------------------------------------------------------------
    public SelectBuilder column(final Column column) {
	return this.column(column.getNameAndAlias());
    }

    public SelectBuilder column(final String columnName) {
	this.checkColumnAdded();
	this.stringBuffer.append(" ").append(columnName);
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Sql function functions
    // --------------------------------------------------------------------------------------------

    public SelectBuilder avg(final Column column) {
	return this.avg(column.getTable().getNameOrAlias() + "."
		+ column.getName());
    }

    public SelectBuilder avg(final String columnName) {
	return this.function(" AVG", columnName);
    }

    public SelectBuilder count(final Column column) {
	return this.count(column.getTable().getNameOrAlias() + "."
		+ column.getName());
    }

    public SelectBuilder count(final String columnName) {
	return this.function(" COUNT", columnName);
    }

    public SelectBuilder function(final String functionName,
	    final String columnName) {
	this.checkColumnAdded();
	this.stringBuffer.append(functionName).append("(").append(columnName)
		.append(")");
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Distinct
    // --------------------------------------------------------------------------------------------

    public SelectBuilder distinct() {
	this.stringBuffer.append(" DISTINCT");
	return this;
    }

    // --------------------------------------------------------------------------------------------
    // Check
    // --------------------------------------------------------------------------------------------

    private void checkColumnAdded() {
	if (this.columnAdded) {
	    this.stringBuffer.append(",");
	}
	this.columnAdded = true;
    }

    // --------------------------------------------------------------------------------------------
    // From
    // --------------------------------------------------------------------------------------------

    public FromBuilder from() {
	this.stringBuffer.append(" ");
	this.fromBuilder = new FromBuilder(this.queryHelper);
	return this.fromBuilder;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return this.stringBuffer.toString() + this.fromBuilder.toString();
    }

}
