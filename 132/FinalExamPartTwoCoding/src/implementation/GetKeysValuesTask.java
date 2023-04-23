package implementation;

import java.util.*;

/* Provided: do not modify */
public class GetKeysValuesTask<K, V> implements Task<K, V> {
	private ArrayList<String> data = new ArrayList<>();

	public void processing(K key, V value) {
		data.add("(" + key + "," + value + ")");
	}

	public ArrayList<String> getData() {
		return data;
	}
}
