package com.topnews.service;

import java.util.List;

import com.topnews.model.CategoryModel;

public interface ICategoryService {

	List<CategoryModel> findAll();
	CategoryModel findOneByCode(String code);
	CategoryModel save(CategoryModel categoryModel);
	CategoryModel update(CategoryModel updateCategory);
	void delete(long[] ids);
	CategoryModel findOne(Long id);
}
