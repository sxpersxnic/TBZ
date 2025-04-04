import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterator<T> {

    private Node<T> top;
    private Node<T> current;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public Stack() {
        top = null;
        current = null;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
        current = top;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        top = top.next;
        current = top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T item = current.data;
        current = current.next;
        return item;
    }

    public Node<T> getTop() {
        return top;
    }

    public Node<T> getCurrent() {
        return current;
    }
}
