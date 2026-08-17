package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SensorsConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private int timeoutInSec = 10;
    private boolean enabled = false;
    private long refreshInterval = 900000;

    @f(complex = true)
    private BaseSensorConfig ambientTemperatureSensor = new BaseSensorConfig(14);

    @f(complex = true)
    private BaseSensorConfig gravitySensor = new BaseSensorConfig(9);

    @f(complex = true)
    private BaseSensorConfig lightSensor = new BaseSensorConfig(3);

    @f(complex = true)
    private BaseSensorConfig linearAccelerationSensor = new BaseSensorConfig(9);

    @f(complex = true)
    private BaseSensorConfig magneticFieldSensor = new BaseSensorConfig(3);

    @f(complex = true)
    private BaseSensorConfig pressureSensor = new BaseSensorConfig(9);

    @f(complex = true)
    private BaseSensorConfig relativeHumiditySensor = new BaseSensorConfig(14);

    @f(complex = true)
    private BaseSensorConfig rotationVectorSensor = new BaseSensorConfig(9);

    @f(complex = true)
    private BaseSensorConfig gyroscopeUncalibratedSensor = new BaseSensorConfig(18);

    public BaseSensorConfig a() {
        return this.ambientTemperatureSensor;
    }

    public BaseSensorConfig b() {
        return this.gravitySensor;
    }

    public BaseSensorConfig c() {
        return this.gyroscopeUncalibratedSensor;
    }

    public BaseSensorConfig d() {
        return this.lightSensor;
    }

    public BaseSensorConfig e() {
        return this.linearAccelerationSensor;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SensorsConfig.class != obj.getClass()) {
            return false;
        }
        SensorsConfig sensorsConfig = (SensorsConfig) obj;
        return this.timeoutInSec == sensorsConfig.timeoutInSec && this.enabled == sensorsConfig.enabled && this.refreshInterval == sensorsConfig.refreshInterval && aa.a(this.ambientTemperatureSensor, sensorsConfig.ambientTemperatureSensor) && aa.a(this.gravitySensor, sensorsConfig.gravitySensor) && aa.a(this.lightSensor, sensorsConfig.lightSensor) && aa.a(this.linearAccelerationSensor, sensorsConfig.linearAccelerationSensor) && aa.a(this.magneticFieldSensor, sensorsConfig.magneticFieldSensor) && aa.a(this.pressureSensor, sensorsConfig.pressureSensor) && aa.a(this.relativeHumiditySensor, sensorsConfig.relativeHumiditySensor) && aa.a(this.rotationVectorSensor, sensorsConfig.rotationVectorSensor) && aa.a(this.gyroscopeUncalibratedSensor, sensorsConfig.gyroscopeUncalibratedSensor);
    }

    public BaseSensorConfig f() {
        return this.magneticFieldSensor;
    }

    public BaseSensorConfig g() {
        return this.pressureSensor;
    }

    public long h() {
        return this.refreshInterval;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.timeoutInSec), Boolean.valueOf(this.enabled), Long.valueOf(this.refreshInterval), this.ambientTemperatureSensor, this.gravitySensor, this.lightSensor, this.linearAccelerationSensor, this.magneticFieldSensor, this.pressureSensor, this.relativeHumiditySensor, this.rotationVectorSensor, this.gyroscopeUncalibratedSensor};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public BaseSensorConfig i() {
        return this.relativeHumiditySensor;
    }

    public BaseSensorConfig j() {
        return this.rotationVectorSensor;
    }

    public int k() {
        return this.timeoutInSec;
    }

    public boolean l() {
        return this.enabled;
    }
}
