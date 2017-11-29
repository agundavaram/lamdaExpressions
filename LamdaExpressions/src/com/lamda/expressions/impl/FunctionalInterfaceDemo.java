package com.lamda.expressions.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.logging.Logger;

import com.lamda.expressions.FunctionalInterface1;
import com.lamda.expressions.FunctionalInterface2;
import com.lamda.expressions.FunctionalInterface3;

public class FunctionalInterfaceDemo {
	 private static final Logger logger = Logger.getLogger( FunctionalInterfaceDemo.class.getName() );
	// creating a method execute, which takes the FunctionalInterface as a param. 
	// Keep in mind we have not yet defined the method doSomeWork
	public static void execute(FunctionalInterface1 worker) {
		// do all sorts of gambling
		worker.doSomeWork();
	}

	public static void main(String [] args) {
		// this will demonstrate java.land.Runable functional interface
		example1();

		// This will demonstrate our custom functional interface
		example2();

		// looping using lamda expression, using :: in Java8
		example3();

		// target types
		// write a lamda expression that takes two int as input and returns a or b based on an if condition
		example4();

		//method referencing
		example5();

		// using java's pre-defined general purpose functional interfaces
		List<Integer> roster = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		Predicate<Integer> tester =  p -> p.intValue() > 5;
		printPersonsWithPredicate(roster, tester);
	}


	// This is demonstrate our custom functional interface
	public static void example2(){
		// First let us look at how the old way was. Using anon classes
		//invoke doSomeWork using Annonymous class
		execute(new FunctionalInterface1() {
			@Override
			public void doSomeWork() {
				logger.info("Worker invoked using Anonymous class");
			}
		});

		//invoke doSomeWork using Lambda expression 
		// class A implements F1 doSomeWork(){logger.info("Worker invoked using Lambda expression")}
		// F1 a = new A();
		// execute(a);
		execute( () -> logger.info("Worker invoked using Lambda expression") );

		// class B implements F1 doSomeWork(){logger.info("Worker invoked using Lambda expression 2")}
		// F1 b = new B();
		// execute(b);
		execute( () -> logger.info("Worker invoked using Lambda expression 2") );
	}

	// this is demonstrate java.land.Runable functional interface
	public static void example1(){
		//Old way:
		new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("Hello from thread, using anon class");
			}
		}).start();

		//New way:
		new Thread(	() -> logger.info("Hello from thread, using Lamda expression") ).start();
		// since  the method run() is a no param method () is used and -> is used to assign the body to the method
	}

	// looping using lamda expression, using :: in Java8
	public static void example3(){

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
		List<String> stringList = Arrays.asList("1", "2", "3", "4", "5", "6", "7");

		//Old way:
		for(Integer n: list) {
			logger.info("using the foreach:"+n);
		}

		//New way:
		Consumer<Integer> consumer =  (n) -> logger.info("using Lamda expressions:"+n);
		Consumer<String> stringConsumer =  (n) -> logger.info("using Lamda expressions:"+n);
		list.forEach(consumer);
		stringList.forEach(stringConsumer);


		/*//or we can use :: double colon operator in Java 8
		list.forEach(System.out::println);*/

	}

	// target types
	// write a lamda expression that takes two int as input and returns 1 or 2 based on input
	public static void example4(){
		// define the method body
		FunctionalInterface2 funcInt2 = (a,b) -> { 
			if(a>1 && b <30) 
				return 1; 
			else return 0;
		};
		// use the method body
		logger.info("validateInput of 10,24 : " + funcInt2.validateInput(10, 24));

		// use the same lamda expression with functionalInterface 3
		FunctionalInterface3 funcInt3 = (a, b) -> { 
			if(a >1 && b < 30) 
				return 1; 
			else return 0;
		};
		logger.info("processInput of 10,24 : " + funcInt3.processInput(10, 24));

	}

	// method referencing
	public static void example5(){
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList("leo","bale","hanks"));
		// this is similar to the the lamda expression above, where pred now has the contains body
		Predicate<String> pred = set::contains;
		boolean exists = pred.test("leo");
		logger.info(""+exists);
	}


	// using Predicate functional interface defined in java.lang.function 
	public static void printPersonsWithPredicate(
			List<Integer> roster, Predicate<Integer> tester) {
		for (Integer p : roster) {
			if (tester.test(p)) {
				logger.info(""+p);
			}
		}
	}
}
