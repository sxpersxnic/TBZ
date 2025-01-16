package com.m320.api.lib.exceptions;

public class ExceptionMessages {

    public static String getNotBlankMessage(String attribute) {
        return attribute + " must not be blank!";
    }

    public static String getInvalidMessage(String attribute) {
        return "Invalid " + attribute;
    }
}
