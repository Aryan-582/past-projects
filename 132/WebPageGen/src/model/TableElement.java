package model;

public class TableElement extends TagElement {

	private int cols, rows;
	private String attributes;
	protected Element[][] element2D;
	
	//constructor that includes specified tags and initializes the 2D array 
	public TableElement(int rows, int cols, String attributes) {
		super("table", true, new TextElement(""), attributes);
		this.element2D = new Element[rows][cols];
		this.attributes = attributes;
	}
	
	//adds an element to the 2D array based on indices
	public void addItem(int rowIndex, int colIndex, Element item) {
		this.element2D[rowIndex][colIndex] = item;
	}
	
	//displays the numeric value at which the table is being utilized
	// by testing if each specific cell is being used
	public double getTableUtilization() {
		double count = 0;
		double cellsCount = 0;
		for (int i = 0; i < this.element2D.length; i++) {
			for (int j = 0; j < this.element2D[i].length; j++) {
				if (this.element2D[i][j] != null && this.element2D[i][j] instanceof Element &&
						!this.element2D[i][j].toString().isBlank()) {
					count++;
					cellsCount++;
				} else {
					cellsCount++;
				}
			}
		}
		
		double tableUtil = (count / cellsCount)*100;
		return tableUtil;
	}
	
	//indents the elements 2D list while also checking for null errors 
	//and adds specified start/end tags
	@Override
	public String genHTML(int indentation) {
		String genHTML = new String();
		genHTML = Utilities.spaces(indentation);
		genHTML += getStartTag() + "\n";
		for (int i = 0; i < element2D.length; i++) {
			genHTML += Utilities.spaces(indentation + 3) + "<tr>";
			for (int j = 0; j < element2D[i].length; j++) {
				try {
					genHTML += "<td>" + element2D[i][j].genHTML(0) + "</td>";
				} catch (Exception NullPointerException) {
					genHTML += "<td></td>";
				}
			}
			genHTML += "</tr>\n";
		}
		genHTML += getEndTag();
		return genHTML;
	}
}
