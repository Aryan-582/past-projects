package implementation;

import java.util.*;

public class Facility implements Iterable<String> {
	protected String name;

	protected class StorageUnit {
		public String unitId;
		public ArrayList<String> items;
		public StorageUnit next;
		public StorageUnit thread_next;

		public StorageUnit(String unitId) {
			this.unitId = unitId;
			this.items = new ArrayList<String>();
			next = null;
		}

		public void addItem(String singleItem) {
			items.add(singleItem);
		}
	}

	protected StorageUnit head;
	protected int totalUnits;
	protected StorageUnit thread_head;

	public Facility(String name) {
		if (Support.isValid(name) == true) {
			this.name = name;
			head = null;
			totalUnits = 0;
		}
	}

	public Facility() {
		this.name = "NONAME";
		head = null;
		totalUnits = 0;
	}

	public Facility addItemToStorageUnit(String unitId, String item) {
		if (Support.isValid(item) && Support.isValid(unitId)) {
			if (head == null) {
				head = new StorageUnit(unitId);
				head.addItem(item);
				totalUnits++;
				return this;
			} else {
				StorageUnit curr = head;
				while (curr != null) {
					if (curr.unitId.equals(unitId)) {
						curr.addItem(item);
						return this;
					} else {
						if (curr.next == null) {
							curr.next = new StorageUnit(unitId);
							curr.next.addItem(item);
							totalUnits++;
							return this;
						} else {
							curr = curr.next;
						}
					}
				}
				return this;
			}
		} else {
			return this;
		}
	}

	public int getTotalUnits() {
		return totalUnits;
	}

	public String getName() {
		return name;
	}

	/*
	 * You will lose credit if you use addItemToStorageUnit during the
	 * implementation of this method.
	 */
	public Facility getFacilityWithFirstUnit(String facilityName) {
		Facility temp = new Facility(facilityName);
		temp.head = this.head;
		temp.totalUnits = 1;
		temp.head.next = null;
		return temp;
	}

	/*
	 * Method must be implemented using recursion. You will lose credit if you use
	 * any iteration statement (e.g., for, while, do while).
	 */
	public int getTotalItemsInFacility() {
		return totalItemsAux(head, 0);
	}

	private int totalItemsAux(StorageUnit head, int items) {
		if (head == null) {
			return items;
		} else if (head.next == null) {
			return head.items.size() + items;
		} else {
			items += head.items.size();
			return totalItemsAux(head.next, items);
		}
	}

	/*
	 * Method must be implemented using recursion. You will lose credit if you use
	 * any iteration statement (e.g., for, while, do while).
	 */
	public ArrayList<String> getItemsInUnitSorted(String unitId) {
		if (Support.isValid(unitId) == true) {
			ArrayList<String> sorted = itemsSortedAux(head, unitId);
			Collections.sort(sorted);
			return sorted;
		} else {
			return null;
		}
	}

	private ArrayList<String> itemsSortedAux(StorageUnit head, String unitId) {
		ArrayList<String> sorted = new ArrayList<>();
		if (head.unitId == unitId) {
			sorted.addAll(head.items);
			return sorted;
		} else if (head.next == null) {
			return sorted;
		} else {
			return itemsSortedAux(head.next, unitId);
		}
	}

	public void setThread(int minimumItemsInUnit) {
		thread_head = null;
		threadAux(head, thread_head, minimumItemsInUnit);
	}

	private void threadAux(StorageUnit head, StorageUnit threadHead, int minimumItemsInUnit) {
		if (head.next == null) {
			if (thread_head == null) {
				if ((head.items.size() >= minimumItemsInUnit)) {
					thread_head = head;
					threadHead = head;
					threadHead.thread_next = null;
				}
			} else {
				if ((head.items.size() >= minimumItemsInUnit)) {
					threadHead.thread_next = head;
				}
			}
		} else {
			if (thread_head == null) {
				if ((head.items.size() >= minimumItemsInUnit)) {
					thread_head = head;
					threadHead = head;
					threadAux(head.next, threadHead, minimumItemsInUnit);
				} else {
					threadAux(head.next, threadHead, minimumItemsInUnit);
				}
			} else {
				if (head.items.size() >= minimumItemsInUnit) {
					threadHead.thread_next = head;
					threadAux(head.next, threadHead, minimumItemsInUnit);
				} else {
					threadAux(head.next, threadHead, minimumItemsInUnit);
				}
			}
		}
	}

	public Iterator<String> iterator() {
		return new Iterator<String>() {
			StorageUnit curr = head;
			int unitsAccessed = 1;

			@Override
			public boolean hasNext() {
				if (curr == null || curr.next == null || unitsAccessed > 3) {
					return false;
				} else {
					return true;
				}
			}

			@Override
			public String next() {
				if (hasNext() == true) {
					String next = curr.unitId;
					curr = curr.next;
					unitsAccessed++;
					return next;
				} else {
					return null;
				}

			}

		};
	}

	/* Provided: Do not modify */
	public String toStringThreadedNodes() {
		String answer = "";

		if (thread_head == null) {
			answer = "NOT THREADED";
		} else {
			StorageUnit curr = thread_head;
			while (curr != null) {
				answer += "UnitId:" + curr.unitId + ", Items: " + curr.items + "\n";

				curr = curr.thread_next;
			}
		}

		return answer;
	}

	/* Provided: Do not modify */
	public String toString() {
		String answer = "Facility name: " + name + "\n";
		answer += "Total Units: " + totalUnits + "\n";
		StorageUnit curr = head;

		while (curr != null) {
			answer += "UnitId:" + curr.unitId + ", Items: " + curr.items + "\n";

			curr = curr.next;
		}

		return answer;
	}
}
