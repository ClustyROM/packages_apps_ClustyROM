
package com.clustycontrol.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;

import com.clustycontrol.R;
import com.clustycontrol.SettingsPreferenceFragment;

public class About extends SettingsPreferenceFragment {

    public static final String TAG = "About";

    Preference mSiteUrl;
    Preference mSourceUrl;
    Preference mIrcUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.prefs_about);
        mSiteUrl = findPreference("clustyrom_website");
        mSourceUrl = findPreference("clustyrom_source");
        mIrcUrl = findPreference("clustyrom_irc");

    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if (preference == mSiteUrl) {
            launchUrl("http://www.clust3r.fr/");
        } else if (preference == mSourceUrl) {
            launchUrl("http://github.com/ClustyROM");
        } else if (preference == mIrcUrl) {
            launchUrl("http://chat.voila.fr");
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    private void launchUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent donate = new Intent(Intent.ACTION_VIEW, uriUrl);
        getActivity().startActivity(donate);
    }
}
