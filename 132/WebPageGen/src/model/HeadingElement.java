package model;

public class HeadingElement extends TagElement implements Element {
	
	private Element content;
	private int level;
	private String attributes;
	//constructor for heading element with the specified tags
	public HeadingElement(Element content, int level, String attributes) {
		super("h" + level, true, content, attributes);
		this.content = content;
	}
	//indents content with start/end tags
	public String genHTML(int indentation) {
		String genHTML = Utilities.spaces(indentation);
		genHTML += getStartTag() + this.content.toString() + getEndTag();
		return genHTML;
	}
}
