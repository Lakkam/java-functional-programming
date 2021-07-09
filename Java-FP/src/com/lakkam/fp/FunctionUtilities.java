package com.lakkam.fp;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionUtilities {

	public static void main(String[] args) {

		// This is a predicate where we use when we want to do a condition check on
		// elements
		Predicate<Integer> predicate = num -> num % 2 == 0;

		// This is a Function where we use when we want to do a logical function
		// execution
		Function<Integer, Integer> function = num -> num * num;

		// This is a Consumer where it executes but not return anything.
		Consumer<Integer> consumer = System.out::println;

		// This is BinaryOperator where it take 2 elements and operate on top of them.
		// And return the result
		BinaryOperator<Integer> binaryResult = (x, y) -> x + y;

		// This is a Supplier where it will not take anything, but return some random
		// values. In this case, we have to use empty paranthesis
		Supplier<Integer> supplier = () -> 2;
		System.out.println(supplier.get());

		// This is another example of Supplier where we wanted to execute multiple
		// statements in lambda expression. So, we have to use curly braces with semi
		// colon end like we decalre ananymous inner class
		Supplier<String> stringSupplier = () -> {
			return "Lakkam";
		};
		System.out.println(stringSupplier.get());

		/*
		 * Along with regular Predicate, Function, Consumer. we have BiPredicate,
		 * BiFunction and BiConsumer which takes two different parameters and perform
		 * operations
		 */

		BiPredicate<Integer, String> biPredicateDeclaration = (num, str) -> {
			return num > 10 && str.length() < 10;
		};
		System.out.println("Testing BiPredicate... : " + biPredicateDeclaration.test(12, "Lakkam"));

		BiFunction<Integer, String, String> biFunctionDeclaration = (num, str) -> {

			return num.toString().concat(str);
		};
		System.out.println("Testing BiFunction... : " + biFunctionDeclaration.apply(2, "swamy"));

		BiConsumer<Integer, Integer> biConsumerDeclaration = (x, y) -> {
			System.out.println(x);
			System.out.println(y);
		};
		biConsumerDeclaration.accept(2, 3);
		
		/**
		 * We have functional interface to handle primitive data elements as well
		 * like IntPredecate LongPredecate
		 */
		
		IntPredicate intPredecate = num->num%2==0;
		System.out.println(intPredecate.test(2));
		
		List<String> courses = List.of("java","python","spring");
		testMethodReference(courses);

	}

	private static void testMethodReference(List<String> courses) {
		
		/*
		 * Approach 1 of using method reference
		 */
		System.out.println("Printing all courses in Uppercase...Approach1 of method reference");
		courses.stream().map(str->str.toUpperCase()).forEach(System.out::println);
		
		/*
		 * Approach 2 of using method reference
		 */
		System.out.println("Printing all courses in Upper case...Approach 2 of method reference");
		courses.stream().map(String::toUpperCase).forEach(System.out::println);;
		
		/*
		 * Approach 3 of using method reference
		 */
		System.out.println("Method reference Approach 3 :");
		Supplier<String> supplier = ()->new String();
		//The above can be declared as
		Supplier<String> supplier2 = String::new;
		
	}

}
