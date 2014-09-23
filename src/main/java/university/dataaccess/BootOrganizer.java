package university.dataaccess;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import university.dataaccess.dao.UserDao;
import university.dataobjects.UniversityUser;

public class BootOrganizer {

	public void secureDatabase(){
		List<UniversityUser> users = userDao.getAll();
		for(UniversityUser user: users){
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userDao.save(user);
		}
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Autowired
	private UserDao userDao;
	
}
