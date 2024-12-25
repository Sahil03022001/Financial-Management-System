package com.financial.transaction.usersystem.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class UtilsManager {

    public static String userIdPrefix = "USR";
    public static int randomIdDigits = 8;

    public static String generateRandomId() {
        return userIdPrefix + getRandomIdNDigits(randomIdDigits);
    }

    public static String getRandomIdNDigits(int n) {
        Random random = new Random();

        StringBuilder numberStr = new StringBuilder();
        numberStr.append(random.nextInt(9) + 1); // First digit should be between 1-9

        for (int i = 1; i < n; i++) {
            numberStr.append(random.nextInt(10)); // Remaining digits can be between 0-9
        }

        return numberStr.toString();
    }
}
