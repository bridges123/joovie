package joovie.services;

import java.util.Date;

public class UIDGenerator {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "!#$%&*";
    private static final int CHARS_LENGTH = CHARS.length();

    public static String generateUID() {
        long timeValue = new Date().getTime();
        timeValue *= (timeValue % 500000000);
        StringBuilder uid = new StringBuilder();
        while (timeValue > 0 || uid.length() < 12) {
            long nowIndex = timeValue % CHARS_LENGTH;
            uid.append(CHARS.charAt((int) nowIndex));
            timeValue = (timeValue - nowIndex) / CHARS_LENGTH;
        }
        return uid.toString();
    }

}
