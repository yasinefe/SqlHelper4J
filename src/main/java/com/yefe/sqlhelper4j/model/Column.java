package com.yefe.sqlhelper4j.model;

public abstract class Column {

    private final Table table;
    private String alias;
    private final String name;

    public String getName() {
	return this.name;
    }

    public Column(final Table table, final String name) {
	this.table = table;
	this.name = name;
    }

    public String getNameOrAlias() {
	if (this.alias != null) {
	    return this.alias;
	} else {
	    return this.table.getNameOrAlias() + "." + this.getName();
	}
    }

    public String getNameAndAlias() {
	if (this.alias != null) {
	    return this.table.getNameOrAlias() + "." + this.getName() + " "
		    + this.alias;
	} else {
	    return this.table.getNameOrAlias() + "." + this.getName();
	}
    }

    public Column as(final String name) {
	this.alias = name;
	return this;
    }

    public Table getTable() {
	return this.table;
    }

}
