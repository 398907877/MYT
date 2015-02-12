package com.yihu.myt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 生成Mybatis工具类，用于根据数据库表成生相应的数据
 * 
 * @author wsb。
 * 
 * @version 2012-9-24(改)
 */
public class MybatisGenerateUtils1 {

	// private static String project_url = "E:\\快盘\\工作\\tools\\src\\"; //
	// 工程路径以\\结束
	private static String project_url = "D:\\8888888\\MYT\\src\\"; // 工程路径以\\结束

	private static String mbt_xml_package = ""; // 配置文件路径
	private static String bean_package = "com\\yihu\\myt\\vo"; // 实体路径
	private static String dao_package = "com\\yihu\\myt\\service\\service"; // dao路径
	private static String sqlNameEnum = "com\\yihu\\myt\\enums"; // 枚举路径
	private static String dao_end_sign = "Service"; // DAO以什么结尾
	private static String encode = "GBK";

	private static String url = "jdbc:sqlserver://10.0.200.21:1433;database=YiHuNet2008";
	private static String user = "NetOper";
	private static String psd = "Net123#YiHu";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String table = "ZIXUN_CloseQue";
	private static String table_text = "CloseQue";

	private static String sql = "select * from " + table;
	private static String keySQL = "select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE where table_name='" + table + "'";
	private static List<String> keyList = getKeyList(); // 主键列表,下面多次用到

	private static Map<String, String> map; // 数据库对应的bean类型

	static {
		map = new HashMap<String, String>();
		map.put("VARCHAR", "String");
		map.put("INT", "Integer");
		map.put("int", "Integer");
		map.put("INTEGER", "Long");
		map.put("FLOAT", "Float");
		map.put("float", "Float");
		map.put("DOUBLE", "Double");
		map.put("TIMESTAMP", "Date");
		map.put("CHAR", "String");
		map.put("varchar", "String");
		map.put("nvarchar", "String");
		map.put("text", "String");
		map.put("datetime", "String");
		map.put("TIMESTAMP_IMPORT", "import java.util.Date;");
		map.put("DATETIME_IMPORT", "import java.util.Date;");
		map.put("LIST_IMPORT", "import java.util.List;");
		map.put("MAP_IMPORT", "import java.util.Map;");
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(keyList);
		geneXml();
		geneBean();
		geneInteface();
		geneImpl();
		geneSqlNameEnum();
	}

	/**
	 * 生成Mbt xml文件
	 * 
	 * @throws Exception
	 */
	public static void geneXml() throws Exception {
		File file = new File(project_url + mbt_xml_package + "/" + getFormatTableName() + ".sql.xml");
		System.out.println(file.getPath());
		Document document = DocumentHelper.createDocument();
		addMbtXmlElement(document);
		writeDocToFile(document, file);
	}

	/**
	 * 生成 bean 文件
	 */
	public static void geneBean() {
		File file = new File(project_url + bean_package + "/" + getFormatTableName() + "Vo.java");
		System.out.println(file.getPath());
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		writeToFile(file, getBeanContext());
	}

	/**
	 * 生成 dao 文件内容
	 */
	public static void geneInteface() {
		File file = new File(project_url + dao_package + "/I" + getFormatTableName() + "Service.java");
		System.out.println(file.getPath());
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		writeToFile(file, getIntefaceContent());
	}

	/**
	 * 生成 dao 文件内容
	 * @throws SQLException 
	 */
	public static void geneImpl() throws SQLException {
		File file = new File(project_url + dao_package + "/impl/" + getFormatTableName() + "Service.java");
		System.out.println(file.getPath());
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		StringBuffer dao = new StringBuffer("package " + getDAOPackage() + ".impl;" + "\r\r");
		dao.append(getPojoType("LIST_IMPORT")).append("\r\r");
		dao.append("import com.coreframework.db.DB;\r");
		dao.append("import com.coreframework.db.JdbcConnection;\r");
		dao.append("import com.coreframework.db.Sql;\r");
		dao.append("import com.yihu.baseinfo.dbutil.MyDatabaseEnum;\r");
		dao.append("import com.yihu.baseinfo.dbutil." + getFormatTableName() + "SqlNameEnum;\r");
		dao.append("import com.yihu.utils.StringUtil;");

		dao.append("import " + getBeanPackage() + "." + getFormatTableName() + "Vo;\r"); // 导入bean类
		dao.append("import " + getDAOPackage() + ".I" + getFormatTableName() + dao_end_sign + ";\r"); // 导入bean类

		dao.append("\r\r\n");
		dao.append("public class " + getFormatTableName() + dao_end_sign + " implements I" + getFormatTableName() + dao_end_sign + "{\r\r\n");

		dao.append("\t").append("/**").append("\r\t*获取列表记录数\r").append("\t*/").append("\r");
		String methodName = "query" + getFormatTableName() + "CountByCondition";
		dao.append("\t").append("public Integer " + methodName + "(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
		dao.append("sql.addVar(\"@condition\", condition.toString());\r\t\t");
		dao.append("Integer count = DB.me().queryForInteger(MyDatabaseEnum.BaseInfo, sql);\r\t\t");
		dao.append("return count;\r\t");
		dao.append("}").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*获取列表\r").append("\t*/").append("\r");
		methodName = "query" + getFormatTableName() + "ListByCondition";
		dao.append("\t").append("public List<" + getFormatTableName() + "Vo> query" + getFormatTableName() + "ListByCondition(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
		dao.append("sql.addVar(\"@condition\", condition.toString());\r\t\t");
		dao.append("sql.addVar(\"@page\", \"\");\r\t\t");
		dao.append("List<" + getFormatTableName() + "Vo" + "> list = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, sql," + getFormatTableName() + "Vo.class" + ");\r\t\t");
		dao.append("return list;\r\t");
		dao.append("}").append("\r\r\n");

		// 添加
		dao.append("\t").append("/**").append("\r\t*添加\r").append("\t*/").append("\r");
		methodName = "insert" + getFormatTableName();
		dao.append("\t").append("public void insert" + getFormatTableName() + "(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		// 生成的get/set
		ResultSetMetaData rsmd = getResultSetMetaData(sql);
		for (int j = 1; j <= rsmd.getColumnCount(); j++) {
			String columnName = rsmd.getColumnName(j); // 字段名
			dao.append("sql.addParamValue(vo.get"+upperFirestChar(columnName)+"());\r\t\t");
		}

		dao.append("DB.me().insert(MyDatabaseEnum.BaseInfo,sql);\r\t\t");
		dao.append("}").append("\r\r\n");

		if (keyList.size() > 0) {
			dao.append("\t").append("/**").append("\r\t*修改\r").append("\t*/").append("\r");
			methodName = "update" + getFormatTableName() + "ByCondition";
			dao.append("\t").append("public void update" + getFormatTableName() + "ByCondition(" + getFormatTableName() + "Vo vo,JdbcConnection conn) throws Exception{");
			dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
			StringBuilder keyEmpty = new StringBuilder();
			for (String key : keyList) {
				key = "get" + key.substring(0, 1).toUpperCase() + key.substring(1) + "()";
				keyEmpty.append(" || StringUtil.isEmpty(vo." + key + ")");
			}
			dao.append("if (vo == null " + keyEmpty + ") {\r\t\t");
			dao.append("	throw new Exception(\" 不能为空或者 主键id 不能为空\");\r\t\t");
			dao.append("}\r\t\t");
			dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
			dao.append("if (condition.length() == 0) {\r\t\t");
			dao.append("throw new Exception(\"未有更新SQL被执行！\");\r\t\t");
			dao.append("} else {\r\t\t");
			dao.append("condition.deleteCharAt(condition.length() - 1);\r\t\t");
			dao.append("sql.addVar(\"@condition\", condition.toString());\r\t\t");
			for (String key : keyList) {
				key = "get" + key.substring(0, 1).toUpperCase() + key.substring(1) + "()";
				dao.append("sql.addParamValue(vo." + key + ");\r\t\t");
			}
			dao.append("}\r\t\t");
			dao.append("DB.me().update(conn, sql);\r\t\t");
			dao.append("}").append("\r\r\n");
		}
		dao.append("}\r");

		writeToFile(file, dao.toString());
	}

	/**
	 * 生成 枚举 文件内容
	 */
	public static void geneSqlNameEnum() {
		File file = new File(project_url + sqlNameEnum + "\\" + getFormatTableName() + "SqlNameEnum.java");
		System.out.println(file.getPath());
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		StringBuffer dao = new StringBuffer("package " + sqlNameEnum.replace("\\", ".") + ";" + "\r\r");
		dao.append("import com.coreframework.db.SqlNameEnum;\r"); //

		dao.append("\r\r\n");
		dao.append("public enum " + getFormatTableName() + "SqlNameEnum implements SqlNameEnum {\r\r\n");
		dao.append("\r\n\t//查询记录数\r\n\t");
		dao.append("query" + getFormatTableName() + "CountByCondition,");
		dao.append("\r\n\t//查询记录\r\n\t");
		dao.append("query" + getFormatTableName() + "ListByCondition,");
		dao.append("\r\n\t//插入\r\n\t");
		dao.append("insert" + getFormatTableName() + ",");
		dao.append("\r\n\t//根据条件更新\r\n\t");
		dao.append("update" + getFormatTableName() + "ByCondition");
		dao.append("\r\n}\r");

		writeToFile(file, dao.toString());
	}

	/**
	 * 生成DAO文件内容
	 * 
	 * @return
	 */
	public static String getIntefaceContent() {
		StringBuffer dao = new StringBuffer("package " + getDAOPackage() + ";" + "\r\r");
		dao.append(getPojoType("LIST_IMPORT")).append("\r\r");
		dao.append("import com.coreframework.ioc.By;\r");

		dao.append("import com.coreframework.db.JdbcConnection;");
		dao.append("import " + getBeanPackage() + "." + getFormatTableName() + "Vo;\r"); // 导入bean类
		dao.append("import " + getDAOPackage() + ".impl." + getFormatTableName() + dao_end_sign + ";\r"); // 导入impl类

		dao.append("\r\r\n");
		dao.append("@By(" + getFormatTableName() + dao_end_sign + ".class)").append("\r\n");
		dao.append("public interface I" + getFormatTableName() + dao_end_sign + "{\r\r\n");

		dao.append("\t").append("/**").append("\r\t*获取列表记录数\r").append("\t*/").append("\r");
		dao.append("\t").append("public Integer query" + getFormatTableName() + "CountByCondition(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*获取列表\r").append("\t*/").append("\r");
		dao.append("\t").append("public List<" + getFormatTableName() + "Vo> query" + getFormatTableName() + "ListByCondition(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*添加\r").append("\t*/").append("\r");
		dao.append("\t").append("public void insert" + getFormatTableName() + "(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");
		if (keyList.size() > 0) {
			dao.append("\t").append("/**").append("\r\t*修改\r").append("\t*/").append("\r");
			dao.append("\t").append("public void update" + getFormatTableName() + "ByCondition(" + getFormatTableName() + "Vo vo,JdbcConnection conn) throws Exception;").append("\r\r\n");
		}
		dao.append("}\r");
		return dao.toString();
	}

	/**
	 * 生成javaBean文件内容
	 */
	public static String getBeanContext() {
		StringBuffer bean = new StringBuffer("package " + getBeanPackage() + ";" + "\r\r");
		try {
			ResultSetMetaData rsmd = getResultSetMetaData(sql);
			// 需要导入的包
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // 数据库类型
				String im = getImport(columnDbType + "_IMPORT");
				if (im != null) {
					bean.append(im).append("\r\n\r\n");
				}
			}

			bean.append("public class " + getFormatTableName() + "Vo{\r");

			// 生成的属性
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // 数据库类型
				String columnName = rsmd.getColumnName(j); // 字段名
				String column = "\tprivate " + getPojoType(columnDbType) + " " + columnName + ";  ";
				// String columnName = rsmd.getColumnName(j).toLowerCase(); // 字段名
				// String column = "\tprivate " + getPojoType(columnDbType) + " " + columnToPropertyName(columnName) + ";  ";
				bean.append(column).append("\r\n\r\n");
			}

			// 生成的get/set
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // 数据库类型
				String columnName = rsmd.getColumnName(j); // 字段名
				bean.append(getMethodStr(columnName, getPojoType(columnDbType))).append("\r\n");
				// String columnName = rsmd.getColumnName(j).toLowerCase(); // 字段名
				// bean.append(getMethodStr(columnToPropertyName(columnName), getPojoType(columnDbType))).append("\r\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		bean.append("}");
		return bean.toString();
	}

	/**
	 * 生成mbt xml文件内容
	 */
	public static void addMbtXmlElement(Document document) {
		Element rootEle = document.addElement("das-client");
		Element resultMap = rootEle.addElement("Sqls");

		addSelectElement(resultMap);
		addInsertElement(resultMap);
		if (keyList != null && keyList.size() != 0) {
			addUpdateElement(resultMap);
			// addDeleteElement(resultMap);
		}
	}

	/**
	 * 生成mbt select内容
	 * 
	 * 
	 */
	public static void addSelectElement(Element rootEle) {
		rootEle.addComment("查询记录数");
		Element resultMap = rootEle.addElement("Sql");
		resultMap.addText("\r\n");
		resultMap.addAttribute("name", "query" + getFormatTableName() + "CountByCondition");
		resultMap.addText("\t\t").addText("SELECT count (*) FROM " + table + " where 1=1 @condition").addText("\r\n\t");

		rootEle.addComment("根据条件分页查询记录 ");
		rootEle.addText("\r\n");
		Element resultMap2 = rootEle.addElement("Sql");
		resultMap2.addText("\r\n");
		resultMap2.addAttribute("name", "query" + getFormatTableName() + "ListByCondition");
		String orderStr = "";
		if (keyList.size() == 1) {
			orderStr = keyList.get(0);
		} else {
			for (int i = 0; i < keyList.size(); i++) {
				orderStr = orderStr + keyList.get(i) + ",";
			}
			orderStr = orderStr.substring(0, orderStr.length() - 1);
		}
		resultMap2.addText("\t\t").addText("SELECT * from (SELECT ROW_NUMBER() OVER( order by " + orderStr + " desc) rowId,* FROM " + table + " where 1=1 @condition ) as a where 1=1 @page")
				.addText("\r\n\t");
	}

	/**
	 * 生成mbt insert内容
	 * 
	 * 
	 */
	public static void addInsertElement(Element rootEle) {
		rootEle.addComment("插入 ");
		Element resultMap = rootEle.addElement("Sql");
		resultMap.addAttribute("name", "insert" + getFormatTableName());

		StringBuffer sb = new StringBuffer("\r\tINSERT INTO " + table + "(");
		try {
			ResultSetMetaData rsmd = getResultSetMetaData(sql);
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnName = rsmd.getColumnName(j); // 字段名
				sb.append("" + columnName);
				if (j != rsmd.getColumnCount()) {
					sb.append(",");
				}
			}
			sb.append(")VALUES(");
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				sb.append("?");
				if (j != rsmd.getColumnCount()) {
					sb.append(",");
				}
			}
			sb.append(")\r\n\t");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.addText(sb.toString());
	}

	/**
	 * 生成mbt update内容
	 * 
	 */
	public static void addUpdateElement(Element rootEle) {
		rootEle.addComment("根据条件更新 ");
		rootEle.addComment("update sql").addText("\r\n");
		Element resultMap = rootEle.addElement("Sql");
		resultMap.addAttribute("name", "update" + getFormatTableName() + "ByCondition");
		resultMap.addText("\r\t\tUPDATE " + table + " SET @condition WHERE ");
		StringBuffer updateById = new StringBuffer();
		for (String string : keyList) {
			updateById.append(string).append(" = ? and ");
		}
		updateById.delete(updateById.length() - 4, updateById.length());
		resultMap.addText(updateById.toString()).addText("\r\n\t");
	}

	/**
	 * 生成mbt delete内容
	 * 
	 * 
	 */
	public static void addDeleteElement(Element rootEle) {

		Element resultMap = rootEle.addElement("delete");
		if (keyList.size() == 1) {
			resultMap.addAttribute("id", "delete" + getFormatTableName() + "By" + upperFirestChar(columnToPropertyName(keyList.get(0))));
			resultMap.addText("\r\t\t").addText("DELETE FROM " + table.toUpperCase() + " WHERE " + keyList.get(0) + " = #{" + keyList.get(0) + "}").addText("\r\t");
		} else {
			resultMap.addAttribute("id", "delete" + getFormatTableName());
			resultMap.addAttribute("parameterType", upperFirestChar(getFormatTableName()));
			StringBuffer delById = new StringBuffer();
			for (String string : keyList) {
				delById.append(string).append(" = #{" + columnToPropertyName(string) + "} and ");
			}
			delById.delete(delById.length() - 4, delById.length());
			resultMap.addText("\r\t\t").addText("DELETE FROM " + table.toUpperCase() + " WHERE " + delById).addText("\r\t");
		}

	}

	/**
	 * 得到Bean的包名
	 * 
	 * @return
	 */
	public static String getBeanPackage() {
		return bean_package.replace("\\", ".");
	}

	/**
	 * 得到DAO的包名
	 * 
	 * @return
	 */
	public static String getDAOPackage() {
		return dao_package.replace("\\", ".");
	}

	public static String getFormatTableName() {
		String formatTableName = table_text;
		return formatTableName;
	}

	/*
	 * 重写字段反向生成POJO类中的成员变量的命名规则 第一个字母大写,其他字母全部小写,如果后面有下划线去除下划线。
	 */
	public static String columnToPropertyName(String column) {

		if (column != null) {
			String[] tmp = column.split("_");
			// 最终的表名
			String finalPropertyName = "";
			if (tmp.length == 1) {
				// 没有下划线
				finalPropertyName = tmp[0].toLowerCase();
			} else {

				finalPropertyName = tmp[0].toLowerCase();
				for (int i = 1; i < tmp.length; i++) {

					if (tmp[i] == null || tmp[i].toString().length() == 0) {
						continue;
					}
					String temp = tmp[i].toLowerCase();
					String tmpChar = temp.substring(0, 1).toUpperCase();
					String postString = temp.substring(1, temp.length());
					temp = tmpChar + postString;
					finalPropertyName = finalPropertyName + temp;
				}
			}
			return finalPropertyName;
		}
		return "";
	}

	/**
	 * 把doc 写到相应的xml文件中
	 * 
	 * @param doc
	 *            Document对象.
	 * @param file
	 *            xml文件对象.
	 */
	public static void writeDocToFile(Document doc, File file) {
		try {
			File parentFile = file.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setTrimText(false);
			format.setEncoding(encode);
			XMLWriter output = new XMLWriter(new FileWriter(file), format);
			output.write(doc);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据传进来的数据类型来得到相应的java类型字符串
	 * 
	 * @param dataType
	 * @return
	 */
	public static String getPojoType(String dataType) {
		String javaType = map.get(dataType);
		if (javaType == null || javaType.length() == 0) {
			System.err.print("不支持的类型:" + dataType);
		}
		return javaType;
	}

	/**
	 * 需要导入的包
	 * 
	 * @param dataType
	 * @return
	 */
	public static String getImport(String dataType) {
		if (map.get(dataType) == null || "".equals(map.get(dataType))) {
			return null;
		} else {
			return map.get(dataType);
		}
	}

	/**
	 * 获取TABLE的元数据
	 * 
	 * @author 翁斯波 2010-11-18
	 */
	public static ResultSetMetaData getResultSetMetaData(String sql) {
		DBControl1 db = new DBControl1(user, psd, url, driver);
		Connection conn = db.getConn();
		PreparedStatement psmt = db.getPreparedStatement(conn, sql);
		ResultSetMetaData rsmd = null;
		ResultSet rs;
		try {
			rs = psmt.executeQuery();
			rsmd = rs.getMetaData();
		} catch (SQLException e) {
			System.out.println("获取ResultSetMetaData失败...在" + MybatisGenerateUtils1.class.getName() + ".ResultSetMetaData()方法中");
			e.printStackTrace();
		}
		return rsmd;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	private static String getMethodStr(String field, String type) {
		StringBuilder get = new StringBuilder("\tpublic ");
		get.append(type).append(" ");
		if (type.equals("boolean")) {
			get.append(field);
		} else {
			get.append("get");
			get.append(upperFirestChar(field));
		}
		get.append("(){").append("\r\n\t\treturn this.").append(field).append(";\r\n\t}\r\n");
		StringBuilder set = new StringBuilder("\tpublic void ");

		if (type.equals("boolean")) {
			set.append(field);
		} else {
			set.append("set");
			set.append(upperFirestChar(field));
		}
		set.append("(").append(type).append(" ").append(field).append("){\r\n\t\tthis.").append(field).append("=").append(field).append(";\r\n\t}\r\n");
		get.append(set);
		return get.toString();
	}

	/**
	 * 获取该表的主键
	 * 
	 * @author 翁斯波 2010-11-18
	 * @param table
	 */
	public static List<String> getKeyList() {
		List<String> keyList = new ArrayList<String>();
		DBControl1 db = new DBControl1(user, psd, url, driver);
		Connection conn = db.getConn();
		PreparedStatement commentPsmt = db.getPreparedStatement(conn, keySQL);
		try {
			ResultSet commentRs = commentPsmt.executeQuery();
			while (commentRs.next()) {
				keyList.add(commentRs.getString("COLUMN_NAME"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keyList;
	}

	/**
	 * 将字符的第一个字母改成大写
	 * 
	 * @param src
	 * @return
	 */
	public static String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}

	/**
	 * 将内容写入到文件中去
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            文件内容
	 */
	public static void writeToFile(File file, String content) {
		try {
			OutputStreamWriter outputStream = new OutputStreamWriter(new FileOutputStream(file), encode);
			outputStream.write(content);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
