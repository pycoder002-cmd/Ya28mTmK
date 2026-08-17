package com.startapp;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d5 {
    public final Node a;
    public final boolean b;

    public d5(String str) throws ParserConfigurationException, SAXException, IOException {
        String replaceFirst = str.replaceFirst("<\\?.*\\?>", "");
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setCoalescing(true);
        newInstance.setExpandEntityReferences(false);
        this.a = newInstance.newDocumentBuilder().parse(new InputSource(new StringReader(replaceFirst))).getFirstChild();
        this.b = true;
    }

    public d5(Node node) {
        this.a = node;
        this.b = false;
    }

    public final String a(String str) {
        Node namedItem = this.a.getAttributes().getNamedItem(str);
        if (namedItem != null) {
            return namedItem.getNodeValue();
        }
        return null;
    }

    public final String a(String str, String str2) {
        d5 b;
        d5 b2 = b(str2, null, null);
        if (b2 == null || (b = b2.b(str, null, null)) == null) {
            return null;
        }
        return b.b();
    }

    public List<d5> a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) a("Creative", "Creatives", null, null)).iterator();
        while (it.hasNext()) {
            d5 b = ((d5) it.next()).b("Linear", null, null);
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public final List<d5> a(String str, String str2, String str3, List<String> list) {
        ArrayList arrayList = new ArrayList();
        d5 b = b(str2, null, null);
        return b == null ? arrayList : b.a(str, str3, list);
    }

    public final List<d5> a(String str, String str2, List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) d.a(this.a, str, str2, list)).iterator();
        while (it.hasNext()) {
            arrayList.add(new d5((Node) it.next()));
        }
        return arrayList;
    }

    public final d5 b(String str, String str2, List<String> list) {
        ArrayList arrayList = (ArrayList) d.a(this.a, str, str2, (List<String>) null);
        Node node = arrayList.isEmpty() ? null : (Node) arrayList.get(0);
        if (node != null) {
            return new d5(node);
        }
        return null;
    }

    public final Integer b(String str) {
        try {
            String a = a(str);
            if (a != null) {
                return Integer.valueOf(Integer.parseInt(a));
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public final String b() {
        Node node = this.a;
        if (node.getFirstChild() == null || node.getFirstChild().getNodeValue() == null) {
            return null;
        }
        return node.getFirstChild().getNodeValue().trim();
    }

    public final List<String> b(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) a(str, str2, null, null)).iterator();
        while (it.hasNext()) {
            String b = ((d5) it.next()).b();
            if (!TextUtils.isEmpty(b)) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public final List<String> c(String str) {
        List<d5> a = a(str, null, null);
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) a).iterator();
        while (it.hasNext()) {
            String b = ((d5) it.next()).b();
            if (!TextUtils.isEmpty(b)) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public final List<d5> d(String str) {
        return a("Tracking", "TrackingEvents", NotificationCompat.CATEGORY_EVENT, Collections.singletonList(str));
    }

    public List<String> e(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((ArrayList) d(str)).iterator();
        while (it.hasNext()) {
            String b = ((d5) it.next()).b();
            if (!TextUtils.isEmpty(b)) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    public final String f(String str) {
        d5 b = b(str, null, null);
        if (b == null) {
            return null;
        }
        return b.b();
    }
}
