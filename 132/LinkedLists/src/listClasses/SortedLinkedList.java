package listClasses;

import java.util.*;


/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 *  @author Dept of Computer Science, UMCP
 *  
 */

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;

	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	public SortedLinkedList<T> add(T element) {
		if (head == null) {
			head = new Node(element);
			tail = head;
			listSize++;
			return this;
		} else {
			Node current = head;
			Node prevNode = head;
			Node add = new Node(element);
			while (current != null) {
				if (current == head) {
					if (comparator.compare(current.data, element) > 0 || 
							comparator.compare(current.data, element) == 0) {
						head = add;
						add.next = prevNode;
						current = current.next;
						listSize++;
						return this;
					} else {
						current = current.next;
					}
				} else {
					if (comparator.compare(current.data, element) > 0|| 
							comparator.compare(current.data, element) == 0) {
						prevNode.next = add;
						add.next = current;
						current = current.next;
						listSize++;
						return this;
					} else  {
						current = current.next;
					}
				}
				
			}
			tail.next = add;
			tail = add;
			listSize++;
			return this;
			
		}
	}
	
	public SortedLinkedList<T> remove(T targetData) {
		this.remove(targetData, comparator);
		return this;
	}
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException("Invalid operation for sorted list.");
	}
}
	
	