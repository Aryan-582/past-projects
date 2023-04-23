package tests;

import java.util.ArrayList;

import implementation.Hotel;

public class SampleDriverHotel {

	public static void main(String[] args) {
		StringBuffer results = new StringBuffer();

		String hotelName = "TerpHotel", address = "CollegePark";
		int maxFloors = 2, roomsPerFloor = 2, roomCapacity = 4;
		int pentHouseCapacity = 10;
		String pentHouseMembers = "Smith";
		boolean hasPool = true;

		Hotel hotelOne = new Hotel(hotelName, address, maxFloors, roomsPerFloor, roomCapacity, pentHouseMembers,
				pentHouseCapacity, hasPool);

		hotelOne.addOccupant("Swati", 0, 1);
		hotelOne.addOccupant("Anastasia", 0, 1);
		hotelOne.addOccupant("Olivia", 0, 1);

		hotelOne.addOccupant("Liv", 1, 1);
		hotelOne.addOccupant("Phoebe", 1, 1);

		results.append(hotelOne + "\n");
		boolean pentHouse = false;
		ArrayList<String> occupants = hotelOne.getOccupants(pentHouse, 0, 1, true);
		results.append(occupants);

		System.out.println(results);
	}
}
