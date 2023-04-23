package InterestTableCalculation;
import java.text.NumberFormat;

public class InterestTable {

	public static String simpleInterest(double principal, double rate, double years) {
		String string = new String();
		string += "Principal: " + principal + "," + " Rate: " + rate + "\n";
		string += "Year, Simple Interest Amount \n";
		for (int i = 1; i <= years; i++) {
			double simpleInterestCalc = principal + (principal * (rate / 100) * i);
			String formattedNumber = NumberFormat.getCurrencyInstance().format(simpleInterestCalc);
			string += i + "-->" + formattedNumber + "\n";

		}
		return string;
	}

	public static String compoundInterest(double principal, double rate, double years) {
		String string = new String();
		string += "Principal: " + principal + "," + " Rate: " + rate + "\n";
		string += "Year, Compound Interest Amount \n";
		for (int i = 1; i <= years; i++) {
			double compoundInterestCalc = principal * Math.pow(1 + (rate / 100), i);
			String formattedNumber = NumberFormat.getCurrencyInstance().format(compoundInterestCalc);
			string += i + "-->" + formattedNumber + "\n";

		}
		return string;
	}

	public static String bothInterest(double principal, double rate, double years) {
		String string = new String();
		string += "Principal: " + principal + "," + " Rate: " + rate + "\n";
		string += "Year, Simple Interest Amount, Compound Interest Amount \n";
		for (int i = 1; i <= years; i++) {
			double simpleInterestCalc = principal + (principal * (rate / 100) * i);
			double compoundInterestCalc = principal * Math.pow(1 + (rate / 100), i);
			String formattedSimple = NumberFormat.getCurrencyInstance().format(simpleInterestCalc);
			String formattedCompound = NumberFormat.getCurrencyInstance().format(compoundInterestCalc);
			string += i + "-->" + formattedSimple + "-->" + formattedCompound + "\n";
		}
		return string;
	}
}
