package com.topnews.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.topnews.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet resultSet) {
		try {
			CategoryModel category = new CategoryModel();
			category.setId(resultSet.getLong("id"));
			category.setCode(resultSet.getString("code"));
			category.setName(resultSet.getString("name"));
			category.setCreatedDate(resultSet.getTimestamp("createddate"));			
			category.setCreatedBy(resultSet.getString("createdby"));			
			if(resultSet.getTimestamp("modifieddate") != null) {
				category.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				category.setCreatedBy(resultSet.getString("modifiedby"));
			}
			return category;
		} catch (SQLException e) {
			return null;
		}
		
	}

	
}
