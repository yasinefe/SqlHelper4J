package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateSelectClause {

    @Test
    public void withAllColumn() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.all() //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT * FROM office", sql);
    }

    @Test
    public void withAllColumnOfATable() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.allColumnOfTable(o) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT office.* FROM office", sql);
    }

    @Test
    public void withCustomColumn() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(
			o.officeCode().getName() + " || " + o.phone().getName()
				+ " column1") //
		.column(o.city()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals(
		"SELECT office_code || phone column1, office.city FROM office",
		sql);
    }

    @Test
    public void withDistinctColumn() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.distinct() //
		.column(o.city()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT DISTINCT office.city FROM office", sql);
    }

    @Test
    public void withColumnCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.city()).custom(" a") //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT office.city a FROM office", sql);
    }

    @Test
    public void withFromCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.select() //
		.column(o.city()) //
		.from() //
		.custom(" " + o.getNameAndAlias()).generateSQL();

	Assert.assertEquals("SELECT office.city FROM office", sql);
    }

    @Test
    public void withTableWithTableAlias() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.column(o.officeCode()) //
		.column(o.phone()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT o.office_code, o.phone FROM office o", sql);
    }

    @Test
    public void withAllColumnOfTableWithTableAlias() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.allColumnOfTable(o) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT o.* FROM office o", sql);
    }

    @Test
    public void withColumnAlias() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.column(o.officeCode().as("office")) //
		.column(o.phone()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals(
		"SELECT o.office_code office, o.phone FROM office o", sql);
    }

    @Test
    public void withCountFunction() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.count(o.officeCode()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT COUNT(o.office_code) FROM office o", sql);
    }

    @Test
    public void withAvgFunction() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.avg(o.officeCode()) //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT AVG(o.office_code) FROM office o", sql);
    }

    @Test
    public void withGenerateSqlMethod() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper //
		.select() //
		.all() //
		.from() //
		.table(o).generateSQL();

	Assert.assertEquals("SELECT * FROM office o", sql);
    }
}
