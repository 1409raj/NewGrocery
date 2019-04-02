package com.sampark.grocery.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.commons.lang.WordUtils;

public class BeanGenerator {
	public static void main(String args[]) {
		String folderName = "/home/malaya/Desktop/logs/";
		Queue<BeanModel> list = new LinkedList<BeanModel>();
		Connection connection = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerydb", "root", "admin@123");
			
			// ColumnDatatypeMapping cdm;
			String sqlSelectTableName = "select table_name from information_schema.TABLES where TABLE_SCHEMA = 'grocerydb'";
			PreparedStatement psSelectTable = connection.prepareStatement(sqlSelectTableName);
			ResultSet rsTable = psSelectTable.executeQuery();
			String tableName;
			while (rsTable.next()) {
				list = new LinkedList<>();
				// cdm = new ColumnDatatypeMapping();
				tableName = rsTable.getString(1);
				System.out.println(tableName);
				String sqlSelectColumn = "select * from information_schema.`COLUMNS` where TABLE_SCHEMA = 'grocerydb' and TABLE_NAME='" + tableName + "' order by ORDINAL_POSITION";

				PreparedStatement psSelectColumn = connection.prepareStatement(sqlSelectColumn);
				ResultSet rsColumn = psSelectColumn.executeQuery();
				String columnName;
				while (rsColumn.next()) {
					columnName = rsColumn.getString("column_name");
					String datatypeName= rsColumn.getString("data_type");
					System.out.println(columnName +" "+datatypeName);
					BeanModel bean = new BeanModel(columnName, datatypeName);
					list.add(bean);
				}
				psSelectColumn.close();
				rsColumn.close();
				writeBean(folderName, tableName, list);
			}
			psSelectTable.close();
			rsTable.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void writeBean(String folderName, String tableName, Queue<BeanModel> list) {
		FileWriter fileWriter;
		BufferedWriter bufferedWriter;
		StringBuilder fileContent;
		String className = getConventionalClassName(tableName);
		
		try {
			fileWriter = new FileWriter(folderName + "/" + className + "Entity.java");
			bufferedWriter = new BufferedWriter(fileWriter);
			fileContent = new StringBuilder();
			fileContent.append("package com.sampark.grocery.model;\n\n");
			
			fileContent.append("import javax.persistence.Entity;\n");
			fileContent.append("import javax.persistence.Table;\n");
			fileContent.append("import javax.persistence.Id;\n");
			fileContent.append("import javax.persistence.Column;\n");
			fileContent.append("import java.sql.Timestamp;\n");
			fileContent.append("import java.sql.Date;\n");
			fileContent.append("/*");
			fileContent.append("\n* File\t\t: ").append(className).append("Entity.java");
			fileContent.append("\n* Date Created\t: ").append(Calendar.getInstance().getTime().toString());
			fileContent.append("\n*/");
			fileContent.append("\n\n");
			
			fileContent.append("@Entity\n");
			fileContent.append("@Table(name=\"" +tableName +"\")\n");
			fileContent.append("public class ").append(className).append("Entity {");
			fileContent.append("\n");
			for (BeanModel bean : list) {
				fileContent.append("\n");
				fileContent.append("\t@Column(name=\"" + bean.getName() +"\")\n");
				fileContent.append("\tprivate");
				fileContent.append(" ");
				fileContent.append(BeanConstants.myMap.get(bean.getDataType().trim()));
				fileContent.append(" ");
				fileContent.append(getConventionalFieldName(bean.getName().trim()));
				fileContent.append(";");
			}

			for (BeanModel bean : list) {
				fileContent.append("\n\n\t");
				fileContent.append("public");
				fileContent.append(" ");
				fileContent.append(BeanConstants.myMap.get(bean.getDataType().trim()));
				fileContent.append(" ");
				fileContent.append(getAccessorMethodName(getConventionalFieldName(bean.getName())));
				fileContent.append("() {");
				fileContent.append("\n\t\t").append("return ").append(getConventionalFieldName(bean.getName().trim())).append(";");
				fileContent.append("\n\t").append("}");

				fileContent.append("\n\n\t");
				fileContent.append("public");
				fileContent.append(" ");
				fileContent.append("void");
				fileContent.append(" ");
				fileContent.append(getMutatorMethodName(getConventionalFieldName(bean.getName().toLowerCase())));
				fileContent.append("(");
				fileContent.append(BeanConstants.myMap.get(bean.getDataType().trim()));
				fileContent.append(" ").append(getConventionalFieldName(bean.getName().trim()));
				fileContent.append(") {");
				fileContent.append("\n\t\t").append("this.").append(getConventionalFieldName(bean.getName().trim())).append(" = ")
						.append(getConventionalFieldName(bean.getName().trim())).append(";");
				fileContent.append("\n\t").append("}");
			}
			fileContent.append("\n").append("}");

			bufferedWriter.write(fileContent.toString());
			bufferedWriter.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getConventionalFieldName(String str) {
		String conventionalClassName = "";
		String[] splittedStr = str.toLowerCase().split("[_]");
		for (int i = 0; i < splittedStr.length; i++) {
			if(i!=0) {
				conventionalClassName += WordUtils.capitalizeFully(splittedStr[i]);
			} else {
				conventionalClassName += splittedStr[i];
			}
		}
		return conventionalClassName;
	}
	public static String getConventionalClassName(String str) {
		String conventionalClassName = "";
		String[] splittedStr = str.split("[_]");
		for (int i = 0; i < splittedStr.length; i++) {
			conventionalClassName += WordUtils.capitalizeFully(splittedStr[i]);
		}
		return conventionalClassName;
	}

	public static String getConventionalMethodName(String str) {
		String conventionalClassName = getConventionalClassName(str);
		return Character.toLowerCase(conventionalClassName.charAt(0)) + conventionalClassName.substring(1);
	}

	public static String getAccessorMethodName(String dataMemberName) {
		return "get" + Character.toUpperCase(dataMemberName.charAt(0)) + dataMemberName.substring(1);
	}

	public static String getMutatorMethodName(String dataMemberName) {
		return "set" + Character.toUpperCase(dataMemberName.charAt(0)) + dataMemberName.substring(1);
	}
}

