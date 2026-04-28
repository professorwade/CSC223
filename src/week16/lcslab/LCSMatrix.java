package week16.lcslab;
import java.util.*;

// TODO: Type your code here
// - Import any required additional packages
// - Declare any desired classes to be used by LCSMatrix
class LCSMatrixEntry {
   public int value;
   public boolean match;
   public HashSet<String> lcsSet = new HashSet<String>();

   // Constructs a default entry that has a value=0, match=false, and an empty
   // lcsSet
   public LCSMatrixEntry() {
      value = 0;
      match = false;
   }

   public LCSMatrixEntry(LCSMatrixEntry toCopy) {
      value = toCopy.value;
      match = toCopy.match;
      lcsSet = new HashSet<String>(toCopy.lcsSet);
   }

   // Utility function that returns the LCSMatrixEntry with the greater value.
   // If the two entries have the same value, then a new entry is returned with
   // the shared value and a union of the two LCS sets.
   public static LCSMatrixEntry max(LCSMatrixEntry entry1, LCSMatrixEntry entry2) {
      if (entry1.value == entry2.value) {
         // Copy entry1's set
         HashSet<String> unionSet = new HashSet<String>(entry1.lcsSet);

         // Add each string from entry2's set
         for (var str : entry2.lcsSet) {
            unionSet.add(str);
         }

         LCSMatrixEntry result = new LCSMatrixEntry();
         result.value = entry1.value;
         result.lcsSet = new HashSet<String>(unionSet);
         return new LCSMatrixEntry(result);
      }
      else if (entry1.value >= entry2.value) {
         return new LCSMatrixEntry(entry1);
      }
      return new LCSMatrixEntry(entry2);
   }
}

public class LCSMatrix {
   private int rowCount;
   private int columnCount;
   // TODO: Add a field for the matrix data
   private ArrayList<LCSMatrixEntry> data = new ArrayList<LCSMatrixEntry>();

   // Returns a copy of the matrix entry at the specified row and column
   // indices, or the default entry if either index is out of bounds.
   private LCSMatrixEntry get(int rowIndex, int columnIndex) {
      // Check if either index is out of bounds
      if (rowIndex < 0 || rowIndex >= rowCount || columnIndex < 0 || columnIndex >= columnCount) {
         return new LCSMatrixEntry();
      }
      return new LCSMatrixEntry(data.get(rowIndex * columnCount + columnIndex));
   }

   public LCSMatrix(String str1, String str2) {
      this.rowCount = str1.length();
      this.columnCount = str2.length();

      // TODO: Type your code here
      // Allocate matrix entries
      for (int i = data.size(); i < (rowCount * columnCount); i++) {
         data.add(new LCSMatrixEntry());
      }

      // Populate the matrix entries
      for (int row = 0; row < rowCount; row++) {
         for (int col = 0; col < columnCount; col++) {
            // Get a reference to the current entry
            LCSMatrixEntry currentEntry = data.get(row * columnCount + col);

            // Check if the characters match
            if (str1.charAt(row) == str2.charAt(col)) {
               // Get the entry in the cell that's up and to the left
               LCSMatrixEntry upLeft = get(row - 1, col - 1);

               // Special case if upper-left's LCS set is empty
               if (0 == upLeft.lcsSet.size()) {
                  currentEntry.lcsSet.add("" + str1.charAt(row));
               }
               else {
                  // New entry's LCS set is the upper-left's LCS set, but with
                  // the matching character concatenated onto each entry
                  for (var lcs : upLeft.lcsSet) {
                     currentEntry.lcsSet.add(lcs + str1.charAt(row));
                  }
               }

               // Set the entry at (row, col)
               currentEntry.value = 1 + upLeft.value;
               currentEntry.match = true;
            }
            else {
               // The current entry does not represent a character match
               currentEntry.match = false;

               // Get maximum between entry to the left and entry above
               LCSMatrixEntry maxEntry = LCSMatrixEntry.max(get(row - 1, col), get(row, col - 1));

               // Copy maxEntry's value and lcs to currentEntry
               currentEntry.value = maxEntry.value;
               currentEntry.lcsSet = new HashSet<String>(maxEntry.lcsSet);
            }
         }
      }
   }

   // OPTIONAL: Add additional methods here, if needed

   // Returns the number of columns in the matrix, which also equals the length
   // of the second string passed to the constructor.
   public int getColumnCount() {
      return columnCount;
   }

   // Returns the matrix entry at the specified row and column indices, or 0 if
   // either index is out of bounds.
   public int getEntry(int rowIndex, int columnIndex) {
      // TODO: Type your code here (remove placeholder line below)
      //return 0;
      // Check if either index is out of bounds
      if (rowIndex < 0 || rowIndex >= rowCount || columnIndex < 0 || columnIndex >= columnCount) {
         return 0;
      }
      return data.get(rowIndex * columnCount + columnIndex).value;
   }

   // Returns the number of rows in the matrix, which also equals the length
   // of the first string passed to the constructor.
   public int getRowCount() {
      return rowCount;
   }

   // Returns the set of distinct, longest common subsequences between the two
   // strings that were passed to the constructor.
   public HashSet<String> getLongestCommonSubsequences() {
      // TODO: Type your code here (remove placeholder line below)
      //return new HashSet<String>();
      // Special case for empty ArrayList
      if (0 == data.size()) {
         return new HashSet<String>();
      }

      // Last matrix entry has the LCS set
      return new HashSet<String>(data.get(data.size() - 1).lcsSet);
   }
}
