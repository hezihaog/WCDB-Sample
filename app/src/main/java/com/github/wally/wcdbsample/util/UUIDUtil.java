package com.github.wally.wcdbsample.util;

import java.util.UUID;

public class UUIDUtil {
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}