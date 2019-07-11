package com.nmid.admin.service;

import java.sql.SQLException;

import com.nmid.admin.dao.adminDao;
import com.nmid.admin.domain.admin;

public class adminService {
	private adminDao dao = new adminDao();

	public admin login(String name) {
		try {
			return dao.findByName(name);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
