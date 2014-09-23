package university.dataaccess.utils;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import university.dataobjects.UniversityUser;
@Entity
@Table(name = "SecurityUser")
public class SecurityUser extends User {

	
	private UniversityUser universityUser;
	
	
	public SecurityUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}


	public UniversityUser getUniversityUser() {
		return universityUser;
	}
	public void setUniversityUser(UniversityUser universityUser) {
		this.universityUser = universityUser;
	}

	

	

}
