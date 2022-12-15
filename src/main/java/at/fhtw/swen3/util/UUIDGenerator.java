package at.fhtw.swen3.util;

import java.util.UUID;

public class UUIDGenerator {

    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 9).toUpperCase();
        return uuid;
    }
}
