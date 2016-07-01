package com.yefe.sqlhelper4j.generator;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TableGenerator {

    private Connection connection = null;

    private String dir;
    private String packageName;

    private String dbType;

    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String password;

    private String tablesSql;
    private String columnsSql;

    private String tablesName;
    private String columnsName;

    public static void main(final String[] args) {
	TableGenerator tableGenerator = new TableGenerator();

	try {
	    tableGenerator.prepareConnection();

	    tableGenerator.generateTables();

	    tableGenerator.closeConnection();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private void prepareConnection() throws Exception {
	ResourceBundle resourceBundle = ResourceBundle.getBundle("conf");

	this.dir = resourceBundle.getString("dir");
	this.packageName = resourceBundle.getString("package.name");

	this.dbType = resourceBundle.getString("db.type");

	this.url = resourceBundle.getString(this.dbType + ".url");
	this.dbName = resourceBundle.getString(this.dbType + ".db.name");
	this.driver = resourceBundle.getString(this.dbType + ".driver");
	this.userName = resourceBundle.getString(this.dbType + ".user.name");
	this.password = resourceBundle.getString(this.dbType + ".password");

	this.tablesSql = resourceBundle.getString(this.dbType + ".sql.tables");
	this.columnsSql = resourceBundle
		.getString(this.dbType + ".sql.columns");
	this.tablesName = resourceBundle.getString(this.dbType + ".table.name");
	this.columnsName = resourceBundle.getString(this.dbType
		+ ".column.name");

	Class.forName(this.driver).newInstance();
	this.connection = DriverManager.getConnection(this.url, this.userName,
		this.password);
    }

    private void generateTables() throws Exception {
	Statement statement = this.connection.createStatement();
	ResultSet resultSet = statement.executeQuery(this.tablesSql
		.replaceFirst("dbName", this.dbName));
	while (resultSet.next()) {
	    String tableName = resultSet.getString(this.tablesName
		    .replaceFirst("dbName", this.dbName));

	    this.generateTable(tableName);

	    System.out.println(tableName + " generated!");
	}
	resultSet.close();
	statement.close();
    }

    private void generateTable(final String tableName) throws Exception {
	String directory = this.packageName.replace('.', '/');

	File file = new File(this.dir + "/" + directory);
	file.mkdirs();

	String javaFileName = this.parseTableOrColumnName(tableName) + "Table";

	FileWriter fileWriter = new FileWriter(new File(this.dir + "/"
		+ directory + "/" + javaFileName + ".java"));

	String lineSeparator = System.getProperty("line.separator");

	fileWriter.write("package " + this.packageName + ";" + lineSeparator);
	fileWriter.write(lineSeparator);
	fileWriter.write("import com.yefe.sqlhelper4j.model.Column;"
		+ lineSeparator);
	fileWriter.write("import com.yefe.sqlhelper4j.model.Table;"
		+ lineSeparator);
	fileWriter.write(lineSeparator);
	fileWriter.write("public class " + javaFileName + " extends Table {"
		+ lineSeparator);
	fileWriter.write(lineSeparator);
	fileWriter.write("    public " + javaFileName + "() {" + lineSeparator);
	fileWriter.write("        super(\"" + tableName + "\");"
		+ lineSeparator);
	fileWriter.write("    }" + lineSeparator);
	fileWriter.write(lineSeparator);
	fileWriter.write("    public " + javaFileName
		+ "(final String alias) {" + lineSeparator);
	fileWriter.write("        super(\"" + tableName + "\", alias);"
		+ lineSeparator);
	fileWriter.write("    }" + lineSeparator);

	Statement statement = this.connection.createStatement();
	ResultSet resultSet = statement.executeQuery(this.columnsSql
		.replaceFirst("tableName", tableName).replaceAll("dbName",
			this.dbName));
	while (resultSet.next()) {
	    String columnName = resultSet.getString(this.columnsName);

	    String javaColumnName = this.parseTableOrColumnName(columnName);
	    String firstLetter = javaColumnName.substring(0, 1); // Get first
								 // letter
	    String remainder = javaColumnName.substring(1); // Get remainder of
							    // word.
	    String columnNameCamelCase = firstLetter.toLowerCase() + remainder;

	    fileWriter.write("    private final " + javaColumnName + " "
		    + columnNameCamelCase + " = new " + javaColumnName
		    + "(this);" + lineSeparator);
	    fileWriter.write(lineSeparator);
	    fileWriter.write("    public " + javaColumnName + " "
		    + columnNameCamelCase + "() {" + lineSeparator);
	    fileWriter.write("        return this." + columnNameCamelCase + ";"
		    + lineSeparator);
	    fileWriter.write("    }" + lineSeparator);
	    fileWriter.write(lineSeparator);
	    fileWriter.write("    public class " + javaColumnName
		    + " extends Column {" + lineSeparator);
	    fileWriter.write(lineSeparator);
	    fileWriter.write("        public " + javaColumnName
		    + "(final Table table) {" + lineSeparator);
	    fileWriter.write("            super(table, \"" + columnName
		    + "\");" + lineSeparator);
	    fileWriter.write("        }" + lineSeparator);
	    fileWriter.write(lineSeparator);
	    fileWriter.write("    }" + lineSeparator);
	    fileWriter.write(lineSeparator);
	}
	fileWriter.write("}" + lineSeparator);

	fileWriter.close();
	resultSet.close();
	statement.close();
    }

    private String parseTableOrColumnName(final String tableName) {
	String[] words = tableName.split("_");

	StringBuffer stringBuffer = new StringBuffer();

	for (String word : words) {
	    String firstLetter = word.substring(0, 1); // Get first letter
	    String remainder = word.substring(1); // Get remainder of word.
	    String capitalized = firstLetter.toUpperCase()
		    + remainder.toLowerCase();

	    stringBuffer.append(capitalized);
	}

	return stringBuffer.toString();
    }

    private void closeConnection() throws SQLException {
	this.connection.close();
    }
}
