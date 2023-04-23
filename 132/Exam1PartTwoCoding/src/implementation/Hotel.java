package implementation;

import java.util.ArrayList;
import java.util.Collections;

public class Hotel extends Building {
	private String hotelName, pentHouseMembers;
	private Room pentHouseRoom;
	private boolean hasPool;

	public Hotel(String hotelName, String address, int maxFloors, int roomsPerFloor, int roomCapacity,
			String pentHouseMembers, int pentHouseCapacity, boolean hasPool) {
		super(address, maxFloors, roomsPerFloor, roomCapacity);
		this.hotelName = hotelName;
		this.pentHouseMembers = pentHouseMembers;
		this.hasPool = hasPool;
		this.pentHouseRoom = new Room(pentHouseCapacity);
	}

	@Override
	public boolean addOccupant(String name, int floorIndex, int roomNumberIndex) {
		if (name == null || name.isBlank()) {
			return false;
		} else {
			if (pentHouseMembers.contains(name)) {
				if (pentHouseRoom.getCapacity() > pentHouseRoom.getNumberOfOccupants()) {
					pentHouseRoom.addOccupant(name);
					return true;
				} else {
					return false;
				}
			} else {
				if (floorIndex < 0 || roomNumberIndex < 0 || floorIndex >= maxFloors || 
				roomNumberIndex >= roomsPerFloor || 
				getRoom(floorIndex, roomNumberIndex).getNumberOfOccupants() == this.roomCapacity) {
					return false;
				} else {
					getRoom(floorIndex, roomNumberIndex).addOccupant(name);
					return true;
				}
				 
			}
		
		}
	}

	public ArrayList<String> getOccupants(boolean pentHouse, int floorIndex, int roomIndex, boolean sorted) {
		if (pentHouse == true) {
			if (sorted == true) {
				ArrayList <String> copy = pentHouseRoom.getOccupants();
				Collections.sort(copy);
				return copy;
			} else {
				ArrayList <String> copy = pentHouseRoom.getOccupants();
				return copy;
			}
			
		} else {
			if (floorIndex < 0 || roomIndex < 0 || floorIndex >= maxFloors || 
				roomIndex >= roomsPerFloor) {
				return new ArrayList<String>();
			} else {
				if (sorted == true) {
					ArrayList<String> copy = getRoom(floorIndex, roomIndex).getOccupants();
					Collections.sort(copy);
					return copy;
				} else {
					ArrayList<String> copy = getRoom(floorIndex, roomIndex).getOccupants();
					return copy;
				}
			}
		}
	}

	public boolean hotelHasPool() {
		return this.hasPool;
	}

	/* Does not include penthouse; if two have the same value the lower level one */
	public int[] getFloorMaxOccupants() {
		int[] maxOccupants = new int[2];
		int maxPeople = 0;
		int biggestRow = 0;
		for (int rows = 0; rows < maxFloors; rows++) {
			int count = 0;
			for (int cols = 0; cols < roomsPerFloor; cols++ ) {
				count += getOccupants(false, rows, cols, true).size();
			}
			if (count > maxPeople) {
				maxPeople = count;
				biggestRow = rows;
			}
		}
		if (maxPeople == 0) {
			maxOccupants[0] = 0;
			maxOccupants[1] = 0;
		} else {
			maxOccupants[0] = biggestRow;
			maxOccupants[1] = maxPeople;
		}
		return maxOccupants;
		
	}

	public static int totalNumberHotelsWithPool(ArrayList<Building> buildings) {
		int totalNumber = 0;
		for (int i = 0; i < buildings.size(); i++) {
			if (buildings.get(i) instanceof Hotel) {
				Hotel copy = (Hotel) buildings.get(i);
				if (copy.hotelHasPool() == true) {
					totalNumber++;
				}
			}
		}
		return totalNumber;
	}

	/* Provided: do not modify */
	@Override
	public String toString() {
		String answer = "Hotel: " + hotelName + "\n";

		answer += "PentHouseMembers: " + pentHouseMembers + "\n";
		answer += "PentHouseRoom: " + pentHouseRoom + "\n";
		answer += "Pool: " + (hasPool ? "Yes" : "No") + "\n";
		answer += super.toString();

		return answer;
	}
}
