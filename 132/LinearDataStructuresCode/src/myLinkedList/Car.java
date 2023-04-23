package myLinkedList;

public class Car implements Comparable<Car> {
	private final int tag;
	private final String owner;

	public Car(int tag, String owner) {
		this.tag = tag;
		this.owner = owner;
	}

	public int compareTo(Car car) {
		return tag - car.tag;
	}

	@Override
	public String toString() {
		return "Car [tag=" + tag + ", owner=" + owner + "]";
	}
}
