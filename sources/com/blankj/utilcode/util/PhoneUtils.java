package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Xml;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jacoco.agent.rt.internal_b0d6a23.core.runtime.AgentOptions;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void call(String str) {
        Utils.getContext().startActivity(IntentUtils.getCallIntent(str));
    }

    public static void dial(String str) {
        Utils.getContext().startActivity(IntentUtils.getDialIntent(str));
    }

    public static List<HashMap<String, String>> getAllContactInfo() {
        SystemClock.sleep(3000L);
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = Utils.getContext().getContentResolver();
        Uri parse = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri parse2 = Uri.parse("content://com.android.contacts/data");
        Cursor query = contentResolver.query(parse, new String[]{"contact_id"}, null, null, null);
        if (query != null) {
            while (query.moveToNext()) {
                try {
                    String string = query.getString(0);
                    if (!StringUtils.isEmpty(string)) {
                        Cursor query2 = contentResolver.query(parse2, new String[]{"data1", "mimetype"}, "raw_contact_id=?", new String[]{string}, null);
                        HashMap hashMap = new HashMap();
                        if (query2 != null) {
                            while (query2.moveToNext()) {
                                String string2 = query2.getString(0);
                                String string3 = query2.getString(1);
                                if (string3.equals("vnd.android.cursor.item/phone_v2")) {
                                    hashMap.put("phone", string2);
                                } else if (string3.equals("vnd.android.cursor.item/name")) {
                                    hashMap.put("name", string2);
                                }
                            }
                        }
                        arrayList.add(hashMap);
                        if (query2 != null) {
                            query2.close();
                        }
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
        }
        return arrayList;
    }

    public static void getAllSMS() {
        Cursor query = Utils.getContext().getContentResolver().query(Uri.parse("content://sms"), new String[]{AgentOptions.ADDRESS, "date", "type", "body"}, null, null, null);
        query.getCount();
        XmlSerializer newSerializer = Xml.newSerializer();
        try {
            newSerializer.setOutput(new FileOutputStream(new File("/mnt/sdcard/backupsms.xml")), "utf-8");
            newSerializer.startDocument("utf-8", true);
            newSerializer.startTag(null, "smss");
            while (query.moveToNext()) {
                SystemClock.sleep(1000L);
                newSerializer.startTag(null, "sms");
                newSerializer.startTag(null, AgentOptions.ADDRESS);
                String string = query.getString(0);
                newSerializer.text(string);
                newSerializer.endTag(null, AgentOptions.ADDRESS);
                newSerializer.startTag(null, "date");
                String string2 = query.getString(1);
                newSerializer.text(string2);
                newSerializer.endTag(null, "date");
                newSerializer.startTag(null, "type");
                String string3 = query.getString(2);
                newSerializer.text(string3);
                newSerializer.endTag(null, "type");
                newSerializer.startTag(null, "body");
                String string4 = query.getString(3);
                newSerializer.text(string4);
                newSerializer.endTag(null, "body");
                newSerializer.endTag(null, "sms");
                System.out.println("address:" + string + "   date:" + string2 + "  type:" + string3 + "  body:" + string4);
            }
            newSerializer.endTag(null, "smss");
            newSerializer.endDocument();
            newSerializer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getContactNum() {
        Log.d("tips", "U should copy the following code.");
    }

    @SuppressLint({"HardwareIds"})
    public static String getIMEI() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    @SuppressLint({"HardwareIds"})
    public static String getIMSI() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    @SuppressLint({"HardwareIds"})
    public static String getPhoneStatus() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        return (((((((((((((("DeviceId(IMEI) = " + telephonyManager.getDeviceId() + "\n") + "DeviceSoftwareVersion = " + telephonyManager.getDeviceSoftwareVersion() + "\n") + "Line1Number = " + telephonyManager.getLine1Number() + "\n") + "NetworkCountryIso = " + telephonyManager.getNetworkCountryIso() + "\n") + "NetworkOperator = " + telephonyManager.getNetworkOperator() + "\n") + "NetworkOperatorName = " + telephonyManager.getNetworkOperatorName() + "\n") + "NetworkType = " + telephonyManager.getNetworkType() + "\n") + "PhoneType = " + telephonyManager.getPhoneType() + "\n") + "SimCountryIso = " + telephonyManager.getSimCountryIso() + "\n") + "SimOperator = " + telephonyManager.getSimOperator() + "\n") + "SimOperatorName = " + telephonyManager.getSimOperatorName() + "\n") + "SimSerialNumber = " + telephonyManager.getSimSerialNumber() + "\n") + "SimState = " + telephonyManager.getSimState() + "\n") + "SubscriberId(IMSI) = " + telephonyManager.getSubscriberId() + "\n") + "VoiceMailNumber = " + telephonyManager.getVoiceMailNumber() + "\n";
    }

    public static int getPhoneType() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getPhoneType();
        }
        return -1;
    }

    public static String getSimOperatorByMnc() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        String simOperator = telephonyManager != null ? telephonyManager.getSimOperator() : null;
        if (simOperator == null) {
            return null;
        }
        char c = 65535;
        int hashCode = simOperator.hashCode();
        if (hashCode != 49679477) {
            switch (hashCode) {
                case 49679470:
                    if (simOperator.equals("46000")) {
                        c = 0;
                        break;
                    }
                    break;
                case 49679471:
                    if (simOperator.equals("46001")) {
                        c = 3;
                        break;
                    }
                    break;
                case 49679472:
                    if (simOperator.equals("46002")) {
                        c = 1;
                        break;
                    }
                    break;
                case 49679473:
                    if (simOperator.equals("46003")) {
                        c = 4;
                        break;
                    }
                    break;
            }
        } else if (simOperator.equals("46007")) {
            c = 2;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
                return "中国移动";
            case 3:
                return "中国联通";
            case 4:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static String getSimOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getSimOperatorName();
        }
        return null;
    }

    public static boolean isPhone() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        return (telephonyManager == null || telephonyManager.getPhoneType() == 0) ? false : true;
    }

    public static boolean isSimCardReady() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        return telephonyManager != null && telephonyManager.getSimState() == 5;
    }

    public static void sendSms(String str, String str2) {
        Utils.getContext().startActivity(IntentUtils.getSendSmsIntent(str, str2));
    }

    public static void sendSmsSilent(String str, String str2) {
        if (StringUtils.isEmpty(str2)) {
            return;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(Utils.getContext(), 0, new Intent(), 0);
        SmsManager smsManager = SmsManager.getDefault();
        if (str2.length() < 70) {
            smsManager.sendTextMessage(str, null, str2, broadcast, null);
            return;
        }
        Iterator<String> it = smsManager.divideMessage(str2).iterator();
        while (it.hasNext()) {
            smsManager.sendTextMessage(str, null, it.next(), broadcast, null);
        }
    }
}
