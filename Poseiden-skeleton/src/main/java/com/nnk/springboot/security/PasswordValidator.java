package com.nnk.springboot.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Methods to validate password at least one capital letter, at least 8 characters, at least one number and one symbol
 */
public class PasswordValidator {

    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,60}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    /**
     * Validate password at least one capital letter, at least 8 characters, at least one number and one symbol
     * @param password validating
     * @return true/false boolean for valid/invalid password
     */
    public static boolean isValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
