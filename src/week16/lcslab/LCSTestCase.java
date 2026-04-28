package week16.lcslab;
import java.util.*;
import java.io.PrintWriter;

// LCSTestCase represents a test for LCSMatrix class functionality. Expected
// vs. actual LCS strings sets are compared and optionally the numerical
// matrices as well.
public class LCSTestCase {
   private String string1 = "";
   private String string2 = "";
   private HashSet<String> expectedLCSSet = new HashSet<String>();
   private ArrayList<ArrayList<Integer>> expectedMatrix = new ArrayList<ArrayList<Integer>>();

   // Converts an LCSMatrix to a ArrayList of integer ArrayLists, representing the
   // numerical matrix. Uses an LCSMatrix's getEntry() method to retrieve
   // each entry.
   private ArrayList<ArrayList<Integer>> convertLCSMatrix(LCSMatrix userMatrix) {
      ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
      for (int rowIndex = 0; rowIndex < userMatrix.getRowCount(); rowIndex++) {
         ArrayList<Integer> row = new ArrayList<Integer>();
         for (int colIndex = 0; colIndex < userMatrix.getColumnCount(); colIndex++) {
            int value = userMatrix.getEntry(rowIndex, colIndex);
            row.add(value);
         }
         result.add(row);
      }
      return new ArrayList<ArrayList<Integer>>(result);
   }

   private void printMatrix(PrintWriter output, ArrayList<ArrayList<Integer>> matrix, String indent) {
      for (var row : matrix) {
         output.write(indent + "[");
         for (int value : row) {
            output.write(" " + value);
         }
         output.write(" ]\n");
      }
   }

   private void printStringSet(PrintWriter output, HashSet<String> stringSet) {
      printStringSet(output, stringSet, ", ");
   }

   private void printStringSet(PrintWriter output, HashSet<String> stringSet, String separator) {
      // Each string requires a preceding separator, except the first string.
      // So printedFirst indicates whether or not the first string has been
      // printed.
      boolean printedFirst = false;
      for (String str : stringSet) {
         if (printedFirst) {
            output.write(separator);
         }
         else {
            printedFirst = true;
         }
         output.write("\"" + str + "\"");
      }
   }

   // If true, executing the test case will only test the matrix entries and
   // not the longest common subsequences. If false, both the matrix and the
   // longest common subsequences functionality will be tested.
   public boolean matrixOnly;

   public LCSTestCase(String string1, String string2, HashSet<String> expectedLCSs) {
      this.expectedLCSSet = new HashSet<String>(expectedLCSs);
      this.string1 = string1;
      this.string2 = string2;
      matrixOnly = false;
   }

   public LCSTestCase(String string1, String string2, ArrayList<ArrayList<Integer>> expectedLCSMatrix,
         HashSet<String> expectedLCSs) {
      this(string1, string2, expectedLCSMatrix, expectedLCSs, false);
   }

   public LCSTestCase(String string1, String string2, ArrayList<ArrayList<Integer>> expectedLCSMatrix,
         HashSet<String> expectedLCSs, boolean testMatrixFunctionalityOnly) {
      this.expectedLCSSet = new HashSet<String>(expectedLCSs);
      this.expectedMatrix = new ArrayList<ArrayList<Integer>>(expectedLCSMatrix);
      this.string1 = string1;
      this.string2 = string2;
      this.matrixOnly = testMatrixFunctionalityOnly;
   }

   public boolean execute(PrintWriter output) {

      // Build the LCSMatrix
      LCSMatrix userMatrix = new LCSMatrix(string1, string2);

      // Get the set of longest common subsequences from the matrix
      HashSet<String> actual = userMatrix.getLongestCommonSubsequences();

      // If this test case includes an expected matrix, then build the
      // numerical matrix from userMatrix and compare the result against the
      // expected matrix
      ArrayList<ArrayList<Integer>> actualMatrix = new ArrayList<ArrayList<Integer>>();
      if (expectedMatrix.size() > 0) {
         // Build the numerical matrix from the user's LCSMatrix object
         actualMatrix = new ArrayList<ArrayList<Integer>>(convertLCSMatrix(userMatrix));

         // The test fails if the matrices are not equal
         if (!expectedMatrix.equals(actualMatrix)) {
            output.write("FAIL: \"" + string1 + "\" and \"" + string2 + "\"\n");

            // Show expected and actual numerical matrices
            output.write("  Expected matrix:\n");
            printMatrix(output, expectedMatrix, "    ");
            output.write("  Actual matrix:\n");
            printMatrix(output, actualMatrix, "    ");
            return false;
         }
      }

      // Compare the expected and actual LCS sets
      boolean pass = true;
      if (!matrixOnly) {
         if (!expectedLCSSet.equals(actual)) {
            pass = false;
         }
      }

      // Output results
      output.write((pass ? "PASS" : "FAIL"));
      output.write(": \"" + string1 + "\" and \"" + string2 + "\"");
      if (matrixOnly) {
         output.write(" (matrix generation only)\n");
      }
      else {
         output.write("\n");
      }
      if (expectedMatrix.size() > 0) {
         // Show expected and actual numerical matrices
         output.write("  Expected matrix:\n");
         printMatrix(output, expectedMatrix, "    ");
         output.write("  Actual matrix:\n");
         printMatrix(output, actualMatrix, "    ");
      }
      if (!matrixOnly) {
         output.write("  Expected LCS set:\n    {");
         printStringSet(output, expectedLCSSet);
         output.write("}\n");
         output.write("  Actual LCS set:\n    {");
         printStringSet(output, actual);
         output.write("}\n");
      }

      return pass;
   }
}
