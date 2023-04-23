package binaryfiles;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;

public class BinaryFileWriterEx {
	public static void main(String[] args) {
		String filename = "fileWriter.bin";
		boolean append = false;

		try {
			DataOutputStream outputStream = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(filename, append)));
			outputStream.writeUTF("The value of PI is ");
			outputStream.writeDouble(Math.PI);
			outputStream.close();
			System.out.println("Results can be found at: " + filename);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
