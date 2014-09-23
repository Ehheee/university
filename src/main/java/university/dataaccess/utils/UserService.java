package university.dataaccess.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import university.dataaccess.dao.UserDao;
import university.dataaccess.dao.UserDaoImpl;
import university.dataobjects.UniversityUser;

/**
 * Very simple class as this is required by spring security.
 * Later there would be a possibility to add new users and
 * then an additional method to crypt users password would be added
 * @author Kaur
 *
 */
public class UserService implements UserDetailsService {

	private Log logger = LogFactory.getLog(getClass());
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		logger.info(userName);
		UniversityUser user = userDao.getByUserName(userName);
		logger.info(user);
		if(user != null){
			List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(user.getPrivilege()));
			SecurityUser securityUser = new SecurityUser(user.getUserName(), user.getPassword(), roles);
			securityUser.setUniversityUser(user);
			return securityUser;
		}else{
			throw new UsernameNotFoundException("No user with username '" + userName + "' found!");
		}
	}
	
	@Autowired
	private UserDao userDao;
	
	
}
