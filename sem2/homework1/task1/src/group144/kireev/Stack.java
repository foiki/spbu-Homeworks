package group144.kireev;
import java.util.Scanner;

/**
 *A class that realizes ordinary stack
 */

public class Stack {
    private class StackElement {
        private int value;
        private StackElement next;

        public StackElement(int value, StackElement next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {

            return value;
        }

        public StackElement getNext() {

            return next;
        }
    }

    private StackElement top;

    /**
     *A method that adds element to the stack
     */

    public void add(int value) {

        top = new StackElement(value, top);
    }

    /**
     *A method that deletes top element from the stack
     */

    public void pop() {

        top = top.getNext();
    }

    /**
     *A method that returns the size of stack
     */

    public int getSize() {
        StackElement current = top;
        int result = 0;
        while (current != null) {
            ++result;
            current = current.getNext();
        }
        return result;
    }

    /**
     *A method that prints the stack
     */

    public void printStack() {
        StackElement current = top;
        while (current != null) {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
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
                    stack.add(newElement);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.printStack();
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
}
