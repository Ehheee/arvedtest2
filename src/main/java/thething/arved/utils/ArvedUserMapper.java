package thething.arved.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import thething.arved.dataobjects.ArvedUser;

public class ArvedUserMapper implements RowMapper<ArvedUser>{

	public ArvedUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArvedUser arvedUser = new ArvedUser();
		arvedUser.setDefaultSort(rs.getString("defaultSort"));
		arvedUser.setEnabled(rs.getBoolean("enabled"));
		arvedUser.setFullName(rs.getString("fullName"));
		arvedUser.setId(rs.getLong("id"));
		arvedUser.setPassword(rs.getString("password"));
		arvedUser.setSalt(rs.getString("salt"));
		arvedUser.setPrivilege(rs.getString("privilege"));
		arvedUser.setPageSize(rs.getInt("pageSize"));
		arvedUser.setUserName(rs.getString("userName"));
		return arvedUser;
	}

	
	
}
