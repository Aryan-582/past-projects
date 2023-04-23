package textfiles;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class ScannerEx {
	public static void main(String[] args) throws IOException {
		String filename = "scanner.txt";
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Scanner fileScanner = new Scanner(bufferedReader);

		while (fileScanner.hasNextLine()) {
			String studentName = fileScanner.next();
			int id = fileScanner.nextInt();

			System.out.print("Student's Name: " + studentName);
			System.out.println(", Id: " + id);
		}

		fileScanner.close();
	}
}
