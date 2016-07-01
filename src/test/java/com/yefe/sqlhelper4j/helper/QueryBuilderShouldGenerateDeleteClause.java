package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateDeleteClause {

    @Test
    public void withOutWhere() {

	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.delete() //
		.table(o).generateSQL();

	Assert.assertEquals("DELETE FROM office", sql);
    }

    @Test
    public void withWhere() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper.delete() //
		.table(o) //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"DELETE FROM office WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper.delete() //
		.custom(" " + o.getName()) //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"DELETE FROM office WHERE office.office_code = '001'", sql);
    }

}
