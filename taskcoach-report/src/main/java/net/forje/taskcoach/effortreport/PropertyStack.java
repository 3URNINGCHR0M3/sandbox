package net.forje.taskcoach.effortreport;

import java.util.Enumeration;
import java.util.Properties;
import java.util.Stack;

public class PropertyStack {

    private Stack<Properties> _stack = new Stack<Properties>();

    public void push(final Properties propertyStack) {
        _stack.push(propertyStack);
    }

    public Properties scrape() {

        Properties properties = new Properties();

        final int size = _stack.size();
        for (int i = (size - 1); i >= 0; i--) {
            Properties current = (Properties) _stack.get(i);
            final Enumeration names = current.propertyNames();

            while (names.hasMoreElements()) {
                String name = (String) names.nextElement();
                if (!properties.containsKey(name)) {
                    properties.put(name, current.getProperty(name));
                }
            }
        }

        return properties;

    }

    public void pop() {
        _stack.pop();
    }

}
