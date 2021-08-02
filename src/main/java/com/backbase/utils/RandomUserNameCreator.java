package com.backbase.utils;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RandomUserNameCreator {

    /**
     * Generate random user name starting with "testuser" and ends with 6 random letters
     *
     * @return random user name
     */
    public static String generateRandomUserName() {
        return "testuser" + randomAlphabetic(6).toLowerCase();
    }
}
