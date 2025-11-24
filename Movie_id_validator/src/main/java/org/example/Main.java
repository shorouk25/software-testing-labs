package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


                // Test Case 1 (Valid ID)
                test("The Dark Knight", "TDK583");

                // Test Case 2 (Wrong prefix)
                test("Spider Man", "SP123");

                // Test Case 3 (Correct prefix but wrong digits count)
                test("Avatar", "A12");

                // Test Case 4 (Digits are not unique)
                test("Spider Man", "SM112");

                // Test Case 5 (Invalid letters in digits)
                test("Avatar", "A12A");

                // Test Case 6 (Another valid example)
                test("Harry Potter", "HP379");

                // Test Case 7 (Extra prefix letter)
                test("Spider Man", "SMX123");
            }

            // Helper method to test easily
            private static void test(String title, String movieId) {
                String result = MovieIDValidator.validate(title, movieId);

                System.out.println("Testing:");
                System.out.println("Title: " + title);
                System.out.println("Movie ID: " + movieId);

                if (result == null) {
                    System.out.println("Result: VALID ✔");
                } else {
                    System.out.println("Result: " + result);
                }

                System.out.println("-----------------------------------");
            }

}