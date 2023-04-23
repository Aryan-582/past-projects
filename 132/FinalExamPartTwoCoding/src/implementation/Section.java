package implementation;

import java.util.*;

public class Section implements Comparable<Section>, Cloneable {
	private int sectionNumber;
	private HashSet<String> students;

	public Section(int sectionNumber) {
		this.sectionNumber = sectionNumber;
		this.students = new HashSet<String>();
	}

	public Section add(String newStudent) {
		students.add(newStudent);
		return this;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public int getEnrollment() {
		return students.size();
	}

	public Set<String> getStudents(boolean sorted) {
		if (sorted == true) {
			Set<String> sort = new TreeSet<String>();
			for (String name: students) {
				sort.add(name);
			}
			return sort;
		} else {
			Set<String> hash = new HashSet<String>();
			for (String name: students) {
				hash.add(name);
			}
			return hash;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Section) {
			if (((Section) obj).getSectionNumber() == this.sectionNumber) {
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		return (this.sectionNumber * 2) + 7;
	}

	public int compareTo(Section section) {
		if (this.getSectionNumber() < section.getSectionNumber()) {
			return -1;
		} else if (this.getSectionNumber() > section.getSectionNumber()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public Section clone() {
		Section cloned = new Section(this.getSectionNumber());
		for (String student: students) {
			cloned.add(student);
		}
		return cloned;
		
	}

	/* Provided: do not modify */
	public String toString() {
		return "Section Number: " + sectionNumber + "-" + getStudents(true);
	}
}
