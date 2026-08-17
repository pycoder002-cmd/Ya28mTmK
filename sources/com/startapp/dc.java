package com.startapp;

import android.content.Context;
import android.os.Build;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityTdscdma;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoTdscdma;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import io.sentry.DefaultSentryClientFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class dc extends yb<cc> {
    public final TelephonyManager.CellInfoCallback j;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends TelephonyManager.CellInfoCallback {
        public a() {
        }

        @Override // android.telephony.TelephonyManager.CellInfoCallback
        public void onCellInfo(List<CellInfo> list) {
            dc.this.b(false);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Comparator<CellInfo> {
        public b(dc dcVar) {
        }

        @Override // java.util.Comparator
        public int compare(CellInfo cellInfo, CellInfo cellInfo2) {
            CellInfo cellInfo3 = cellInfo;
            CellInfo cellInfo4 = cellInfo2;
            if (cellInfo3.isRegistered() == cellInfo4.isRegistered()) {
                return dc.a(cellInfo4) - dc.a(cellInfo3);
            }
            if (cellInfo3.isRegistered()) {
                return -1;
            }
            return cellInfo4.isRegistered() ? 1 : 0;
        }
    }

    public dc(Context context, p5 p5Var, l9 l9Var) {
        super(context, p5Var, l9Var, "c9c194d3e01bcf14", "086ea3852ae4e475");
        if (Build.VERSION.SDK_INT >= 29) {
            this.j = new a();
        } else {
            this.j = null;
        }
    }

    public static int a(CellInfo cellInfo) {
        CellSignalStrength cellSignalStrength;
        if (cellInfo instanceof CellInfoCdma) {
            cellSignalStrength = ((CellInfoCdma) cellInfo).getCellSignalStrength();
        } else if (cellInfo instanceof CellInfoGsm) {
            cellSignalStrength = ((CellInfoGsm) cellInfo).getCellSignalStrength();
        } else if (cellInfo instanceof CellInfoLte) {
            cellSignalStrength = ((CellInfoLte) cellInfo).getCellSignalStrength();
        } else {
            int i = Build.VERSION.SDK_INT;
            cellSignalStrength = (i < 29 || !(cellInfo instanceof CellInfoNr)) ? (i < 29 || !(cellInfo instanceof CellInfoTdscdma)) ? (i < 18 || !(cellInfo instanceof CellInfoWcdma)) ? null : ((CellInfoWcdma) cellInfo).getCellSignalStrength() : ((CellInfoTdscdma) cellInfo).getCellSignalStrength() : ((CellInfoNr) cellInfo).getCellSignalStrength();
        }
        if (cellSignalStrength != null) {
            return cellSignalStrength.getLevel();
        }
        return 0;
    }

    @Override // com.startapp.yb
    public cc a(String str) {
        if (str != null) {
            cc ccVar = cc.a;
            try {
                return new cc(new JSONObject(str));
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public final void a(cc ccVar, CellLocation cellLocation) {
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            ccVar.a(4, wa.b(String.valueOf(gsmCellLocation.getCid())));
            ccVar.a(3, wa.b(String.valueOf(gsmCellLocation.getLac())));
        } else if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            ccVar.a(1, wa.b(String.valueOf(cdmaCellLocation.getBaseStationLatitude())));
            ccVar.a(2, wa.b(String.valueOf(cdmaCellLocation.getBaseStationLongitude())));
        }
    }

    public void a(cc ccVar, List<CellInfo> list) {
        CellIdentityWcdma cellIdentity;
        int tac;
        int timingAdvance;
        int tac2;
        CellSignalStrengthGsm cellSignalStrength;
        int timingAdvance2;
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new b(this));
        ccVar.a(6, wa.b(arrayList.toString()));
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            CellInfo cellInfo = (CellInfo) it.next();
            if (z) {
                z = false;
                if (cellInfo instanceof CellInfoCdma) {
                    CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
                    if (cellIdentity2 != null) {
                        int latitude = cellIdentity2.getLatitude();
                        int longitude = cellIdentity2.getLongitude();
                        if (latitude != Integer.MAX_VALUE && longitude != Integer.MAX_VALUE) {
                            ccVar.a(1, wa.b(String.valueOf(latitude)));
                            ccVar.a(2, wa.b(String.valueOf(longitude)));
                        }
                    }
                } else if (cellInfo instanceof CellInfoGsm) {
                    CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                    CellIdentityGsm cellIdentity3 = cellInfoGsm.getCellIdentity();
                    if (cellIdentity3 != null) {
                        int lac = cellIdentity3.getLac();
                        if (lac != Integer.MAX_VALUE) {
                            ccVar.a(3, wa.b(String.valueOf(lac)));
                        }
                        int cid = cellIdentity3.getCid();
                        if (cid != Integer.MAX_VALUE) {
                            ccVar.a(4, wa.b(String.valueOf(cid)));
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 26 && (cellSignalStrength = cellInfoGsm.getCellSignalStrength()) != null && (timingAdvance2 = cellSignalStrength.getTimingAdvance()) != Integer.MAX_VALUE) {
                        ccVar.a(13, String.valueOf(timingAdvance2));
                    }
                } else if (cellInfo instanceof CellInfoLte) {
                    CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                    CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
                    if (cellIdentity4 != null && (tac2 = cellIdentity4.getTac()) != Integer.MAX_VALUE) {
                        ccVar.a(5, wa.b(String.valueOf(tac2)));
                    }
                    CellSignalStrengthLte cellSignalStrength2 = cellInfoLte.getCellSignalStrength();
                    if (cellSignalStrength2 != null && (timingAdvance = cellSignalStrength2.getTimingAdvance()) != Integer.MAX_VALUE) {
                        ccVar.a(13, String.valueOf(timingAdvance));
                    }
                } else {
                    int i = Build.VERSION.SDK_INT;
                    if (i >= 29 && (cellInfo instanceof CellInfoNr)) {
                        CellIdentityNr cellIdentityNr = (CellIdentityNr) ((CellInfoNr) cellInfo).getCellIdentity();
                        if (cellIdentityNr != null && (tac = cellIdentityNr.getTac()) != Integer.MAX_VALUE) {
                            ccVar.a(5, wa.b(String.valueOf(tac)));
                        }
                    } else if (i >= 29 && (cellInfo instanceof CellInfoTdscdma)) {
                        CellIdentityTdscdma cellIdentity5 = ((CellInfoTdscdma) cellInfo).getCellIdentity();
                        if (cellIdentity5 != null) {
                            int lac2 = cellIdentity5.getLac();
                            if (lac2 != Integer.MAX_VALUE) {
                                ccVar.a(3, wa.b(String.valueOf(lac2)));
                            }
                            int cid2 = cellIdentity5.getCid();
                            if (cid2 != Integer.MAX_VALUE) {
                                ccVar.a(4, wa.b(String.valueOf(cid2)));
                            }
                        }
                    } else if (i >= 18 && (cellInfo instanceof CellInfoWcdma) && (cellIdentity = ((CellInfoWcdma) cellInfo).getCellIdentity()) != null) {
                        int lac3 = cellIdentity.getLac();
                        if (lac3 != Integer.MAX_VALUE) {
                            ccVar.a(3, wa.b(String.valueOf(lac3)));
                        }
                        int cid3 = cellIdentity.getCid();
                        if (cid3 != Integer.MAX_VALUE) {
                            ccVar.a(4, wa.b(String.valueOf(cid3)));
                        }
                    }
                }
            }
            if (!cellInfo.isRegistered()) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 29 && (cellInfo instanceof CellInfoNr)) {
                ccVar.a(14, 1);
            }
        }
    }

    public void b(boolean z) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
            cc ccVar = new cc(new JSONObject());
            ccVar.a(7, Integer.valueOf(telephonyManager.getSimState()));
            ccVar.a(8, telephonyManager.getSimOperator());
            ccVar.a(9, telephonyManager.getSimOperatorName());
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                ccVar.a(15, String.valueOf(telephonyManager.getSimCarrierId()));
                ccVar.a(16, String.valueOf(telephonyManager.getSimCarrierIdName()));
            }
            ccVar.a(10, Integer.valueOf(telephonyManager.getPhoneType()));
            ccVar.a(11, wa.b(telephonyManager.getNetworkOperator()));
            ccVar.a(12, wa.b(telephonyManager.getNetworkOperatorName()));
            if (ya.a(this.a, "android.permission.ACCESS_FINE_LOCATION")) {
                if (i >= 29 && z) {
                    telephonyManager.requestCellInfoUpdate(this.f, this.j);
                }
                if (i >= 17) {
                    List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
                    if (allCellInfo != null) {
                        a(ccVar, allCellInfo);
                    }
                } else {
                    a(ccVar, telephonyManager.getCellLocation());
                }
            }
            b((dc) ccVar);
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }

    @Override // com.startapp.sb
    public Object c() {
        return cc.a;
    }

    @Override // com.startapp.yb
    public String c(cc ccVar) {
        return ccVar.b.toString();
    }

    @Override // com.startapp.yb
    public long d() {
        return DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
    }

    @Override // com.startapp.yb
    public boolean f() {
        return true;
    }

    @Override // com.startapp.yb
    public void g() {
        b(true);
    }
}
