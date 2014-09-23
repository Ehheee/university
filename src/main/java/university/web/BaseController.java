package university.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ui.Model;

import university.dataaccess.XmlService;
import university.dataaccess.dao.CourseDao;
import university.dataaccess.dao.HibernateDao;
import university.dataaccess.dao.StudentCourseDao;
import university.dataaccess.dao.StudentDao;
import university.dataaccess.utils.DataFilter;
import university.dataaccess.utils.StudentComparator;
import university.dataobjects.Course;
import university.dataobjects.Student;
import university.dataobjects.StudentCourse;
/**
 * Abstract basecontroller that does most of the processing so that 
 * the real controller containing requestmappings would be simpler, shorter
 * and more focused on mappings than the processing of data.
 * @author Kaur
 *
 */
public abstract class BaseController {

	protected Log logger = LogFactory.getLog(getClass());
	public BaseController(){
		daos = new HashMap<String, HibernateDao<?>>();
	}
	
	/**
	 * Creates type specific filter.
	 * @param dataType identifies the type for which to create filter
	 * @return
	 */
	public DataFilter<?> createFilter(String dataType){
		if("student".equals(dataType)){
			return new DataFilter<Student>(Student.class);
		}
		if("course".equals(dataType)){
			return new DataFilter<Course>(Course.class);
		}
		return null;
	}
	/**
	 * Creates filter from HttpServletRequest.
	 * RequestParameters specify the filter
	 * @param dataType
	 * @param request
	 * @return
	 */
	protected DataFilter<?> filterFromRequest(String dataType, HttpServletRequest request){
		DataFilter<?> filter = createFilter(dataType);
		filter.setOrderBy(request.getParameter("orderBy"));
		String page = request.getParameter("page");
		if(page != null){
			filter.setPage(Integer.valueOf(page));
		}
		
		Map<String, String[]> params = request.getParameterMap();
		for(Entry<String, String[]> param: params.entrySet()){
			if(param.getValue().length > 1){
				filter.setProperty(param.getKey(), param.getValue());
			}else{
				filter.setProperty(param.getKey(), param.getValue()[0]);
			}
		}
		return filter;
	}
	
	/**
	 * Uses filter and dataType to query data from appropriate hibernateDao
	 * Adds it to the model
	 * @param filter
	 * @param model
	 * @param dataType
	 */
	protected void dataToModel(DataFilter filter, Model model, String dataType){
		logger.info(dataType);
		logger.info(daos.get(dataType));
		if("student".equals(dataType)){
			List<Student> students= studentDao.getByFilter(filter);
			Collections.sort(students, new StudentComparator());
			model.addAttribute("students", students);
		}else{
			model.addAttribute(dataType + "s", daos.get(dataType).getByFilter(filter));
		}
		
	}
	
	/**
	 * Adds a single object to the model
	 * @param dataType
	 * @param id
	 * @param model
	 */
	protected void dataToModel(String dataType, Long id,  Model model ){
		model.addAttribute(dataType, daos.get(dataType).getOne(id));
		
		
	}
	/**
	 * Adds a suitable empty object for spring form backing
	 * @param dataType
	 * @param model
	 */
	protected void formToModel(String dataType, Model model){
		if("course".equals(dataType)){
			model.addAttribute("courseForm", new Course());
		}
		if("student".equals(dataType)){
			model.addAttribute("studentForm", new Student());
		}
	}
	
	
	
	protected Map<String, HibernateDao<?>> daos;
	
	@PostConstruct
	public void init(){
		logger.info("postconstructer started");
		daos.put("course", this.courseDao);
		daos.put("student", this.studentDao);
		daos.put("studentCourse", this.studentCourseDao);
	}
	
	@Autowired
	protected HibernateDao<Course> courseDao;
	
	
	@Autowired
	protected HibernateDao<Student> studentDao;
	
	@Autowired 
	protected HibernateDao<StudentCourse> studentCourseDao;

	@Autowired
	protected XmlService xmlService;
	
}
