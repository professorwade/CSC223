package week6.llqueue;

import java.io.PrintStream;

public interface QueueADT {
    // Methods that may change the queue
    public void enqueue(int item);
    public int dequeue();

    // Methods that do not change the queue
    public int peek();
    public boolean isEmpty();
    public int getLength();
    public void print(PrintStream out);
}