package implementation;

import java.util.Comparator;

public class FacilityComparator implements Comparator<Facility> {
	private boolean increasing;
	
	public FacilityComparator(boolean increasing) {
		this.increasing = increasing;
	}

	public int compare(Facility facility1, Facility facility2) {
		if (increasing == true) {
			if (facility1.getTotalUnits() < facility2.getTotalUnits()) {
				return -1;
			} else if (facility1.getTotalUnits() > facility2.getTotalItemsInFacility()) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (facility1.getTotalUnits() < facility2.getTotalUnits()) {
				return 1;
			} else if (facility1.getTotalUnits() > facility2.getTotalUnits())  {
				return -1;
			} else {
				return 0;
			}
		}
	}
}
