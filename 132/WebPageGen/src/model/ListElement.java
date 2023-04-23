package model;

import java.util.ArrayList;

public class ListElement extends TagElement {
	private boolean ordered;
	private String attributes;
	private ArrayList<Element> elements;
	
	//constructor with ordered boolean for lists and with the specified tags
	public ListElement(boolean ordered, String attributes)  {
		super("ol", true, new TextElement(""), attributes);
		this.ordered = ordered;
		this.attributes = attributes;
		this.elements = new ArrayList<Element>();
		if (ordered == false) {
			this.tagName = "ul";
		}
	}
	//adds an element to the elements arraylist
	public void addItem(Element item) {
		this.elements.add(item);
	}
	
	//indents elements list and adds tags between them
	public String genHTML(int indentation) {
		String genHTML = new String();
		genHTML = Utilities.spaces(indentation);
		genHTML += getStartTag() + "\n";
		for (int i = 0; i < elements.size(); i++) {
			genHTML += Utilities.spaces(indentation + 3) + "<li>\n";
			genHTML += elements.get(i).genHTML(indentation + 6)+ "\n";
			genHTML += Utilities.spaces(indentation + 3) + "</li>\n";
		}
		genHTML += Utilities.spaces(indentation) + getEndTag();
		return genHTML;
	}
}
