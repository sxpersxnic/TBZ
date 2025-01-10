import java.util.*;

public class Tree<T> {
    private T value;
    private List<Tree<T>> children;

    private Tree(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value) {
        return new Tree<>(value);
    }

    public T getValue() {
        return value;
    }

    public List<Tree<T>> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public Tree<T> addChild(T value) {
        Tree<T> newChild = new Tree<>(value);
        children.add(newChild);
        return newChild;
    }

//    public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
//        Queue<Tree<T>> queue = new ArrayDeque<>();
//        queue.add(root);
//
//        while (!queue.isEmpty()) {
//            Tree<T> current = queue.remove();
//        }
//    }
}
