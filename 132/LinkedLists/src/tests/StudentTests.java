package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import listClasses.BasicLinkedList;
import listClasses.SortedLinkedList;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {
	//tests constructor, addtofront, addtoend, retrieveLastElement, retrieveFirstElement
	// getSize, getFirst, and getLast
	@Test
	public void test() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		String expected = "YellowPurpleBlueYellow";
		String answer = "";
		basicList.addToEnd("Red").addToFront("Blue").addToFront("Yellow");
		answer += basicList.getFirst();
		basicList.addToFront("Purple");
		answer += basicList.getFirst();
		basicList.retrieveLastElement();
		answer += basicList.getLast();
		basicList.retrieveFirstElement();
		answer += basicList.getFirst();
		int size = basicList.getSize();
		assertTrue(answer.equals(expected) && size == 2);
	}
	//tests remove and iterator
	@Test
	public void test2() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		basicList.addToFront("Red").addToEnd("Yellow").addToEnd("Blue");
		String expected = "RedYellowBlueRedBlue";
		String answer = "";
		for (String objects: basicList) {
			answer += objects;
		}
		basicList.remove("Yellow", String.CASE_INSENSITIVE_ORDER);
		for (String objects: basicList) {
			answer += objects;
		}
		assertTrue(answer.equals(expected));
	}
	//tests get getReversedList
	@Test
	public void test3() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		String expected = "YellowRed";
		String answer = "";
		basicList.addToEnd("Red").addToEnd("Blue").addToEnd("Yellow");
		BasicLinkedList<String> reversedList = basicList.getReverseList();
		answer +=reversedList.getFirst();
		answer +=reversedList.getLast();
		assertTrue(expected.equals(answer));
		
	}
	//tests getReverseArrayList
	@Test
	public void test4() {
		BasicLinkedList<String> basicList = new BasicLinkedList<String>();
		String expected = "YellowRed";
		String answer = "";
		basicList.addToEnd("Red").addToEnd("Blue").addToEnd("Yellow");
		ArrayList<String> reversedList = basicList.getReverseArrayList();
		answer += reversedList.get(0);
		answer += reversedList.get(2);
		assertTrue(expected.equals(answer));
		
	}
	//ignore this test lol
	@Test
	public void test5() {
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		sortedList.add("Yellow").add("Red").add("Blue");
		sortedList.remove("Yellow");
		System.out.println(sortedList);
	}
	//tests sortedlinkedlist constructor, add, and remove, also tests iterator
	@Test
	public void test6() {
		SortedLinkedList<String> sortedList = new SortedLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
		String expected = "02359239";
		String answer = "";
		sortedList.add("5").add("3").add("9").add("0").add("2");
		for (String objects: sortedList)  {
			answer += objects;
		}
		sortedList.remove("5").remove("0");
		for (String objects: sortedList)  {
			answer += objects;
		}
		assertTrue(answer.equals(expected));
	}

}
