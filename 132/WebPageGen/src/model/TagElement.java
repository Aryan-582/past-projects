package model;

import java.util.ArrayList;

public class TagElement implements Element {

	private static int id = 0;
	private static boolean enableIds;
	protected String tagName;
	private boolean endTag;
	protected Element content;
	private String attributes;
	private int uniqueid;
	protected ArrayList<Element> elements;
	
	//constructor which initializes all tags to be used and initializes certian
	//instance variables based on their element case
	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		if (this instanceof ParagraphElement || this instanceof ListElement) {
			this.elements = new ArrayList<Element>();
			elements.add(content);
		} else {
			this.content = content;
		}
		this.tagName = tagName;
		this.endTag = endTag;
		this.content = content;
		this.attributes = attributes;
		id++;
		this.uniqueid = id;
		
	}
	//return uniqueID
	public int getId() {
		return uniqueid;
	}
	//returns StringID
	public String getStringId() {
		String stringId = new String();
		stringId = tagName + uniqueid;
		return stringId;
	}
	//returns startTag based on which Element the tag is being used for
	public String getStartTag() {
		String startTag = new String("<");
		if (this instanceof ImageElement || this instanceof AnchorElement) {
			if (enableIds == true && attributes != null && !attributes.isBlank()) {
				startTag += tagName + " id=" + "\"" + getStringId() + "\"" + " " + attributes ;
			} else if (enableIds == false && attributes != null && !attributes.isBlank()) {
				startTag += tagName + " " + attributes; 
			} else if (enableIds == true && (attributes == null || attributes.isBlank())) {
				startTag += tagName + " id=" + "\"" + getStringId() + "\"";
			} else {
				startTag += tagName;
			}
		} else {
			if (enableIds == true && attributes != null && !attributes.isBlank()) {
				startTag += tagName + " id=" + "\"" + getStringId() + "\"" +" " + attributes + ">";
			} else if (enableIds == false && attributes != null && !attributes.isBlank()) {
				startTag += tagName + " " + attributes + ">";
			} else if (enableIds == true && (attributes == null || attributes.isBlank())) {
				startTag += tagName + " id=" + "\"" + getStringId() + "\"" + ">";
			} else {
				startTag += tagName + ">";
			}
		}	
		return startTag;
	}
	//returns endTags based on which Element is being used for and if the endTag
	//boolean is true
	public String getEndTag() {
		String endTags = "</" + tagName + ">";
		if (endTag == true) {
			return endTags;
		} else if (this instanceof ImageElement) {
			endTags = ">";
			return endTags;
		} else {
			return "";
		}
	}
	//sets the attributes instance variable
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	//resets IDs back to starting 0
	public static void resetIds() {
		id = 0;
	}
	//boolean to see if IDs want to be implmented
	public static void enableId(boolean choice) {
		enableIds = choice;
	}
	//indents content with specified start and end tags
	public String genHTML(int indentation) {
		String genHTML = Utilities.spaces(indentation);
		genHTML += this.getStartTag() + this.content + this.getEndTag();
		return genHTML;
	}
	
}
