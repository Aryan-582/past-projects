package multipleChoice;

public class MultipleChoice {
	
	/* READ READ READ READ READ
	 * 
	 * To answer a question, remove the "throw" statement and add a return statement
	 * with the letter representing your answer.The letter representing your choice
	 * MUST BE IN LOWERCASE.
	 * 
	 * REMEMBER TO CHECK THE SUBMIT SERVER AFTER YOU SUBMIT THIS PART OF THE EXAM.
	 * There is a release test called "MultipleChoiceAnsweredNotGraded" that 
	 * tells you whether you have provided answers (not that they are correct) 
	 * for all the multiple choice questions. If you pass the release test
	 * "MultipleChoiceAnsweredNotGraded" that means you have provided an answer
	 * for every multiple choice question. Secret tests will verify whether those
	 * answers are correct or not. 
	 * 
	 * UNLIKE THE CODING PART, FOR THE MULTIPLE CHOICE QUESTION PART, ONLY 
	 * THE LAST SUBMISSION YOU PROVIDE (THAT COMPILES) WILL BE USED TO 
	 * COMPUTE YOUR GRADE, NOT THE HIGHEST SCORING ONE.
	 *
	 */
	
	public static char question01() {
		/*
		  In Java, which technique can be implemented using the private access specifier?
		  
		  a. Constructor Collection
		  b. Encapsulation
		  c. Garbage Collection
		  d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question02() {
		/*
		  In Java, which of the following allow us to define an IS-A relationship?
		  
		  a. A class
		  b. An interface
		  c. a. and b.
		  d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question03() {
		/*
		  Some graduate students updated the algorithm used by the submit server
		  and now testing a project takes 40% less time than before. We don't know 
		  what the students did, only that testing takes less time now.  This scenario 
		  is an example of?
		  
		  a. Data Abstraction
		  b. Procedural Abstraction
		  c. Efficient TA Execution
		  d. None of the above
		*/
		
		return 'a';
	}
	
	
	public static char question04() {
		/*
		  Which of the following allow us to avoid code duplication?

		  a. Auxiliary(private) methods that several methods rely on.
		  b. Inheritance
		  c. a. and b.
		  d. Garbage Collection
		  e. None of the above.
		*/

		return 'c';
	}
	
	public static char question05() {
		/*
		  In Java, a class can:
		 
		  a. Extend only one class, and implement multiple interfaces.
		  b. Extend more than one class and implement multiple interfaces.
		  c. Extend only one class and implement only one interface.
		  d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question06() {
		/*
		  Class A compiles, and has a single constructor that takes a string as parameter.
		  Class B extends class A. If "super()" is the first statement in the constructor 
		  of class B, then:
		 
		  a. B's constructor will compile.
		  b. We need to define a default constructor in class A in order for B's constructor
		     to compile.
		  c. We need to remove the "super()" from the class B's constructor for it to compile.
		  d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question07() {
		/*
		  The Programmer class defines a method named "implement" that has the following 
		  prototype:
		  
		  protected String implement(int linesOfCode);
		  
		  The JavaProgrammer class extends the Programmer class. Which of the following
		  is the prototype of a method in the JavaProgrammer class that OVERRIDES 
		  the "implement" method of the Programmer class? 
		  
		  a. public String implement(int linesOfCode);
		  b. private String implement(int linesOfCode);
		  c. public String implement();
		  d. a. and b.
		  e. a. and c. 
		*/
		
		return 'b';
	}
	
	public static char question08() {
		/*
		  This question relies on the same information as the previous one. The
		  Programmer class defines a method named "implement" that has the following 
		  prototype:
		  
		  protected String implement(int linesOfCode);
		  
		  The JavaProgrammer class extends the Programmer class. Which of the following
		  is the prototype of a method in the JavaProgrammer class that OVERLOADS (not 
		  OVERRIDES as in the previous question) the "implement" method of the Programmer 
		  class? 
		  
		  a. public String implement(int linesOfCode);
		  b. private String implement(int linesOfCode);
		  c. public String implement();
		  d. a. and b.
		  e. a. and c. 
		*/
		
		return 'c';
	}
	
	public static char question09() {
		/* 
		  We can tell whether two objects belong EXACTLY to the same class by using:
		 
		  a. The instanceof operator.
		  b. The getClass() method.
		  c. a. or b.
		  d. None of the above.
		*/
		  
		return 'b';
	}

	public static char question10() {
		/*
		  Class A defines a method called clear().  Class B, extends class A
		  and provides a method clear() (it has the same prototype as the one in class A).
		  Which clear() method will be called in the following code fragment if we assume 
		  early/static binding is being used?  You can assume A and B have default 
		  constructors.
		  
		  				A obj = new B();
		  				obj.clear();
		
		  a. The clear() method of class A.
		  b. The clear() method of class B.
		  c. The clear() method in the Object class.
		  d. None of the above.
		 */
		
		return 'a';
	}
		
	public static char question11() {
		/*
		  Class A is an abstract class with a single abstract method called analyze().
		  Class B extends class A, only defines a single non-abstract method called task(), 
		  and does not provide an implementation for analyze(). Which of the following
		  are true?
		  
          a. Class B will compile whether it is defined abstract or not.
          b. Class B must be defined abstract, otherwise the code will not compile.
          c. We can create instances of B, but not of A.
          d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question12() {
		/*
		  The average(int x) method in a class called "Tools" performs a special 
		  average computation.  We would like classes extending the "Tools" class 
		  to use the method, but not to change the computation the method does. 
		  How should the average method be defined in order to provide this guarantee?
		   
		  a. The method should be defined as protected.
		  b. The method should be defined as static.
		  c. The method should be defined as final.
		  d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question13() {
		/*
		  The method sum() is called in the linearize() method.  If sum() can throw
		  a checked exception, then:
		  
		  public void linearize() {
		  	 sum();
		  }

          a. The above linearize() method will compile.
          b. The above linearize() method will not compile.
          c. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question14() {
		/*
		  A set of tests generate 90% test coverage for a piece of code.  This means that:
		  
          a. 90% of the code has no bugs and 10% has bugs.
          b. 90% of the code has no bugs and 10% may or may not have bugs.
          c. 90% of the code has bugs and 10% has no bugs.
          d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question15() {
		/*
		  Class A is an immutable class. If we have an array of references to objects of type
		  A, which copying process will allow us to have an array copy where changes to 
		  any component of the array will not affect the original? We need the most efficient
		  copying process.  

          a. Deep copy, where the array and each of the objects the array refers to are duplicated.
          b. Shallow copy, where only the array is duplicated, but references to the objects
             are shared between the two arrays.
          c. Reference copy, where we only make a copy of the address that points to the original
             array object.
          d. None of the above.
		*/
		
		return 'a';
	}
		
	public static char question16() {
		/*
		   In the expression a.separate(b), a and b are reference variables
		   of the same type.  Inside of the separate method, which object 
		   is associated with the current object reference ("this")?
		   
		   a. The same object associated with a.
		   b. The same object associated with b.
		   c. There is no object associated with "this". 
		   d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question17() {
		/*
		  A Car class does not define an equals method (one that returns a 
		  boolean and has an Object reference parameter). If a and b are Car 
		  references, a.equals(b) is equivalent to:
		   
		   a. a == b
		   b. a != b
		   c. a < b
		   d. None of the above.
		*/
		
		return 'a';
	}
}
