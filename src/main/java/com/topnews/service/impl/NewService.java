package com.topnews.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.topnews.dao.ICategoryDAO;
import com.topnews.dao.INewDAO;
import com.topnews.model.CategoryModel;
import com.topnews.model.NewsModel;
import com.topnews.paging.Pageble;
import com.topnews.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDAO newDAO;
	
	@Inject
	private ICategoryDAO categoryDAO;
	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newDAO.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newModel) {
		newModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		newModel.setCreatedBy("");
		CategoryModel categoryModel = categoryDAO.findOneByCode(newModel.getCategoryCode());
		newModel.setCategoryId(categoryModel.getId());
		Long newId = newDAO.save(newModel);
		return newDAO.findOne(newId);
	}

	@Override
	public NewsModel update(NewsModel updateNew) {
		NewsModel oldNew = newDAO.findOne(updateNew.getId());
		updateNew.setCreatedDate(oldNew.getCreatedDate());
		updateNew.setCreatedBy(oldNew.getCreatedBy());
		updateNew.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//		updateNew.setModifiedBy("");
		CategoryModel categoryModel = categoryDAO.findOneByCode(updateNew.getCategoryCode());
		updateNew.setCategoryId(categoryModel.getId());
		newDAO.update(updateNew);
		return newDAO.findOne(updateNew.getId());
	}

	@Override
	public void delete(long[] ids) {
		for(long id: ids) {
			
			newDAO.delete(id);
		}
	}

	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDAO.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDAO.getTotalItem();
	}

	@Override
	public NewsModel findOne(Long id) {
		NewsModel newsModel  = newDAO.findOne(id);
		CategoryModel categoryModel = categoryDAO.findOne(newsModel.getCategoryId());
		newsModel.setCategoryCode(categoryModel.getCode());
		return newsModel;
	}

	@Override
	public List<NewsModel> findTop(int top) {
		return newDAO.findTop(top);
	}

	@Override
	public List<NewsModel> findTopByCategoryId(Long categoryId) {
		List<NewsModel> topList = new ArrayList<>();
		for(int i=0; i<3; i++) {
			topList.add(newDAO.findByCategoryId(categoryId).get(i));
		}
		return topList;
	}

}
