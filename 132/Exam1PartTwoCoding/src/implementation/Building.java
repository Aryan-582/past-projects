package implementation;

public class Building {
	protected Room[][] allFloors;
	protected int maxFloors, roomsPerFloor, roomCapacity;
	private final String address;

	public Building(String address, int maxFloors, int roomsPerFloor, int roomCapacity) {
		this.maxFloors = maxFloors;
		this.roomsPerFloor = roomsPerFloor;
		this.roomCapacity = roomCapacity;
		this.address = address;
		this.allFloors = new Room[maxFloors][roomsPerFloor];
		for (int rows = 0; rows < maxFloors; rows++) {
			for (int cols = 0; cols < roomsPerFloor; cols++) {
				allFloors[rows][cols] = new Room(roomCapacity);
			}
		}
	}

	public Building() {
		this("NOADDRESS", 1, 1, 1);
	}

	protected Room getRoom(int floorIndex, int roomNumberIndex) {
		if (floorIndex < 0 || roomNumberIndex < 0 || floorIndex >= maxFloors || roomNumberIndex >= roomsPerFloor) {
			throw new IllegalArgumentException("Invalid Parameters");
		} else {
			return allFloors[floorIndex][roomNumberIndex];
		}
	}

	public boolean addOccupant(String name, int floorIndex, int roomNumberIndex) {
		if (floorIndex < 0 || roomNumberIndex < 0 || floorIndex >= maxFloors || 
				roomNumberIndex >= roomsPerFloor || name == null || name.isBlank() || 
				getRoom(floorIndex, roomNumberIndex).getNumberOfOccupants() == this.roomCapacity) {
			return false;
		} else {
			getRoom(floorIndex, roomNumberIndex).addOccupant(name);
			return true;
		}
			
	}

	/* Provided: Do not modify */
	@Override
	public String toString() {
		String answer = "Address: " + address + "\n";

		answer += "MaxFloors: " + maxFloors + ", ";
		answer += "RoomsPerFloor: " + roomsPerFloor + ", ";
		answer += "RoomCapacity:" + roomCapacity + "\n";

		for (int floorIndex = 0; floorIndex < maxFloors; floorIndex++) {
			answer += "\n****** Floor: " + floorIndex + " ******\n";
			for (int roomIndex = 0; roomIndex < roomsPerFloor; roomIndex++) {
				answer += "Room: " + roomIndex + ", ";
				answer += allFloors[floorIndex][roomIndex] + "\n";
			}
		}

		return answer;
	}
}
