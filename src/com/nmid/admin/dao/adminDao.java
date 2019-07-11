package com.nmid.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import tools.TxQueryRunner;

import com.nmid.admin.domain.admin;

public class adminDao {
	private QueryRunner runner = new TxQueryRunner();

	public admin findByName(String name) throws SQLException {
		String sql = "select * from admin where name=?";
		admin adminer = runner.query(sql, new BeanHandler<admin>(admin.class),name);
		return adminer;
	}
	
}
