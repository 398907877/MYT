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


	
	private String user = "NetOper"; // 用户名
	private String password = "Net123#YiHu"; // 连接数据库密码
	private String url = "jdbc:sqlserver://10.0.200.21:1433;database=YiHuNet2008"; // 连接url
	// String url =
	// "jdbc:mysql://117.27.142.145:3306/bbs?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true";
	private String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // 连接驱动

	/**
	 * 空的构造
	 */
	public DBControl1() {
	}

	/**
	 * 构造方法
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
	 * 通过JDBC方式获取数据库连接
	 */
	public Connection getGameConn() {
		Connection conn = null; // 连接
		try {
			Class.forName(drive); // 加载驱动
			conn = DriverManager.getConnection(url, user, password); // 获取连接
		} catch (ClassNotFoundException e) {
			System.out.println("获取连接失败..........可能是出错原因:1.驱动包未加");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("获取连接失败...........可能是出错原因:1.驱动包未加,2.用户名密码出错,3.数据库连接URL或drive写错");
			e.printStackTrace();
		}
		return conn; // 返回连接
	}

	/**
	 * 通过JDBC方式获取数据库连接
	 */
	public Connection getConn() {
		Connection conn = null; // 连接
		try {
			Class.forName(drive); // 加载驱动
			conn = DriverManager.getConnection(url, user, password); // 获取连接
		} catch (ClassNotFoundException e) {
			System.out.println("获取连接失败..........可能是出错原因:1.驱动包未加");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out
					.println("获取连接失败...........可能是出错原因:1.驱动包未加,2.用户名密码出错,3.数据库连接URL或drive写错");
			e.printStackTrace();
		}
		return conn; // 返回连接
	}
	
	/**
	 * 获取结果集
	 * 
	 * @author 翁斯波 2011-4-11
	 * @param sql
	 *            查询的sql语句.支持'?'号
	 * @param params
	 *            参数,对应sql中的'?' ,null:不使用
	 * @return 返回一个result结果集
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql, List<Object> params) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		con = this.getGameConn(); // 获取连接
		ps = this.getPreparedStatement(con, sql); // 获取PreparedStatement
		if (params != null) {
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
		}
		rs = ps.executeQuery(); // 结果集
		return rs;
	}

	/**
	 * 将数据库查询出来的数据存放在list中,list中存放的是map,
	 * 
	 * @param sql
	 *            查询的sql语句.支持'?'号
	 * @param params
	 *            参数,对应sql中的'?' ,null:不使用
	 * @return 返回一个List。
	 * @throws SQLException
	 */
	public List<Map<String, Object>> queryForList(String sql, List<Object> params) throws SQLException {

		ResultSetMetaData rsmd = null;// 源数据
		int columnCount;// 字段数
		ResultSet rs = null;
		Map<String, Object> map = null;
		rs = getResultSet(sql, params);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(rs.getFetchSize()); // 定义适合大小的list
		try {
			while (rs.next()) {
				rsmd = rs.getMetaData();// 获取源数据
				columnCount = rsmd.getColumnCount();
				map = new LinkedHashMap<String, Object>(columnCount); // 定义适合大小的map
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
	 * 获取PreparedStatement,
	 * 
	 * @param con
	 *            数据库连接
	 * @param sql
	 *            sql语句
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(Connection con, String sql) {
		try {
			return con.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("获取PreparedStatement失败!..................");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接
	 * 
	 * @param rs
	 *            结果集
	 * @param st
	 *            状态
	 * @param ps
	 *            预编译
	 * @param conn
	 *            连接
	 */
	public void closeConn(ResultSet rs, Statement st, PreparedStatement ps, Connection conn) {
		// 关闭结果集
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

		// 关闭连接
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 连接
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 执行添加,删除,修改相应的方法
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
			con = this.getGameConn(); // 获取连接
			con.setAutoCommit(false);
			Statement st = con.createStatement();
			for (int i = 0; i < params.size(); i++) {
				// 把一个SQL命令加入命令列表
				st.addBatch((String) params.get(i));

			}
			// 执行批量更新
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
		System.out.println("开始插入数据。。。");
		System.out.println(control.getGameConn());
	}
}
