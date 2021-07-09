package com.lakkam.fp;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * @author ranga Behaviour Parametrization in FP is nothing but, passing method
 *         logic as a reference variable to other method. Its nothing but,
 *         passing a predicate as method variable
 * 
 *         Overall we can pass predicated and mappers as parameters in
 *         functional programming and have simpler logic
 */
public class BehaviousParameterizationP2 {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(2, 3, 4, 5);
		printEvenNumbers(numbers);
		printOddNumbers(numbers);

		// Approach 2 - by passing logic of the even and odd filter as predicate
		printNumbers(numbers, num -> num % 2 == 0);
		printNumbers(numbers, num -> num % 2 != 0);

		Function<? super Integer, ? extends Integer> mapper = num -> num * num;
		printSquareOfNumbers(numbers, mapper);

	}

	@SuppressWarnings("unchecked")
	private static void printSquareOfNumbers(List<Integer> numbers, Function mapper) {
		System.out.println("Printing Square numbers...");
		numbers.stream().map(mapper).forEach(System.out::println);

	}

	private static void printNumbers(List<Integer> numbers, Predicate<Integer> predicate) {
		System.out.println("Printing through behaviour parametrization....");
		numbers.stream().filter(predicate).forEach(System.out::println);

	}

	private static void printOddNumbers(List<Integer> numbers) {
		numbers.stream().filter(num -> num % 2 != 0).forEach(System.out::println);

	}

	private static void printEvenNumbers(List<Integer> numbers) {
		numbers.stream().filter(num -> num % 2 == 0).forEach(System.out::println);

	}

}
