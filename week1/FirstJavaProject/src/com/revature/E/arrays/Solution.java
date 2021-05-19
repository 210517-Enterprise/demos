package com.revature.E.arrays;

import java.util.Scanner;

public class Solution {
	
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		

        int[] myIntegers = getIntegers(5);
        int[] sorted = sortIntegersDesc(myIntegers);
        printArray(sorted);

	}

	public static int[] getIntegers(int capacity) {

		int[] array = new int[capacity];
		
        System.out.println("Enter " + capacity +" integer values:\r");
        
        for(int i=0; i<array.length; i++) {
            array[i] = scan.nextInt();
            
        }
        return array;

	}

	public static void printArray(int[] arr) {

		for (int n : arr) {
			System.out.println(n);
		}

	}

	public static int[] sortIntegersDesc(int[] array) {

		// Step 1. Copy all values of the array passed in, into the sorted array
		// ===== WAY # 1 (old way) ==========
		int[] sortedArray = new int[array.length];

		for (int i = 0; i < array.length; i++) {
			sortedArray[i] = array[i];
		}
		
		// ===== WAY # 2 (faster way) ==========
		//  int[] sortedArray = Arrays.copyOf(array, array.length);


		boolean flag = true;
		int temp;

		while (flag) {

			flag = false;
			for (int i = 0; i < sortedArray.length - 1; i++) {

				if (sortedArray[i] < sortedArray[i + 1]) {
					
					temp = sortedArray[i]; 				 // we save the lower value to put after
					
					sortedArray[i] = sortedArray[i + 1]; // here we switch the values
					
					sortedArray[i + 1] = temp; 			 // assign the second position to the lower value
					
					flag = true;
					
					// if there are no more high values, we break out of the loop
				}
			}
		}
		return sortedArray;
	}

}
