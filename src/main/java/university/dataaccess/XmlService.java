package university.dataaccess;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import university.dataaccess.utils.XmlListWrapper;

@Component
public class XmlService {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	public XmlService(){
		
	}

	@SuppressWarnings("unchecked")
	public <T> XmlListWrapper<T>  listFromFile(String fileName){
		XmlListWrapper<T> resultList;
		File f = new File(fileName);
		Source source = new StreamSource(f);
		resultList = (XmlListWrapper<T>)marshaller.unmarshal(source);
		
		return resultList;
		
	}
	
	/**
	 * Takes an uploaded MultipartFile and creates a List of type T
	 * If file is not xml or doesn't contain objects of type T
	 * then unmarshal should throw error and a null List is returned
	 * @param file
	 * @return
	 */
	public <T> XmlListWrapper<T> listFromMultipartFile(MultipartFile file){
		XmlListWrapper<T> resultList = null;
		InputStream stream = null;
		try{
			stream = file.getInputStream();
			Source source = new StreamSource(stream);
			resultList = (XmlListWrapper<T>) marshaller.unmarshal(source);
		}catch(Exception e){
			logger.error("Error reading xml file from form", e);
		}finally{
			if(stream != null){
				IOUtils.closeQuietly(stream);
			}
		}
		
		return resultList;
	}
	
	/**
	 * Currently unused but could be quite easily plugged in to provide possibility
	 * of saving a list defined by a filter into an xml file for sending data to some other
	 * student management system
	 * @param list
	 * @param fileName
	 */
	public <T> void listToFile(List<T> list, String fileName){
		
		File f = new File(fileName);
		XmlListWrapper<T> listWrapper = new XmlListWrapper<T>(list);
		Result result = new StreamResult(f);
		marshaller.marshal(listWrapper, result);

	}
	
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	public Jaxb2Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Jaxb2Marshaller marshaller) {
		this.marshaller = marshaller;
	}
	
}
