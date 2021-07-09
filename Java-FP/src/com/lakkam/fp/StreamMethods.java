package com.lakkam.fp;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamMethods {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(12, 2, 3, 4, 4, 7, 5);
		List<String> courses = List.of("Java", "Python", "Spring", "Hadoop");
		int sum = addListOfNumbers(numbers);
		sumOfSquaresfromList(numbers);
		System.out.println(sum);

		// Everything in single statement
		System.out.println(numbers.stream().reduce(0, Integer::sum));

		// This will give distinct elements from list
		numbers.stream().distinct().forEach(System.out::println);

		// This will give sorted order of elements - Only distinct
		numbers.stream().distinct().sorted().forEach(System.out::println);

		// Comparator test
		System.out.println("Natural Order of courses : ");
		courses.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
		System.out.println("Reverse Order of courses : ");
		courses.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("Custom ordering condition.. : ");
		courses.stream().sorted(Comparator.comparing(x -> x.length())).forEach(System.out::println);

		/*
		 * Basically we need to collect only even numbers from the list 1.Use filter to
		 * check the even number condition 2.Use collect method and Collectors.toList()
		 * to collect the elements to new list 3.perform stream and sorted and forEach
		 * element printing on top of the newly created list
		 */
		System.out.println("Created List with only even numbers from original list : ");
		numbers.stream().filter(number -> number % 2 == 0).collect(Collectors.toList()).stream().sorted()
				.forEach(System.out::println);
		
		/*
		 * Collect the list with length of all course titles
		 */
		System.out.println("Print list of course titles lengths... : ");
		courses.stream().map(str->str.length()).collect(Collectors.toList()).stream().forEach(System.out::println);;
	}

	private static void sumOfSquaresfromList(List<Integer> numbers) {

		// Whenever we want to modify the values, then use map
		int sum = numbers.stream().map(number -> number * number).reduce(0, Integer::sum);
		System.out.println("Sum of Squres : " + sum);

		// Whenever we want to verify the conditions and do the operation, then use
		// filters
		int sumOfOddNumbers = numbers.stream().filter(number -> number % 2 != 0).reduce(0, Integer::sum);
		System.out.println("Sum of Odd Numbers : " + sumOfOddNumbers);

	}

	/*
	 * In functional programming terminalogy, we call addition as reduce to single
	 * number. Thats why we have a method called reduce to execute sum of list of
	 * elements whcih will take initial value as 0.
	 */
	private static int addListOfNumbers(List<Integer> numbers) {
		/*
		 * Approach 1: Use reduce method and make a call to sum method explicitly
		 */
		// return numbers.stream().reduce(0, StreamReduce::sum);

		/*
		 * Approach 2 : Replace explicit call with Lambda expression
		 */
		// return numbers.stream().reduce(0, (x,y)->x+y);

		/*
		 * Approach 3: Replace Lambda expression with Integer sum methods
		 *
		 */

		return numbers.stream().reduce(0, Integer::sum);
	}

	private static int sum(int a, int b) {
		return a + b;
	}

}
