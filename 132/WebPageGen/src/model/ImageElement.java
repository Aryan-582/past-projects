package model;

public class ImageElement extends TagElement {
	
	private String imageUrl, alt, attributes;
	private int width, height;
	
	//constructor with all parameters and image information
	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, new TextElement(
                " src=\"" + imageURL + "\" width=\"" + width + "\" height=\"" + height + "\" alt=\"" + alt + "\""),
                attributes);
		this.imageUrl = imageURL;
	}
	//returns imageURL
	public String getImageURL() {
		return imageUrl;
	}
}



