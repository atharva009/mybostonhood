package util.ADTStack;

import javax.swing.*;

public class NavigationStack {

    private static ArrayStack<JFrame> navStack = new ArrayStack<>();

    public static void push(JFrame frame) {
        navStack.push(frame);
    }

    public static void back() {
        if (!navStack.isEmpty()) {
            JFrame current = navStack.pop();
            current.dispose();
            if (!navStack.isEmpty()) {
                JFrame previous = navStack.peek();
                previous.setVisible(true);
            } else {
                System.exit(0);
            }
        }
    }

    public static void clear() {
        navStack.clear();
    }
}
