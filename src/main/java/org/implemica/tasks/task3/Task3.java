package org.implemica.tasks.task3;

import java.math.BigInteger;
import java.util.Arrays;

public class Task3 {

    public static void main(String[] args) {
        Long sum = getFactorialSum(100);
        System.out.println(sum);
    }

    // This method used for find sum of factorial digits.
    public static Long getFactorialSum(long digit) {
        // Digit can be only more than zero value
        if (digit < 1) {
            return -1L;
        }
        // Using method for find factorial
        BigInteger factorial = findFactorial(digit);
        // Doing split factorial 123 -> [1,2,3]
        String[] digits = factorial.toString().split("");
        Long sum = Arrays.stream(digits) // Getting array like stream
                .map(Long::valueOf) // Converting String value to Long value
                .reduce(Long::sum).get(); // Calculating sum of digits and getting result
        return sum;
    }

    // This method used for find factorial of some digit
    private static BigInteger findFactorial(long digit) {
        BigInteger result = BigInteger.ONE; // start value is 1
        for (long i = 2; i <= digit; i++) { // start value is 2
            // 1) 1 * 2 = 2
            // 2) 2 * 3 = 6
            // 3) ...
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

}
