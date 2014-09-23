package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import university.dataaccess.XmlService;
import university.dataaccess.dao.HibernateDao;
import university.dataaccess.dao.StudentDao;
import university.dataaccess.utils.DataFilter;
import university.dataaccess.utils.XmlListWrapper;
import university.dataobjects.*;
import university.web.MainController;
public class A1 {

	public static void main(String[] args){
		/*
		StudentDao studentDao = new StudentDao();
		HibernateDao dao = studentDao;
		MainController cont = new MainController();
		DataFilter filter = cont.createFilter("Course");
		//DataFilter filter = new DataFilter(StudentCourse.class);
		filter.setProperty("id", 1L);
		filter.setProperty("name", "Kaur");
		filter.setOrderBy("name");
		filter.setOrderHow("ASC");
		System.out.println(filter.getQuery());
		*/
		
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
		
		
		
		
		
		try{	
			Jaxb2Marshaller marshaller2 = new Jaxb2Marshaller();
			marshaller2.setClassesToBeBound(StudentCourse.class, Course.class, XmlListWrapper.class, Student.class);
			XmlService service = new XmlService();
			
			service.setMarshaller(marshaller2);
			//service.listToFile(courses, "courses.xml");
			service.listToFile(students, "students.xml");
			//service.listToFile(scs, "studentCourses.xml");
			XmlListWrapper<Course> parsedCourseList = service.<Course>listFromFile( "courses.xml");
			for(Course c: parsedCourseList.getRecords()){
				System.out.println(c);
			}
			XmlListWrapper<Student> parsedStudentList = service.<Student>listFromFile( "students.xml");
			for(Student s: parsedStudentList.getRecords()){
				System.out.println(s);
			}
		
			
			
			//Unmarshaller unmarshaller = jc.createUnmarshaller();
			//Object o = unmarshaller.unmarshal(testfile2);
			//System.out.println(o.getClass());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
