package week5.sorting;

public class Program {
    public static void main(String[] args) {
        int[] numbers = { 72, 91, 53, 12, 48, 19, 7, 1, 86 };

        // Create instances of SinglyLinkedList and DoublyLinkedList
        ListADT[] linkedLists = {
                new SinglyLinkedList(),
                new DoublyLinkedList()
        };

        // Append numbers to each list
        for (ListADT linkedList : linkedLists) {
            for (int number : numbers) {
                linkedList.append(number);
            }
        }

        String[] listNames = {
                "Singly-linked",
                "Doubly-linked"
        };

        // For each list, print before sorting, sort, then print again
        for (int i = 0; i < linkedLists.length; i++) {
            ListADT linkedList = linkedLists[i];

            System.out.print(listNames[i] + " list before sorting: ");
            linkedList.print(System.out);
            linkedList.sort();

            System.out.print(listNames[i] + " list after sorting:  ");
            linkedList.print(System.out);
        }
    }
}
