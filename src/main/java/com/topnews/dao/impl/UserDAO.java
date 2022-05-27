package com.topnews.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.topnews.dao.IUserDAO;
import com.topnews.mapper.UserMapper;
import com.topnews.model.UserModel;
import com.topnews.paging.Pageble;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users");
		sql.append(" INNER JOIN role ON role.id = users.roleid");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = query(sql.toString(), new UserMapper(), userName, password, status);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public Long save(UserModel userModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO USERS(USERNAME, PASSWORD, FULLNAME, STATUS, ROLEID, CREATEDDATE, CREATEDBY)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), userModel.getUserName(), userModel.getPassword(), userModel.getFullName(),
				userModel.getStatus(), userModel.getRoleId(), userModel.getCreatedDate(), userModel.getCreatedBy());
	}

	@Override
	public UserModel findOne(Long userId) {
		String sql = "SELECT * FROM users WHERE id=?";
		List<UserModel> users = query(sql, new UserMapper(), userId);
		return users.isEmpty() ? null : users.get(0);
	}

	@Override
	public void update(UserModel updateUser) {
		String sql = "UPDATE USERS SET USERNAME = ?,PASSWORD=?, FULLNAME=?, STATUS=?, ROLEID=?, MODIFIEDDATE=?, MODIFIEDBY=? WHERE ID=?";
		update(sql, updateUser.getUserName(), updateUser.getPassword(), updateUser.getFullName(), updateUser.getStatus(),
				updateUser.getRoleId(), updateUser.getModifiedDate(), updateUser.getModifiedBy());
		
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM users WHERE id= ?";
		update(sql, id);
		
	}

	@Override
	public List<UserModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM users");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" " +pageble.getSorter().getSortBy());
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" OFFSET "+pageble.getOffset()+" ROWS FETCH NEXT");
			sql.append(" "+pageble.getLimit()+" ROWS ONLY");
		}
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "select count(*) from users";
		return count(sql);
	}

}
