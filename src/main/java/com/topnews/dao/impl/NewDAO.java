package com.topnews.dao.impl;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.topnews.dao.INewDAO;
import com.topnews.mapper.NewMapper;
import com.topnews.model.NewsModel;
import com.topnews.paging.Pageble;

public class NewDAO extends AbstractDAO<NewsModel>  implements INewDAO {	
	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid=?";
		return query(sql, new NewMapper(), categoryId);
	}
	@Override
	public Long save(NewsModel newsModel) {
		//String sql = "INSERT INTO news (title, content, categoryid) VALUES(?,?,?)";
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, thumbnail, shortdecription,");
		sql.append(" content, categoryid, createdby, createddate)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newsModel.getTitle(),newsModel.getThumbNail(),newsModel.getShortDescription(),newsModel.getContent(), newsModel.getCategoryId(), newsModel.getCreatedBy(), newsModel.getCreatedDate());
				
	}
	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id=?";
		List<NewsModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}
	@Override
	public void update(NewsModel updateNew) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, THUMBNAIL = ?, SHORTDECRIPTION = ?, ");
		sql.append("CONTENT = ?, CATEGORYID = ?, CREATEDDATE = ?, MODIFIEDDATE = ?, ");
		sql.append("CREATEDBY = ?, MODIFIEDBY = ? WHERE ID = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbNail(), updateNew.getShortDescription(), 
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getModifiedDate(),
				updateNew.getCreatedBy(), updateNew.getModifiedBy(), updateNew.getId());

	}
	@Override
	public void delete(long id) {

		String sql = "DELETE FROM news WHERE id= ?";
		update(sql, id);
	}
	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" " +pageble.getSorter().getSortBy());
		}
		if(pageble.getOffset() != null && pageble.getLimit() != null) {
			sql.append(" OFFSET "+pageble.getOffset()+" ROWS FETCH NEXT");
			sql.append(" "+pageble.getLimit()+" ROWS ONLY");
		}
		return query(sql.toString(), new NewMapper());
		
	}
	@Override
	public int getTotalItem() {
		String sql = "select count(*) from news";
		return count(sql);
	}
	@Override
	public List<NewsModel> findTop(int top) {
		String sql = "SELECT * FROM news ORDER BY id desc FETCH FIRST "+top+" ROWS ONLY";
		return query(sql, new NewMapper());
	}
}

