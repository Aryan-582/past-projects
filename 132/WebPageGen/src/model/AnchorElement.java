package model;

public class AnchorElement extends TagElement {
	private String url, linkText, attributes;
	
	//anchor constructor with specified tags
	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, new TextElement(" href=" + "\"" + url + "\"" + ">" + linkText), attributes);
		this.url = url;
		this.linkText = linkText;
		this.attributes = attributes;
	}
	//returns linkText
	public String getLinkText() {
		return linkText;
	}
	//return URL text
	public String getUrlText() {
		return url;
	}
	//indents the AnchorElement and adds the start/end tags
	public String genHTML(int indentation) {
		String genHTML = new String();
		genHTML = Utilities.spaces(indentation);
		genHTML += getStartTag() + this.content + getEndTag();
		return genHTML;
	}
	
}
