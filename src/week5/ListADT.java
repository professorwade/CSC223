package week5;

import java.io.PrintStream;

public interface ListADT {
    // Methods that may change the list
    public void append(int item);
    public void prepend(int item);
    public boolean insertAfter(int existingItem, int newItem);
    public boolean remove(int item);

    // Methods that do not change the list
    public boolean contains(int item);
    public void print(PrintStream out);
    public boolean isEmpty();
    public int getLength();
}
