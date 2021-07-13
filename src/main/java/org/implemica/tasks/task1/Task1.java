package org.implemica.tasks.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        // Creating keyboard input
        System.out.println("Enter N value:");
        Scanner scanner = new Scanner((System.in));
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();

            // Getting results and print
            List<String> parenthesisResultList = getParenthesis(n);
            for (String parenthesisResult : parenthesisResultList) {
                System.out.println(parenthesisResult);
            }
        }
    }


    public static List<String> getParenthesis(int n) {
        char[] parenthesis = new char[n * 2];
        List<String> parenthesisResultList = new ArrayList<>();
        // N non-negative integer
        if (n > 0) {
            generateParentheses(parenthesisResultList, parenthesis, 0, n, 0, 0);
        }
        return parenthesisResultList;
    }

    // The recursive function that accepts an array, position at array,
    // count of opening brackets and count of closing brackets and the value of n
    private static void generateParentheses(List<String> parenthesisResultList, char[] parenthesis, int pos, int n, int open, int close) {
        // If the value of the opening parenthesis and closing parenthesis is n
        if (close == n) {
            // Add to the result list the possible combinations
            parenthesisResultList.add(new String(parenthesis));
        } else {
            // If the number of opening parentheses is less than n value,
            // the function is called recursively with the following
            // parameters: arr[pos]='(', position in the array +1, number of
            // open parentheses +1, number of closing parentheses and value n value.
            if (open < n) {
                parenthesis[pos] = '(';
                generateParentheses(parenthesisResultList, parenthesis, pos + 1, n, open + 1, close);
            }
            // If the number of open parentheses is greater than the number of
            // closing parentheses, then the function is called recursively with the
            // following parameters: arr[pos]=')', position in the array +1, number
            // of open parentheses, number of closing parentheses +1 and n value
            if (open > close) {
                parenthesis[pos] = ')';
                generateParentheses(parenthesisResultList, parenthesis, pos + 1, n, open, close + 1);
            }
        }
    }

}