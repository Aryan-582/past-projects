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
		  The StringProc interface definition is provided below.  Can this interface
		  be used to write a lambda expression?
		  
		  public interface StringProc {
			 public String gen(char sym, int times);
			 public Boolean verify();
		  }
		
		  a. No, as the verify() method returns a Boolean instead of boolean.
		  b. No, as all the methods are not returning the same type.
		  c. Yes.
		  d. No, for a reason other than a. or b.
		*/
		
		return 'd';
	}
	
	public static char question02() {
		/*
		  A new online test grading system is able to grade multiple choice,
		  true/false questions, and coding questions, providing a summary of
		  results.  We don't know what developers did, just that providing
		  the questions and answers generates a report. This scenario 
		  is an example of:
		  
		  a. Procedural Abstraction
		  b. Data Abstraction
		  c. Object-Oriented Design
		  d. None of the above
		*/
		
		return 'b';
	}
	
	public static char question03() {
		/*
		  The following times for different number of values (n) corresponds 
		  to an algorithm recently developed.  What is the Big-O of the algorithm?
		  
		     n | Running Time (seconds)
		    64 |       10
		   128 |       20
		   256 |       40
		   512 |       80
		   
		  a. O(1)
		  b. O(n)
		  c. O(n^2) // Quadratic
		  d. O(n^3) // Cubic
		  e. O(log(n))
		  f. O(nlog(n)) 
		  g. None of the above
		*/
		
		return 'c';
	}
	
	public static char question04() {
		/*
		  The following times for different number of values (n) corresponds 
		  to an algorithm recently developed.  What is the Big-O of the algorithm?
		  
		     n | Running Time (seconds)
		    64 |       10
		   128 |       10
		   256 |       10
		   512 |       10
		   
		  a. O(1)
		  b. O(n)
		  c. O(n^2) // Quadratic
		  d. O(n^3) // Cubic
		  e. O(log(n))
		  f. O(nlog(n)) 
		  g. None of the above
		*/
			
		return 'a';
	}
	
	public static char question05() {
		/*
		  What is the Big-O for the following expression? n^2 stands for n square.
		  
		  				200log(n) + n(n^2) + 457		
		 
		  a. O(1)
		  b. O(n)
		  c. O(n^2) // Quadratic
		  d. O(n^3) // Cubic
		  e. O(log(n))
		  f. O(nlog(n)) 
		  g. None of the above
		*/
		
		return 'c';
	}
	
	public static char question06() {
		/*
		  Class A and B compile, class B extends class A, class A does not define any
		  constructors and class B does not define any constructor.  When a constructor 
		  with two integer values is added to class A, class A compiles, but class 
		  B does not compile anymore. Why?
		 
		  a. There is something wrong with the constructor added to class A.
		  b. A default constructor in class A is needed for class B to compile.
		  c. A default constructor in class B is needed.
		  d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question07() {
		/*
		  The Book class defines a method named "moveToPage" that has the following 
		  prototype:
		  
		                       public String moveToPage(int page);
		  
		  The ElectronicBook class extends the Book class. Which of the following
		  is the prototype of a method in the ElectronicBook class that OVERRIDES 
		  the "moveToPage" method of the Book class? 
		  
		  a. public String moveToPage(int page);
		  b. private String moveToPage(int page);
		  c. public String moveToPage(String position);
		  d. a. and b.
		  e. a. and c. 
		  f. None of the above.
		*/
		
		return 'd';
	}
	
	public static char question08() {
		/* 
		  This question relies on the same classes described in the previous question.
		  
		  The Book class defines a method named "moveToPage" that has the following prototype:
		  
		                     public String moveToPage(int page);
		  
		  The ElectronicBook class extends the Book class. Which of the following
		  is the prototype of a method in the ElectronicBook class that OVERLOADS
		  the "moveToPage" method of the Book class? 
		  
		  a. public String moveToPage(int page);
		  b. private String moveToPage(int page);
		  c. public String moveToPage(String position);
		  d. a. and b.
		  e. a. and c. 
		  f. None of the above.
		*/
		  
		return 'c';
	}

	public static char question09() {
		/*
		  The Plane class defines a method called land(). The Jet class extends Plane
		  and provides a land() method (it has the same prototype as the one in Plane).
		  Which land() method will be called in the following code fragment if we assume 
		  late (dynamic) binding is being used?  You can assume Plane and Jet have 
		  default constructors.
		  
		  				Plane obj = new Jet();
		  				obj.land();
		
		  a. The land() method of the Jet class.
		  b. The land() method of the Plane class.
		  c. The land() method in Plane's superclass.
		  d. None of the above.
		 */
		
		return 'a';
	}
		
	public static char question10() {
		/*
	      A set of tests generate 99% test coverage for a piece of code.  This means that:
		  
          a. 99% of the code has no bugs and 1% has bugs.
          b. 99% of the code has been executed by tests and 1% has not been executed by tests.
          c. 1% of the code has been executed by tests and 99% has not been executed by tests.
          d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question11() {
		/*
		  We need to easily identify objects as animated or not-animated. 
		  Which concept covered in the course will allow us to easily 
		  identify what kind of object we have?

          a. Marker design pattern.
          b. Singleton design pattern.
          c. Decorator design pattern.
          d. State design pattern.
          e. None of the above.
		*/
		
		return 'd';
	}
	
	public static char question12() {
		/*
		  A car dealership can customize a car by adding any combination of the
		  following: stereo system, sunroof, and alarm system. Which concept covered 
		  in the course will allow us to reduce the number of classes we have 
		  to define to support all possible car configurations?

          a. Marker design pattern.
          b. Singleton design pattern.
          c. Decorator design pattern.
          d. State design pattern.
          e. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question13() {
		/*
		  A Table class does not define an equals method (one that returns a 
		  boolean and has an Object reference parameter). If x and y are Table 
		  references, x.equals(y) is equivalent to:
		   
		   a. x != y
		   b. x == y
		   c. x.compareTo(y) == 0
		   d. b. and c.
		   e. None of the above.
		*/
		
		return 'b';
	}
		
	public static char question14() {
		/*
		   The Train class needs the functionality provided by a class called Support, but
		   we are not sure whether the Train class should extend Support or define a
		   Support instance variable and assign it an object of type Support.  Which
		   choice should we use?
		   	   
		   a. Train class defining a Support instance variable.
		   b. Train class extending Support.
		   c. Either one is fine. 
		   d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question15() {
		/* 
		   Which component of the Model/View/Controller did you implement for the
		   Clear Cells/Process Cells project?
		   
		   a. View
		   b. Model
		   c. Controller
		   d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question16() {
		/*
		   The following code does not compile.  Why? 
		   
		   ArrayList<Integer> list = new ArrayList<>();
		   ArrayList<Object> objs = list;
		   
		   a. Because an ArrayList<Object> cannot store a reference to an Integer.
		   b. Because we needed to use int instead of Integer.
		   c. Because we could add something other than an Integer to list via objs 
		      if the assignment were allowed.
		   d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question17() {
		/*		   
		   What is the Big-O associated with the following code fragment?
		   
		   for (int i = 0; i < n; i += 2) {
			  for (int k = n; k <= n + 1; k++) {
				 System.out.println(i);
			  }
		   }
		   
		   a. O(1)
		   b. O(n)
		   c. O(n^2) // Quadratic
		   d. O(n^3) // Cubic
		   e. O(log(n))
		   f. O(nlog(n)) 
		   g. None of the above
		*/
		
		return 'c';
	}
	
	public static char question18() {
		/*		   
		   What is the Big-O associated with the following code fragment?
		   
		   for (int i = 1; i <= n / 2; i++) {
			  k = 1;
			  while (k < n) {
				 k *= 3;
			  }
		   }
		   		   
		   a. O(1)
		   b. O(n)
		   c. O(n^2) // Quadratic
		   d. O(n^3) // Cubic
		   e. O(log(n))
		   f. O(nlog(n)) 
		   g. None of the above

		*/
		
		return 'f';
	}
	
	public static char question19() {
		/*		   
		   What is the Big-O associated with the following code fragment?
		   
		   for (int i = 1; i <= n / 4; i++) {  
			  for (int m = 1; m <= n; m++) {    
				 System.out.println(i);			// Line1
			  }                                 
			  for (int m = 1; m <= n; m++) {
				 System.out.println(i);         // Line2
			  }
		   }
		 		   		   
		   a. O(1)
		   b. O(n)
		   c. O(n^2) // Quadratic
		   d. O(n^3) // Cubic
		   e. O(log(n))
		   f. O(nlog(n)) 
		   g. None of the above

		*/
		
		return 'c';
	}
	
	public static char question20() {
		/*		   
			In the previous question, which is the critical section? 
			
		    a. Line1
		    b. Line2
		    c. a. and b.
		    d. None of the above
		*/
		
		return 'c';
	}
	
	public static char question21() {
		/*		   
		   Values are inserted into a binary search tree in decreasing order (each
		   value is smaller than the previous one). What is the Big-O for a search
		   operation in the tree after the insertion has been completed?
		   
		   a. O(1)
		   b. O(n)
		   c. O(n^2) // Quadratic
		   d. O(n^3) // Cubic
		   e. O(log(n))
		   f. O(nlog(n)) 
		   g. None of the above
		   
		*/
		
		return 'b';
	}
	
	public static char question22() {
		/*		   
			      10
			     /  \
			   15    23
			   /
			 50
			   
		   What is the heap that results from removing the root element (10) from the
		   above heap?
		
		   a.     50
			     /  \
			   15    23
			  
		   b.     15
			     /  \
			   50    23
			   
		   c.     50
			     /  \
			   23    15
		    
		   d.     23
			     /  \
			   15    50
		    
		   e. None of the above		
		
		*/
		
		return 'a';
	}
	
	public static char question23() {
		/*		   
           The following array represents a cars hash table that relies on open 
           addressing with linear probing.  NeverUsed represent an entry that 
           has never been used, and Removed an entry that used to hold a value.

                 Index  Car
                   0    BlueCar
                   1    NeverUsed
                   2    RedCar
                   3    Removed
                   4    NeverUsed
                   5    GreenCar
    
           Where will (index in the table) a car that hashes to entry with 
           index 5 (five) be placed in the table?

		   a. 0
		   b. 1
		   c. 2
		   d. 3
		   e. 4
		   f. 5 
		   g. None of the above
          
		*/
		
		return 'd';
	}
	
	public static char question24() {
		/*		   
           The following array represents a cars hash table that relies on open 
           addressing with linear probing.  NeverUsed represent an entry that 
           has never been used, and Removed an entry that used to hold a value.

               Index  Car
                 0    BlueCar
                 1    NeverUsed
                 2    RedCar
                 3    Removed
                 4    NeverUsed
                 5    GreenCar
  
           Where will (index in the table) a car that hashes to entry with 
           index 3 (three) be placed in the table?

		   a. 0
		   b. 1
		   c. 2
		   d. 3
		   e. 4
		   f. 5 
		   g. None of the above
        
		*/
		
		return 'd';
	}
	
	public static char question25() {
		/*		   
           The class Dress overrides the equals method so two dresses are considered
           the same if they have the same size.  What are the consequences of defining 
           for the class a hashCode method that returns the constant value -15?
            
           a. The Java Hash Code Contract will NOT be satisfied.
		   b. The Java Hash Code Contract will be satisfied.
		   c. There will be a large number of collisions.
		   d. There will NOT be a large number of collisions.
		   e. b. and c. 
		   f. b. and d.
		   g. None of the above
		*/
		
		return 'e';
	}
	
	public static char question26() {
		/*		   
		   In a a threaded environment, which components are shared among threads?
			
		   a. Stack
		   b. Heap
		   c. a. and b.
		   d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question27() {
		/*		   
		   A string object is shared by 100 threads.  When will a data race take place?
			
		   a. If we do not protect the string object with a lock object.
		   b. If we do not protect the threads' stacks with a lock object.
		   c. Never
		   d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question28() {
		/*		   
		   Assume t1 represents a thread.  Which of the following will allow
		   the thread to start executing immediately? 
			
		   a. t1.join()
		   b. t1.interrupt()
		   c. t1.start() 
		   d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question29() {
		/*		   
		   Assume t1 and t2 are threads.  What will take place if we execute the
		   following code?
		   
		   t1.start(); t1.join(); t2.start(); t2.join();
			
		   a. Concurrency among threads will take place.
		   b. Concurrency among threads will NOT take place.
		   c. t2 will finish before t1.
		   d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question30() {
		/*		   
		   Assume t1 is a thread.  Which thread will block when t1.join() is executed
		   and t1 has not finished?
			
		   a. Any thread.
		   b. The thread executing t1.join().
		   c. t1
		   d. None of the above.
		*/
		
		return 'b';
	}
	
	public static char question31() {
		/*		   
		   public void task(ArrayList<String> list, String entry) {
			  int y = 100;
		      
		      System.out.println("Processing");
		      list.add(entry + y);
		      System.out.println("Added");
	       }

	       The task method is executed by several threads, all sharing the 
		   ArrayList associated with the list parameter. In this code: 

		   a. We do not have any data race problems.
		   b. We can prevent a data race by defining the method as synchronized, although
		      this approach will limit concurrency.
		   c. We can prevent a data race without limiting concurrency by acquiring a 
		      lock (shared by all threads) that encloses list.add(entry + y);.
		   d. b. and c.
		   e. None of the above.
		*/
		
		return 'd';
	}
	
	public static char question32() {
		/*		   
		   Assume t1 is a thread that is blocked waiting for an input/output 
		   operation (e.g., file reading) to complete. Once the input/output
		   operation has been completed, the thread:  

		   a. Will start executing immediately.
		   b. Will have to wait its turn to execute again.
		   c. Needs to execute the start() method.
		   d. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question33() {
		/*		   
		   Assume we have two threads t1 and t2. The first thread prints the
		   message "Sunny" a 1000 times and t2 prints the message "Cloudy" a 1000
		   times.  For us to see the messages interleaved (e.g., "Sunny", "Cloudy",
		   "Sunny", "Cloudy", ...) the scheduler must have:

		   a. A preemptive scheduling policy.
		   b. A non-preemptive scheduling policy.
		   c. Any scheduling policy will work.
		   d. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question34() {
		/*
		   For the following undirected graph, provide a Breadth First Search 
		   (BFS) starting at vertex B (B like the first letter of Beach) using 
		   the algorithm described in lecture. Pick nodes to visit using 
		   alphabetical order (when multiple choices are possible). Do not confuse 
		   BFS with DFS. The graphs edges are A-D, A-B, B-D, C-D, D-E. 
		   
		                          A
		                        /   \
		                       D_____B
                              / \
		                     C   E
		    
		   a. BADCE
		   b. BDECA
		   c. BECDA
		   d. BCEDA
		   e. None of the above.
		  */
		
		return 'a';
	}
	
	public static char question35() {
		/*
		   For the following undirected graph, provide a Depth First Search 
		   (DFS) starting at vertex B (B like the first letter of Beach) using 
		   the algorithm described in lecture. Pick nodes to visit using 
		   alphabetical order (when multiple choices are possible). Do not confuse 
		   BFS with DFS. The graphs edges are A-D, A-B, B-D, C-D, D-E. 
		   
		                          A
		                        /   \
		                       D_____B
                              / \
		                     C   E
		    
		   a. BADCE
		   b. BDECA
		   c. BECDA
		   d. BCEDA
		   e. None of the above.
		  */
		
		return 'a';
	}
	
	public static char question36() {
		/*
		   Apply Dijkstra's algorithm using A as the starting (source) node. 
		   Indicate the cost and predecessor for each node in the graph after 
		   processing two nodes (A and one additional node). The graph's edges 
		   of the undirected graph are A-D (3), A-B (7), B-D (3), C-D (5), D-E (2). 
		   
		                          A
		                       3 / \ 7
		                        /   \
		                       D__3__B
                              / \
                           5 /   \ 2
		                    C     E
		    
		    Below -1 stands for infinity, - (dash) no predecessor, and we use a 
		    VERTEX[COST, PREDECESSOR] notation.  For example, the entry C[10, B] 
		    means the cost of reaching C is 10, coming from B (cost is 10, 
		    predecessor is B).
		     
		    Which of the following is the cost and predecessor of nodes in the graph
		    after processing two nodes (A and one additional node)? 
		    
		       
		    a. A[0,-]  B[7,A]  C[-1,-]  D[3,A]  E[-1,-] 
		    b. A[0,-]  B[6,A]  C[-1,-]  D[3,A]  E[-1,-] 
		    c. A[0,-]  B[6,D]  C[8,D]   D[3,A]  E[5,D] 
		  	d. A[0,-]  B[6,A]  C[5,D]   D[3,B]  E[2,D] 
		    e. None of the above.
		  */
		
		return 'c';
	}
	
	public static char question37() {
		/*		   
		   A new sorting algorithm works better when the data is sorted or nearly
		   sorted. To sort data the algorithm needs to copy data to a new array that is
		   half of the size of the array that we need to sort.  Which properties apply
		   to this sorting algorithm?
		  
		   a. Adaptive
		   b. In-Place
		   c. Stable
		   d. a. and b.
		   e. a. and c.
		   f. b. and c.
		   g. None of the above.
		*/
		
		return 'a';
	}
	
	public static char question38() {
		/*		   
		   A graduate student claims she/he developed a new sorting algorithm based
		   on comparing keys that has a Big-O of O(n).  What can we say about this
		   algorithm?
		  
		   a. Seems like a new choice.
		   b. This kind of algorithm is not possible.
		   c. The algorithm is stable.
		   d. The algorithm is in-place.
		   e. None of the above.
		*/
		
		return 'c';
	}
	
	public static char question39() {
		/*		   
		   Which of the following sequences represent the result of applying 
		   the quicksort partitioning function to an array where the pivot value 
		   is 90? We are sorting elements in increasing order. 

		   a.  10   180     2     90     -20     145
		   b.  10   -20     2     90     180     145
		   c. -20    10     2     90     180     145
		   d. b. and c.
		   e. None of the above.
		*/
		
		return 'e';
	}
	
	public static char question40() {
		/*		   
		   A class named Shoes implements the Comparable interface. Which of 
		   the following sorting algorithms can use the Shoes compareTo method to
		   sort Shoes objects?  

		   a. Bubblesort
		   b. Radix Sort
		   c. Quicksort 
		   d. a. and c.
		   e. a. and b.
		   f. a., b., and c.
		   g. None of the above.
		*/
		
		return 'd';
	}
	
	public static char question41() {
		/*	
		   When implementing a new algorithm, we recognized that many of the subproblems
		   we generate from the original problem are the same (they share the same
		   solution). Which is the best algorithm strategy we should use for this kind 
		   of problem?
		   
		   a. Heuristic
		   b. Greedy
		   c. Divide and Conquer 
		   d. Dynamic Programming
		   e. Branch and Bound
		   f. Brute Force
		   g. None of the above.
		*/
		
		return 'd';
	}
}
