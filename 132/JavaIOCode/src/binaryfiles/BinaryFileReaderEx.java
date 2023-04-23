package binaryfiles;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;

public class BinaryFileReaderEx {
	public static void main(String[] args) {
		String filename = "fileWriter.bin";

		try {
			DataInputStream inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
			String message = inputStream.readUTF();
			double pi = inputStream.readDouble();
			inputStream.close();
			System.out.println("Read values: " + message + pi);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
