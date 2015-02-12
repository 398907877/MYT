package com.yihu.myt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class DBControl1 {


	
	private String user = "NetOper"; // �û���
	private String password = "Net123#YiHu"; // �������ݿ�����
	private String url = "jdbc:sqlserver://10.0.200.21:1433;database=YiHuNet2008"; // ����url
	// String url =
	// "jdbc:mysql://117.27.142.145:3306/bbs?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true";
	private String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // ��������

	/**
	 * �յĹ���
	 */
	public DBControl1() {
	}

	/**
	 * ���췽��
	 * 
	 * @param user
	 * @param password
	 * @param url
	 * @param drive
	 */
	public DBControl1(String user, String password, String url, String drive) {
		this.user = user;
		this.password = password;
		this.url = url;
		this.drive = drive;
	}

	/**
	 * ͨ��JDBC��ʽ��ȡ���ݿ�����
	 */
	public Connection getGameConn() {
		Connection conn = null; // ����
		try {
			Class.forName(drive); // ��������
			conn = DriverManager.getConnection(url, user, password); // ��ȡ����
		} catch (ClassNotFoundException e) {
			System.out.println("��ȡ����ʧ��..........�����ǳ���ԭ��:1.������δ��");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("��ȡ����ʧ��...........�����ǳ���ԭ��:1.������δ��,2.�û����������,3.���ݿ�����URL��driveд��");
			e.printStackTrace();
		}
		return conn; // ��������
	}

	/**
	 * ͨ��JDBC��ʽ��ȡ���ݿ�����
	 */
	public Connection getConn() {
		Connection conn = null; // ����
		try {
			Class.forName(drive); // ��������
			conn = DriverManager.getConnection(url, user, password); // ��ȡ����
		} catch (ClassNotFoundException e) {
			System.out.println("��ȡ����ʧ��..........�����ǳ���ԭ��:1.������δ��");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out
					.println("��ȡ����ʧ��...........�����ǳ���ԭ��:1.������δ��,2.�û����������,3.���ݿ�����URL��driveд��");
			e.printStackTrace();
		}
		return conn; // ��������
	}
	
	/**
	 * ��ȡ�����
	 * 
	 * @author ��˹�� 2011-4-11
	 * @param sql
	 *            ��ѯ��sql���.֧��'?'��
	 * @param params
	 *            ����,��Ӧsql�е�'?' ,null:��ʹ��
	 * @return ����һ��result�����
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql, List<Object> params) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = this.getGameConn(); // ��ȡ����
		ps = this.getPreparedStatement(con, sql); // ��ȡPreparedStatement
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
		rs = ps.executeQuery(); // �����
		return rs;
	}

	/**
	 * �����ݿ��ѯ���������ݴ����list��,list�д�ŵ���map,
	 * 
	 * @param sql
	 *            ��ѯ��sql���.֧��'?'��
	 * @param params
	 *            ����,��Ӧsql�е�'?' ,null:��ʹ��
	 * @return ����һ��List��
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryForList(String sql, List<Object> params) throws SQLException {

		ResultSetMetaData rsmd = null;// Դ����
		int columnCount;// �ֶ���
		ResultSet rs = null;
		Map<String, Object> map = null;
		rs = getResultSet(sql, params);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(rs.getFetchSize()); // �����ʺϴ�С��list
		try {
			while (rs.next()) {
				rsmd = rs.getMetaData();// ��ȡԴ����
				columnCount = rsmd.getColumnCount();
				map = new LinkedHashMap<String, Object>(columnCount); // �����ʺϴ�С��map
				for (int j = 1; j <= columnCount; j++) {
					map.put(rsmd.getColumnName(j), rs.getObject(rsmd.getColumnName(j)));
				}
				list.add(map);
			}
		} finally {
			this.closeConn(rs, null, (PreparedStatement) rs.getStatement(), rs.getStatement().getConnection());
		}
		return list;
	}

	/**
	 * ��ȡPreparedStatement,
	 * 
	 * @param con
	 *            ���ݿ�����
	 * @param sql
	 *            sql���
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(Connection con, String sql) {
		try {
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("��ȡPreparedStatementʧ��!..................");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �ر�����
	 * 
	 * @param rs
	 *            �����
	 * @param st
	 *            ״̬
	 * @param ps
	 *            Ԥ����
	 * @param conn
	 *            ����
	 */
	public void closeConn(ResultSet rs, Statement st, PreparedStatement ps, Connection conn) {
		// �رս����
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// �ر�����
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ����
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ִ�����,ɾ��,�޸���Ӧ�ķ���
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public int execute(List<Object> params) throws SQLException {
		int queryCount = 0;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = this.getGameConn(); // ��ȡ����
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			for (int i = 0; i < params.size(); i++) {
				// ��һ��SQL������������б�
				st.addBatch((String) params.get(i));

			}
			// ִ����������
			st.executeBatch();
			con.commit();
			con.setAutoCommit(true);
		} finally {
			this.closeConn(null, null, ps, con);
		}

		return queryCount;
	}

	public static void main(String[] args) throws SQLException {
		DBControl1 control = new DBControl1();
		List<Object> params = new ArrayList<Object>();
		System.out.println("��ʼ�������ݡ�����");
		System.out.println(control.getGameConn());
	}
}
