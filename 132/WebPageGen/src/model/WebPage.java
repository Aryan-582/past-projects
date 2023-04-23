package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {

	private String title;
	private ArrayList<Element> elements;
	
	//constructor that initializes the title and elements list
	public WebPage(String title) {
		this.title = title;
		this.elements = new ArrayList<Element>();
	}
	
	//adds an element to the elements list and returns its ID
	public int addElement(Element element) {
		if (element instanceof TagElement) {
			elements.add(element);
			return ((TagElement) element).getId();
		} else {
			return -1;
		}
	}
	
	//indents the elements list with specified tags
	public String getWebPageHTML(int indentation) {
		String webHTML = "<!doctype html>\n<html>\n";
		webHTML += Utilities.spaces(indentation) + "<head>\n";
		webHTML += Utilities.spaces(indentation) + "<meta charset=\"utf-8\"/>\n";
		webHTML += Utilities.spaces(indentation) + "<title>" + title + "</title>\n";
		webHTML += Utilities.spaces(indentation) + "</head>\n";
		webHTML += Utilities.spaces(indentation) + "<body>\n";
		for (int i = 0; i < elements.size(); i++) {
			webHTML += elements.get(i).genHTML(indentation);
			webHTML += "\n";
		}
		webHTML += Utilities.spaces(indentation) + "</body>\n" + "</html>";
		return webHTML;
	}
	
	//calls on Utilities method that provides the string to a file
	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, this.getWebPageHTML(indentation));
	}
	
	//searches for a specific element in the elements list
	public Element findElem(int id) {
		for (int i = 0; i < elements.size(); i++) {
			if(((TagElement)(elements.get(i))).getId() == id) {
				return elements.get(i);
			}
		}
		return null;
	}
	
	//returns values of which elements are being used in the web page
	//and how much the of table element is being utilized 
	public String stats() {
		String stats = new String();
		int lists = 0;
		int paragraphs = 0;
		int tables = 0;
		double tableUtil = 0;
		double tableUtilFinal = 0;
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i) instanceof ListElement) {
				lists++;
			} else if (elements.get(i) instanceof ParagraphElement) {
				paragraphs++;
			} else if (elements.get(i) instanceof TableElement) {
				tables++;
				tableUtilFinal += ((TableElement)(elements.get(i))).getTableUtilization();
			}
		}
		
		tableUtil = (tableUtilFinal / tables);
		stats += "List Count: " + lists + "\n";
		stats += "Paragraph Count: " + paragraphs + "\n";
		stats += "Table Count: " + tables + "\n";
		stats += "TableElement Utilization: " + tableUtil;
		
		return stats;
	}
	
	//compares the titles of webpages to find their alphebetical order
	public int compareTo(WebPage webPage) {
		if (this.title.equals(webPage.title)) {
			return 0;
		} else if (this.title.compareTo(webPage.title) > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
	//turns on.off ID usage
	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}
}
