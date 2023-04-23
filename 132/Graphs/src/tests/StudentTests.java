package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import implementation.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
    
    @Test
	public void test() {
		Graph<Double> graph = createGraph();
		
		System.out.println(graph.getData("A"));
	}
	
}