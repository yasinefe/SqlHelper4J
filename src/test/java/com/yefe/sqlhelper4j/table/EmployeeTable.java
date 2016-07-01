package com.yefe.sqlhelper4j.table;

import com.yefe.sqlhelper4j.model.Column;
import com.yefe.sqlhelper4j.model.Table;

public class EmployeeTable extends Table {

    public EmployeeTable() {
	super("employee");
    }

    public EmployeeTable(final String alias) {
	super("employee", alias);
    }

    private final OfficeCode officeCode = new OfficeCode(this);

    public OfficeCode officeCode() {
	return this.officeCode;
    }

    public class OfficeCode extends Column {

	public OfficeCode(final Table table) {
	    super(table, "office_code");
	}

    }

    private final Name name = new Name(this);

    public Name name() {
	return this.name;
    }

    public class Name extends Column {

	public Name(final Table table) {
	    super(table, "name");
	}

    }

    private final Salary salary = new Salary(this);

    public Salary salary() {
	return this.salary;
    }

    public class Salary extends Column {

	public Salary(final Table table) {
	    super(table, "salary");
	}

    }

}
