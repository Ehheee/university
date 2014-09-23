package university.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import university.dataaccess.XmlService;
import university.dataaccess.utils.XmlListWrapper;
import university.dataobjects.Course;
import university.dataobjects.Student;
import university.dataobjects.StudentCourse;


public class Test1 {

	@Test
	public void testXmlMapping(){
		Course course = new Course();
		course.setName("Ajalugu");
		course.setId(1L);
		
		Course course2 = new Course();
		course2.setName("Matemaatika");
		course2.setId(2L);
		List<Course> courses = new ArrayList<Course>();
		courses.add(course);
		courses.add(course2);
		
		Student student1 = new Student();
		student1.setId(1L);
		student1.setName("Kaur Kase");
		
		
		Student student2 = new Student();
		student2.setId(2L);
		student2.setName("Juhan Juurikas");
		List<Student> students = new ArrayList<Student>();
		students.add(student1);
		students.add(student2);
		
		StudentCourse sc1 = new StudentCourse();
		sc1.setStatus("completed");
		sc1.setGrade(5);
		sc1.setWeight(3);
		sc1.setCourse(course);
		sc1.setStudent(student1);
		
		StudentCourse sc2 = new StudentCourse();
		sc2.setStatus("completed");
		sc2.setGrade(5);
		sc2.setWeight(3);
		sc2.setCourse(course);
		sc2.setStudent(student2);
		
		StudentCourse sc3 = new StudentCourse();
		sc3.setStatus("completed");
		sc3.setGrade(5);
		sc3.setWeight(6);
		sc3.setCourse(course2);
		sc3.setStudent(student1);
		
		StudentCourse sc4 = new StudentCourse();
		sc4.setStatus("completed");
		sc4.setGrade(5);
		sc4.setWeight(6);
		sc4.setCourse(course2);
		sc4.setStudent(student2);
		
		
		List<StudentCourse> scs = new ArrayList<StudentCourse>();
		scs.add(sc2);
		scs.add(sc1);
		scs.add(sc4);
		scs.add(sc3);
		
		Jaxb2Marshaller marshaller2 = new Jaxb2Marshaller();
		marshaller2.setClassesToBeBound(StudentCourse.class, Course.class, XmlListWrapper.class, Student.class);
		XmlService service = new XmlService();
		
		service.setMarshaller(marshaller2);
		service.listToFile(courses, "courses.xml");
		service.listToFile(students, "students.xml");
		service.listToFile(scs, "studentCourses.xml");
		
		assertTrue(courses.containsAll(service.<Course>listFromFile("courses.xml").getRecords()));
		assertTrue(students.containsAll(service.<Student>listFromFile("students.xml").getRecords()));
		assertTrue(scs.containsAll(service.<StudentCourse>listFromFile("studentCourses.xml").getRecords()));
	}
}
