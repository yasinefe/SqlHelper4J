package com.yefe.sqlhelper4j.helper;

import com.yefe.sqlhelper4j.builder.DeleteBuilder;
import com.yefe.sqlhelper4j.builder.InsertBuilder;
import com.yefe.sqlhelper4j.builder.SelectBuilder;
import com.yefe.sqlhelper4j.builder.UpdateBuilder;

public class QueryHelper {

    private SelectBuilder selectBuilder;

    private InsertBuilder insertBuilder;

    private UpdateBuilder updateBuilder;

    private DeleteBuilder deleteBuilder;

    public static SelectBuilder select() {
	return new QueryHelper().selectBuilder();
    }

    public SelectBuilder selectBuilder() {
	this.selectBuilder = new SelectBuilder(this);
	return this.selectBuilder;
    }

    public static InsertBuilder insert() {
	return new QueryHelper().insertBuilder();
    }

    public InsertBuilder insertBuilder() {
	this.insertBuilder = new InsertBuilder(this);
	return this.insertBuilder;
    }

    public static UpdateBuilder update() {
	return new QueryHelper().updateBuilder();
    }

    public UpdateBuilder updateBuilder() {
	this.updateBuilder = new UpdateBuilder(this);
	return this.updateBuilder;
    }

    public static DeleteBuilder delete() {
	return new QueryHelper().deleteBuilder();
    }

    public DeleteBuilder deleteBuilder() {
	this.deleteBuilder = new DeleteBuilder(this);
	return this.deleteBuilder;
    }

    @Override
    public String toString() {
	if (this.selectBuilder != null) {
	    return this.selectBuilder.toString();
	} else if (this.insertBuilder != null) {
	    return this.insertBuilder.toString();
	} else if (this.updateBuilder != null) {
	    return this.updateBuilder.toString();
	} else {
	    return this.deleteBuilder.toString();
	}
    }

}