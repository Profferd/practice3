package com.intern.practice3.task2;

import com.intern.practice3.task2.exceptions.PropertyNotFound;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Properties;

public class UtilLoader {

    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) throws PropertyNotFound {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(String.valueOf(propertiesPath));
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new PropertyNotFound("Property file not found");
        }
        String instant = null;
        Field f = null;
        try {
            f = cls.getDeclaredField("timeProperty");
        } catch (NoSuchFieldException e) {
            throw new PropertyNotFound("time property field not found");
        }
        Annotation annotation = f.getAnnotation(Property.class);
        if (annotation instanceof Property) {
            Property cAnn = (Property) annotation;
            instant = cAnn.format();
        }
        final DateTimeFormatter FMT = new DateTimeFormatterBuilder()
                .appendPattern(instant)
                .toFormatter()
                .withLocale(Locale.getDefault())
                .withZone(ZoneId.systemDefault());
        T t = null;
        try {
            t = (T) cls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new PropertyNotFound("Error: " + e);
        }
        Object stringProperty = properties.get("stringProperty");
        Integer integer = null;
        Instant instant1 = null;
        try {
            integer = Integer.parseInt((String) properties.get("numberProperty"));
            instant1 = FMT.parse((CharSequence) properties.get("timeProperty"), Instant::from);
        } catch (NumberFormatException | NullPointerException e) {
            throw new PropertyNotFound("Error: ", e);
        }
        try {
            if (stringProperty != null) {
                cls.getMethod("setStringProperty", new String().getClass()).invoke(t, stringProperty);
            } else {
                throw new PropertyNotFound("String property not found");
            }
            if (integer != null) {
                cls.getMethod("setMyNumber", new Integer(0).getClass()).invoke(t, integer);
            } else {
                throw new PropertyNotFound("Number property not found");
            }
            if (instant1 != null) {
                cls.getMethod("setTimeProperty", Instant.now().getClass()).invoke(t, instant1);
            } else {
                throw new PropertyNotFound("Instant property not found");
            }
            return (T) t;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new PropertyNotFound("Error: " + e);
        }
    }

}
