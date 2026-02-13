package week6.javastackexample;

import java.util.Stack;

public class Program {
    public static void main(String[] args) {

        String[] itemsToInsert = {
                "Write text", "Select all text", "Bold selected text",
                "Underline selected text", "Delete all text"
        };

        // Create the stack and push items
        Stack<String> actionStack = new Stack<String>();
        for (String item : itemsToInsert) {
            actionStack.push(item);
            System.out.println("Pushed \"" + item + "\"");
        }

        System.out.println("actionStack's size is " + actionStack.size());

        // Pop and print items until the stack is empty
        while (actionStack.size() > 0) {
            String poppedItem = actionStack.pop();
            System.out.println("Poppped \"" + poppedItem + "\"");
        }

    }
}