package com.startapp;

import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public interface b8 {
    void close();

    void createCalendarEvent(String str);

    void expand(String str);

    boolean open(String str);

    void playVideo(String str);

    void resize();

    void setExpandProperties(Map<String, String> map);

    void setOrientationProperties(Map<String, String> map);

    void setResizeProperties(Map<String, String> map);

    void storePicture(String str);

    void useCustomClose(String str);
}
