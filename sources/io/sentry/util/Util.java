package io.sentry.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public final class Util {
    private Util() {
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static Map<String, String> parseCsv(String str, String str2) {
        if (isNullOrEmpty(str)) {
            return Collections.emptyMap();
        }
        String[] split = str.split(",");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str3 : split) {
            String[] split2 = str3.split(":");
            if (split2.length != 2) {
                throw new IllegalArgumentException("Invalid " + str2 + " entry: " + str3);
            }
            linkedHashMap.put(split2[0], split2[1]);
        }
        return linkedHashMap;
    }

    public static Double parseDouble(String str, Double d) {
        return isNullOrEmpty(str) ? d : Double.valueOf(Double.parseDouble(str));
    }

    public static Map<String, String> parseExtra(String str) {
        return parseCsv(str, "extras");
    }

    @Deprecated
    public static Set<String> parseExtraTags(String str) {
        return parseMdcTags(str);
    }

    public static Integer parseInteger(String str, Integer num) {
        return isNullOrEmpty(str) ? num : Integer.valueOf(Integer.parseInt(str));
    }

    public static Long parseLong(String str, Long l) {
        return isNullOrEmpty(str) ? l : Long.valueOf(Long.parseLong(str));
    }

    public static Set<String> parseMdcTags(String str) {
        return isNullOrEmpty(str) ? Collections.emptySet() : new HashSet(Arrays.asList(str.split(",")));
    }

    public static Map<String, String> parseTags(String str) {
        return parseCsv(str, "tags");
    }

    public static boolean safelyRemoveShutdownHook(Thread thread) {
        try {
            return Runtime.getRuntime().removeShutdownHook(thread);
        } catch (IllegalStateException e) {
            if (e.getMessage().equals("Shutdown in progress")) {
                return false;
            }
            throw e;
        }
    }

    public static String trimString(String str, int i) {
        if (str == null) {
            return null;
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i - 3) + "...";
    }
}
