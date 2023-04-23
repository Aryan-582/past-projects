package textfiles;

import java.io.FileReader;
import java.io.*;

public class FileReaderEx {
	public static void main(String[] args) {
		FileReader fileReader = null;

		try {
			/* Try with an invalid filename */
			fileReader = new FileReader("fileReader2.txt");
			int characterValue;
			while ((characterValue = fileReader.read()) != -1) {
				System.out.print((char) characterValue);
			}
			fileReader.close();
		} catch (IOException e) {
			/* Notice use of System.err */
			System.err.println(e.getMessage());
		}
	}
}
