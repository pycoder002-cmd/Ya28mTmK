package cu.uci.android.apklis.device;

import android.util.Log;
import cu.uci.android.apklis.MainApp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class RootInstaller {
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x00ff: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:68:0x00fe */
    public boolean install(String str) {
        DataOutputStream dataOutputStream;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        BufferedReader bufferedReader3;
        BufferedReader bufferedReader4 = null;
        bufferedReader4 = null;
        DataOutputStream dataOutputStream2 = null;
        DataOutputStream dataOutputStream3 = null;
        try {
            try {
                try {
                    Process exec = Runtime.getRuntime().exec("su");
                    dataOutputStream = new DataOutputStream(exec.getOutputStream());
                    try {
                        dataOutputStream.write(("pm install -r " + str + "\n").getBytes(Charset.forName("utf-8")));
                        dataOutputStream.flush();
                        dataOutputStream.writeBytes("exit\n");
                        dataOutputStream.flush();
                        exec.waitFor();
                        BufferedReader bufferedReader5 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
                        try {
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader5.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                            }
                            String sb2 = sb.toString();
                            Log.d("Installer", "install msg is " + sb2);
                            if (!sb2.contains("Failure")) {
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                if (bufferedReader5 != null) {
                                    bufferedReader5.close();
                                }
                                return true;
                            }
                            if (sb2.equals("Permission denied")) {
                                if (dataOutputStream != null) {
                                    dataOutputStream.close();
                                }
                                if (bufferedReader5 != null) {
                                    bufferedReader5.close();
                                }
                                return false;
                            }
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (bufferedReader5 != null) {
                                bufferedReader5.close();
                            }
                            return false;
                        } catch (IOException e) {
                            bufferedReader2 = bufferedReader5;
                            e = e;
                            dataOutputStream2 = dataOutputStream;
                            MainApp.log("RootInstaller", e);
                            e.printStackTrace();
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            return false;
                        } catch (Exception e2) {
                            bufferedReader = bufferedReader5;
                            e = e2;
                            dataOutputStream3 = dataOutputStream;
                            MainApp.log("RootInstaller", e);
                            e.printStackTrace();
                            if (dataOutputStream3 != null) {
                                dataOutputStream3.close();
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return false;
                        } catch (Throwable th) {
                            bufferedReader4 = bufferedReader5;
                            th = th;
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (bufferedReader4 != null) {
                                bufferedReader4.close();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader2 = null;
                    } catch (Exception e4) {
                        e = e4;
                        bufferedReader = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dataOutputStream = null;
                    bufferedReader4 = bufferedReader3;
                }
            } catch (IOException e5) {
                e = e5;
                bufferedReader2 = null;
            } catch (Exception e6) {
                e = e6;
                bufferedReader = null;
            } catch (Throwable th4) {
                th = th4;
                dataOutputStream = null;
            }
        } catch (IOException e7) {
            MainApp.log("RootInstaller", e7);
            return false;
        }
    }
}
