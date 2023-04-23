package textfiles;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class BufferedWriterEx {
	public static void main(String[] args) {
		String filename = "BufferedWriter.txt", names[] = { "Robert", "Mike", "Tom" };
		boolean append = false;

		try {
			/* Try with an invalid filename */
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename, append));
			for (String name : names) {
				bufferedWriter.write(name);
				bufferedWriter.newLine();
			}
			bufferedWriter.flush(); // Forces flushing of buffer
			bufferedWriter.close(); // Also flushes the buffer so previous flush unnecessary
									
			System.out.println("Results can be found at: " + filename);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
