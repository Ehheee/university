package university.dataaccess.utils;

import java.util.*;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import university.dataobjects.Course;
import university.dataobjects.StudentCourse;
/**
 * According to my research(which means I could be wrong)
 * JAXB needs a wrapper class for mapping lists nicely to/from xml.
 * This is the required class
 * @author Kaur
 *
 * @param <T>
 */
@XmlRootElement(name="records")
public class XmlListWrapper<T> {


    protected List<T> records;

    public XmlListWrapper(){
    	records = new ArrayList<T>();
    }

    public XmlListWrapper(List<T> list){
        this.records=list;
    }

    @XmlAnyElement(lax=true) 
    public List<T> getRecords(){
        return records;
    }

}