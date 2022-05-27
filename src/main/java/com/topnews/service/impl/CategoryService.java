package com.topnews.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.topnews.dao.ICategoryDAO;
import com.topnews.model.CategoryModel;
import com.topnews.service.ICategoryService;

public class CategoryService implements ICategoryService {
	
	//weld - servlet
	@Inject
	ICategoryDAO categoryDAO;
//	public CategoryService() {
//		categoryDAO = new CategoryDAO();
//	}

	@Override
	public List<CategoryModel> findAll() {
		return categoryDAO.findAll();
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		return categoryDAO.findOneByCode(code);
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel) {
		categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long categoryId = categoryDAO.save(categoryModel);
		return categoryDAO.findOne(categoryId);
	}

	@Override
	public CategoryModel update(CategoryModel updateCategory) {
		CategoryModel oldCategory = categoryDAO.findOne(updateCategory.getId());
		updateCategory.setCreatedDate(oldCategory.getCreatedDate());
		updateCategory.setCreatedBy(oldCategory.getCreatedBy());
		updateCategory.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//		updateNew.setModifiedBy("");
		categoryDAO.update(updateCategory);
		return categoryDAO.findOne(updateCategory.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {

			categoryDAO.delete(id);
		}
		
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDAO.findOne(id);
	}

}
