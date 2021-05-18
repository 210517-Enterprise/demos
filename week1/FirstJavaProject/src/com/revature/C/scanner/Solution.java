package com.revature.C.scanner;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Please enter the maximum grade amount:");
		double maxPoints = scan.nextDouble();

		// BONUS: To format it just cast to (int).
		// int cleanMax = (int) maxScore;

		/*
		 * OR you can use something called DecimalFormat This is actually a String
		 */
		DecimalFormat cleanDub = new DecimalFormat("0.#");

		System.out.println("Please enter the student's number grade out of " + cleanDub.format(maxPoints));
		double score = scan.nextDouble();

		// This is why we need doubles...because if I had used ints, then this would
		// floor to 0 and multiply by 100 every time.
//		double percent = (score / maxScore) * 100; // we have to change this to double
		double percent = calculatePercent(score, maxPoints);

		// BONUS: To format it just cast to (int).
		int cleanPercent = (int) percent;

		// Or you could do this:
		// String cleanPercent = cleanDub.format(percent);
		/*
		 * https://stackoverflow.com/questions/14204905/java-how-to-remove-trailing-
		 * zeros-from-a-double
		 */

		String letterGrade;

		if (percent >= 90) {
			letterGrade = "A";
		} else if (percent >= 80) {
			letterGrade = "B";
		} else if (percent >= 70) {
			letterGrade = "C";
		} else if (percent >= 60) {
			letterGrade = "D";
		} else {
			letterGrade = "F";
		}

		System.out.println("The student's letter grade is " + letterGrade + ". That's " + cleanPercent + "%!");

	}

	private static double calculatePercent(double score, double maxPoints) {

		return (score / maxPoints) * 100;
	}

}
