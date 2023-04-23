package priorityQueueEx;

public class Patient implements Comparable<Patient> {
	private String name;
	private int priority;

	public Patient(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String toString() {
		return "Name: " + name + " Priority: " + priority;
	}

	public int compareTo(Patient p) {
		return equals(p) ? 0 : (priority < p.priority ? -1 : 1);
	}

	public boolean equals(Object obj) {
		if (obj != null && getClass() == obj.getClass()) {
			return name.equals(((Patient) obj).name);
		}

		return false;
	}

	public int hashCode() {
		return name.charAt(0);
	}
}
