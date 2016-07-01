package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.EmployeeTable;
import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateWhereClause {

    @Test
    public void withSingleCondition() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select().all().from().table(o).table(e)
		.where().column(o.officeCode()).equal().column(e.officeCode())
		.generateSQL();

	Assert.assertEquals("SELECT * FROM office o, employee e "
		+ "WHERE o.office_code = e.office_code", sql);
    }

    @Test
    public void withQuestionMark() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper.select() //
		.all() //
		.from() //
		.table(o) //
		.where() //
		.column(o.officeCode()).equal().questionMark().generateSQL();

	Assert.assertEquals("SELECT * FROM office o "
		+ "WHERE o.office_code = ?", sql);
    }

    @Test
    public void withCustom() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select() //
		.all() //
		.from() //
		.table(o) //
		.table(e) //
		.where() //
		.column(o.officeCode()).equal().custom(" ?").generateSQL();

	Assert.assertEquals("SELECT * FROM office o, employee e "
		+ "WHERE o.office_code = ?", sql);
    }

    @Test
    public void withMultipleCondition() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select() //
		.all() //
		.from() //
		.table(o) //
		.table(e) //
		.where() //
		.column(o.officeCode()).equal().column(e.officeCode()) //
		.and().column(o.city()).equal().value("ISTANBUL").generateSQL();

	Assert.assertEquals("SELECT * FROM office o, employee e "
		+ "WHERE o.office_code = e.office_code "
		+ "AND o.city = 'ISTANBUL'", sql);
    }

    @Test
    public void withGroupedCondition() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select().all().from().table(o).table(e)
		.where().column(o.officeCode()).equal().column(e.officeCode())
		.and().group().column(o.phone()).like().value("90%")
		.closeGroup() //
		.and().group() //
		.column(e.name()).greater().value("A") //
		.or().column(e.name()).less().value("B")//
		.closeGroup().generateSQL(); //

	Assert.assertEquals("SELECT * FROM office o, employee e "
		+ "WHERE o.office_code = e.office_code "
		+ "AND ( o.phone LIKE '90%' ) "
		+ "AND ( e.name > 'A' OR e.name < 'B' )", sql);
    }

    @Test
    public void withNotEqual() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select().all().from().table(o).where().column(
		o.officeCode()).notEqual().column(e.officeCode()).generateSQL();

	Assert.assertEquals("SELECT * FROM office o "
		+ "WHERE o.office_code <> e.office_code", sql);
    }

    @Test
    public void withLessEqual() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select().all().from().table(o).where().column(
		o.officeCode()).lessEqual().column(e.officeCode())
		.generateSQL();

	Assert.assertEquals("SELECT * FROM office o "
		+ "WHERE o.office_code <= e.office_code", sql);
    }

    @Test
    public void withGreaterEqual() {
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select().all().from().table(o).where().column(
		o.officeCode()).greaterEqual().column(e.officeCode())
		.generateSQL();

	Assert.assertEquals("SELECT * FROM office o "
		+ "WHERE o.office_code >= e.office_code", sql);
    }

    @Test
    public void withIntegerValue() {
	OfficeTable o = new OfficeTable("o");

	Integer value = new Integer(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.phone()).equal().value(value).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.phone = 2", sql);
    }

    @Test
    public void withLongValue() {
	OfficeTable o = new OfficeTable("o");

	Long value = new Long(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.phone()).equal().value(value).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.phone = 2", sql);
    }

    @Test
    public void withDoubleValue() {
	OfficeTable o = new OfficeTable("o");

	Double value = new Double(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.phone()).equal().value(value).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.phone = 2.0", sql);
    }

    @Test
    public void withInStringCondition() {
	OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.officeCode()).in("001", "002").generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.office_code IN ('001', '002')", sql);
    }

    @Test
    public void withInIntegerCondition() {
	OfficeTable o = new OfficeTable("o");

	Integer value1 = new Integer(1);
	Integer value2 = new Integer(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.officeCode()).in(value1, value2).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.office_code IN (1, 2)", sql);
    }

    @Test
    public void withInLongCondition() {
	OfficeTable o = new OfficeTable("o");

	Long value1 = new Long(1);
	Long value2 = new Long(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.officeCode()).in(value1, value2).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.office_code IN (1, 2)", sql);
    }

    @Test
    public void withInDoubleCondition() {
	OfficeTable o = new OfficeTable("o");

	Double value1 = new Double(1);
	Double value2 = new Double(2);

	String sql = QueryHelper.select() //
		.column(o.city()) //
		.from() //
		.table(o) //
		.where() //
		.column(o.officeCode()).in(value1, value2).generateSQL(); //

	Assert.assertEquals("SELECT o.city FROM office o "
		+ "WHERE o.office_code IN (1.0, 2.0)", sql);
    }

}
