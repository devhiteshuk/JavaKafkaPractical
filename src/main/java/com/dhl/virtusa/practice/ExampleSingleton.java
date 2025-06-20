package com.dhl.virtusa.practice;

import java.io.Serial;
import java.io.Serializable;

public class ExampleSingleton implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;

    private static ExampleSingleton instance;

    private ExampleSingleton() {
        // Private constructor to prevent instantiation
    }

    public static ExampleSingleton getInstance() {
        if (instance == null) {
            synchronized (ExampleSingleton.class) {
                if (instance == null) {
                    instance = new ExampleSingleton();
                }
            }
        }
        return instance;
    }

    public void displayMessage() {
        System.out.println("Hello from ExampleSingleton!");
    }

    @Serial
    protected Object readResolve() {
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }
}
