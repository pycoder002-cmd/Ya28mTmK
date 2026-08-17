package com.startapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import com.startapp.networkTest.data.BatteryInfo;
import com.startapp.networkTest.enums.BatteryChargePlugTypes;
import com.startapp.networkTest.enums.BatteryHealthStates;
import com.startapp.networkTest.enums.BatteryStatusStates;
import io.sentry.marshaller.json.JsonMarshaller;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u {
    private static final String a = "u";
    private BatteryManager b;
    private Context c;

    public u(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.b = (BatteryManager) context.getSystemService("batterymanager");
        }
        this.c = context;
    }

    private void a(BatteryInfo batteryInfo) {
        BatteryManager batteryManager = this.b;
        if (batteryManager == null) {
            return;
        }
        int intProperty = batteryManager.getIntProperty(1);
        if (intProperty != Integer.MIN_VALUE) {
            batteryInfo.BatteryCapacity = intProperty;
        }
        int intProperty2 = this.b.getIntProperty(2);
        if (intProperty2 != Integer.MIN_VALUE) {
            batteryInfo.BatteryCurrent = intProperty2;
        }
        long longProperty = this.b.getLongProperty(5);
        if (longProperty != Long.MIN_VALUE) {
            batteryInfo.BatteryRemainingEnergy = longProperty;
        }
    }

    public BatteryInfo a() {
        Intent intent = null;
        try {
            intent = this.c.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        } catch (Throwable th) {
            h1.a(th);
        }
        BatteryInfo batteryInfo = new BatteryInfo();
        if (intent == null) {
            batteryInfo.MissingPermission = true;
            return batteryInfo;
        }
        int intExtra = intent.getIntExtra("status", 1);
        BatteryStatusStates batteryStatusStates = BatteryStatusStates.Unknown;
        if (intExtra == 2) {
            batteryStatusStates = BatteryStatusStates.Charging;
        } else if (intExtra == 3) {
            batteryStatusStates = BatteryStatusStates.Discharging;
        } else if (intExtra == 4) {
            batteryStatusStates = BatteryStatusStates.NotCharging;
        } else if (intExtra == 5) {
            batteryStatusStates = BatteryStatusStates.Full;
        }
        batteryInfo.BatteryStatus = batteryStatusStates;
        int intExtra2 = intent.getIntExtra("plugged", -1);
        batteryInfo.BatteryChargePlug = intExtra2 != 1 ? intExtra2 != 2 ? intExtra2 != 4 ? BatteryChargePlugTypes.Unknown : BatteryChargePlugTypes.Wireless : BatteryChargePlugTypes.USB : BatteryChargePlugTypes.AC;
        batteryInfo.BatteryLevel = (intent.getIntExtra(JsonMarshaller.LEVEL, -1) / intent.getIntExtra("scale", -1)) * 100.0f;
        int intExtra3 = intent.getIntExtra("health", -1);
        batteryInfo.BatteryHealth = intExtra3 != 2 ? intExtra3 != 3 ? intExtra3 != 4 ? intExtra3 != 5 ? intExtra3 != 7 ? BatteryHealthStates.Unknown : BatteryHealthStates.Cold : BatteryHealthStates.OverVoltage : BatteryHealthStates.Dead : BatteryHealthStates.Overheat : BatteryHealthStates.Good;
        int intExtra4 = intent.getIntExtra("temperature", -1);
        if (intExtra4 > -1) {
            batteryInfo.BatteryTemp = (intExtra4 / 10.0f) + "";
        }
        int intExtra5 = intent.getIntExtra("voltage", -1);
        if (intExtra5 > -1) {
            batteryInfo.BatteryVoltage = intExtra5;
        }
        batteryInfo.BatteryTechnology = b2.a(intent.getStringExtra("technology"));
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                a(batteryInfo);
            } catch (Throwable th2) {
                h1.a(th2);
            }
        }
        return batteryInfo;
    }
}
