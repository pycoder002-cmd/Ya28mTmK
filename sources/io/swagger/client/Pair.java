package io.swagger.client;

/* loaded from: classes2.dex */
public class Pair {
    private String name = "";
    private String value = "";

    public Pair(String str, String str2) {
        setName(str);
        setValue(str2);
    }

    private boolean isValidString(String str) {
        return (str == null || str.trim().isEmpty()) ? false : true;
    }

    private void setName(String str) {
        if (isValidString(str)) {
            this.name = str;
        }
    }

    private void setValue(String str) {
        if (isValidString(str)) {
            this.value = str;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
