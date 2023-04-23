package textfiles;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterEx {
	public static void main(String[] args) {
		String filename = "fileWriter.txt";
		boolean append = false;
		String[] names = { "John", "Mary", "Heather" };

		try {
			/* Try with an invalid filename */
			FileWriter fileWriter = new FileWriter(filename, append);

			for (String name : names) {
				fileWriter.write(name + "\n");
			}
			fileWriter.close();
			System.out.println("Results can be found at: " + filename);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
