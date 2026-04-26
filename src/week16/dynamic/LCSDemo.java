package week16.dynamic;

public class LCSDemo {
    static String longestCommonSubstring(String str1, String str2) {
        // Allocate the matrix
        int[][] matrix = new int[str1.length()][str2.length()];

        // Variables to remember the largest matrix element's value and row index
        int maxValue = 0;
        int maxValueRow = -1;

        for (int row = 0; row < str1.length(); row++) {
            for (int col = 0; col < str2.length(); col++) {
                // Check if the characters match
                if (str1.charAt(row) == str2.charAt(col)) {
                    // Get the value in the cell that's up and to the
                    // left, or 0 if no such cell
                    int upLeft = 0;
                    if (row > 0 && col > 0) {
                        upLeft = matrix[row - 1][col - 1];
                    }

                    // Set the value for this cell
                    matrix[row][col] = 1 + upLeft;
                    if (matrix[row][col] > maxValue) {
                        maxValue = matrix[row][col];
                        maxValueRow = row;
                    }
                }
                else {
                    matrix[row][col] = 0;
                }
            }
        }

        // Return the longest common substring
        int startIndex = maxValueRow - maxValue + 1;
        return str1.substring(startIndex, startIndex + maxValue);
    }

    static String longestCommonSubstringOptimized(String str1, String str2) {
        // Create one row of the matrix
        int[] matrixRow = new int[str2.length()];

        // Variables to remember the largest matrix element's value and row index
        int maxValue = 0;
        int maxValueRow = -1;

        for (int row = 0; row < str1.length(); row++) {
            // Variable to hold the upper-left value from the
            // current matrix position
            int upLeft = 0;
            for (int col = 0; col < str2.length(); col++) {
                // Save the current cell's value; this will be upLeft
                // for the next iteration
                int savedCurrent = matrixRow[col];

                // Check if the characters match
                if (str1.charAt(row) == str2.charAt(col)) {
                    matrixRow[col] = 1 + upLeft;

                    // Update the saved maximum value and row, if appropriate
                    if (matrixRow[col] > maxValue) {
                        maxValue = matrixRow[col];
                        maxValueRow = row;
                    }
                }
                else {
                    matrixRow[col] = 0;
                }

                // Update the upLeft variable
                upLeft = savedCurrent;
            }
        }

        // The longest common substring is the substring in str1 from index
        // maxValueRow - maxValue + 1, up to and including maxValueRow
        int startIndex = maxValueRow - maxValue + 1;
        return str1.substring(startIndex, maxValueRow + 1);
    }

    public static void main(String[] args) {
        String firstString = "dynamic";
        String secondString = "programming";

        System.out.printf("Input strings \"%s\" and \"%s\"%n", firstString, secondString);

        String unoptimized = longestCommonSubstring(firstString, secondString);
        String optimized = longestCommonSubstringOptimized(firstString, secondString);
        System.out.println();
        System.out.println("Unoptimized solution: " + unoptimized);
        System.out.println("Optimized solution: " + optimized);
    }
}
