package com.intern.practice3.task2;

import com.intern.practice3.task2.exceptions.PropertyNotFound;

import java.io.File;
import java.nio.file.Path;

public class Demo {

    public static void main(String[] args) throws PropertyNotFound {
        UtilLoader u = new UtilLoader();
        TestClass tclass = new TestClass();
        File file = new File("src/main/resources/types.properties");
        Path p = Path.of(file.getPath());
        tclass = UtilLoader.loadFromProperties(tclass.getClass(), p);
        System.out.println(tclass.getMyNumber() + " " + tclass.getStringProperty() + " " + tclass.getTimeProperty());
    }
}
