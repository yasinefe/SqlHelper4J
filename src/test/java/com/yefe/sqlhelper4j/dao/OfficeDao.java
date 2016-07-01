package com.yefe.sqlhelper4j.dao;

import com.yefe.sqlhelper4j.helper.QueryHelper;
import com.yefe.sqlhelper4j.table.OfficeTable;

public class OfficeDao {

    private static final OfficeTable o = new OfficeTable("o");

    private static final String SELECT_OFFICE = QueryHelper//
	    .select() //
	    .column(o.officeCode()) //
	    .column(o.phone()) //
	    .from() //
	    .table(o).generateSQL(); //

    public static void main(final String[] args) {
	System.out.println(SELECT_OFFICE);
    }

}
