package com.gohn.nativedialog;

/* loaded from: classes.dex */
public enum ButtonType {
    NO_BUTTON(0),
    ONE_BUTTON(1),
    TWO_BUTTON(3),
    THREE_BUTTON(7);

    private final int value;

    ButtonType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
