package tests;

import implementation.Building;

public class SampleDriverBuilding {

	public static void main(String[] args) {
		String address = "Infinite Loop Blvd";
		int maxFloors = 2, roomsPerFloor = 4, roomCapacity = 6;

		Building buildingOne = new Building(address, maxFloors, roomsPerFloor, roomCapacity);

		String occupantName = "Johnny";
		buildingOne.addOccupant(occupantName, 0, 0);
		buildingOne.addOccupant("Aastha", 0, 0);

		buildingOne.addOccupant("Liv", 1, 1);
		buildingOne.addOccupant("Olivia", 1, 2);

		System.out.println(buildingOne);
	}
}
