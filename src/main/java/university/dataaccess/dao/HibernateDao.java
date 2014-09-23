package university.dataaccess.dao;

import java.util.List;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import university.dataaccess.utils.DataFilter;


@Service
@Transactional
public  interface HibernateDao<T> {

	public List<T> getByFilter(DataFilter<T> filter);
	public T getOne(long id);
	public List<T> getAll();
	public T create(T object);
	public T save(T object);
	public void saveList(List<T> objects);
	public void delete(T object);
	public void deleteById(long id);
	
}
