package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {
	private String attributes;
	private ArrayList<Element> elements;
	
	//constructor with the specified tags and initalizes array list
	public ParagraphElement(String attributes) {
		super("p", true, new TextElement(""), attributes);
		this.attributes = attributes;
		elements = new ArrayList<Element>();
	}
	//adds element to elements array list
	public void addItem(Element item) {
		this.elements.add(item);
	}
	//indents the elements list and put the specified start/end tags
	public String genHTML(int indentation) {
		String genHTML = new String();
		genHTML = Utilities.spaces(indentation);
		genHTML += getStartTag() + "\n";
		for (int i = 0; i < elements.size(); i++) {
			genHTML += elements.get(i).genHTML(indentation + 3) + "\n";
		}
		genHTML += Utilities.spaces(indentation) + getEndTag();
		return genHTML;
	}
	
}
