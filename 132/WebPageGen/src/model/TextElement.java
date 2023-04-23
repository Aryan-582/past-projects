package model;

public class TextElement implements Element {

	String text;
	
	//constructor that initialzes text variable
	public TextElement(String text) {
		this.text = text;
	}
	//indents the text with specificed parameter
	public String genHTML(int indentation) {
		String genHTML = new String();
		genHTML += Utilities.spaces(indentation);
		genHTML += text;
		return genHTML;
	}
	
	//toString that returns a copy of the text
	public String toString() {
		String toString = this.text;
		return toString;
	}
	
}
