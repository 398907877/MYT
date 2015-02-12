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
 * ����Mybatis�����࣬���ڸ������ݿ�������Ӧ������
 * 
 * @author wsb��
 * 
 * @version 2012-9-24(��)
 */
public class MybatisGenerateUtils1 {

	// private static String project_url = "E:\\����\\����\\tools\\src\\"; //
	// ����·����\\����
	private static String project_url = "D:\\8888888\\MYT\\src\\"; // ����·����\\����

	private static String mbt_xml_package = ""; // �����ļ�·��
	private static String bean_package = "com\\yihu\\myt\\vo"; // ʵ��·��
	private static String dao_package = "com\\yihu\\myt\\service\\service"; // dao·��
	private static String sqlNameEnum = "com\\yihu\\myt\\enums"; // ö��·��
	private static String dao_end_sign = "Service"; // DAO��ʲô��β
	private static String encode = "GBK";

	private static String url = "jdbc:sqlserver://10.0.200.21:1433;database=YiHuNet2008";
	private static String user = "NetOper";
	private static String psd = "Net123#YiHu";
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String table = "ZIXUN_CloseQue";
	private static String table_text = "CloseQue";

	private static String sql = "select * from " + table;
	private static String keySQL = "select COLUMN_NAME from INFORMATION_SCHEMA.KEY_COLUMN_USAGE where table_name='" + table + "'";
	private static List<String> keyList = getKeyList(); // �����б�,�������õ�

	private static Map<String, String> map; // ���ݿ��Ӧ��bean����

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
	 * ����Mbt xml�ļ�
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
	 * ���� bean �ļ�
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
	 * ���� dao �ļ�����
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
	 * ���� dao �ļ�����
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

		dao.append("import " + getBeanPackage() + "." + getFormatTableName() + "Vo;\r"); // ����bean��
		dao.append("import " + getDAOPackage() + ".I" + getFormatTableName() + dao_end_sign + ";\r"); // ����bean��

		dao.append("\r\r\n");
		dao.append("public class " + getFormatTableName() + dao_end_sign + " implements I" + getFormatTableName() + dao_end_sign + "{\r\r\n");

		dao.append("\t").append("/**").append("\r\t*��ȡ�б��¼��\r").append("\t*/").append("\r");
		String methodName = "query" + getFormatTableName() + "CountByCondition";
		dao.append("\t").append("public Integer " + methodName + "(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
		dao.append("sql.addVar(\"@condition\", condition.toString());\r\t\t");
		dao.append("Integer count = DB.me().queryForInteger(MyDatabaseEnum.BaseInfo, sql);\r\t\t");
		dao.append("return count;\r\t");
		dao.append("}").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*��ȡ�б�\r").append("\t*/").append("\r");
		methodName = "query" + getFormatTableName() + "ListByCondition";
		dao.append("\t").append("public List<" + getFormatTableName() + "Vo> query" + getFormatTableName() + "ListByCondition(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
		dao.append("sql.addVar(\"@condition\", condition.toString());\r\t\t");
		dao.append("sql.addVar(\"@page\", \"\");\r\t\t");
		dao.append("List<" + getFormatTableName() + "Vo" + "> list = DB.me().queryForBeanList(MyDatabaseEnum.BaseInfo, sql," + getFormatTableName() + "Vo.class" + ");\r\t\t");
		dao.append("return list;\r\t");
		dao.append("}").append("\r\r\n");

		// ���
		dao.append("\t").append("/**").append("\r\t*���\r").append("\t*/").append("\r");
		methodName = "insert" + getFormatTableName();
		dao.append("\t").append("public void insert" + getFormatTableName() + "(" + getFormatTableName() + "Vo vo) throws Exception{\r\t\t");
		dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
		// ���ɵ�get/set
		ResultSetMetaData rsmd = getResultSetMetaData(sql);
		for (int j = 1; j <= rsmd.getColumnCount(); j++) {
			String columnName = rsmd.getColumnName(j); // �ֶ���
			dao.append("sql.addParamValue(vo.get"+upperFirestChar(columnName)+"());\r\t\t");
		}

		dao.append("DB.me().insert(MyDatabaseEnum.BaseInfo,sql);\r\t\t");
		dao.append("}").append("\r\r\n");

		if (keyList.size() > 0) {
			dao.append("\t").append("/**").append("\r\t*�޸�\r").append("\t*/").append("\r");
			methodName = "update" + getFormatTableName() + "ByCondition";
			dao.append("\t").append("public void update" + getFormatTableName() + "ByCondition(" + getFormatTableName() + "Vo vo,JdbcConnection conn) throws Exception{");
			dao.append("Sql sql = DB.me().createSql(" + getFormatTableName() + "SqlNameEnum." + methodName + ");\r\t\t");
			StringBuilder keyEmpty = new StringBuilder();
			for (String key : keyList) {
				key = "get" + key.substring(0, 1).toUpperCase() + key.substring(1) + "()";
				keyEmpty.append(" || StringUtil.isEmpty(vo." + key + ")");
			}
			dao.append("if (vo == null " + keyEmpty + ") {\r\t\t");
			dao.append("	throw new Exception(\" ����Ϊ�ջ��� ����id ����Ϊ��\");\r\t\t");
			dao.append("}\r\t\t");
			dao.append("StringBuilder condition = new StringBuilder();\r\t\t");
			dao.append("if (condition.length() == 0) {\r\t\t");
			dao.append("throw new Exception(\"δ�и���SQL��ִ�У�\");\r\t\t");
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
	 * ���� ö�� �ļ�����
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
		dao.append("\r\n\t//��ѯ��¼��\r\n\t");
		dao.append("query" + getFormatTableName() + "CountByCondition,");
		dao.append("\r\n\t//��ѯ��¼\r\n\t");
		dao.append("query" + getFormatTableName() + "ListByCondition,");
		dao.append("\r\n\t//����\r\n\t");
		dao.append("insert" + getFormatTableName() + ",");
		dao.append("\r\n\t//������������\r\n\t");
		dao.append("update" + getFormatTableName() + "ByCondition");
		dao.append("\r\n}\r");

		writeToFile(file, dao.toString());
	}

	/**
	 * ����DAO�ļ�����
	 * 
	 * @return
	 */
	public static String getIntefaceContent() {
		StringBuffer dao = new StringBuffer("package " + getDAOPackage() + ";" + "\r\r");
		dao.append(getPojoType("LIST_IMPORT")).append("\r\r");
		dao.append("import com.coreframework.ioc.By;\r");

		dao.append("import com.coreframework.db.JdbcConnection;");
		dao.append("import " + getBeanPackage() + "." + getFormatTableName() + "Vo;\r"); // ����bean��
		dao.append("import " + getDAOPackage() + ".impl." + getFormatTableName() + dao_end_sign + ";\r"); // ����impl��

		dao.append("\r\r\n");
		dao.append("@By(" + getFormatTableName() + dao_end_sign + ".class)").append("\r\n");
		dao.append("public interface I" + getFormatTableName() + dao_end_sign + "{\r\r\n");

		dao.append("\t").append("/**").append("\r\t*��ȡ�б��¼��\r").append("\t*/").append("\r");
		dao.append("\t").append("public Integer query" + getFormatTableName() + "CountByCondition(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*��ȡ�б�\r").append("\t*/").append("\r");
		dao.append("\t").append("public List<" + getFormatTableName() + "Vo> query" + getFormatTableName() + "ListByCondition(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");

		dao.append("\t").append("/**").append("\r\t*���\r").append("\t*/").append("\r");
		dao.append("\t").append("public void insert" + getFormatTableName() + "(" + getFormatTableName() + "Vo vo) throws Exception;").append("\r\r\n");
		if (keyList.size() > 0) {
			dao.append("\t").append("/**").append("\r\t*�޸�\r").append("\t*/").append("\r");
			dao.append("\t").append("public void update" + getFormatTableName() + "ByCondition(" + getFormatTableName() + "Vo vo,JdbcConnection conn) throws Exception;").append("\r\r\n");
		}
		dao.append("}\r");
		return dao.toString();
	}

	/**
	 * ����javaBean�ļ�����
	 */
	public static String getBeanContext() {
		StringBuffer bean = new StringBuffer("package " + getBeanPackage() + ";" + "\r\r");
		try {
			ResultSetMetaData rsmd = getResultSetMetaData(sql);
			// ��Ҫ����İ�
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // ���ݿ�����
				String im = getImport(columnDbType + "_IMPORT");
				if (im != null) {
					bean.append(im).append("\r\n\r\n");
				}
			}

			bean.append("public class " + getFormatTableName() + "Vo{\r");

			// ���ɵ�����
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // ���ݿ�����
				String columnName = rsmd.getColumnName(j); // �ֶ���
				String column = "\tprivate " + getPojoType(columnDbType) + " " + columnName + ";  ";
				// String columnName = rsmd.getColumnName(j).toLowerCase(); // �ֶ���
				// String column = "\tprivate " + getPojoType(columnDbType) + " " + columnToPropertyName(columnName) + ";  ";
				bean.append(column).append("\r\n\r\n");
			}

			// ���ɵ�get/set
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnDbType = rsmd.getColumnTypeName(j); // ���ݿ�����
				String columnName = rsmd.getColumnName(j); // �ֶ���
				bean.append(getMethodStr(columnName, getPojoType(columnDbType))).append("\r\n");
				// String columnName = rsmd.getColumnName(j).toLowerCase(); // �ֶ���
				// bean.append(getMethodStr(columnToPropertyName(columnName), getPojoType(columnDbType))).append("\r\n");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		bean.append("}");
		return bean.toString();
	}

	/**
	 * ����mbt xml�ļ�����
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
	 * ����mbt select����
	 * 
	 * 
	 */
	public static void addSelectElement(Element rootEle) {
		rootEle.addComment("��ѯ��¼��");
		Element resultMap = rootEle.addElement("Sql");
		resultMap.addText("\r\n");
		resultMap.addAttribute("name", "query" + getFormatTableName() + "CountByCondition");
		resultMap.addText("\t\t").addText("SELECT count (*) FROM " + table + " where 1=1 @condition").addText("\r\n\t");

		rootEle.addComment("����������ҳ��ѯ��¼ ");
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
	 * ����mbt insert����
	 * 
	 * 
	 */
	public static void addInsertElement(Element rootEle) {
		rootEle.addComment("���� ");
		Element resultMap = rootEle.addElement("Sql");
		resultMap.addAttribute("name", "insert" + getFormatTableName());

		StringBuffer sb = new StringBuffer("\r\tINSERT INTO " + table + "(");
		try {
			ResultSetMetaData rsmd = getResultSetMetaData(sql);
			for (int j = 1; j <= rsmd.getColumnCount(); j++) {
				String columnName = rsmd.getColumnName(j); // �ֶ���
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
	 * ����mbt update����
	 * 
	 */
	public static void addUpdateElement(Element rootEle) {
		rootEle.addComment("������������ ");
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
	 * ����mbt delete����
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
	 * �õ�Bean�İ���
	 * 
	 * @return
	 */
	public static String getBeanPackage() {
		return bean_package.replace("\\", ".");
	}

	/**
	 * �õ�DAO�İ���
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
	 * ��д�ֶη�������POJO���еĳ�Ա�������������� ��һ����ĸ��д,������ĸȫ��Сд,����������»���ȥ���»��ߡ�
	 */
	public static String columnToPropertyName(String column) {

		if (column != null) {
			String[] tmp = column.split("_");
			// ���յı���
			String finalPropertyName = "";
			if (tmp.length == 1) {
				// û���»���
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
	 * ��doc д����Ӧ��xml�ļ���
	 * 
	 * @param doc
	 *            Document����.
	 * @param file
	 *            xml�ļ�����.
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
	 * ���ݴ������������������õ���Ӧ��java�����ַ���
	 * 
	 * @param dataType
	 * @return
	 */
	public static String getPojoType(String dataType) {
		String javaType = map.get(dataType);
		if (javaType == null || javaType.length() == 0) {
			System.err.print("��֧�ֵ�����:" + dataType);
		}
		return javaType;
	}

	/**
	 * ��Ҫ����İ�
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
	 * ��ȡTABLE��Ԫ����
	 * 
	 * @author ��˹�� 2010-11-18
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
			System.out.println("��ȡResultSetMetaDataʧ��...��" + MybatisGenerateUtils1.class.getName() + ".ResultSetMetaData()������");
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
	 * ��ȡ�ñ������
	 * 
	 * @author ��˹�� 2010-11-18
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
	 * ���ַ��ĵ�һ����ĸ�ĳɴ�д
	 * 
	 * @param src
	 * @return
	 */
	public static String upperFirestChar(String src) {
		return src.substring(0, 1).toUpperCase().concat(src.substring(1));
	}

	/**
	 * ������д�뵽�ļ���ȥ
	 * 
	 * @param filePath
	 *            �ļ�·��
	 * @param content
	 *            �ļ�����
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
