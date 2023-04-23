package implementation;

import java.util.*;



public class Course {
	protected class Node {
		public Section section;
		public Node next;

		protected Node(Section section) {
			this.section = section;
			next = null;
		}
	}

	protected Node head;

	public Course() {
		head = null;
	}

	public Course addSection(int sectionNumber, String taName) {
		if (taName == null) {
			if (head == null) {
				Section section = new Section(sectionNumber);
				Node sectionAdd = new Node(section);
				head = sectionAdd;
			} else {
				Section section = new Section(sectionNumber);
				Node sectionAdd = new Node(section);
				Node current = head;
				while (current.next != null) {
					current = current.next;
				}
				current.next = sectionAdd;
			}
		} else {
			if (head == null) {
				Section section = new HonorSection(sectionNumber, taName);
				Node sectionAdd = new Node(section);
				head = sectionAdd;
			} else {
				Section section = new HonorSection(sectionNumber, taName);
				Node sectionAdd = new Node(section);
				Node current = head;
				while (current.next != null) {
					current = current.next;
				}
				current.next = sectionAdd;
			}
		}
		return this;
	}

	public Course addStudent(int sectionNumber, String student) {
		if (sectionNumber == 0) {
			Section found = head.section;
			Node current = head;
			while (current != null) {
				if (current.section.getEnrollment() < found.getEnrollment()) {
					found = current.section;
				}
				current = current.next;
			}
			found.add(student);
		} else {
			Section found = null;
			Node current = head;
			while (current.next != null) {
				if (current.section.getSectionNumber() == sectionNumber) {
					found = current.section;
				}
				if (current.next.section.getSectionNumber() == sectionNumber) {
					found = current.next.section;
				}
				current = current.next;
			}
			if (found == null) {
				throw new IllegalArgumentException("Section not found");
			} else {
				found.add(student);
			}
		}
		return this;
	}

	public int getEnrollmentPerSection(TreeMap<Integer, Integer> answer) {
		answer.clear();
		Node current = head;
		int totalStudents = 0;
		while (current != null) {
			answer.put(current.section.getSectionNumber(), current.section.getStudents(false).size());
			totalStudents += current.section.getStudents(false).size();
			current = current.next;
		}	
		return totalStudents;
	}

	public ArrayList<String> getTAsHonorsSections() {
		ArrayList<String> list = new ArrayList<String>();
		Node current = head;
		while (current != null) {
			if (current.section instanceof HonorSection) {
				HonorSection honor = (HonorSection) current.section;
				list.add(honor.getTAName());
			}
			current = current.next;
		}
		return list;
	}

	public Course removeSection(int sectionNumber) {
		Node current = head;
		Node prevNode = null;
		while (current != null) {
			if (current.section.getSectionNumber() != sectionNumber) {
				prevNode = current;
				current = current.next;
			} else {
				if (current.next == null) {
					prevNode.next = null;
					current = current.next;
				} else if (current == head) {
					for (String student :head.section.getStudents(false)) {
						head.next.section.add(student);
					}
					head = head.next;
					current = head;
				} else {
					for (String student: current.section.getStudents(false)) {
						current.next.section.add(student);
					}
					prevNode.next = current.next;
					current = current.next;
				}
			}
		}
		return this;
		
	}

	/* Provided: do not modify */
	public String toString() {
		return toString(head);
	}

	/* Provided: do not modify */
	private String toString(Node headAux) {
		if (headAux != null) {
			return headAux.section + "\n" + toString(headAux.next);
		}
		return "";
	}
}
