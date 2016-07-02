# SQL Helper for Java

This project has been created to build SQL string using java classes.

### Select Example

```
    OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper
		.select()
		.distinct()
		.column(o.city())
		.from()
		.table(o).generateSQL();
```

### Insert Example

```
    OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper
		.insert()
		.table(o)
		.column(o.officeCode())
		.column(o.city())
		.column(o.phone())
		.values()
		.value("003")
		.value("istanbul")
		.value("02120989808").generateSQL();
```

### Delete Example

```
    OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper.delete()
		.table(o)
		.where()
		.column(o.officeCode()).equal().value("001").generateSQL();
```

### Order Example

```
    OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper
		.select()
		.column(o.city())
		.column(o.phone())
		.from()
		.table(o)
		.order().column(o.officeCode()).asc().generateSQL();
```

### Update Example

```
    OfficeTable o = new OfficeTable("o");

	String sql = QueryHelper
		.update()
		.table(o)
		.set()
		.column(o.city()).equal().value("levent")
		.column(o.phone()).equal().value("02160000001")
		.where()
		.column(o.officeCode()).equal().value("001").generateSQL();
```

### Where Example

```
	OfficeTable o = new OfficeTable("o");
	EmployeeTable e = new EmployeeTable("e");

	String sql = QueryHelper.select()
		.all()
		.from()
		.table(o)
		.table(e)
		.where()
		.column(o.officeCode()).equal().custom(" ?").generateSQL();
```
# How to Generate Metadata

TableGenerator class can be used to generate metadata for all tables in a DB. conf.properties should be configured to point the DB.