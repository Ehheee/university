package university.dataobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UniversityUser")
/**
 * Class to represent simple login user.
 * Currently unfinished, should have the possibility to link to
 * Student so when a user doesn't have admin privilege,
 * user would only be able to view data related to him/herself
 * @author Kaur
 *
 */
public class UniversityUser {

	private Long id;
	private String userName;
	private String password;
	private String privilege;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	
}
