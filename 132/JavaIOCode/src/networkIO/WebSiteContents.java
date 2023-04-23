package networkIO;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WebSiteContents {
	public static void main(String[] args) throws IOException {
		String urlStr = "http://www.cs.umd.edu/~nelson/classes/utilities/URLExample.html";

		System.out.println(readURLContents(urlStr));
	}

	public static String readURLContents(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		URLConnection connection = url.openConnection();
		InputStream input = connection.getInputStream();
		Scanner scanner = new Scanner(input);
		StringBuffer buffer = new StringBuffer();

		while (scanner.hasNextLine()) {
			buffer.append(scanner.nextLine());
			buffer.append("\n");
		}

		scanner.close();

		return buffer.toString();
	}
}
