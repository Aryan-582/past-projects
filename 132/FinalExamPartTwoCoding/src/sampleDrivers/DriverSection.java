package sampleDrivers;

import java.util.*;

import implementation.Section;

public class DriverSection {

	public static void main(String[] args) {
		int sectionNumber = 50;
		Section section = new Section(sectionNumber);

		section.add("Rachel");
		section.add("Maurice");
		System.out.println(section);
		System.out.println("SectionNumber: " + section.getSectionNumber());
		System.out.println("Enrollment: " + section.getEnrollment());

		Set<String> students = section.getStudents(true);
		System.out.println("Students: " + students);

		Section sectionTwo = new Section(100);
		System.out.println("Comparison: " + (section.compareTo(sectionTwo) < 0));
	}

}
