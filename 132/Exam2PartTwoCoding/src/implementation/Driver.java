package implementation;

import java.util.*;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("======== Marker #1 ========");
		/* Checking isValid */
		System.out.println("Valid?: " + Support.isValid("UMCP") + "\n");

		/* Creating a Facility */
		System.out.println("======== Marker #2 ========");
		Facility facility = new Facility("CollegePark");
		System.out.println(facility);

		/* Adding items to different units */
		System.out.println("======== Marker #3 ========");
		String storageId = "StorageUnitOne", item = "BiologyBook";
		facility.addItemToStorageUnit(storageId, item);
		facility.addItemToStorageUnit(storageId, "PHPBook");
		facility.addItemToStorageUnit(storageId, "JavaScriptBook");

		storageId = "StorageTwo";
		facility.addItemToStorageUnit(storageId, "Mac");

		storageId = "AnotherUnit";
		facility.addItemToStorageUnit(storageId, "Star Wars DVD");
		facility.addItemToStorageUnit(storageId, "Hallmark DVD");

		/* Displaying the Facility */
		System.out.println(facility);

		System.out.println("======== Marker #4 ========");
		System.out.println("Name: " + facility.getName());
		System.out.println("TotalUnits: " + facility.getTotalUnits());
		System.out.println("TotalItemsInFacility: " + facility.getTotalItemsInFacility() + "\n");

		System.out.println("======== Marker #5 ========");
		ArrayList<String> items = facility.getItemsInUnitSorted("StorageUnitOne");
		System.out.println("Items Sorted: " + items + "\n");

		System.out.println("======== Marker #6 ========");
		int minimumItemsInUnit = 2;
		facility.setThread(minimumItemsInUnit);
		System.out.println("Thread Output");
		System.out.println(facility.toStringThreadedNodes());

		System.out.println("======== Marker #7 ========");
		Iterator<String> iterator = facility.iterator();
		System.out.println("Iterator output:");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println();

		System.out.println("======== Marker #8 ========");
		Facility facilityTwo = new Facility();
		System.out.println(facilityTwo);
		System.out.println("---------------------------");
		facilityTwo.addItemToStorageUnit("UnitOne", "PoolTable");
		System.out.println(facilityTwo);

		System.out.println("======== Marker #9 ========");
		boolean increasing = true;
		FacilityComparator comparator = new FacilityComparator(increasing);
		System.out.print("Less than zero: ");
		System.out.println(comparator.compare(facilityTwo, facility) < 0);
		System.out.println();

		System.out.println("======== Marker #10 ========");
		String facilityName = "FacilityWithFirstUnit";
		Facility facilityWithFirstUnit = facility.getFacilityWithFirstUnit(facilityName);
		System.out.println(facilityWithFirstUnit);
	}
}
