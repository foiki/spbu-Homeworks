package group144.kireev;
import java.util.Scanner;

/** A class that realizes ordinary stack */
public class Stack {

    private StackElement top;
    private int length;

    /** A method that adds element to the stack */
    public void push(int value) {

        top = new StackElement(value, top);
        ++length;
    }

    /** A method that deletes top element from the stack */
    public void pop() throws NullPointerException {
        if (top == null) {
            throw new NullPointerException("Nothing to delete!");
        }
        top = top.next;
        --length;
    }

    /** A method that returns the size of stack */
    public int getSize() {
        return length;
    }

    /** A method that prints the stack */
    public void printStack() throws NullPointerException {
        if (top == null) {
            throw new NullPointerException("No elements in the stack!");
        }
        StackElement current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int newRequest = -1;
        Stack stack = new Stack();
        System.out.println("Enter the command if you now how it works or type '5' to see help menu");
        while (newRequest != 0) {
            System.out.print("Enter new request: ");
            newRequest = in.nextInt();
            switch (newRequest) {
                case 0:
                    System.out.println("Bye!");
                    return;
                case 1:
                    System.out.print("Enter new element to add: ");
                    int newElement = in.nextInt();
                    stack.push(newElement);
                    break;
                case 2:
                    try {
                        stack.pop();
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        stack.printStack();
                    } catch (NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("The Stack size is: " + stack.getSize());
                    break;
                case 5:
                    System.out.println("Enter '0' to exit");
                    System.out.println("Enter '1' to add new element to Stack");
                    System.out.println("Enter '2' to delete top element");
                    System.out.println("Enter '3' to print Stack");
                    System.out.println("Enter '4' to print size of Stack");
                    System.out.println("Enter '5' to see help menu");
                    break;
            }
        }
    }

    private class StackElement {
        private int value;
        private StackElement next;

        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }
    }
}
