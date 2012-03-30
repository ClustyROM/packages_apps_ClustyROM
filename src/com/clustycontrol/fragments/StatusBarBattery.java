package com.clustycontrol.fragments;

import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;

import com.clustycontrol.R;

public class StatusBarBattery extends PreferenceFragment implements
        OnPreferenceChangeListener {

    private static final String PREF_BATT_BAR = "battery_bar_list";
    private static final String PREF_BATT_BAR_STYLE = "battery_bar_style";
    private static final String PREF_BATT_BAR_COLOR = "battery_bar_color";
    private static final String PREF_BATT_BAR_WIDTH = "battery_bar_thickness";
    private static final String PREF_BATT_ANIMATE = "battery_bar_animate";
    private static final String PREF_BATT_ICON = "battery_icon_list";
	
	/* TEST EXPERIMENTAL */
	/*private static final String WIFI_SIGNAL_COLOR = "wifi_signal_color";
	private static final String MOBILE_SIGNAL_COLOR = "mobile_signal_color";
	private static final String BATTERY_ICON_COLOR = "battery_icon_color";*/

    ListPreference mBatteryIcon;
    ListPreference mBatteryBar;
    ListPreference mBatteryBarStyle;
    ListPreference mBatteryBarThickness;
    CheckBoxPreference mBatteryBarChargingAnimation;
    ColorPickerPreference mBatteryBarColor;
	/* TEST EXPERIMENTAL */
	/*ColorPickerPreference mWifiSignalColor;
	ColorPickerPreference mMobileSignalColor;
	ColorPickerPreference mBatteryIconColor;*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.prefs_statusbar_battery);

        mBatteryIcon = (ListPreference) findPreference(PREF_BATT_ICON);
        mBatteryIcon.setOnPreferenceChangeListener(this);
        mBatteryIcon.setValue((Settings.System.getInt(getActivity()
                .getContentResolver(), Settings.System.STATUSBAR_BATTERY_ICON,
                0))
                + "");
		
		/* TEST EXPERIMENTAL */
		mBatteryIconColor = (ColorPickerPreference) findPreference(BATTERY_ICON_COLOR);
		mBatteryIconColor.setOnPreferenceChangeListener(this);
		
        mBatteryBar = (ListPreference) findPreference(PREF_BATT_BAR);
        mBatteryBar.setOnPreferenceChangeListener(this);
        mBatteryBar.setValue((Settings.System
                .getInt(getActivity().getContentResolver(),
                        Settings.System.STATUSBAR_BATTERY_BAR, 0))
                + "");

        mBatteryBarStyle = (ListPreference) findPreference(PREF_BATT_BAR_STYLE);
        mBatteryBarStyle.setOnPreferenceChangeListener(this);
        mBatteryBarStyle.setValue((Settings.System.getInt(getActivity()
                .getContentResolver(),
                Settings.System.STATUSBAR_BATTERY_BAR_STYLE, 0))
                + "");

        mBatteryBarColor = (ColorPickerPreference) findPreference(PREF_BATT_BAR_COLOR);
        mBatteryBarColor.setOnPreferenceChangeListener(this);

        mBatteryBarChargingAnimation = (CheckBoxPreference) findPreference(PREF_BATT_ANIMATE);
        mBatteryBarChargingAnimation.setChecked(Settings.System.getInt(
                getActivity().getContentResolver(),
                Settings.System.STATUSBAR_BATTERY_BAR_ANIMATE, 0) == 1);

        mBatteryBarThickness = (ListPreference) findPreference(PREF_BATT_BAR_WIDTH);
        mBatteryBarThickness.setOnPreferenceChangeListener(this);
        mBatteryBarThickness.setValue((Settings.System.getInt(getActivity()
                .getContentResolver(),
                Settings.System.STATUSBAR_BATTERY_BAR_THICKNESS, 1))
                + "");
				
		/* TEST EXPERIMENTAL */
		/*mWifiSignalColor = (ColorPickerPreference) findPreference(WIFI_SIGNAL_COLOR);
		mWifiSignalColor.setOnPreferenceChangeListener(this);
		
		mMobileSignalColor = (ColorPickerPreference) findPreference(MOBILE_SIGNAL_COLOR);
		mMobileSignalColor.setOnPreferenceChangeListener(this);*/
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        if (preference == mBatteryBarChargingAnimation) {

            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_BAR_ANIMATE,
                    ((CheckBoxPreference) preference).isChecked() ? 1 : 0);
            return true;

        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mBatteryBarColor) {
            String hex = ColorPickerPreference.convertToARGB(Integer
                    .valueOf(String.valueOf(newValue)));
            preference.setSummary(hex);

            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_BAR_COLOR, intHex);
            return true;

        } else if (preference == mBatteryBar) {

            int val = Integer.parseInt((String) newValue);
            return Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_BAR, val);

        } else if (preference == mBatteryBarStyle) {

            int val = Integer.parseInt((String) newValue);
            return Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_BAR_STYLE, val);

        } else if (preference == mBatteryBarThickness) {

            int val = Integer.parseInt((String) newValue);
            return Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_BAR_THICKNESS, val);

        } else if (preference == mBatteryIcon) {

            int val = Integer.parseInt((String) newValue);
            return Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BATTERY_ICON, val);
        } else if (preference == mBatteryIconColor) {
			
			String hex = ColorPickerPreference.convertToARGB(Integer
					.valueOf(String.valueOf(newValue)));
            preference.setSummary(hex);
			
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.BATTERY_ICON_COLOR, intHex);
            return true;
       /* } else if (preference == mWifiSignalColor) {
            String hex = ColorPickerPreference.convertToARGB(Integer
			.valueOf(String.valueOf(newValue)));
            preference.setSummary(hex);
			
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.WIFI_SIGNAL_COLOR, intHex);
            return true;
        } else if (preference == mMobileSignalColor) {
            String hex = ColorPickerPreference.convertToARGB(Integer
			.valueOf(String.valueOf(newValue)));
            preference.setSummary(hex);
			
            int intHex = ColorPickerPreference.convertToColorInt(hex);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.MOBILE_SIGNAL_COLOR, intHex);
            return true;*/
        } 
        return false;
    }

}
