package tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtils1 {
	private static ThreadLocal<Connection>threadlocal = new ThreadLocal<Connection>();
	static ComboPooledDataSource datasource = new ComboPooledDataSource();
	public static Connection getConnection() throws SQLException{
		Connection con = threadlocal.get();
		if(con != null)
			return con;
		return datasource.getConnection();
	}
	public static DataSource getDataSource(){
		return datasource;
	}
	public static void BeginTransaction() throws SQLException{
		Connection con = threadlocal.get();
		if(con != null)
			throw new RuntimeException("不能重复开启事务");
		con = datasource.getConnection();
		con.setAutoCommit(false);
		threadlocal.set(con);
	}
	public static void CommitTransaction() throws SQLException{
		Connection con = threadlocal.get();
		if(con == null)
			throw new RuntimeException("请先开启事务");
		con.commit();
		con.close();
		threadlocal.remove();
	}
	public static void RollbackTrans() throws SQLException{
		Connection con = threadlocal.get();
		con.rollback();
		con.close();
		threadlocal.set(con);
	}
	public static void releaseConnection(Connection connection) throws SQLException{
		Connection con = threadlocal.get();
		if(con == null)
			connection.close();
		if(con != connection)
			connection.close();
	}
}
