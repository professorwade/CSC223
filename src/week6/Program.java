package week6;

import java.util.Queue;
import java.util.LinkedList;

public class Program {
    public static void main(String[] args) {
        // Declare items to insert
        String[] itemsToInsert = {
                "Move to (100,200)", "Click", "Move to (640,480)", "Double-click"
        };

        // Create the queue and insert items
        Queue<String> actionQueue = new LinkedList<String>();
        for (String item : itemsToInsert) {
            actionQueue.add(item);
            System.out.println("Enqueued \"" + item + "\"");
        }

        System.out.println("actionQueue's size is " + actionQueue.size());

        // Dequeue and print items until the queue is empty
        while (actionQueue.size() > 0) {
            String dequeuedItem = actionQueue.remove();
            System.out.println("Dequeued \"" + dequeuedItem + "\"");
        }
    }
}