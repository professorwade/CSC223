package week16.lcslab;
import java.util.*;
import java.io.PrintWriter;

public class Main {
   public static void main(String[] args) {
      ArrayList<LCSTestCase> testCases = new ArrayList<LCSTestCase>() {
         {
            add(new LCSTestCase("ALASKAN", "BANANAS", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // B  A  N  A  N  A  S
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 1, 1)), // A
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 1, 1, 1)), // L
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 2, 2, 2)), // A
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 2, 2, 3)), // S
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 2, 2, 3)), // K
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 2, 2, 3, 3)), // A
               new ArrayList<Integer>(Arrays.asList(0, 1, 2, 2, 3, 3, 3)))), // N
               new HashSet<String>(Arrays.asList("AAA", "AAS", "AAN"))));

            add(new LCSTestCase("BAAB", "ABBA", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // A  B  B  A
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1)), // B
               new ArrayList<Integer>(Arrays.asList(1, 1, 1, 2)), // A
               new ArrayList<Integer>(Arrays.asList(1, 1, 1, 2)), // A
               new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2)))), // B
               new HashSet<String>(Arrays.asList("AA", "BB", "AB", "BA"))));

            add(new LCSTestCase("ABBA", "BAAB", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // B  A  A  B
               new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1)), // A
               new ArrayList<Integer>(Arrays.asList(1, 1, 1, 2)), // B
               new ArrayList<Integer>(Arrays.asList(1, 1, 1, 2)), // B
               new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2)))), // A
               new HashSet<String>(Arrays.asList("AA", "BB", "AB", "BA"))));

            add(new LCSTestCase("lower case", "UPPER CASE", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // U  P  P  E  R     C  A  S  E
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), // l
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), // o
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), // w
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), // e
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)), // r
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1)), //
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1)), // c
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1)), // a
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1)), // s
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1)))), // e
               new HashSet<String>(Arrays.asList(" "))));

            add(new LCSTestCase("PROGRAMMING", "PROBLEM", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // P  R  O  B  L  E  M
               new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 1, 1)), // P
               new ArrayList<Integer>(Arrays.asList(1, 2, 2, 2, 2, 2, 2)), // R
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 3)), // O
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 3)), // G
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 3)), // R
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 3)), // A
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 4)), // M
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 4)), // M
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 4)), // I
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 4)), // N
               new ArrayList<Integer>(Arrays.asList(1, 2, 3, 3, 3, 3, 4)))), // G
               new HashSet<String>(Arrays.asList("PROM"))));

            add(new LCSTestCase("LOOK", "ZYBOOKS", new ArrayList<ArrayList<Integer>>(Arrays.asList(
                                                 // Z  Y  B  O  O  K  S
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0)),   // L
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 1, 1, 1)),   // O
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 2, 2, 2)),   // O
               new ArrayList<Integer>(Arrays.asList(0, 0, 0, 1, 2, 3, 3)))), // K
               new HashSet<String>(Arrays.asList("OOK")), true));

            add(new LCSTestCase("ZYBOOKS", "LOOK", new HashSet<String>(Arrays.asList("OOK"))));

            add(new LCSTestCase("A_B_C", "X_Y_Z", new HashSet<String>(Arrays.asList("__"))));

            add(new LCSTestCase("ABCEDEFGHIJKL", "MNOPQRSTUVWXYZ", new HashSet<String>(Arrays.asList())));

            add(new LCSTestCase("DATA STRUCTURES", "ALGORITHMS", new HashSet<String>(Arrays.asList("ARTS"))));

            add(new LCSTestCase("", "", new HashSet<String>(Arrays.asList())));

            add(new LCSTestCase("RELATIVELY", "ACTIVE", new HashSet<String>(Arrays.asList("ATIVE"))));

            add(new LCSTestCase("ACTIVE", "RELATIVELY", new HashSet<String>(Arrays.asList("ATIVE"))));

            add(new LCSTestCase("very very very very very very very very very long string", "short string",
                  new HashSet<String>(Arrays.asList("o string", "r string"))));

            add(new LCSTestCase("five food groups", "dairy, vegetables, fruits, grains, and protein",
                  new HashSet<String>(Arrays.asList("ive f grp", "ive fd ro", "ive f gro", "ive f grs"))));

            add(new LCSTestCase("A MAN A PLAN A CANAL PANAMA", "THE RAIN IN SPAIN STAYS MAINLY IN THE PLAIN",
                  new HashSet<String>(Arrays.asList(" AN  PAN A ANL PAN"))));
         }
      };

      PrintWriter testFeedback = new PrintWriter(System.out);

      // Execute each test case and count the number that pass
      int passCount = 0;
      for (LCSTestCase testCase : testCases) {
         if (testCase.execute(testFeedback)) {
            passCount++;
         }
         testFeedback.flush();
         System.out.println();
      }

      // Print the summary
      System.out.println(passCount + " of " + testCases.size() + " test cases passed");
      System.out.println();

      testFeedback.close();
   }
}
