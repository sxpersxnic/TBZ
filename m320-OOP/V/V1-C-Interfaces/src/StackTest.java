import java.util.NoSuchElementException;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // Test for method: Push
        System.out.println("Push test:");
        stack.push(1);
        stack.push(2);
        stack.push(3);

        if (!stack.isEmpty()) {
            System.out.println("Push test successful");
        } else {
            System.out.println("Push test failed");
        }

        // Test for method: hasNext and next
        System.out.println("hasNext test:");
        System.out.println("Iterating through stack:");
        while (stack.hasNext()) {
            System.out.println(stack.next());
        }

        // Test for method: Remove
        System.out.println("Remove test:");
        stack.remove();
        stack.remove();
        stack.remove();

        if (stack.isEmpty()) {
            System.out.println("Remove method test successful");
        } else {
            System.out.println("Remove method test failed");
        }

        // Test for method: isEmpty
        System.out.println("isEmpty test:");
        if (stack.isEmpty()) {
            System.out.println("isEmpty method test successful");
        } else {
            System.out.println("isEmpty method test failed");
        }

        // Remove on empty stack test
        System.out.println("Remove on empty stack test:");
        try {
            stack.remove();
            System.out.println("Exception test failed");
        } catch (NoSuchElementException e) {
            System.out.println("Exception test successful");
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Next on empty stack test
        System.out.println("Next on empty stack test:");
        try {
            stack.next();
            System.out.println("Exception test failed");
        } catch (NoSuchElementException e) {
            System.out.println("Exception test successful");
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
