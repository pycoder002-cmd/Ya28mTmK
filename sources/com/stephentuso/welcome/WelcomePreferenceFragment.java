package com.stephentuso.welcome;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/* loaded from: classes.dex */
public class WelcomePreferenceFragment extends PreferenceFragmentCompat {
    public static final String KEY_XML_ID = "preference_xml_id";

    public static WelcomePreferenceFragment newInstance(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_XML_ID, i);
        WelcomePreferenceFragment welcomePreferenceFragment = new WelcomePreferenceFragment();
        welcomePreferenceFragment.setArguments(bundle);
        return welcomePreferenceFragment;
    }

    @Override // android.support.v7.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() == null) {
            return;
        }
        addPreferencesFromResource(getArguments().getInt(KEY_XML_ID));
    }

    @Override // android.support.v7.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
    }
}
