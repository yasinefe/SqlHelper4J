package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateOrderClause {

    @Test
    public void withAsc() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.city()) //
		.column(o.phone()) //
		.from() //
		.table(o) //
		.order().column(o.officeCode()).asc().generateSQL();

	Assert.assertEquals("SELECT office.city, office.phone FROM office "
		+ "ORDER BY office.office_code ASC", sql);
    }

    @Test
    public void withAlias() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.officeCode().as("offcode")) //
		.from() //
		.table(o) //
		.order().column(o.officeCode()).asc().generateSQL();

	Assert.assertEquals("SELECT office.office_code offcode "
		+ "FROM office ORDER BY offcode ASC", sql);
    }

    @Test
    public void withCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.officeCode()) //
		.from() //
		.table(o) //
		.order().column(o.officeCode()).custom(" ASC").generateSQL();

	Assert.assertEquals("SELECT office.office_code FROM office "
		+ "ORDER BY office.office_code ASC", sql);
    }

    @Test
    public void withDesc() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.city()) //
		.column(o.phone()) //
		.from() //
		.table(o) //
		.order().column(o.officeCode()).desc().generateSQL();

	Assert.assertEquals("SELECT office.city, office.phone FROM office "
		+ "ORDER BY office.office_code DESC", sql);
    }

    @Test
    public void withMultipleColumn() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.city()) //
		.column(o.phone()) //
		.from() //
		.table(o) //
		.order().column(o.officeCode()).asc()//
		.column(o.city()).desc().generateSQL();

	Assert.assertEquals("SELECT office.city, office.phone FROM office "
		+ "ORDER BY office.office_code ASC, office.city DESC", sql);
    }

    @Test
    public void withWhereClause() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.all() //
		.from() //
		.table(o) //
		.where().column(o.city()).like().value("A%") //
		.order().column(o.officeCode()).asc().generateSQL();

	Assert.assertEquals("SELECT * FROM office WHERE office.city LIKE 'A%' "
		+ "ORDER BY office.office_code ASC", sql);
    }
}
