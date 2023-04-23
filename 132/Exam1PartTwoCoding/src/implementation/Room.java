package implementation;

import java.util.ArrayList;
import java.util.Collections;

public class Room implements Comparable<Room> {
	private int capacity;
	private ArrayList<String> occupants;

	public Room(int capacity) {
		this.capacity = capacity;
		this.occupants = new ArrayList<String>();
	}

	public boolean addOccupant(String name) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Invalid Parameter");
		} else {
			for (int i = 0; i < this.occupants.size(); i++) {
				if (occupants.get(i).equals(name)) {
					return false;
				}
			}
			if (this.capacity == this.occupants.size()) {
				return false;
			} else {
				this.occupants.add(name);
				return true;
			}
		}
	}

	public int getCapacity() {
		return this.capacity;
	}

	public Room(Room room) {
		this.capacity = room.capacity;
		this.occupants = room.occupants;
	}

	public ArrayList<String> getOccupants() {
		ArrayList<String> copy = new ArrayList<String>();
		for (int i = 0; i < this.occupants.size(); i++) {
			copy.add(this.occupants.get(i));
		}
		return copy;
	}

	public int getNumberOfOccupants() {
		return this.occupants.size();
	}

	public int compareTo(Room room) {
		if (this.occupants.size() < room.occupants.size()) {
			return -1;
		} else if (this.occupants.size() > room.occupants.size()) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Room) {
			if (((Room) obj).capacity == this.capacity) {
				if (((Room) obj).occupants.size() == this.occupants.size()) {
					int sameName = 0; 
					ArrayList<String> temp = ((Room) obj).occupants;
					ArrayList<String> temp2 = this.occupants; 
					Collections.sort(temp);
					Collections.sort(temp2);
					for (int i = 0; i < this.occupants.size(); i++) {
						if (temp.get(i).equals(temp2.get(i))) {
							sameName++;
						}
					}
					if (sameName == this.occupants.size()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
					

	/* Provided: Do not modify */
	@Override
	public String toString() {
		String answer = "Capacity: " + capacity + ", ";

		return answer += "Occupants: " + occupants;
	}
}
