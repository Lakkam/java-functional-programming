package com.lakkam.fp;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/*
 * This is to explore how functional interface is designed and implemented in Java
 */
public class FuntionalInterfaceP1 {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(2, 3, 4, 5);
		numbers.stream().forEach(System.out::println);
		Function<Integer, Integer> mapper = num -> num * num;
		// How the above statement really works in Functional Interface concept
		/*
		 * So, FI is an interface which has only one abstract method and instead of
		 * giving an implementation to it, Java introduced a declarative way like
		 * num->num*num. The below implementation shows, how it internally converts the
		 * code.
		 */
		Function<Integer, Integer> mapper2 = new Function<Integer, Integer>() {

			@Override
			public Integer apply(Integer t) {
				// TODO Auto-generated method stub
				return t * t;
			}
		};

		numbers.stream().map(mapper2).sorted().forEach(System.out::println);
		
		BinaryOperator<Integer> accumulator = Integer::sum;
		BinaryOperator<Integer> accumulator2 = new BinaryOperator<Integer>() {
			
			@Override
			public Integer apply(Integer t, Integer u) {
				// TODO Auto-generated method stub
				return t+u;
			}
		};
		Integer sumOfNumbers = numbers.stream().reduce(0, accumulator2);
		System.out.println("Sum : "+sumOfNumbers);

	}

}
