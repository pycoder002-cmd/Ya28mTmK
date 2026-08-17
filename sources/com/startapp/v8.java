package com.startapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.sentry.marshaller.json.JsonMarshaller;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v8 {
    public HashMap<Integer, SensorEvent> a = new HashMap<>();

    public JSONArray a() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (SensorEvent sensorEvent : this.a.values()) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            Sensor sensor = sensorEvent.sensor;
            jSONObject2.put("name", sensor.getName());
            jSONObject2.put("vendor", sensor.getVendor());
            jSONObject2.put(ClientCookie.VERSION_ATTR, sensor.getVersion());
            jSONObject2.put("maximum range", sensor.getMaximumRange());
            jSONObject2.put("power", sensor.getPower());
            jSONObject2.put("resolution", sensor.getResolution());
            jSONObject2.put("accuracy", sensorEvent.accuracy);
            jSONObject2.put(JsonMarshaller.TIMESTAMP, sensorEvent.timestamp);
            JSONArray jSONArray2 = new JSONArray();
            int length = sensorEvent.values.length;
            for (int i = 0; i < length; i++) {
                jSONArray2.put(r2[i]);
            }
            jSONObject2.put("values", jSONArray2);
            jSONObject.put(String.valueOf(sensor.getType()), jSONObject2);
            jSONArray.put(jSONObject);
        }
        if (jSONArray.length() > 0) {
            return jSONArray;
        }
        return null;
    }
}
