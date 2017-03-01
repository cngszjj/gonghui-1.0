package com.gh.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class HibernateSubList<T> implements HibernateCallback<T> {

	int pageNum;
	int pageSize;
	public HibernateSubList(int pageNum,int pageSize){
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
	@Override
	public T doInHibernate(Session session) throws HibernateException,
			SQLException {
		Query query = session.createQuery("");
	       query.setFirstResult(pageNum);
	       query.setMaxResults(pageSize);
	       List list = query.list();
	       return  (T) list;
		
	}

}
