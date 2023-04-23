package objectStreams;

import javax.swing.*;
import java.io.*;

public class Driver {
	public static void main(String[] args) throws Exception {
		Phonebook phonebook = initPhonebook();
		int choice;

		do {
			String menu = "Enter 1 to look up a phone\n";
			menu += "Enter 2 to add/update a phone\n";
			menu += "Enter 3 to display phone book\n";
			menu += "Enter 4 to clear phone book\n";
			menu += "Enter 5 to quit";

			choice = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
			String name, number;

			switch (choice) {
			case 1:
				name = JOptionPane.showInputDialog(null, "Enter name");
				JOptionPane.showMessageDialog(null, phonebook.get(name));
				break;
			case 2:
				name = JOptionPane.showInputDialog(null, "Enter name");
				number = JOptionPane.showInputDialog(null, "Enter number");
				phonebook.addUpdate(name, number);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, phonebook);
				break;
			case 4:
				phonebook.clear();
				break;
			case 5:
				break;
			default:
				JOptionPane.showMessageDialog(null, "Invalid choice.");
			}
		} while (choice != 5);

		savePhonebook(phonebook);
	}

	private static Phonebook initPhonebook() throws Exception {
		File file = new File("phonebookFile.info");

		if (!file.exists()) {
			return new Phonebook();
		} else {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
			Phonebook phonebook = (Phonebook) input.readObject();
			input.close();

			return phonebook;
		}
	}

	private static void savePhonebook(Phonebook phonebook) throws Exception {
		File file = new File("phonebookFile.info");
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));

		output.writeObject(phonebook);
		output.close();
	}
}
