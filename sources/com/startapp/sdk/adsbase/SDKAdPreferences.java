package com.startapp.sdk.adsbase;

import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SDKAdPreferences implements Serializable {
    private static final long serialVersionUID = 1;
    private Gender gender = null;
    private String age = null;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Gender {
        MALE("m"),
        FEMALE("f");

        private String gender;

        Gender(String str) {
            this.gender = str;
        }

        public static Gender parseString(String str) {
            Gender[] values = values();
            for (int i = 0; i < 2; i++) {
                Gender gender = values[i];
                if (gender.getGender().equals(str)) {
                    return gender;
                }
            }
            return null;
        }

        public String getGender() {
            return this.gender;
        }

        @Override // java.lang.Enum
        public String toString() {
            return getGender();
        }
    }

    public String getAge() {
        return this.age;
    }

    public Gender getGender() {
        return this.gender;
    }

    public SDKAdPreferences setAge(int i) {
        this.age = Integer.toString(i);
        return this;
    }

    public SDKAdPreferences setAge(String str) {
        this.age = str;
        return this;
    }

    public SDKAdPreferences setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String toString() {
        return "SDKAdPreferences [gender=" + this.gender + ", age=" + this.age + "]";
    }
}
