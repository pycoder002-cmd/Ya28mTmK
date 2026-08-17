package org.androidannotations.api.sharedpreferences;

import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* loaded from: classes2.dex */
public final class SetXmlSerializer {
    private static final String NAMESPACE = "";
    private static final String SET_TAG = "AA_set";
    private static final String STRING_TAG = "AA_string";

    private SetXmlSerializer() {
    }

    public static Set<String> deserialize(String str) {
        TreeSet treeSet = new TreeSet();
        XmlPullParser newPullParser = Xml.newPullParser();
        try {
            newPullParser.setInput(new StringReader(str));
            newPullParser.next();
            newPullParser.require(2, "", SET_TAG);
            while (newPullParser.next() != 3) {
                newPullParser.require(2, "", STRING_TAG);
                newPullParser.next();
                newPullParser.require(4, null, null);
                treeSet.add(newPullParser.getText());
                newPullParser.next();
                newPullParser.require(3, null, STRING_TAG);
            }
            return treeSet;
        } catch (IOException e) {
            Log.w("getStringSet", e);
            return null;
        } catch (XmlPullParserException e2) {
            Log.w("getStringSet", e2);
            return null;
        }
    }

    public static String serialize(Set<String> set) {
        if (set == null) {
            set = Collections.emptySet();
        }
        StringWriter stringWriter = new StringWriter();
        XmlSerializer newSerializer = Xml.newSerializer();
        try {
            newSerializer.setOutput(stringWriter);
            newSerializer.startTag("", SET_TAG);
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                newSerializer.startTag("", STRING_TAG).text(it.next()).endTag("", STRING_TAG);
            }
            newSerializer.endTag("", SET_TAG).endDocument();
        } catch (IOException | IllegalArgumentException | IllegalStateException unused) {
        }
        return stringWriter.toString();
    }
}
