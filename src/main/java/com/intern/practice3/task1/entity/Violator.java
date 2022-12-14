package com.intern.practice3.task1.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Violator {
    @JsonProperty("date_time")
    public String dateTime;

    @JsonProperty("first_name")
    public String firstName;

    @JsonProperty("last_name")
    public String lastName;

    @JsonProperty("type")
    public Type type;

    @JsonProperty("fine_amount")
    public int fineAmount;

    public int counter = 0;

    Violator() {

    }

    Violator(String dateTime, String firstName, String lastName, Type type, int fineAmount) {
        this.dateTime = dateTime;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.fineAmount = fineAmount;
    }

    public int getAverageFine() {
        int averageFine = 0;

        if (counter != 0) {
            averageFine = fineAmount / counter;
        }

        return averageFine;
    }

    public int getFineAmount() {
        return fineAmount;
    }


    @Override
    public String toString() {
        return firstName + " " + lastName + "\n" + "Total fines: " + fineAmount + "\n" + "Violations registered: "
                + counter + "\n";
    }
}
