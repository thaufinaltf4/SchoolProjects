import java.util.Arrays;

/**
 * Implement each of the 10 methods tested in JUnitTests.java. Study the tests
 * to determine how the methods should work.
 */
public class Java1Review {

	public static void main(String[] args) {
}
	// Test 1 - Floating Point Division
	public static double divide (double x, double y) {
		return x / y;
	}


	// Test 2 - Integer Division
	public static int divide (int x, int y) {
		if (y == 0) {
			throw new ArithmeticException ("Cannot divide by zero");
		}
		return x / y;
	}
	
	
	// Test 3 - Division by 7
	public static boolean isDivisibleBy7 (int x) {
		return x%7 == 0;
	}
	
	
	// Test 4 - Overloading the main Method
	public static String main(String tag) {
		return "Overloaded main method was passed \"" + tag + "\".";
	}

	
	// Test 5 - Returning the Lowest Integer of a Triplet
	public static int findMin(int x, int y, int z) {
		return Math.min(Math.min(x, y), z);
	}
	
	
	// Test 6 - Returning the Lowest Integer in an Array
	public static int findMin(int[] array) {
		Arrays.sort(array);
		return array[0];
	}
	
	
	// Test 7 - Return the Average of Integers in an Array
	public static double average(int[] array) {
		int tracer = 0;
		for (int i = 0; i < array.length; i++) tracer += array[i];
		return tracer / (double)array.length; // I used AI to find an error here in which I did not cast this as a double
	}
	
	
	// Test 8 - Converting Strings in an Array to Match Lower Case
	public static void toLowerCase(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].toLowerCase();
		}
	}
	
	
	// Test 9 - Return a Copy of a Given String in Lower Case
	public static String[] toLowerCaseCopy(String[] strings) {
		String[] lowercopy = new String[strings.length];
		for (int i = 0; i < strings.length; i++) {
			lowercopy[i] = strings[i].toLowerCase();
		}
		return lowercopy;
	}
	
	
	// Test 10 - Replace Duplicates in an Array with 0
	public static void removeDuplicates(int[] array) {
		for (int x = 0; x < array.length; x++) {
			boolean flag = false;
				for (int y = 0; y < array.length; y++) {
					if (array[x] == array[y] && x != y) {
						array[y] = 0;
						flag = true;
					}
				}
				if(flag == true)
					array[x] = 0;
			}
		}
	
	
}
			
		
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	