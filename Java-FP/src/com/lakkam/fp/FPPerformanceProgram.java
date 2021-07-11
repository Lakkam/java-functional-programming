package com.lakkam.fp;

import java.util.List;
import java.util.stream.LongStream;

/**
 * 
 * @author ranga This is to verify how and why FP is easy to write and achive
 *         better performance in java programming than traditional approach.
 */
public class FPPerformanceProgram {

	public static void main(String[] args) {

		/**
		 * Lets say, we have list of course, and if want to retrieve first course whcih
		 * length has greater than 4 and convert the course name to upper case.
		 * 
		 * In this scenario, we have sequence of steps involved like filter, map
		 * contains upper case logic and treturn the first course that has length>4
		 */

		List<String> courseList = List.of("Java", "Microservices", "SpringBoot", "AWS", "Hibernate");

		// The below statement return the value that we want
		System.out.println(
				courseList.stream().filter(course -> course.length() > 4).map(String::toUpperCase).findFirst());

		// Lets see what exactly happening while executing the above statement

		// Result for above statement is Java Microservices MICROSERVICES
		// So, it is just executing till it finds the course that has length>4 and it is
		// not even looking further in the list.
		// All these, peek,filter and map are intermediate operations and very lazy.
		// Till it finds terminal operation like findFirst()
		// these intermediate operations wont return anything and wont execute.
		// Thsi is the best way to achive performance in Java...

		courseList.stream().peek(System.out::println).filter(course -> course.length() > 4).map(String::toUpperCase)
				.peek(System.out::println).findFirst();
		
		
		/**
		 * Parallel streams execution to achive more performance in FP.
		 * 
		 */
		long timer = System.currentTimeMillis();
		System.out.println("sum of 1-1000000 range values : "+LongStream.range(1, 100000000).sum());
		System.out.println("Time it took to sum 1-100000 without Parallel streaming: "+(System.currentTimeMillis()-timer)); //54 ms
		
		long timer2 = System.currentTimeMillis();
		System.out.println("sum of 1-1000000 range values : "+LongStream.range(1, 100000000).parallel().sum());
		System.out.println("Time it took to sum 1-100000 with Parallel streaming: "+(System.currentTimeMillis()-timer2)); //22 ms

	}

}
