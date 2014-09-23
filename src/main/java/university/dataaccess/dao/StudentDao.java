package university.dataaccess.dao;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import university.dataobjects.Student;
import university.dataobjects.StudentCourse;
@Repository
@Transactional
public class StudentDao extends AbstractHibernateDao<Student> implements HibernateDao<Student> {

	public StudentDao(){
		super.setClazz(Student.class);
	}
	public Student save(Student student){
		student.setWeightedAverage(calculateAverage(student.getStudentCourses()));
		return super.save(student);
	}

	
	private Double calculateAverage(Set<StudentCourse> courses){
		int totalWeight = 0;
		int totalWithWeights = 0;
		for(StudentCourse studentCourse: courses){
			totalWeight = totalWeight + studentCourse.getWeight();
			totalWithWeights = totalWithWeights + studentCourse.getWeight() * studentCourse.getGrade();
		}
		if(totalWeight == 0){
			return 0d;
		}
		return   ((double)totalWithWeights) / totalWeight;
	}
	
}
