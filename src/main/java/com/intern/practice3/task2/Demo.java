package com.intern.practice3.task2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.text.ParseException;

public class Demo {

    public static void main(String[] args) throws IOException, NoSuchFieldException, ParseException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        UtilLoader u = new UtilLoader();
        TestClass tclass = new TestClass();
        File file = new File("src/main/resources/types.properties");
        Path p = Path.of(file.getPath());
        tclass = UtilLoader.loadFromProperties(tclass.getClass(), p);
        System.out.println(tclass.getMyNumber() + " " + tclass.getStringProperty() + " " + tclass.getTimeProperty());
    }
}
