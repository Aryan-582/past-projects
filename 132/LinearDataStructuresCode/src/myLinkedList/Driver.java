package myLinkedList;

public class Driver {
	public static void main(String[] args) {
		MyLinkedList<String> friendsList = new MyLinkedList<String>();

		friendsList.add("Rose").add("Sarah").add("Peter").add("Albert").add("Kelly");
		System.out.println("Initial list: " + friendsList.getListString());
		System.out.println("Reversed list order: " + friendsList.getListStringReversed());

		MyLinkedList<String> inBetween = friendsList.getListWithDataInBetween("Kelly", "Rose");
		System.out.println("InBetween: " + inBetween);

		/* Would the following work if Car did not implement Comparable? */
		MyLinkedList<Car> carList = new MyLinkedList<Car>();
		carList.add(new Car(134789, "Jack"));
		carList.add(new Car(100200, "Linda"));
		System.out.println("Car list: " + carList);

		/* Variable declared so we can show we don't need the import */
		java.util.ArrayList<Car> cars = carList.getArrayList();
		System.out.println("Cars: ");
		for (Car car : cars) {
			System.out.println(car);
		}
	}
}
