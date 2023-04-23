package myLinkedList;

import java.util.ArrayList;

public class MyLinkedList<T extends Comparable<T>> { /* Notice the parameter */
	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this.data = data;
			next = null; /* Do we really need this assignment? */
		}
	}

	/* List head pointer */
	private Node head;

	public MyLinkedList() {
		head = null;
	}

	/* Adding at the front of the list */
	public MyLinkedList<T> add(T data) {
		Node newNode = new Node(data);
		newNode.next = head;
		head = newNode;

		return this;
	}

	public String toString() {
		String result = "\" ";
		Node curr = head;

		while (curr != null) {
			result += curr.data + " ";

			curr = curr.next;
		}

		return result + "\"";
	}

	public boolean find(T target) {
		Node curr = head;

		while (curr != null) {
			if (curr.data.compareTo(target) == 0) {
				return true;
			}

			curr = curr.next;
		}

		return false;
	}

	public T getLastElement() {
		if (head == null) {
			return null;
		}

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}

		return curr.data;
	}

	/* No insertion will take place if list empty or targetElement not found */
	public void insertElementAfter(T targetElement, T toInsert) {
		Node curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) {
				Node newNode = new Node(toInsert);
				newNode.next = curr.next;
				curr.next = newNode;
				return; /* What would happen if we don't return? */
			}

			curr = curr.next;
		}
	}

	/* No insertion will take place if list empty or targetElement not found */
	public void insertElementBefore(T targetElement, T toInsert) {
		Node prev = null, curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) {
				Node newNode = new Node(toInsert);
				if (curr == head) {
					newNode.next = head;
					head = newNode;
				} else {
					prev.next = newNode;
					newNode.next = curr;
				}
				return;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}

	public void delete(T targetElement) {
		Node prev = null, curr = head;

		while (curr != null) {
			if (curr.data.compareTo(targetElement) == 0) {
				if (curr == head) {
					head = head.next;
				} else {
					prev.next = curr.next;
				}
				return;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
	}

	/* This method illustrates that you can define a list and update
	 * its instance variables before returning this list.
	 */
	public MyLinkedList<T> getListWithDataInBetween(T start, T end) {
		MyLinkedList<T> newList = new MyLinkedList<T>();

		if (head != null) {
			Node curr = head, last = null;

			while (curr != null) {
				if (curr.data.compareTo(start) >= 0 && curr.data.compareTo(end) <= 0) {
					Node newNode = new Node(curr.data);
					if (newList.head == null) {
						newList.head = newNode;
					} else {
						last.next = newNode;
					}
					last = newNode;
				}
				curr = curr.next;
			}
		}

		return newList;
	}

	/* Recursive methods */

	/* Returning a string with the list data */
	public String getListString() {
		return getListString(head);
	}

	/*
	 * Auxiliary method for previous method that satisfies the recursion requirement
	 */
	private String getListString(Node headAux) {
		if (headAux == null) {
			return "";
		}
		return headAux.data + " " + getListString(headAux.next);
	}

	/* Returns elements of the list in reversed order */
	public String getListStringReversed() {
		return getListStringReversed(head);
	}

	private String getListStringReversed(Node headAux) {
		/* The ternary operator can be useful when implementing linked list code */
		return headAux == null ? "" : getListStringReversed(headAux.next) + " " + headAux.data;
	}

	/* Returns ArrayList with data elements */
	public ArrayList<T> getArrayList() {
		ArrayList<T> answer = new ArrayList<>();

		getArrayList(head, answer);
		return answer;
	}

	/* Auxiliary now has more than one parameter */
	private void getArrayList(Node headAux, ArrayList<T> answer) {
		if (headAux != null) {
			answer.add(headAux.data);
			getArrayList(headAux.next, answer);
		}
	}
}
