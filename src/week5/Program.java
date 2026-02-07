package week5;

public class Program {
    public static void main(String[] args) {
        SinglyLinkedList numList = new SinglyLinkedList();

        // Insert various items using append(), prepend(), and insertAfter()
        numList.append(14);          // List: 14
        numList.append(2);           // List: 14, 2
        numList.append(20);          // List: 14, 2, 20
        numList.prepend(31);         // List: 31, 14, 2, 20
        numList.insertAfter(2, 16);  // List: 31, 14, 2, 16, 20
        numList.insertAfter(20, 55); // List: 31, 14, 2, 16, 20, 55

        // Output list
        System.out.print("List after adding items: ");
        numList.print(System.out);

        // Remove the tail node, then the head node
        numList.remove(55);  // List: 31, 14, 2, 16, 20
        numList.remove(31);  // List: 14, 2, 16, 20

        // Output list again
        System.out.print("List after removing first and last items: ");
        numList.print(System.out);

        // Insert three more items
        numList.prepend(67);         // List: 67, 14, 2, 16, 20
        numList.insertAfter(20, 58); // List: 67, 14, 2, 16, 20, 58
        numList.append(89);          // List: 67, 14, 2, 16, 20, 58, 89

        // Output final list
        System.out.print("List after inserting three more items: ");
        numList.print(System.out);
    }
}
