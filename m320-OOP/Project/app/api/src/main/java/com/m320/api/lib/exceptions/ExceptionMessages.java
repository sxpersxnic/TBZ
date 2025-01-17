package com.m320.api.lib.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExceptionMessages {

    public static String getNotBlankMessage(String attribute) {
        return attribute + " must not be blank!";
    }

    public static String getInvalidMessage(String attribute) {
        return "Invalid " + attribute;
    }

    public static String getNotFoundMessage(String notFoundObject) {
        return notFoundObject + " not found!";
    }

    public static String getFailedValidationMessage(Map<String, List<String>> errors) {
        List<String> list = new ArrayList<>();
        errors.forEach( (key, value) -> {
            list.add(key + ": " + value);
        });

        return list.toString();
    }

    public static String getAlreadyInUseMessage(String attribute) {
        return attribute + " is already in use!";
    }

    public static String getAlreadyExistsMessage(String attribute) {
        return attribute + " already exists!";
    }
}
