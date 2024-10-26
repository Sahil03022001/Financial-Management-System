package com.financial.transaction.accountsystem.utils;

import java.util.Random;

public class AccountUtilsManager {

    private static final String accountIdPrefix = "ACC";

    public static String createAccountId() {
        return accountIdPrefix + getRandomIdNDigits(8);
    }

    public static String getRandomIdNDigits(int n) {
        Random random = new Random();

        StringBuilder numberStr = new StringBuilder();
        numberStr.append(random.nextInt(9) + 1);

        for (int i = 1; i < n; i++) {
            numberStr.append(random.nextInt(10));
        }

        return numberStr.toString();
    }
}
