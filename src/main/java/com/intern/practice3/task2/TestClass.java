package com.intern.practice3.task2;

import java.time.Instant;

public class TestClass {
    @Property(name = "stringProperty")
    private String stringProperty;

    @Property(name = "numberProperty")
    private Integer myNumber;

    @Property(format="dd.MM.yyyy HH:mm")
    private Instant timeProperty;

    public String getStringProperty() {
        return stringProperty;
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
    }

    public Integer getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(Integer myNumber) {
        this.myNumber = myNumber;
    }

    public Instant getTimeProperty() {
        return timeProperty;
    }

    public void setTimeProperty(Instant timeProperty) {
        this.timeProperty = timeProperty;
    }
}
