package com.topnews.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.topnews.dao.IUserDAO;
import com.topnews.model.UserModel;
import com.topnews.paging.Pageble;
import com.topnews.service.IUserService;

public class UserService implements IUserService {

	/*
	 * private IUserDAO userDAO;
	 * 
	 * public UserService() { userDAO = new UserDAO(); }
	 */
	@Inject
	private IUserDAO userDAO;
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}
	@Override
	public UserModel save(UserModel userModel) {
		userModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		newModel.setCreatedBy("");
		Long userId = userDAO.save(userModel);
		return userDAO.findOne(userId);
	}
	@Override
	public UserModel update(UserModel updateUser) {
		UserModel oldUser = userDAO.findOne(updateUser.getId());
		updateUser.setCreatedDate(oldUser.getCreatedDate());
		updateUser.setCreatedBy(oldUser.getCreatedBy());
		updateUser.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//		updateNew.setModifiedBy("");
		userDAO.update(updateUser);
		return userDAO.findOne(updateUser.getId());
	}
	@Override
	public void delete(long[] ids) {
		for (long id : ids) {

			userDAO.delete(id);
		}
		
	}
	@Override
	public List<UserModel> findAll(Pageble pageble) {
		return userDAO.findAll(pageble);
	}
	@Override
	public int getTotalItem() {
		return userDAO.getTotalItem();
	}
	@Override
	public UserModel findOne(Long id) {
		return userDAO.findOne(id);
	}
}
