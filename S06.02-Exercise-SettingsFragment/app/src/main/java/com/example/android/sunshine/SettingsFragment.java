package com.example.android.sunshine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
// COMPLETED (4) Create SettingsFragment and extend PreferenceFragmentCompat
// COMPLETED (10) Implement OnSharedPreferenceChangeListener from SettingsFragment
public class SettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // COMPLETED (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
        addPreferencesFromResource(R.xml.pref_general);

        // Do step 9 within onCreatePreference
        // COMPLETED (9) Set the preference summary on each preference that isn't a CheckBoxPreference
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceScreen.getPreference(i);

            if (!(preference instanceof CheckBoxPreference)) {
                String stringValue = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, stringValue);
            }
        }
    }


    @Override
    public void onStop() {
        super.onStop();

        // COMPLETED (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

        // COMPLETED (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    // COMPLETED (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
    private void setPreferenceSummary(Preference preference, Object object) {
        String value = object.toString();

        preference.setSummary(value);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // COMPLETED (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
        Preference preference = findPreference(key);

        if (preference != null && !(preference instanceof CheckBoxPreference)) {
            setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
        }
    }
}
