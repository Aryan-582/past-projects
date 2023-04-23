package tests;

import implementation.Room;

public class SampleDriverRoom {

	public static void main(String[] args) {
		StringBuffer results = new StringBuffer();
		int capacity = 10;
		
		Room roomOne = new Room(capacity);
		roomOne.addOccupant("Diana");
		roomOne.addOccupant("Alejandro");
		roomOne.addOccupant("Sierra");
		results.append(roomOne).append("\n");
		results.append("Capacity: " + roomOne.getCapacity() + "\n");
		results.append("Occupants: " + roomOne.getOccupants() + "\n");
		results.append("Number of Occupants: " + roomOne.getNumberOfOccupants());
		
		System.out.println(results);
	}
}

