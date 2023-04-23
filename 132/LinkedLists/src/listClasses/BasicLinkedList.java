package listClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BasicLinkedList<T> implements Iterable<T> {
	
	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;
	
	/* size */
	protected int listSize;
	
	
	public BasicLinkedList( ) {
		head = null;
		tail = null;
		listSize = 0;
 	}
	
	public int getSize() {
		return listSize;
	}
	
	public BasicLinkedList<T> addToEnd (T data) {
		if (head == null) {
			Node newNode = new Node(data);
			head = newNode;
			tail = head;
			listSize++;
			return this;
		} else {
			Node newNode = new Node(data);
			Node current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = newNode;
			tail = newNode;
			listSize++;
			return this;
		}
	}
	
	public BasicLinkedList<T> addToFront (T data) {
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
			listSize++;
			return this;
		} else {
			newNode.next = head;
			head = newNode;
			listSize++;
			return this;
		}
	}
	
	public T getFirst() {
		if (head != null) {
			return head.data;
		} else {
			return null;
		}
	}
	
	public T getLast() {
		if (tail != null) {
			return tail.data;
		} else {
			return null;
		}
	}
	
	public T retrieveFirstElement() {
		if (head == null) {
			return null;
		} else if (head == tail) {
			Node start = head;
			head = null;
			tail = null;
			listSize--;
			return start.data;
		} else {
			Node start = head;
			Node current = head.next;
			head = current;
			listSize--;
			return start.data;
		}
	}
	
	public T retrieveLastElement() {
		if (head == null) {
			return null;
		} else {
			Node current = head;
			Node copy = head;
			if (head.next == null) {
				head = null;
				tail = null;
				listSize--;
				return current.data;
			} else {
				while (current.next != null) {
					copy = current;
					current = current.next;
				}
				tail = copy;
				tail.next = null;
				listSize--;
				return current.data;
			}
		}
	}
	
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		if (this.listSize == 0) {
			return this;
		} else {
			Node current = head;
			Node prevNode = null;
			while (current.next != null) {
				if (comparator.compare(targetData, current.data) != 0) {
					prevNode = current;
					current = current.next;
				} else {
					if (current == tail) {
						current = null;
						tail = prevNode;
						prevNode.next = null;
						listSize--;
					} else if (current == head) {
						head = head.next;
						current = head;
						listSize--;
					} else {
						prevNode.next = current.next;
						current = current.next;
						listSize--;
					}
				}
			}
			return this;
		}
	}
	
	
	public ArrayList<T> getReverseArrayList() {
		BasicLinkedList<T> reversed = this.getReverseList();
		ArrayList<T> reversedArray = new ArrayList<T>();
		Node current = reversed.head;
		return reversedArrayAux(current, reversed, reversedArray);
		
	}
	
	public ArrayList<T> reversedArrayAux(Node current, BasicLinkedList<T> reversed, ArrayList<T> reversedArray) {
		if (current == null) {
			return reversedArray;
		} else {
			reversedArray.add(current.data);
			return reversedArrayAux(current.next, reversed, reversedArray);
		}
	}
	
	public BasicLinkedList<T> getReverseList() {
		if (head == null && tail == null) {
			return new BasicLinkedList<T>();
		} else {
			BasicLinkedList<T> reversed = new BasicLinkedList<T>();
			Node current = head;
			return reverseListAux(current, reversed);
		}
	}
	
	private BasicLinkedList<T> reverseListAux(Node current, BasicLinkedList<T> reversed) {
		if (current == null) {
			return reversed;
		} else if (head == tail) {
			reversed.addToFront(head.data);
			return reversed;
		} else {
			reversed.addToFront(current.data);
			return reverseListAux(current.next, reversed);
		}
	}
	
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			
			Node current = head;
			@Override
			public boolean hasNext() {
				if (current != null) {
					return true;
				} else {
					return false;
				}
			}

			@Override
			public T next() {
				if (hasNext() == false) {
					return null;
				} else {
					T currentData = current.data;
					current = current.next;
					return currentData;
				}
			}
			
		};
	}
	
}

