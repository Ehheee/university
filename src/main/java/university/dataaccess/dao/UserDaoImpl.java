package university.dataaccess.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import university.dataobjects.UniversityUser;

@Transactional
public class UserDaoImpl extends AbstractHibernateDao<UniversityUser> implements UserDao {

	public UserDaoImpl(){
		super.setClazz(UniversityUser.class);
	}
	public UniversityUser getByUserName(String username){
		return (UniversityUser) getCurrentSession().createQuery("from UniversityUser where username = :username").setString("username", username).list().get(0);
	}
}
