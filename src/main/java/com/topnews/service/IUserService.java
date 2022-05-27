package com.topnews.service;

import java.util.List;

import com.topnews.model.UserModel;
import com.topnews.paging.Pageble;

public interface IUserService {

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);

	UserModel save(UserModel userModel);

	UserModel update(UserModel updateUser);

	void delete(long[] ids);

	List<UserModel> findAll(Pageble pageble);

	int getTotalItem();

	UserModel findOne(Long id);
}
