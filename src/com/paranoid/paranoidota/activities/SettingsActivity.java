/*
 * Copyright 2014 ParanoidAndroid Project
 *
 * This file is part of Paranoid OTA.
 *
 * Paranoid OTA is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Paranoid OTA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Paranoid OTA.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.paranoid.paranoidota.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.paranoid.paranoidota.R;
import com.paranoid.paranoidota.URLStringReader;
import com.paranoid.paranoidota.URLStringReader.URLStringReaderListener;
import com.paranoid.paranoidota.Utils;
import com.paranoid.paranoidota.helpers.SettingsHelper;
import com.paranoid.paranoidota.widget.Preference;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SettingsActivity extends PreferenceActivity implements
        OnSharedPreferenceChangeListener {

    private SettingsHelper mSettingsHelper;
    private ListPreference mCheckTime;

    @Override
    @SuppressWarnings("deprecation")
    protected void onCreate(Bundle savedInstanceState) {

        mSettingsHelper = new SettingsHelper(this);

        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        addPreferencesFromResource(R.layout.activity_settings);

        mCheckTime = (ListPreference) findPreference(SettingsHelper.PROPERTY_CHECK_TIME);

        mCheckTime.setValue(String.valueOf(mSettingsHelper.getCheckTime()));

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    @Deprecated
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            android.preference.Preference preference) {

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (SettingsHelper.PROPERTY_CHECK_TIME.equals(key)) {
            Utils.setAlarm(this, mSettingsHelper.getCheckTime(), false, true);
        }
    }
}
