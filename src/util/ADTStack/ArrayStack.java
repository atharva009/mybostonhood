package util.ADTStack;

import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {

    private T[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 25;

    /** Default constructor */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /** Constructor with custom capacity */
    public ArrayStack(int initialCapacity) {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
    }

    public void push(T newEntry) {
        if (topIndex == stack.length - 1) {
            doubleCapacity();
        }
        stack[++topIndex] = newEntry;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T top = stack[topIndex];
        stack[topIndex] = null;
        topIndex--;
        return top;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack[topIndex];
    }

    public boolean isEmpty() {
        return topIndex < 0;
    }

    public void clear() {
        while (topIndex >= 0) {
            stack[topIndex] = null;
            topIndex--;
        }
    }

    private void doubleCapacity() {
        @SuppressWarnings("unchecked")
        T[] oldStack = stack;
        @SuppressWarnings("unchecked")
        T[] newStack = (T[]) new Object[2 * oldStack.length];
        System.arraycopy(oldStack, 0, newStack, 0, oldStack.length);
        stack = newStack;
    }
}
