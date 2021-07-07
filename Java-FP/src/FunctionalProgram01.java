import java.util.List;

public class FunctionalProgram01 {

	public static void main(String args[]) {

		List<Integer> numbers = List.of(12, 3, 4, 59, 9);
		printSquareOfNumbers(numbers);
		// printNumbers(numbers);
		// Lets say, if we want to print only Even numbers from list
		// printOnlyEvenNumbers(numbers);
		// printOnlyOddNumbers(numbers);
		List<String> courses = List.of("Java", "Spring", "Servers", "SpringSecurity");
		// printAllCourses(courses);
		//printSpringCourses(courses);

	}

	private static void printSquareOfNumbers(List<Integer> numbers) {
		//If we wanted to do some manupulation on the items, then
		//we use something called mappers as below in functional programming
		numbers.stream().map(number -> number * number).forEach(System.out::println);

	}

	private static void printSpringCourses(List<String> courses) {
		courses.stream().filter(course -> course.contains("Spring")).forEach(System.out::println);

	}

	private static void printAllCourses(List<String> courses) {
		courses.stream().forEach(System.out::println);

	}

	private static void printOnlyOddNumbers(List<Integer> numbers) {
		numbers.stream().filter(number -> number % 2 != 0).forEach(System.out::println);

	}

	private static boolean isEven(int number) {
		return number % 2 == 0;
	}

	private static void printOnlyEvenNumbers(List<Integer> numbers) {

		/*
		 * Approach 1 Here we are using concept called filter to define any if condition
		 * on the numbers before we perform any action on that like printing etc.
		 */

		numbers.stream().filter(FunctionalProgram01::isEven).forEach(System.out::println);

		/*
		 * Approach 2 Here we can use Lamda Expression to execute the filter function
		 * logic instead of making a call to seperate method
		 */

		numbers.stream().filter(number -> number % 2 == 0).forEach(System.out::println);

	}

	private static void print(int number) {
		System.out.println(number);
	}

	private static void printNumbers(List<Integer> numbers) {
		// In Funtional Programming, we need to think of what to do with the numbers
		// Take that into a stream and then apply the functionality
		// When we say streams, we will have to decide on for each stream element what
		// we need to
		// Here, for each number, i want to print it

		/*
		 * Approach 1
		 */
		numbers.stream().forEach(FunctionalProgram01::print); // This is called Method Reference

		/*
		 * Approach 2 We can directly make a call to the print logic inside forEach In
		 * this case, we dont need seperate print method
		 */
		numbers.stream().forEach(System.out::println);

	}

}
