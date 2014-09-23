package university.dataaccess.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import university.dataaccess.utils.DataFilter;
/**
 * Generic hibernateDao that is extended by specific implementations.
 * Most methods behave the same for all classes so this helps with maintanance
 * and it is easier to add new types in the future
 * @author Kaur
 *
 * @param <T>
 */
public abstract class AbstractHibernateDao <T> {

	
	private Class< T > clazz;
	protected void setClazz( Class< T > clazz ){
		this.clazz = clazz;
	}
	
	public List<T> getByFilter(DataFilter filter){
		Query q = getCurrentSession().createQuery(filter.getQuery());
		if(filter.getPage() != null && filter.getPageSize() != null){
			q.setFirstResult(filter.getPage() * filter.getPageSize());
			q.setMaxResults(filter.getPageSize());
		}
		if(filter.getPage() == null && filter.getPageSize() != null){
			q.setMaxResults(filter.getPageSize());
		}
		if(filter.getOrderBy() != null){
			q.setString("orderBy", filter.getOrderBy());
		}
		q.setProperties(filter.getProperties());
		
		return q.list();
	}
	
	public T getOne(long id){
		return (T)getCurrentSession().get(clazz, id);
	}
	
	public List<T> getAll(){
		return getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
	}
	
	public T create(T object){
		getCurrentSession().save(object);
		return object;
	}
	
	public T save(T object){
		return (T) getCurrentSession().merge(object);
	}
	
	public void saveList(List<T> objects){
		for(T object:objects){
			this.save(object);
		}
	}
	
	public void delete(T object){
		getCurrentSession().delete(object);
	}
	
	public void deleteById(long id){
		T object = (T) getCurrentSession().get(clazz, id);
		getCurrentSession().delete(object);
	}
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Autowired
	private SessionFactory sessionFactory;	
	public SessionFactory getSessionFactory() {		
		return sessionFactory;	
	}	
	public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;	
	}
}
