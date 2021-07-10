package com.lakkam.fp;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Courses {
	private int courseID;
	private String courseName;
	private int numberOfStudents;
	private int reviewScore;

	public Courses(int courseID, String courseName, int numberOfStudents, int reviewScore) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.numberOfStudents = numberOfStudents;
		this.reviewScore = reviewScore;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public int getReviewScore() {
		return reviewScore;
	}

	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}

	@Override
	public String toString() {
		return "Courses [courseID=" + courseID + ", courseName=" + courseName + ", numberOfStudents=" + numberOfStudents
				+ ", reviewScore=" + reviewScore + "]";
	}

}

/**
 * 
 * @author ranga This is basically for how stream methods and lambda expressions
 *         works on top of a List with custom class objects Methods that we are
 *         exploring is, noneMatch(), anyMatch(), allMatch()
 */
public class FPCustomClassStreamMethods {

	public static void main(String[] args) {

		List<Courses> courseList = List.of(new Courses(001, "Java", 1000, 4), new Courses(002, "spring", 2000, 5),
				new Courses(003, "MicroServices", 3000, 5), new Courses(004, "Cloud", 5000, 4));

		// Now, if we wanted to check how many courses with greater than score 4. Then
		// we need to write a predicate and execute stream operation on top of
		// courseList

		Predicate<Courses> courseScoreGreaterThan4Predicate = course -> course.getReviewScore() > 4;
		Predicate<Courses> numberOfStudentsLessThan1000Predicate = course -> course.getNumberOfStudents() <= 1000;

		// System.out.println("courseScoreGreaterThan4Predicate : anyMatch :
		// "+courseList.stream().anyMatch(courseScoreGreaterThan4Predicate));

		// Getting the course that has review score greater than 4
		// courseList.stream().filter(courseScoreGreaterThan4Predicate).forEach(System.out::println);

		/**
		 * Probable usecase for anyMatch
		 */
		if (courseList.stream().anyMatch(courseScoreGreaterThan4Predicate)) {
			courseList.stream().filter(courseScoreGreaterThan4Predicate).forEach(System.out::println);
		}

		/**
		 * The below will not return anything. becasue there are no courses with less
		 * than 1000 students. It should be allMatch
		 */
		if (courseList.stream().allMatch(numberOfStudentsLessThan1000Predicate)) {
			courseList.stream().filter(numberOfStudentsLessThan1000Predicate).forEach(System.out::println);
		}

		/**
		 * Lets test Comparator and apply that to Courses class object through
		 * functional programming and sort course objects
		 */

		Comparator<Courses> compareWithReviewScore = Comparator.comparingInt(Courses::getReviewScore).reversed();

		System.out.println("Course List sorting in reviewscore..");
		courseList.stream().sorted(compareWithReviewScore).collect(Collectors.toList()).forEach(System.out::println);

		System.out.println("course List based on noOfStudents order....");
		Comparator<Courses> comparatorWithNumberOfStudents = Comparator.comparingInt(Courses::getNumberOfStudents);
		courseList.stream().sorted(comparatorWithNumberOfStudents).forEach(System.out::println);

		/**
		 * Along with above, we have some other utility methods in strems like skip(5) -
		 * This skips first 5 results in the resulting lambda limit(4) - This will limit
		 * the results to 4 from the lambda takeWhile(predicate) - This will check for
		 * that predecate condition and when the condition matches, it will results the
		 * next subsequent set dropWhile(predecate) - When predecate condition matches,
		 * it will stop returning the results
		 */

		/**
		 * Lets test some more utility methods of stream...like min, max, top, findFirst
		 * and findAny findAny and findFirst works on top of filter results that takes a
		 * predecate...
		 */

		System.out.println("Getting minimum number of students course details from list.....>>>");
		courseList.stream().min(Comparator.comparingInt(Courses::getNumberOfStudents)).stream()
				.forEach(System.out::println);
		courseList.stream().max(Comparator.comparingInt(Courses::getReviewScore)).stream().forEach(System.out::println);

		System.out.println("testing findAny from course contains Java....>>>");
		courseList.stream().filter(course -> course.getCourseName().contains("Java")).findAny().stream()
				.forEach(System.out::println);

		/**
		 * Lets explore some more methods like sum, avg,count on top of filtered
		 * results...
		 */
		System.out.println("Count of Number of courses where number of students>2000.... "
				+ courseList.stream().filter(course -> course.getNumberOfStudents() > 2000).count());

		// Here we are combining filter with predecate and function with map and getting
		// sum out of it...
		// we can declare our predecates and map actions seperately and use them here as
		// well
		System.out.println("Sum of Number of students for whcih courses where number of students>2000.... "
				+ courseList.stream().filter(course -> course.getNumberOfStudents() > 2000)
						.mapToInt(Courses::getNumberOfStudents).sum());

		/**
		 * Exploring groupingBy utility in streams...
		 */

		//The below will give course details group by review score..
		courseList.stream().collect(Collectors.groupingBy(Courses::getReviewScore)).entrySet()
		.forEach(System.out::println);
		
		//The below will give count of course group by review score...
		courseList.stream().collect(Collectors.groupingBy(Courses::getReviewScore, Collectors.counting())).entrySet()
				.forEach(System.out::println);
		;
	}

}
