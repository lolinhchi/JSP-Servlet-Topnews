package com.topnews.dao.impl;

import java.util.List;

import com.topnews.dao.ICategoryDAO;
import com.topnews.mapper.CategoryMapper;
import com.topnews.model.CategoryModel;

public class CategoryDAO  extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	
	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id=?";
		List<CategoryModel> categories = query(sql, new CategoryMapper(), id);
		return categories.isEmpty() ? null : categories.get(0);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> categories = query(sql, new CategoryMapper(), code);
		return categories.isEmpty() ? null : categories.get(0);
	}

	@Override
	public Long save(CategoryModel categoryModel) {
		String sql = "INSERT INTO CATEGORY(NAME, CODE, CREATEDDATE, CREATEDBY) VALUES(?, ?, ?, ?)";
		return insert(sql, categoryModel.getName(), categoryModel.getCode(), categoryModel.getCreatedDate(), categoryModel.getCreatedBy());
	}

	@Override
	public void update(CategoryModel updateCategory) {
		String sql = "UPDATE CATEGORY SET NAME = ?, CODE = ?, CREATEDDATE = ?, CREATEDBY = ?, MODIFIEDDATE = ?, MODIFIEDBY =? WHERE ID = ?";
		update(sql, updateCategory.getName(), updateCategory.getCode(), updateCategory.getCreatedDate(), updateCategory.getCreatedBy(),
				updateCategory.getModifiedDate(), updateCategory.getModifiedBy(), updateCategory.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM category WHERE id= ?";
		update(sql, id);
	}
		

}
