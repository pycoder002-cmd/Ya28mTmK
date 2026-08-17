package cu.uci.android.apklis.device.Service;

import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SilentInstallAccessibilityService extends AccessibilityService {
    Map<Integer, Boolean> handledMap = new HashMap();

    @TargetApi(14)
    private boolean iterateNodesAndHandle(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14 && accessibilityNodeInfo != null) {
            int childCount = accessibilityNodeInfo.getChildCount();
            if ("android.widget.Button".equals(accessibilityNodeInfo.getClassName())) {
                String charSequence = accessibilityNodeInfo.getText().toString();
                Log.d("TAG", "content is " + charSequence);
                if ("Siguiente".equals(charSequence) || "SIGUIENTE".equals(charSequence) || "Next".equals(charSequence) || "NEXT".equals(charSequence) || "Install".equals(charSequence) || "INSTALL".equals(charSequence) || "Instalar".equals(charSequence) || "INSTALAR".equals(charSequence) || "Listo".equals(charSequence) || "OK".equals(charSequence)) {
                    accessibilityNodeInfo.performAction(16);
                    return true;
                }
            } else if ("android.widget.ScrollView".equals(accessibilityNodeInfo.getClassName())) {
                accessibilityNodeInfo.performAction(4096);
            }
            for (int i = 0; i < childCount; i++) {
                if (iterateNodesAndHandle(accessibilityNodeInfo.getChild(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfo source;
        if (Build.VERSION.SDK_INT < 14 || (source = accessibilityEvent.getSource()) == null) {
            return;
        }
        int eventType = accessibilityEvent.getEventType();
        if ((eventType == 2048 || eventType == 32) && this.handledMap.get(Integer.valueOf(accessibilityEvent.getWindowId())) == null && iterateNodesAndHandle(source)) {
            this.handledMap.put(Integer.valueOf(accessibilityEvent.getWindowId()), true);
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }
}
