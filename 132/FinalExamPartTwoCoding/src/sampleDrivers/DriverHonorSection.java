package sampleDrivers;

import java.util.*;

import implementation.HonorSection;
import implementation.Section;

public class DriverHonorSection {

	public static void main(String[] args) {
		int sectionNumber = 50;
		String taName = "Helen";
		HonorSection honorSection = new HonorSection(sectionNumber, taName);

		honorSection.add("Rachel");
		honorSection.add("Maurice");
		System.out.println(honorSection);
		System.out.println("SectionNumber: " + honorSection.getSectionNumber());
		System.out.println("Enrollment: " + honorSection.getEnrollment());
		System.out.println("TAName: " + honorSection.getTAName());

		Set<String> students = honorSection.getStudents(true);
		System.out.println("Students: " + students);

		Section sectionTwo = new Section(100);
		System.out.println("Comparison: " + (honorSection.compareTo(sectionTwo) < 0));
	}

}
