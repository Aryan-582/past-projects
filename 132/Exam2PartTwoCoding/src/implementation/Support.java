package implementation;

public class Support {
	public static boolean isValid(String param) {
		if (param == null || param.isBlank()) {
			throw new IllegalArgumentException("Invalid Argument");
		} else {
			return true;
		}
	}
}
