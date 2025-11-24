package org.example;

public class MovieIDValidator {
    // Extract all capital letters from the title
    public static String extractCapitalLetters(String title) {
        StringBuilder sb = new StringBuilder();

        for (char ch : title.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    // Validate the movie ID and return:
    // null → valid
    // error message → invalid
    public static String validate(String title, String movieId) {

        // Extract capital letters from title
        String expectedPrefix = extractCapitalLetters(title);

        // Rule 1: Check prefix
        if (!movieId.startsWith(expectedPrefix)) {
            return "ERROR: Movie Id letters " + movieId + " are wrong";
        }

        // After prefix, the remaining part must be exactly 3 digits
        String suffix = movieId.substring(expectedPrefix.length());

        // Rule 2: Check suffix length = exactly 3 digits
        if (suffix.length() != 3 || !suffix.matches("\\d{3}")) {
            return "ERROR: Movie Id letters " + movieId + " are wrong";
        }

        // Rule 3: Check uniqueness of digits
        if (suffix.charAt(0) == suffix.charAt(1) ||
                suffix.charAt(0) == suffix.charAt(2) ||
                suffix.charAt(1) == suffix.charAt(2)) {

            return "ERROR: Movie Id numbers " + movieId + " aren't unique";
        }

        // If everything is fine → valid
        return null;
    }
}
