package objectStreams;

import java.util.*;
import java.io.*;

public class Phonebook implements Serializable {
	private static final long serialVersionUID = 1L;
	private TreeMap<String, String> book = new TreeMap<>();

	public void addUpdate(String name, String phone) {
		book.put(name, phone);
	}

	public String get(String name) {
		return book.get(name);
	}

	public String toString() {
		return book.toString();
	}

	public void clear() {
		book.clear();
	}
}
