package com.startapp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m1 {
    private static final String a = "m1";
    private static final int b = 32;
    private static final int c = 40;
    private static final int d = 48;
    private static final int e = 123;
    private static final int f = 3;
    private static final int g = 3;
    private static final long h = 2208988800L;
    private long i;

    private long a(byte[] bArr, int i) {
        int i2 = bArr[i];
        int i3 = bArr[i + 1];
        int i4 = bArr[i + 2];
        int i5 = bArr[i + 3];
        if ((i2 & 128) == 128) {
            i2 = (i2 & Opcodes.LAND) + 128;
        }
        if ((i3 & 128) == 128) {
            i3 = (i3 & Opcodes.LAND) + 128;
        }
        if ((i4 & 128) == 128) {
            i4 = (i4 & Opcodes.LAND) + 128;
        }
        if ((i5 & 128) == 128) {
            i5 = (i5 & Opcodes.LAND) + 128;
        }
        return (i2 << 24) + (i3 << 16) + (i4 << 8) + i5;
    }

    private long b(byte[] bArr, int i) {
        return ((a(bArr, i) - h) * 1000) + ((a(bArr, i + 4) * 1000) / 4294967296L);
    }

    private void c(byte[] bArr, int i) {
        for (int i2 = i; i2 < i + 8; i2++) {
            bArr[i2] = 0;
        }
    }

    public long a() {
        return this.i;
    }

    public boolean a(String str, int i) {
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
            try {
                datagramSocket.setSoTimeout(i);
                byte[] bArr = new byte[48];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, InetAddress.getByName(str), 123);
                bArr[0] = 27;
                c(bArr, 40);
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(new DatagramPacket(bArr, 48));
                datagramSocket.close();
                this.i = b(bArr, 32);
                datagramSocket.close();
                return true;
            } catch (Throwable th) {
                th = th;
                try {
                    h1.a(th);
                    return false;
                } finally {
                    if (datagramSocket != null) {
                        datagramSocket.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            datagramSocket = null;
        }
    }
}
