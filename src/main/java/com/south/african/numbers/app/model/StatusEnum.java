package com.south.african.numbers.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum StatusEnum {

    ACCEPTED("ACCEPTED","Phone Number Accepted"),

    INCORRECT("INCORRECT","Phone Number is empty"),

    INCORRECT_NO_PREFIX("INCORRECT","Prefix is not present"),

    INCORRECT_PREFIX("INCORRECT", "Incorrect Prefix"),

    INCORRECT_PHONE_NUMBER_LENGHT("INCORRECT","The Phone Number length must be 11 characters"),

    INCORRECT_CHARS("INCORRECT", "Chars are not allowed"),
    INCORRECT_ID("INCORRECT","Incorrect Phone Number ID"),

    INCORRECT_EMPTY_ID("INCORRECT","ID is Empty"),
    CORRECTED_MISSING_PREFIX("CORRECTED","Added missing Prefix"),

    CORRECTED_REPLACE_PREFIX("CORRECTED","Replaced incorrect Prefix");

    private String code;
    private String description;

}
