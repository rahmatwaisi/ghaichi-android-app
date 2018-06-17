package com.sorinaidea.arayeshgah.datahelper;

/**
 * Created by mr-code on 2/26/2018.
 */

public enum Gender {

    MALE("وانت"),
    FEMALE("وانت پیکان");

    private String type;

    Gender(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static Gender fromString(String text) {
        for (Gender gender : Gender.values()) {
            if (gender.type.equalsIgnoreCase(text)) {
                return gender;
            }
        }
        return null;
    }
}