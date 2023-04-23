package textfiles;

import java.io.*;
import java.util.Scanner;

public class ScannerEx2 {
	public static void main(String[] args) throws IOException {
		String filename = "scanner.txt";

		Scanner fileScanner = new Scanner(new File(filename));
		
		while (fileScanner.hasNextLine()) {
			String studentName = fileScanner.next();
			int id = fileScanner.nextInt();

			System.out.print("Student's Name: " + studentName);
			System.out.println(", Id: " + id);
		}

		fileScanner.close();
	}
}
