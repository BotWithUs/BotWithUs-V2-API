package net.botwithus.rs3.interfaces;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InterfaceIterator implements Iterator<Component> {

    private final Deque<Iterator<Component>> stack;

    public InterfaceIterator(Interface root) {
        this.stack = new ArrayDeque<>();
        if (root != null && root.getComponents() != null) {
            this.stack.push(root.getComponents().iterator()); // Assuming `getChildren()` returns List<Component>
        }
    }

    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<Component> currentIterator = stack.peek();
            if (currentIterator.hasNext()) {
                return true;
            } else {
                stack.pop(); // No more elements at this level, move up
            }
        }
        return false; // No more elements to iterate over
    }

    @Override
    public Component next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Get the next component from the current iterator
        assert stack.peek() != null;
        Component nextComponent = stack.peek().next();

        // If the component has children, add their iterator to the stack
        if (nextComponent.getChildren() != null && !nextComponent.getChildren().isEmpty()) {
            stack.push(nextComponent.getChildren().iterator());
        }

        return nextComponent;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Removal not supported");
    }
}
