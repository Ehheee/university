package university.dataaccess.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import university.dataobjects.UniversityUser;
@Service

public interface UserDao extends HibernateDao<UniversityUser>{

	public UniversityUser getByUserName(String username);
}
