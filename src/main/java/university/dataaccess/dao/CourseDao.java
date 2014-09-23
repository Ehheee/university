package university.dataaccess.dao;


import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import university.dataobjects.Course;
@Repository
@Service
@Transactional
public class CourseDao extends AbstractHibernateDao<Course> implements
		HibernateDao<Course> {
	
	public CourseDao(){
		super.setClazz(Course.class);
	}

}
