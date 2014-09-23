package university.dataaccess.utils;

import java.util.Comparator;

import university.dataobjects.Student;
/**
 * Class to be used for ordering student collections.
 * Orders by average and with equal averages by name
 * @author Kaur
 *
 */
public class StudentComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		int averageCompare = Double.compare(o1.getWeightedAverage(), o2.getWeightedAverage());
		return averageCompare != 0 ? averageCompare: o1.getName().compareTo(o2.getName());
	}

}
