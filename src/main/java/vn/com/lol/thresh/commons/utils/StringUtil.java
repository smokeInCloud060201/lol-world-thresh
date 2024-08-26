package vn.com.lol.thresh.commons.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class StringUtil {

    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int CHARACTERS_LENGTH = 62;
    private static final String DEFAULT_HASH = "SHA-256";


    public static String generateRandomString(int length) throws Exception {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS_LENGTH);
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return hashString(sb.toString());
    }

    private static String hashString(String input) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance(DEFAULT_HASH);
            byte[] encodedHash = digest.digest(input.getBytes());
            return bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error when generating hash string {}", e.getMessage());
            throw new NoSuchAlgorithmException(e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

}
