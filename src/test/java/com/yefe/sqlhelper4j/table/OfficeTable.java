package com.yefe.sqlhelper4j.table;

import com.yefe.sqlhelper4j.model.Column;
import com.yefe.sqlhelper4j.model.Table;

public class OfficeTable extends Table {

    public OfficeTable() {
	super("office");
    }

    public OfficeTable(final String alias) {
	super("office", alias);
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

    private final City city = new City(this);

    public City city() {
	return this.city;
    }

    public class City extends Column {

	public City(final Table table) {
	    super(table, "city");
	}

    }

    private final Phone phone = new Phone(this);

    public Phone phone() {
	return this.phone;
    }

    public class Phone extends Column {

	public Phone(final Table table) {
	    super(table, "phone");
	}

    }

}
