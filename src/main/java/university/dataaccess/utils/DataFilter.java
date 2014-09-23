package university.dataaccess.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Currently possibly overkill but when application grows larger and more complex
 * then it would make it easier to filter requests and generate appropriate queries
 * based on request parameters
 * @author Kaur
 *
 * @param <T>
 */
public  class DataFilter<T> {

	private Integer page;
	private Integer pageSize;
	private String orderBy;
	private String orderHow;
	private Map<String, Object> properties;
	private Class<T> clazz;
	private String fromQuery;
	
	public DataFilter(Class<T> clazzToSet){
		this.clazz = clazzToSet;
		fromQuery = "FROM " + clazz.getSimpleName();
		this.properties = new HashMap<String, Object>();
	}
	
	public String getQuery(){
		String query = addWhereQuery(fromQuery);
		query = orderQuery(query);
		return query;
	}
	
	private String addWhereQuery(String query){
		StringBuilder sb = new StringBuilder(query);
		String and = " WHERE ";
		for(Entry<String, Object> entry: properties.entrySet()){
			sb.append(and).append(entry.getKey()).append(" = :").append(entry.getKey());
			and = " AND ";
		}
		return sb.toString();
	}
	
	private String orderQuery(String query){
		if(orderBy != null){
			query = query + " order by :orderBy ";
		}if(orderHow != null){
			query = query + orderHow;
		}
		return query;
	}
	
	public void setProperty(String key, Object value){
		this.properties.put(key, value);
	}

	
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderHow() {
		return orderHow;
	}
	public void setOrderHow(String orderHow) {
		this.orderHow = orderHow;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	
	
}
