package com.topnews.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topnews.model.RoleModel;
import com.topnews.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setFullName(resultSet.getString("fullname"));
			user.setPassword(resultSet.getString("password"));
			user.setStatus(resultSet.getInt("status"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				user.setRole(role);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			user.setCreatedDate(resultSet.getTimestamp("createddate"));			
			user.setCreatedBy(resultSet.getString("createdby"));			
			if(resultSet.getTimestamp("modifieddate") != null) {
				user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				user.setCreatedBy(resultSet.getString("modifiedby"));
			}
			return user;
		} catch (SQLException e) {
			return null;
		}
	}

}
