package com.topnews.paging;

import com.topnews.sort.Sorter;

public interface Pageble {

	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
