package week10.trie;

public class Program {
    public static void main(String[] args) {
        String[] insertions = {
                "CAT", "DOG", "BIRD", "FISH", "HAMSTER", "SNAKE"
        };
        String[] searches = {
                "CAT", "BAT", "RAT", "HIPPOPOTAMUS", "HAMSTER", "FERRET", "OCTOPUS"
        };

        // Create a trie and insert some strings
        Trie trie = new Trie();
        for (String stringToInsert : insertions) {
            System.out.println("Inserting \"" + stringToInsert + "\"");
            trie.insert(stringToInsert);
        }

        // Search for various strings, displaying the number of nodes visited during
        // each search
        for (String searchString : searches) {
            System.out.print("Search for \"" + searchString + "\" returned ");

            boolean found = trie.contains(searchString);
            int numNodesVisited = trie.getNodesVisited();

            System.out.println((found ? "true" : "false") + " and visited " +
                    numNodesVisited + " " + (numNodesVisited == 1 ? "node" : "nodes"));
        }
    }
}