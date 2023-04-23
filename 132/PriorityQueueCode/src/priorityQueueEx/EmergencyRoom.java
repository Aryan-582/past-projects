package priorityQueueEx;

import java.util.PriorityQueue;

public class EmergencyRoom {
	private PriorityQueue<Patient> queue = new PriorityQueue<Patient>();

	public void addPatient(Patient patient) {
		queue.offer(patient);
	}

	public Patient nextPatient() {
		return queue.poll();
	}

	/* To update priority we need to remove and then add again */
	public void updatePriority(String patientsName, int newPriority) {
		// To remove we create a patient with patientsName and any priority
		queue.remove(new Patient(patientsName, 0));

		// Reinserting patient with updated priority
		Patient patient = new Patient(patientsName, newPriority);
		queue.offer(patient);
	}

	public String toString() {
		String answer = "";

		for (Patient patient : queue) {
			answer += patient + "\n";
		}

		return answer;
	}

	public static void main(String[] args) {
		String[] names = { "John", "Mary", "Peter", "Kelly", "Mike" };
		int[] priorities = { 10, 2, 4, 8, 11 };
		EmergencyRoom emergencyRoom = new EmergencyRoom();

		for (int i = 0; i < names.length; i++) {
			emergencyRoom.addPatient(new Patient(names[i], priorities[i]));
		}

		System.out.println("**** Patients Waiting:\n" + emergencyRoom);

		System.out.println("**** After updating Peter's priority");
		int newPriority = 3;
		emergencyRoom.updatePriority("Peter", newPriority);
		System.out.println("**** Patients Waiting:\n" + emergencyRoom);

		System.out.println("Next Patient:\n" + emergencyRoom.nextPatient());
	}
}
