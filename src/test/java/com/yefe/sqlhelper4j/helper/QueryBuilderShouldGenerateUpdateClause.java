package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateUpdateClause {

    @Test
    public void withWhere() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.column(o.city()).equal().value("levent") //
		.column(o.phone()).equal().value("02160000001") //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"UPDATE office SET office.city = 'levent', office.phone = '02160000001' "
			+ "WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withOutWhere() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.column(o.city()).equal().value("levent") //
		.column(o.phone()).equal().value("02160000001").generateSQL();

	Assert
		.assertEquals(
			"UPDATE office SET office.city = 'levent', office.phone = '02160000001'",
			sql);
    }

    @Test
    public void withValues() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.column(o.city()).equal().value(new Long(1)) //
		.column(o.phone()).equal().value(new Integer(1)) //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"UPDATE office SET office.city = 1, office.phone = 1 "
			+ "WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withColumnEqualQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.columnEqualQuestionMark(o.city()) //
		.columnEqualQuestionMark(o.phone()) //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"UPDATE office SET office.city = ?, office.phone = ? "
			+ "WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withColumnNameEqualQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.columnEqualQuestionMark(o.city().getName()) //
		.columnEqualQuestionMark(o.phone().getName()) //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals("UPDATE office SET city = ?, phone = ? "
		+ "WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.column(o.officeCode()).equal().custom(
			" '00' + office.office_code") //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"UPDATE office SET office.office_code = '00' + office.office_code "
			+ "WHERE office.office_code = '001'", sql);
    }

    @Test
    public void withDoubleValuesAndQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.update() //
		.table(o) //
		.set() //
		.column(o.city()).equal().value(new Double(1)) //
		.column(o.phone()).equal().questionMark() //
		.where()//
		.column(o.officeCode()).equal().value("001").generateSQL();

	Assert.assertEquals(
		"UPDATE office SET office.city = 1.0, office.phone = ? "
			+ "WHERE office.office_code = '001'", sql);
    }

}
