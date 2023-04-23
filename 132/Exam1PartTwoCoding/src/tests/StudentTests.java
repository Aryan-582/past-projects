package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import implementation.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
	@Test
	public void test() {
		StringBuffer results = new StringBuffer();
		String hotelName = "TerpHotel", address = "CollegePark";
		int maxFloors = 2, roomsPerFloor = 2, roomCapacity = 4;
		int pentHouseCapacity = 1;
		String pentHouseMembers = "SmithCory";
		boolean hasPool = true;

		Hotel hotelOne = new Hotel(hotelName, address, maxFloors, roomsPerFloor, roomCapacity, pentHouseMembers,
				pentHouseCapacity, hasPool);

		hotelOne.addOccupant("Swati", 8, 8);
		hotelOne.addOccupant("Anastasia", 0, 1);
		hotelOne.addOccupant("Olivia", 0, 1);
		hotelOne.addOccupant("olivia", 0, 1);
		hotelOne.addOccupant("Knastasia", 0, 1);
		hotelOne.addOccupant("Klivia", 0, 1);

		hotelOne.addOccupant("Liv", 1, 1);
		hotelOne.addOccupant("Phoebe", 1, 1);
		hotelOne.addOccupant("Cory", 1, 1);
		hotelOne.addOccupant("Smith", 1, 1);

		results.append(hotelOne + "\n");
		boolean pentHouse = true;
		ArrayList<String> occupants = hotelOne.getOccupants(pentHouse, 0, 1, true);
		results.append(occupants);

		System.out.println(results);
	}

	@Test
	public void test2() {

		String hotelName = "TerpHotel", address = "CollegePark";
		int maxFloors = 4, roomsPerFloor = 3, roomCapacity = 4;
		int pentHouseCapacity = 10;
		String pentHouseMembers = "Smith";
		boolean hasPool = true;

		Hotel hotelOne = new Hotel(hotelName, address, maxFloors, roomsPerFloor, roomCapacity, pentHouseMembers,
				pentHouseCapacity, hasPool);

		hotelOne.addOccupant("A", 1, 0);
		hotelOne.addOccupant("B", 1, 1);
		hotelOne.addOccupant("C", 1, 2);

		hotelOne.addOccupant("D", 0, 1);
		hotelOne.addOccupant("E", 0, 1);

		System.out.println(hotelOne);

		int[] maxResults = hotelOne.getFloorMaxOccupants();
		System.out.println("getFloorMaxOccupantsResults[0]: " + maxResults[0]);
		System.out.println("getFloorMaxOccupantsResults[1]: " + maxResults[1]);

	}

}