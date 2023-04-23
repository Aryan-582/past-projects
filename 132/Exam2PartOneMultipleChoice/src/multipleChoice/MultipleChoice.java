package multipleChoice;

public class MultipleChoice {
	
	/* READ READ READ READ READ
	 * 
	 * To answer a question, remove the "throw" statement and add a return statement
	 * with the letter representing your answer.The letter representing your choice
	 * MUST BE IN LOWERCASE. For example, return 'a'.  You need to add single quotes
	 * to the choice you selected (e.g., 'a' instead of just a).
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
		  The Analyzer interface is defined as follows:
		  
		  public interface Analyzer {
			 public double displace(int delta);
		  }

		  Which of the options below is a lambda expression that when assigned to 
		  variable x will generate the output 50.0 for the code fragment below?
		  
		  Analyzer x = 
		  System.out.println(x.displace(100));
		
		  a. () -> delta / 2
		  b. delta -> delta / 2
		  c. a. and b.
		  d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question02() {
		/*
		  The Printer interface is defined as follows:
		  
		  public interface Printer {
			 public String print();
		  }

		  Which of the options below is a lambda expression that when assigned to 
		  variable p will generate the output "100 printed" for the code fragment below?
		  
		  Printer p = 
		  p.print();
		
		  a. () -> 100 + " printed"
		  b. (int, string) -> 100 + " printed"
		  c. (int) -> 100 + " printed"
		  d. a. and b.
		*/
		
		return 'a';
	}
	
	public static char question03() {
		/*
		  The Tree interface defines 4 abstract methods and no default methods.
		  For this interface:
		  
		  a. We can only define 4 lambda expressions with this interface.
		  b. We can only define 1 lambda expression with this interface.
		  c. We cannot define any lambda expressions with this interface.
		  d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question04() {
		/*
		  The following times for different number of values (n) corresponds 
		  to an algorithm recently developed.  What is the Big-O of the algorithm?
		  
		     n | Running Time (seconds)
		    64 |       10
		   128 |       12
		   256 |       14
		   512 |       16
		   
		  a. O(1)
		  b. O(n)
		  c. O(n^2) // Quadratic
		  d. O(log(n))
		  e. O(nlog(n)) 
		  f. None of the above
		*/
		
		return 'c';
	}
	
	public static char question05() {
		/*
		  The following times for different number of values (n) corresponds 
		  to an algorithm recently developed.  What is the Big-O of the algorithm?
		  
		     n | Running Time (seconds)
		    64 |       10
		   128 |       40
		   256 |      160
		   512 |      640
		   
		  a. O(1)
		  b. O(n)
		  c. O(n^2) // Quadratic
		  d. O(log(n))
		  e. O(nlog(n)) 
		  f. None of the above
		*/
			
		return 'd';
	}
	
	public static char question06() {
		/*
		  What is the Big-O for the following expression?
		  
		  				10n + nlog(n) + 3nlog(n)		
		 
		  a. O(nlog(n))
		  b. O(log(n))
		  c. O(n)
		  d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question07() {
		/*
		  Big-O results are valid for:
		  
		  a. Large input data sets (large n).
		  b. Small input data sets (small n).
		  c. For any input data sets.
		  d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question08() {
		/*
		  The clone() method associated with the Object class generates:
		  
		  a. A deep copy.
		  b. A shallow copy.
		  c. A reference copy.
		  d. Any kind of copy we want.
		  e. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question09() {
		/* 
		  A class named Banana has a finalize() (destructor) method that prints 
		  the message "Goodbye".  A program creates two Banana objects, performs 
		  some computation, and ends.  How many "Goodbye" messages we will see?
		  
		  a. Two (one for each Banana object).
		  b. One
		  c. More than two.
		  d. We cannot tell how many.
		*/
		  
		return 'd';
	}

	public static char question10() {
		/*
		  When is a static initialization block in a class executed?
		  
		  a. When the constructor calls it.
		  b. When the class is loaded.
		  c. Every time a static method is called.
		  d. None of the above.
		 */
		
		return 'b';
	}
		
	public static char question11() {
		/*
		  Let's assume T represents the type parameter in a generic class.  Which
		  of the following creates an array of type T with a size 3?

		  a. T[] data = (T[]) new Object[3];
		  b. T[] data = (T[]) new T[3];
		  c. T data = new Object[3];
		  d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question12() {
		/*
		  The class Apple extends the class Snack.  A Bag class is a collection
		  of Apple references (similar to ArrayList).  Would the second assignment compile?
		  
		  Bag<Apple> list = new Bag<Apple>();
		  Bag<Snack> sec = list;  // Would this compile?
		  
		  a. Yes, it is possible to assign list to sec (it will compile).
		  b. No, it is NOT possible to assign list to sec (it will NOT compile).
		  c. We don't have enough information to tell whether it will compile or not.
		  d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question13() {
		/*
		  A printer company sells a basic printer to which it can add any combination
		  of options. Possible options are: scanning, faxing, copying and some 
		  other options the company has not defined yet.  We can have a printer 
		  that only prints and copy; one that only prints and faxs, etc.  Which 
		  concept covered in the course will allow us to reduce the number of 
		  classes we have to define to support all possible printers?

          a. Garbage Collection.
          b. Decorator design pattern.
          c. Singleton design pattern.
          d. ArrayList of objects.
          e. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question14() {
		/*
		  In Java it is NOT possible to control how many instances of a class we can have.
		  
          a. True
          b. False
		*/
		
		return 'b';
	}
	
	public static char question15() {
		/*
		  In a problem statement/definition/description for a system you need to design,
		  nouns may represent:
		  		  
          a. Methods of the design.
          b. Instance variables or classes of the design. 
          c. The kind of garbage collector we need to use.
          d. None of the above.
		*/
		
		return 'b';
	}
		
	public static char question16() {
		/*
		   We need to define a new class B based on the functionality provided by class A.  
		   We have two choices:
		   
		   Choice #1: Define class B by extending class A.
		   Choice #2: Define class B by defining an instance variable of type A in class B.
		   
		   If we are not sure which choice to use, which one should we pick?
		   
		   a. Choice #1.
		   b. Choice #2.
		   c. Either one is fine. 
		   d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question17() {
		/*
		   A software team is developing a new email system. One member of
		   the team, Rose, is developing the code that will send and receive 
		   messages. Another member, Peter, is developing the user interface
		   for the system (the buttons, textboxes needed, etc.)
		   
		   Which component of the Model/View/Controller is Rose developing?
		   
		   a. Model
		   b. View
		   c. Controller
		   d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question18() {
		/*		   
		   A inner class can access private and public variables and methods of 
		   the outer class. 
		   
		   a. True
		   b. False
		*/
		
		return 'a';
	}
	
	public static char question19() {
		/*		   
		   The following code fragment compiles.  What can we say about the 
		   variable called data?
		   
		   for (String elem : data) {
			  System.out.println(elem);
		   }

		   
		   a. data is a string.
		   b. data is an instance of a class that implements the Iterator interface.
		   c. data is an instance of a class that implements the Iterable interface.
		   d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question20() {
		/*		   
		   Accessing the first element of a linked list is an operation that
		   has following Big-O:
		     
		   a. O(n)
		   b. O(1)
		   c. O(log(n))
		   d. None of the above.
		*/
		
		return 'b';
	}
}
