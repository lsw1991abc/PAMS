package com.lssrc.pams.util;

import java.sql.*;
import java.util.*;

public class DBUtils {
	private static String driver, url, user, password;

	static {
		ResourceBundle rb = ResourceBundle.getBundle("jdbc");
		driver = rb.getString("jdbc.driverClassName");
		url = rb.getString("jdbc.url");
		user = rb.getString("jdbc.username");
		password = rb.getString("jdbc.username");
	}

	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Object[][] queryForArray(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Object[][] result = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					stmt.setObject(i + 1, args[i]);
				}
			}
			rs = stmt.executeQuery();
			rs.last();
			int rows = rs.getRow();
			rs.beforeFirst();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			result = new Object[rows][cols];
			int currentRow = 0;
			while (rs.next()) {
				for (int i = 0; i < cols; i++) {
					result[currentRow][i] = rs.getObject(i + 1);
				}
				currentRow++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static List queryForList(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List result = new ArrayList();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					stmt.setObject(i + 1, args[i]);
				}
			}
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			String[] colNames = new String[cols];
			for (int i = 0; i < cols; i++) {
				colNames[i] = rsmd.getColumnName(i + 1);
			}
			while (rs.next()) {
				Map row = new HashMap();
				for (int i = 0; i < cols; i++) {
					row.put(colNames[i], rs.getObject(i + 1));
				}
				result.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Map queryForMap(String sql, Object[] args) throws Exception {
		List result = queryForList(sql, args);
		Map row = null;
		if (result.size() == 1) {
			row = (Map) result.get(0);
		} else {
			throw new Exception(result.size() + "");
		}
		return row;
	}

	public static boolean update(String sql, Object[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;
		boolean result = true;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					stmt.setObject(i + 1, args[i]);
				}
			}
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
