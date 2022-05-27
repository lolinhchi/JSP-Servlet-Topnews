package com.topnews.dao;

import java.util.List;

import com.topnews.model.NewsModel;
import com.topnews.paging.Pageble;

public interface INewDAO extends GenericDAO<NewsModel>{
	List<NewsModel> findByCategoryId(Long categoryId);
	Long save(NewsModel newsModel);
	NewsModel findOne(Long id);
	void update(NewsModel updateNew);
	void delete(long id);
	List<NewsModel> findAll(Pageble pageble);
	int getTotalItem();
	List<NewsModel> findTop(int top);
}
