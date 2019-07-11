package tools;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class TxQueryRunner extends QueryRunner {

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		int result[] =  super.batch(conn, sql, params);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh)
			throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		T result = super.query(conn, sql, rsh);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh)
			throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		@SuppressWarnings("deprecation")
		T result = super.query(sql, params, rsh);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params)
			throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		T result = super.query(conn, sql, rsh, params);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		T result = super.query(conn, sql, rsh);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		int result = super.update(conn, sql, params);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		int result = super.update(conn, sql, param);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection conn = JDBCUtils1.getConnection();
		int result = super.update(conn, sql);
		JDBCUtils1.releaseConnection(conn);
		return result;
	}
	
}
