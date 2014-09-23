package university.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import university.dataaccess.utils.DataFilter;
import university.dataobjects.Course;
import university.dataobjects.Student;
import university.dataobjects.StudentCourse;

/**
 * Getting data is put into 'generic' controller methods as possibly there could be more types of data so this would potentially help in keeping the number of methods down.
 * Saving data is in different methods as then it is possible to have Spring fill new objects of specific types.
 * @author Kaur
 *
 */
@Controller
@RequestMapping("/")
public class MainController extends BaseController{

	
	@RequestMapping(method = RequestMethod.GET)
	public String startPage(){
		return "main";
	}
	
	/**
	 * Request that only has a string in request path after /university/
	 * requests for objects from single datatype.
	 * If objects become more complex then it is possible to change
	 * jsp files so that request parameters would be sent with get request
	 * and they would be converted to a filter and hibernateDao would then 
	 * only return filtered data. The filter could also be used for sorting
	 * in database/hibernate level.
	 * @param dataType
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{s:[a-zA-Z]+}", method = RequestMethod.GET)
	public String getByType(@PathVariable("s") String dataType, HttpServletRequest request, Model model){
		DataFilter filter = this.filterFromRequest(dataType, request);
		this.dataToModel(filter, model, dataType);
		this.formToModel(dataType, model);
		model.addAttribute("dataType", dataType);
		return "main";
	}
	
	
	@RequestMapping(value = "/{s:[a-zA-Z]+}/{id:[0-9]+}", method = RequestMethod.GET)
	public String getByTypeAndId(@PathVariable("s") String dataType, @PathVariable("id") Long id, HttpServletRequest request, Model model){
		model.addAttribute("dataType", dataType);
		model.addAttribute("studentCourseForm", new StudentCourse());
		this.dataToModel(dataType, id, model);
		return "main";
	}
	
		
	
	@RequestMapping(value = "/new/course", method = RequestMethod.GET)
	public String newCourse(Model model){
		model.addAttribute(new Course());
		return "main";
	}
	
	@RequestMapping(value = "/new/student", method = RequestMethod.GET)
	public String newStudent(Model model){
		model.addAttribute(new Student());
		return "main";
	}
	
	
	/**
	 * Saves course.
	 * Redirects to newly saved course
	 * @param course
	 * @return
	 */
	@RequestMapping(value = "/new/course", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("courseForm") Course course){
		course = this.courseDao.save(course);
		return "redirect:/course/" + course.getId();
	}
	
	/**
	 * Saves student
	 * Redirects to newly saved student
	 * @param student
	 * @return
	 */
	@RequestMapping(value = "/new/student", method = RequestMethod.POST)
	public String saveStudent(@ModelAttribute("studentForm") Student student){
		student = this.studentDao.save(student);
		return "redirect:/student/" + student.getId();
	}
	
	/**
	 * Saves  studentCourse.
	 * Redirects back to the page where studentCourse was saved from
	 * @param studentCourse
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/new/studentcourse", method = RequestMethod.POST)
	public String saveStudentCourse(@ModelAttribute("studentCourseForm") StudentCourse studentCourse, HttpServletRequest request){
		logger.info(studentCourse.getCourse());
		logger.info(studentCourse.getStudent());
		
		
		studentCourseDao.save(studentCourse);
		String redirectTo = request.getHeader("Referer");
		return "redirect:" + redirectTo; 
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String getUploadPage(Model model){
		model.addAttribute("jspContent", "upload.jsp");
		
		 
		return "main";
	}
	
	/**
	 * Method to receive xml file uploads.
	 * Datatype of uploaded xml is set in html form dropdown.
	 * Otherwise there should be multiple different upload pages for different file types
	 * and this could be harder to maintain
	 * @param request
	 * @param dataType
	 * @return redirect to the datatype that xml contained
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String postUpload(MultipartHttpServletRequest request, @RequestParam("dataType") String dataType){
		MultipartFile file = request.getFile("xml");
		logger.info(dataType);
		if("student".equals(dataType)){
			List<Student> studentList= xmlService.<Student>listFromMultipartFile(file).getRecords();
			this.studentDao.saveList(studentList);
		}
		if("course".equals(dataType)){
			List<Course> courseList = xmlService.<Course>listFromMultipartFile(file).getRecords();
			this.courseDao.saveList(courseList);
		}
		if("studentCourse".equals(dataType)){
			List<StudentCourse> studentCourseList= xmlService.<StudentCourse>listFromMultipartFile(file).getRecords();
			this.studentCourseDao.saveList(studentCourseList);
		}
		
		return "redirect:/" + dataType;
	}
	
}
