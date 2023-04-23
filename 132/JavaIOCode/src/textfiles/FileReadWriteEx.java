package textfiles;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileReadWriteEx {
	public static void main(String args[]) throws IOException {
		File inputFile = new File(args[0]), outputFile = new File(args[1]);
		Scanner fileScanner = null;
		PrintWriter printWriter = null;

		try {
			/* Open the scanner */
			fileScanner = new Scanner(inputFile);

			/* Opening file we will write to */
			printWriter = new PrintWriter(outputFile);
			while (fileScanner.hasNextLine()) {
				String studentName = fileScanner.next();
				int id = fileScanner.nextInt();
				printWriter.print("Student's Name: " + studentName);
				printWriter.println(", Id: " + id);
			}
			System.out.println("Results can be found in: " + outputFile);
		} finally {
			/* Ensure we also close both */
			closeIgnoringExceptions(printWriter);
			if (fileScanner != null) {
				fileScanner.close();
			}
		}
	}

	public static void closeIgnoringExceptions(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
			assert true; // Ignore
		}
	}
}
