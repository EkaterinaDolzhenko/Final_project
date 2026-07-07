package praktikum;

import org.apache.commons.lang3.RandomStringUtils;

public class TestDataGenerator {

    private static final String EMAIL_DOMAIN = "@test.com";
    private static final String DEFAULT_PASSWORD = "TestPass123!";

    public static String generateUniqueEmail() {
        return "testuser_" + System.currentTimeMillis() + "_" +
                RandomStringUtils.randomAlphabetic(5).toLowerCase() + EMAIL_DOMAIN;
    }

    public static String generatePassword() {
        return DEFAULT_PASSWORD;
    }

    public static User createTestUser() {
        return new User(
                generateUniqueEmail(),
                generatePassword()
        );
    }
}