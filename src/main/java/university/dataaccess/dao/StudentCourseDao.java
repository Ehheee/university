package university.dataaccess.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import university.dataobjects.Student;
import university.dataobjects.StudentCourse;
@Repository
@Transactional
public class StudentCourseDao extends AbstractHibernateDao<StudentCourse> implements
		HibernateDao<StudentCourse> {

	public StudentCourseDao(){
		super.setClazz(StudentCourse.class);
	}
	public StudentCourse save(StudentCourse object){
		Student student = studentDao.getOne(object.getStudent().getId());
		object = super.save(object);
		student.addStudentCourse(object);
		studentDao.save(student);
		return object;
	}
	public void saveList(List<StudentCourse> objects){
		for(StudentCourse studentCourse: objects){
			Student student = studentDao.getOne(studentCourse.getStudent().getId());
			student.addStudentCourse(studentCourse);
			studentDao.save(student);
		}
	}
	
	@Autowired
	private HibernateDao<Student> studentDao;
}
