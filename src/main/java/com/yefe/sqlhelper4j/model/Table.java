package com.yefe.sqlhelper4j.model;

public abstract class Table {

    private final String name;

    private String alias;

    public Table(final String name) {
	this.name = name;
    }

    public Table(final String name, final String alias) {
	this.name = name;
	this.alias = alias;
    }

    public String getName() {
	return this.name;
    }

    public String getNameOrAlias() {
	if (this.alias != null) {
	    return this.alias;
	} else {
	    return this.name;
	}
    }

    public String getNameAndAlias() {
	if (this.alias != null) {
	    return this.name + " " + this.alias;
	} else {
	    return this.name;
	}
    }

}
