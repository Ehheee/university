package university.dataobjects;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Student", namespace = "Student")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "Student")
public class Student {

	private Long id;
	private int version;
	private String name;
	@XmlTransient
	private Set<StudentCourse> studentCourses;
	private Double weightedAverage;
	
	public Student(){
		studentCourses = new LinkedHashSet<StudentCourse>();
	}
	
	@Version
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	public Set<StudentCourse> getStudentCourses() {
		return studentCourses;
	}

	public void setStudentCourses(Set<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}
	
	
	public void addStudentCourse(StudentCourse studentCourse){
		this.studentCourses.add(studentCourse);
	}
	
	

	public Double getWeightedAverage() {
		return weightedAverage;
	}
	public void setWeightedAverage(Double weightedAverage) {
		this.weightedAverage = weightedAverage;
	}
	public String toString(){
		return "Student" + "{id: " + id + "name: " + name + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((studentCourses == null) ? 0 : studentCourses.hashCode());
		result = prime * result + version;
		result = prime * result
				+ ((weightedAverage == null) ? 0 : weightedAverage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (studentCourses == null) {
			if (other.studentCourses != null)
				return false;
		} else if (!studentCourses.equals(other.studentCourses))
			return false;
		if (version != other.version)
			return false;
		if (weightedAverage == null) {
			if (other.weightedAverage != null)
				return false;
		} else if (!weightedAverage.equals(other.weightedAverage))
			return false;
		return true;
	}
	
	
	
}
