package binaryfiles;

import java.io.*;

public class CopyingBytes {
	public static void main(String[] args) throws IOException {
		String sourceFile = "UMDglobe.gif";
		String targetFileName = "copyUMDglobe.gif";
		File file = new File("UMDglobe.gif");

		if (!file.exists()) {
			System.err.println(sourceFile + " does not exist.");
			System.exit(1);
		}
		InputStream inputStream = new FileInputStream(new File("UMDglobe.gif"));
		OutputStream outputStream = new FileOutputStream("copyUMDglobe.gif");

		int byteData;
		while ((byteData = inputStream.read()) != -1) {
			outputStream.write(byteData);
		}

		inputStream.close();
		outputStream.close();
		System.out.println(targetFileName + " created; refresh project to see the file.");
	}
}
