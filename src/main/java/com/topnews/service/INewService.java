package com.topnews.service;

import java.util.List;

import com.topnews.model.NewsModel;
import com.topnews.paging.Pageble;

public interface INewService {

	List<NewsModel> findByCategoryId(Long categoryId);
	List<NewsModel> findTopByCategoryId(Long categoryId);
	NewsModel save(NewsModel newsModel);
	NewsModel update(NewsModel updateNew);
	void delete(long[] ids);
	List<NewsModel> findAll(Pageble pageble); 
	int getTotalItem();
	NewsModel findOne(Long id);
	List<NewsModel> findTop(int top);
}
