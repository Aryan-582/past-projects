package cmsc420_s23; // Do not alter this line or your program will fail the autograder

import java.util.ArrayList;
import java.util.Collections;

public class AdjustableStack<Element> {
	
	private ArrayList<Element> stack;
	private ArrayList<Locator> locators;
	
	public class Locator { 
		public int position;
		public Element element;
	}

	public AdjustableStack() { 
		this.stack = new ArrayList<Element>();
		this.locators = new ArrayList<Locator>();
	}
	public Locator push(Element element) { 
		this.stack.add(element);
		Locator loc = new Locator();
		loc.position = stack.size() - 1;
		loc.element = element;
		locators.add(loc);
		return loc;	
	}
	public Element pop() throws Exception { 
		if (stack.size() == 0) {
			throw new Exception("Pop of empty stack");
		}
		return stack.remove(stack.size() - 1);
	}
	public Element peek() throws Exception { 
		if (stack.size() == 0) {
			throw new Exception("Peek of empty stack");
		}
		Element ele = stack.get(stack.size() - 1);
		return ele;
	}
	public int size() { 
		return stack.size();
	}
	public ArrayList<Element> list() { 
		Collections.reverse(stack);
		ArrayList<Element> joe = new ArrayList<Element>(stack);
		Collections.reverse(stack);
		return joe;
	}
	public void promote(Locator loc) {
		if (loc.position == stack.size() - 1) {
			return; 
		}
		Collections.swap(stack, loc.position, loc.position + 1);
		for (Locator locates : locators) {
			if (locates.element == stack.get(loc.position)) {
				locates.position--;
			}
		}
		loc.position++;
	}
	public void demote(Locator loc) { 
		if (loc.position == 0) {
			return; 
		}
		Collections.swap(stack, loc.position, loc.position - 1);
		for (Locator locates : locators) {
			if (locates.element == stack.get(loc.position)) {
				locates.position++;
			}
		}
		loc.position--;
		
		
	}
	public int getDepth(Locator loc) {
		ArrayList<Element> list = list();
		int count = 0;
		for (Element element : list){
			if (element == loc.element) {
				return count;
			} else {
				count++;
			}
		}
		return count;
	}
	
}
