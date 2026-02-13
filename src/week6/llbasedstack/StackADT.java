package week6.llbasedstack;

import java.io.PrintStream;

public interface StackADT {
    // Methods that may change the stack
    public void push(int item);
    public int pop();

    // Methods that do not change the stack
    public int peek();
    public boolean isEmpty();
    public int getLength();
    public void print(PrintStream out);
}