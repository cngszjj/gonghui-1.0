
/*
 * 
 * @author jun
 *
 * @date 2014年4月28日 11:32:17
 *
 * @action 一个简单公共的hibernate通用dao实现类
 */
package com.gh.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.gh.dao.GenericDao;


/**
 * 
 * @author jun
 * 
 * @action 一个公共的Hibernate通用dao实现类<br>
 *         数据库访问层,每一个实现类都应该来继承该类<br>
 *         不应该重写里面的方法,需要相应的方法,直接到数据访问层每个类对应的接口中添加
 */
public abstract class GenericDaoImpl<E extends Serializable, PK extends Serializable>
		implements GenericDao<E, PK> {
	public HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 为E对应的实例类型
	 */
	private Class<?> entityClass;

	/**
	 * 获取E实例类的类型
	 */
	public GenericDaoImpl() {
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			this.entityClass = (Class<?>) ((ParameterizedType) t)
					.getActualTypeArguments()[0];
		}
	}

	@SuppressWarnings("unchecked")
	public E get(PK id) {
		return (E) hibernateTemplate.get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E get(PK id, LockMode lock) {
		E entity = (E) hibernateTemplate.get(this.entityClass, id,
				lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	public Object getStackValue(DetachedCriteria criteria, String propertyName,
			Stack value) {
		switch (value) {
		case MAX:
			criteria.setProjection(Projections.max(propertyName));
			break;
		case MIN:
			criteria.setProjection(Projections.min(propertyName));
			break;
		case AVG:
			criteria.setProjection(Projections.avg(propertyName));
			break;
		default:
			criteria.setProjection(Projections.sum(propertyName));
			break;
		}
		return this.findByCriteria(criteria, 0, 1).get(0);
	}

	public Integer getRowCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		return (Integer) this.findByCriteria(criteria, 0, 1).get(0);
	}

	@SuppressWarnings("unchecked")
	public E load(PK id) {
		return (E) hibernateTemplate.load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public E load(PK id, LockMode lock) {
		E entity = (E) hibernateTemplate.load(this.entityClass, id,
				lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> loadAll() {
		return (List<E>) hibernateTemplate.loadAll(entityClass);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String hql) {
		return hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	public List<E> find(String hql, Object... values) {
		return hibernateTemplate.find(hql, values);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName, Object... values) {
		return hibernateTemplate.findByNamedQuery(queryName, values);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQuery(String queryName) {
		return hibernateTemplate.findByNamedQuery(queryName);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params) {
		return hibernateTemplate.findByNamedQueryAndNamedParam(
				queryName, (String[]) params.keySet().toArray(),
				params.values().toArray());
	}

	@SuppressWarnings("unchecked")
	public List<E> findByNamedParam(String queryName, Map<String, Object> params) {
		return hibernateTemplate
				.findByNamedParam(queryName,
						(String[]) params.keySet().toArray(),
						params.values().toArray());
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(DetachedCriteria criteria) {
		return hibernateTemplate.findByCriteria(criteria);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults) {
		return hibernateTemplate.findByCriteria(criteria,
				firstResult, maxResults);
	}

	public void save(E entity) throws HibernateException {
		hibernateTemplate.save(entity);
	}

	public void saveOrUpdate(E entity) throws HibernateException {
		hibernateTemplate.saveOrUpdate(entity);
	}

	public void saveOrUpdate(Collection<E> entitys) throws HibernateException {
		hibernateTemplate.saveOrUpdateAll(entitys);
	}

	public void delete(E entity) throws HibernateException {
		hibernateTemplate.delete(entity);
	}

	public void delete(E entity, LockMode lock) throws HibernateException {
		hibernateTemplate.delete(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	public void delete(Collection<E> entitys) throws HibernateException {
		hibernateTemplate.deleteAll(entitys);
	}

	public void update(E entity) throws HibernateException {
		hibernateTemplate.update(entity);
	}

	public void update(E entity, LockMode lock) throws HibernateException {
		hibernateTemplate.update(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	public Integer bulkUpdate(String hql) {
		return hibernateTemplate.bulkUpdate(hql);
	}

	public Integer bulkUpdate(String hql, Object... values) {
		return hibernateTemplate.bulkUpdate(hql, values);
	}

	public void flush() throws HibernateException {
		hibernateTemplate.flush();
	}

	public void lock(E entity, LockMode lock) throws HibernateException {
		hibernateTemplate.lock(entity, lock);
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {
		return DetachedCriteria.forClass(c);
	}

	
	@SuppressWarnings("unchecked")
	public List<E> find(final String hql,final int firstResult,final int maxResults){
		List<E> T_list = new ArrayList<E>();
		try {
			
			
			T_list = hibernateTemplate.executeFind(
					new HibernateCallback<List<E>>() {
						@Override
						public List<E> doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							
							try {
								Query query = session.createQuery(hql);
								
								if (maxResults >= 0) {
									query.setMaxResults(maxResults);
								}
								if (firstResult >= 0) {
									query.setFirstResult(firstResult);
								}
								List<E> _T_list = query.list();
							
								return _T_list;
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								return null;
							}
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return T_list;
		
	}
	@SuppressWarnings("unchecked")
	public List<E> find(final String hql,final int firstResult,final int maxResults,final Object... values){
		List<E> T_list = new ArrayList<E>();
		try {
			
			System.out.println("BaseDaoImpl gethibernateTemplate ... ");
			T_list = this.hibernateTemplate.executeFind(
					new HibernateCallback<List<E>>() {
						@Override
						public List<E> doInHibernate(Session session)
								throws HibernateException, SQLException {
							
							
							Query query = session.createQuery(hql);
							
							if (maxResults >= 0) {
								query.setMaxResults(maxResults);
							}
							if (firstResult >= 0) {
								query.setFirstResult(firstResult);
							}

							// 设置参数列表
							if (values != null) {
								for (int i = 0; i < values.length; i++) {
									Object param = values[i];

									query.setParameter(i, param);
								
									// from User where name = :myname,
									// q.setParameter("myname",
									// "张三")
								}
							}

							List<E> _T_list = query.list();

							return _T_list;
						}
					});
		} catch (Exception e) {
			System.out.println(e);
		}
		return T_list;
		
	}
	public int findCountBySql( final String hql){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx =  session.beginTransaction();
		Query query = session.createSQLQuery(hql);
		List list = query.list();
		BigDecimal bg =  (BigDecimal)(list.get(0));
		int i = bg.intValue();
		
		tx.commit();
		session.clear();
		session.close();
	
		return i;
		
	}
	public int findCountByHql( final String hql){
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx =  session.beginTransaction();
		Query query = session.createQuery(hql);
		List list = query.list();
		int i = 0;
		if(list.size()>0){
			BigDecimal bg =  (BigDecimal)(list.get(0));
			i = bg.intValue();
		}
		
		
		tx.commit();
		session.clear();
		session.close();
	
		return i;
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> findByObj(final String hql,final int firstResult,final int maxResults,Object... values){
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			list = this.getHibernateTemplate().executeFind(
				     new HibernateCallback(){
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
								Query query = session.createQuery(hql);
						       query.setFirstResult(firstResult);
						       query.setMaxResults(maxResults);
						       List list = query.list();
							return list;
						}});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Object[]> findBySql(final String sql){
		List<Object[]> list = new ArrayList<Object[]>();
		try {
			list = this.getHibernateTemplate().executeFind(
				     new HibernateCallback(){
						@Override
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							SQLQuery query = session.createSQLQuery(sql);
						       List list = query.list();
							return list;
						}});
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}

	
	

}

