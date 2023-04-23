package priorityQueueEx;

import java.util.PriorityQueue;
import java.util.Iterator;

public class Basics {
	public static void main(String[] args) {
		PriorityQueue<String> nameQueue = new PriorityQueue<String>();

		/* Adding some elements */
		nameQueue.offer("Mary");
		nameQueue.offer("Linda");
		nameQueue.offer("John");
		nameQueue.offer("Albert");
		nameQueue.offer("Kelly");
		nameQueue.offer("Bob");

		/* Iterating over the elements. IMPORTANT: iterator does */
		/* not return the elements in any particular order */
		System.out.println("**** Iterating over the elements...");
		Iterator<String> iterator = nameQueue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

		System.out.println("**** Iterating after removing Bob...");
		nameQueue.remove("Bob");
		for (String name : nameQueue) {
			System.out.println(name);
		}

		/* Retrieving the elements */
		System.out.println("**** Retrieving the elements...");
		String name = nameQueue.poll();
		while (name != null) {
			System.out.println(name);
			name = nameQueue.poll();
		}
	}
}
