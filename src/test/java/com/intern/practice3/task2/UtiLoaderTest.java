package com.intern.practice3.task2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.intern.practice3.task2.exceptions.PropertyNotFound;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtiLoaderTest {

    @Test
    public void stringIsMissingProperty() {
        TestClass t = new TestClass();
        Path p = Path.of("src/test/resources/stringMissing.properties");
        Throwable throwable =
                Assertions.assertThrows(PropertyNotFound.class, () -> UtilLoader.loadFromProperties(t.getClass(), p));
        Assertions.assertEquals("String property not found", throwable.getMessage());
    }

    @Test
    public void numberIsMissingProperty() {
        TestClass t = new TestClass();
        Path p = Path.of("src/test/resources/numberMissing.properties");
        Throwable throwable =
                Assertions.assertThrows(PropertyNotFound.class, () -> UtilLoader.loadFromProperties(t.getClass(), p));
        Assertions.assertEquals("Number property not found", throwable.getMessage());
    }

    @Test
    public void instantIsMissingProperty() {
        TestClass t = new TestClass();
        Path p = Path.of("src/test/resources/instantMissing.properties");
        Throwable throwable =
                Assertions.assertThrows(PropertyNotFound.class, () -> UtilLoader.loadFromProperties(t.getClass(), p));
        Assertions.assertEquals("Instant property not found", throwable.getMessage());
    }

    @Test
    public void verifiesAllTypes() throws PropertyNotFound {
        TestClass t = new TestClass();
        Path p = Path.of("src/test/resources/allTypes.properties");
        t = UtilLoader.loadFromProperties(t.getClass(), p);
        Assertions.assertEquals(10, t.getMyNumber());
        Assertions.assertEquals("value1", t.getStringProperty());
        Assertions.assertEquals(LocalDateTime.parse("29.11.2022 18:30",
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).atZone(ZoneId.of("Europe/Kiev")).toInstant(), t.getTimeProperty());
    }

}
