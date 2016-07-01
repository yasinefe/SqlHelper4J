package com.yefe.sqlhelper4j.helper;

import org.junit.Assert;
import org.junit.Test;

import com.yefe.sqlhelper4j.table.OfficeTable;

public class QueryBuilderShouldGenerateInsertClause {

    @Test
    public void withStringValues() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.value("003") //
		.value("istanbul") //
		.value("02120989808").generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( '003', 'istanbul', '02120989808' )", sql);
    }

    @Test
    public void withIntegerValues() {
	OfficeTable o = new OfficeTable();

	Integer value1 = new Integer(1);
	Integer value2 = new Integer(2);
	Integer value3 = new Integer(3);

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.value(value1) //
		.value(value2) //
		.value(value3).generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( 1, 2, 3 )", sql);
    }

    @Test
    public void withLongValues() {
	OfficeTable o = new OfficeTable();

	Long value1 = new Long(1);
	Long value2 = new Long(2);
	Long value3 = new Long(3);

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.value(value1) //
		.value(value2) //
		.value(value3).generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( 1, 2, 3 )", sql);
    }

    @Test
    public void withDoubleValues() {
	OfficeTable o = new OfficeTable();

	Double value1 = new Double(1);
	Double value2 = new Double(2);
	Double value3 = new Double(3);

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.value(value1) //
		.value(value2) //
		.value(value3).generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( 1.0, 2.0, 3.0 )", sql);
    }

    @Test
    public void withCustom() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.value("003") //
		.custom(", 'istanbul'") //
		.value("02120989808").generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( '003', 'istanbul', '02120989808' )", sql);
    }

    @Test
    public void withQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.questionMark() //
		.questionMark() //
		.questionMark().generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( ?, ?, ? )", sql);
    }

    @Test
    public void withAllQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.allQuestionMark().generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( ?, ?, ? )", sql);
    }

    @Test
    public void withCountQuestionMark() {
	OfficeTable o = new OfficeTable();

	String sql = QueryHelper //
		.insert() //
		.table(o) //
		.column(o.officeCode()) //
		.column(o.city()) //
		.column(o.phone()) //
		.values() //
		.questionMark(3).generateSQL();

	Assert.assertEquals(
		"INSERT INTO office ( office.office_code, office.city, office.phone ) "
			+ "VALUES ( ?, ?, ? )", sql);
    }

}
