package com.topnews.dao;

import java.util.List;

import com.topnews.model.UserModel;
import com.topnews.paging.Pageble;

public interface IUserDAO {

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

	Long save(UserModel userModel);

	UserModel findOne(Long userId);

	void update(UserModel updateUser);

	void delete(long id);

	List<UserModel> findAll(Pageble pageble);

	int getTotalItem();	
}
