package com.dhl.virtusa.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BreakSingletonViaSerialization {
    public static void main(String[] args) throws Exception {
        ExampleSingleton s1 = ExampleSingleton.getInstance();
 
        // Serialize
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        oos.writeObject(s1);
        oos.close();
 
        // Deserialize
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.ser"));
        ExampleSingleton s2 = (ExampleSingleton) ois.readObject();
 
        System.out.println(s1 == s2);  // false ➤ Singleton broken

        ExampleSingleton s3 = ExampleSingleton.getInstance();
        ExampleSingleton s4 = (ExampleSingleton) s1.clone();

        System.out.println(s3 == s4);
    }
}