package com.startapp;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.startapp.networkTest.data.radio.NetworkRegistrationInfo;
import com.startapp.networkTest.enums.ThreeStateShort;
import cz.msebera.android.httpclient.cookie.ClientCookie;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w1 {
    private static NetworkRegistrationInfo a(String[] strArr) {
        NetworkRegistrationInfo networkRegistrationInfo = new NetworkRegistrationInfo();
        for (String str : strArr) {
            if (str.startsWith("transportType")) {
                networkRegistrationInfo.TransportType = e(d(str));
            } else if (str.startsWith(ClientCookie.DOMAIN_ATTR)) {
                networkRegistrationInfo.Domain = d(str);
            } else if (str.startsWith("regState")) {
                networkRegistrationInfo.RegState = d(str);
            } else if (str.startsWith("accessNetworkTechnology")) {
                networkRegistrationInfo.NetworkTechnology = d(str);
            } else if (str.startsWith("reasonForDenial")) {
                networkRegistrationInfo.ReasonForDenial = d(str);
            } else if (str.startsWith("emergencyEnabled")) {
                networkRegistrationInfo.EmergencyEnabled = d(str).equals("true");
            } else if (str.startsWith("mIsUsingCarrierAggregation")) {
                networkRegistrationInfo.CarrierAggregation = d(str).equals("true") ? ThreeStateShort.Yes : ThreeStateShort.No;
            } else {
                String str2 = "";
                if (str.startsWith("cellIdentity")) {
                    String d = d(str);
                    networkRegistrationInfo.CellTechnology = d;
                    networkRegistrationInfo.CellTechnology = d.replace("CellIdentity", "");
                } else if (str.startsWith("mCid") || str.startsWith("mCi") || str.startsWith("mNetworkId") || str.startsWith("mNci")) {
                    networkRegistrationInfo.CellId = d(str);
                } else if (str.startsWith("mLac") || str.startsWith("mTac") || str.startsWith("mSystemId")) {
                    networkRegistrationInfo.Tac = d(str);
                } else if (str.startsWith("mBsic") || str.startsWith("mPsc") || str.startsWith("mPci") || str.startsWith("mBasestationId")) {
                    String d2 = d(str);
                    if (!d2.startsWith("0x") || d2.length() <= 2) {
                        str2 = d2;
                    } else {
                        try {
                            str2 = String.valueOf((int) Long.parseLong(d2.substring(2), 16));
                        } catch (Throwable th) {
                            h1.a(th);
                        }
                    }
                    networkRegistrationInfo.Pci = str2;
                } else if (str.startsWith("mArfcn") || str.startsWith("mUarfcn") || str.startsWith("mEarfcn") || str.startsWith("mNrArfcn")) {
                    try {
                        networkRegistrationInfo.Arfcn = Integer.parseInt(d(str));
                    } catch (Throwable th2) {
                        h1.a(th2);
                    }
                } else if (str.startsWith("mBandwidth")) {
                    try {
                        networkRegistrationInfo.Bandwidth = Integer.parseInt(d(str));
                    } catch (Throwable th3) {
                        h1.a(th3);
                    }
                } else if (str.startsWith("mMcc")) {
                    networkRegistrationInfo.Mcc = d(str);
                } else if (str.startsWith("mMnc")) {
                    networkRegistrationInfo.Mnc = d(str);
                } else if (str.startsWith("mAlphaLong")) {
                    networkRegistrationInfo.OperatorLong = d(str);
                } else if (str.startsWith("mAlphaShort")) {
                    networkRegistrationInfo.OperatorShort = d(str);
                } else if (str.startsWith("mMaxDataCalls")) {
                    try {
                        networkRegistrationInfo.MaxDataCalls = Integer.parseInt(d(str));
                    } catch (Throwable th4) {
                        h1.a(th4);
                    }
                } else if (str.startsWith("availableServices")) {
                    networkRegistrationInfo.AvailableServices = d(str);
                } else if (str.startsWith("nrState") || str.startsWith("nrStatus")) {
                    networkRegistrationInfo.NrState = d(str);
                } else if (str.startsWith("isDcNrRestricted")) {
                    networkRegistrationInfo.DcNrRestricted = d(str).equals("true") ? ThreeStateShort.Yes : ThreeStateShort.No;
                } else if (str.startsWith("isNrAvailable")) {
                    networkRegistrationInfo.NrAvailable = d(str).equals("true") ? ThreeStateShort.Yes : ThreeStateShort.No;
                } else if (str.startsWith("isEnDcAvailable")) {
                    networkRegistrationInfo.EnDcAvailable = d(str).equals("true") ? ThreeStateShort.Yes : ThreeStateShort.No;
                }
            }
        }
        return networkRegistrationInfo;
    }

    private static String a(String str) {
        return str.replace("isDcNrRestricted = false", "isDcNrRestricted=false").replace("isDcNrRestricted = true", "isDcNrRestricted=true").replace("isNrAvailable = false", "isNrAvailable=false").replace("isNrAvailable = true", "isNrAvailable=true").replace("isEnDcAvailable = false", "isEnDcAvailable=false").replace("isEnDcAvailable = true", "isEnDcAvailable=true").replace("mIsUsingCarrierAggregation = false", "mIsUsingCarrierAggregation=false").replace("mIsUsingCarrierAggregation = true", "mIsUsingCarrierAggregation=true");
    }

    private static String[] b(String str) {
        return str.replace("NetworkRegistrationState", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replace("NetworkRegistrationInfo", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replace("}", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replace("{", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).replace(":", "").replaceAll(" +", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).trim().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public static NetworkRegistrationInfo[] c(String str) {
        String str2 = "mNetworkRegistrationStates=";
        try {
            int indexOf = str.indexOf("mNetworkRegistrationStates=");
            if (indexOf == -1) {
                str2 = "mNetworkRegistrationInfos=";
                indexOf = str.indexOf("mNetworkRegistrationInfos=");
            }
            if (indexOf == -1) {
                return new NetworkRegistrationInfo[0];
            }
            String replaceAll = str.substring(indexOf).substring(str2.length() + 1).replaceAll("\\[\\w@", "@");
            int indexOf2 = replaceAll.indexOf("]");
            int indexOf3 = replaceAll.indexOf("[");
            while (indexOf3 != -1 && indexOf2 > indexOf3) {
                replaceAll = replaceAll.replaceFirst("\\[", "").replaceFirst("]", "");
                indexOf3 = replaceAll.indexOf("[");
                indexOf2 = replaceAll.indexOf("]");
            }
            String[] split = replaceAll.substring(0, indexOf2).split(", ");
            NetworkRegistrationInfo[] networkRegistrationInfoArr = new NetworkRegistrationInfo[split.length];
            for (int i = 0; i < split.length; i++) {
                split[i] = a(split[i]);
                split[i] = split[i].trim();
                networkRegistrationInfoArr[i] = a(b(split[i]));
            }
            return networkRegistrationInfoArr;
        } catch (Throwable th) {
            h1.a(th);
            return new NetworkRegistrationInfo[0];
        }
    }

    private static String d(String str) {
        String[] split = str.split("=");
        return split.length > 1 ? split[1] : "";
    }

    private static String e(String str) {
        try {
            int parseInt = Integer.parseInt(str);
            return parseInt != 1 ? parseInt != 2 ? Integer.toString(parseInt) : "WLAN" : "WWAN";
        } catch (Throwable th) {
            h1.b(th);
            return str;
        }
    }
}
