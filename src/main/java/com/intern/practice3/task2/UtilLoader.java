package com.intern.practice3.task2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

public class UtilLoader {

    public static <T>T loadFromProperties(Class<T> cls, Path propertiesPath) throws IOException, NoSuchFieldException, ParseException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Properties properties = new Properties();
        T t = (T) cls.newInstance();
        Method m[] = cls.getDeclaredMethods();
        FileInputStream fileInputStream = new FileInputStream(String.valueOf(propertiesPath));
        properties.load(fileInputStream);
        String instant = null;
        Field f = TestClass.class.getDeclaredField("timeProperty");
        Annotation annotation = f.getAnnotation(Property.class);
        if(annotation instanceof Property){
            Property cAnn = (Property) annotation;
            instant = cAnn.format();
        }
        final DateTimeFormatter FMT = new DateTimeFormatterBuilder()
                .appendPattern(instant)
                .toFormatter()
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        Object stringProperty = properties.get("stringProperty");
        Integer integer = Integer.parseInt((String) properties.get("numberProperty"));
        Instant instant1 = FMT.parse((CharSequence) properties.get("timeProperty"), Instant::from);
        if (stringProperty != null) {
            cls.getMethod("setStringProperty", new String().getClass()).invoke(t, stringProperty);
        } else {
            throw new FileNotFoundException();
        }
        if (integer != null) {
            cls.getMethod("setMyNumber", new Integer(0).getClass()).invoke(t, integer);
        } else {
            throw new FileNotFoundException();
        }
        if (instant1 != null) {
            cls.getMethod("setTimeProperty", Instant.now().getClass()).invoke(t, instant1);
        } else {
            throw new FileNotFoundException();
        }
        return (T) t;
    }

}
