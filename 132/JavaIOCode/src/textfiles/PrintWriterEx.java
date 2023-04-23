package textfiles;

/* 
 * Reads line of text where the first column has a string representing a name
 * and the second an integer (e.g., Peter 10).  We use Scanner to read the name 
 * of the file from standard input.  The file reader is wrapped with a buffered 
 * reader for efficiency.  We use a Scanner class to read each field of a record.  
 * File in the project we can use for this example is printWriterInput.txt.
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PrintWriterEx {
	public static void main(String[] args) {

		/* Reading the file names from standard input */
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input file name: ");
		String inputFileName = scanner.nextLine();

		System.out.println("Output file name: ");
		String outputFileName = scanner.nextLine();

		try {

			/* Opening the input file */
			FileReader fileReader = new FileReader(inputFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			Scanner fileScanner = new Scanner(bufferedReader);

			/* Opening file we will write to */
			FileWriter fileWriter = new FileWriter(outputFileName);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			PrintWriter printWriter = new PrintWriter(bufferedWriter);

			while (fileScanner.hasNextLine()) {
				String studentName = fileScanner.next();
				int id = fileScanner.nextInt();

				printWriter.print("Student's Name: " + studentName);
				printWriter.println(", Id: " + id);
			}

			/* Closing resources */
			fileScanner.close();
			printWriter.close();

			System.out.println("Results can be found in: " + outputFileName);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		scanner.close();
	}
}
