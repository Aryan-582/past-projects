package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import implementation.Course;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
    @Test
    public void test() {
    	Course courseOne = new Course();
		String taName;
		int sectionNumber;

		System.out.println("======== Marker #1 ========");
		/* Adding regular section */
		sectionNumber = 50;
		taName = null;
		courseOne.addSection(sectionNumber, "Beter");

		/* Adding honor section */
		sectionNumber = 10;
		taName = "Peter";
		courseOne.addSection(sectionNumber, taName);
		System.out.println(courseOne);

		System.out.println("======== Marker #2 ========");
		/* Adding students */
		sectionNumber = 10;
		courseOne.addStudent(sectionNumber, "Marcos");
		courseOne.addStudent(sectionNumber, "Rose");
		courseOne.addStudent(sectionNumber, "Tom");
		
		sectionNumber = 50;
		courseOne.addStudent(sectionNumber, "Kelly");
		System.out.println(courseOne);
		sectionNumber = 20;
		courseOne.addSection(sectionNumber, "bruh");

		System.out.println("======== Marker #3 ========");
		sectionNumber = 0; // Section with lowest enrolmment
		courseOne.addStudent(sectionNumber, "Sabrina");
		System.out.println(courseOne);

		System.out.println("======== Marker #4 ========");
		System.out.println("Enrollment Per Section");
		TreeMap<Integer, Integer> enrollmentPerSection = new TreeMap<>();
		courseOne.getEnrollmentPerSection(enrollmentPerSection);
		System.out.println(enrollmentPerSection + "\n");

		System.out.println("======== Marker #5 ========");
		System.out.print("TAs honor section ");
		ArrayList<String> list = courseOne.getTAsHonorsSections();
		System.out.println(list + "\n");

		System.out.println("======== Marker #6 ========");
		sectionNumber = 10;
		System.out.println("Removing section " + sectionNumber);
		courseOne.removeSection(sectionNumber);
		System.out.println(courseOne);
    }
}