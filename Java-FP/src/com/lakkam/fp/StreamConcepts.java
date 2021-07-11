package com.lakkam.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 
 * @author ranga This is for various Stream concepts like IntStream etc
 *
 */
public class StreamConcepts {

	public static void main(String[] args) {

		// Approach 1 of declaring a stream of objects
		Stream<Integer> intStream = Stream.of(1, 2, 3, 4);
		// intStream.forEach(System.out::println);

		/*
		 * Approach 2 of declaring a stream objects with range of elements from 1-10
		 * where last range element is not considered while printing or for any //
		 * operation like sum, average, etc.
		 * 
		 * We have IntStream, DoubleStream, etc to handle primitive values inside
		 * streams...
		 */

		IntStream.range(1, 10).forEach(e -> System.out.println("IntStream values from 1-10 range: " + e)); // This will
																											// print
																											// numbers
																											// from 1-9

		// Sometimes,we really want to create Streams dynamically instead of sequential
		// int values as above. We can use iterate method to to that.
		IntStream.iterate(1, e -> e * 2).limit(10).forEach(System.out::println);// Priting square numbers

		IntStream.iterate(2, e -> e + 2).limit(10).peek(System.out::println);// Printing even numbers

		/*
		 * Convert these primitive streams to List For that we need to do a boxing
		 * operation on top of stream
		 */

		System.out.println("Converting IntStream to List...");
		IntStream.range(1, 10).limit(5).boxed().collect(Collectors.toList()).forEach(System.out::println);

		/*
		 * Lets explore some concepts on Strings...How stream works on strings and its
		 * related operations
		 */

		// Stream courseStream = Stream.of("Spring", "API", "AWS", "Java", "JavaFP");
		List<String> courses = List.of("Spring", "API", "AWS", "Java", "JavaFP");
		List<String> courses2 = List.of("Spring", "API", "AWS", "Java", "JavaFP");
		// If we want to join these values

		System.out.println(courses.stream().collect(Collectors.joining()));

		/*
		 * Lets say, if i want tp split each string in stream seprated by comma..
		 */
		System.out.println("String opeartaion within Streams....");
		// The below one prints all stream Objects that executed on top of split
		// function. So, we need to flatMap it to extract the actual result
		System.out.println(courses.stream().map(course -> course.toString().split(",")).collect(Collectors.toList()));
		System.out.println(
				courses.stream().map(course -> course.split("")).flatMap(Arrays::stream).collect(Collectors.toList()));

		System.out.println("Tuples strings : "
				+ courses.stream().flatMap(course -> courses2.stream().map(course2 -> List.of(course, course2)))
						.collect(Collectors.toList()));

		/**
		 * Higher Order Functions... Higher Order function is a function that returns a
		 * function... In simpler terms, a method that returns a Predicate which
		 * contains logic. So, here we are using method logic as a normal data and
		 * returning it from an another method..
		 */

		List<Courses> courseList = List.of(new Courses(1, "Java", 5000, 5), new Courses(2, "AWS", 4000, 4));
		// If we want to get courses whcih has number of students greater than 4000,
		// then we need to write a predicate for that first
		int numberOfStudentsThreshhold = 4000;
		Predicate<Courses> numberOfStudentsPredicate = numberOfStudentsPredecateMethod(numberOfStudentsThreshhold);

		System.out.println("Courses that has score>4000 : "
				+ courseList.stream().filter(numberOfStudentsPredicate).collect(Collectors.toList()));

	}

	/*
	 * Here this is a higher order function that is returning a method logic as
	 * return type whcih is a predicate. So, we can use the functions as first class
	 * citizens like regular variables in FP. This enables the readability and
	 * maintainability of the Java code much more elegant.
	 */
	private static Predicate<Courses> numberOfStudentsPredecateMethod(int numberOfStudentsThreshhold) {
		return course -> course.getNumberOfStudents() > numberOfStudentsThreshhold;
	}

}
