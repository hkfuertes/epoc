package com.upna.ibio16.epoc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import com.upna.ibio16.epoc.R;
import com.upna.ibio16.epoc.model.Usuario;


public class UserPreferencesFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

    Usuario usuario;

    private void bindPreferenceSummaryToValue(Preference preference, String value) {
        if(preference != null) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(this);

            // Trigger the listener immediately with the preference's
            // current value.
            this.onPreferenceChange(preference, value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        setHasOptionsMenu(true);

        //Recuperamos el usuario
        usuario = Usuario.findById(Usuario.class,1);

        // Bind the summaries of EditText/List/Dialog/Ringtone preferences
        // to their values. When their values change, their summaries are
        // updated to reflect the new value, per the Android Design
        // guidelines.
        bindPreferenceSummaryToValue(findPreference("user_name"), usuario.getNombre());
        bindPreferenceSummaryToValue(findPreference("user_lastname"), usuario.getApellidos());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            startActivity(new Intent(getActivity(), SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void paintSummary(Preference preference, Object value){
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list.
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(stringValue);

            // Set the summary to reflect the new value.
            preference.setSummary(
                    index >= 0
                            ? listPreference.getEntries()[index]
                            : null);

        } else {
            // For all other preferences, set the summary to the value's
            // simple string representation.
            preference.setSummary(stringValue);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        //Paint Summary
        this.paintSummary(preference,value);
        String stringValue = value.toString();

        switch (preference.getKey()){
            case "user_name":
                usuario.setNombre(stringValue);
                break;
            case "user_lastname":
                usuario.setApellidos(stringValue);
                break;
            case "user_date":
                usuario.setFechaNacimiento(stringValue, "YYYY-MM-dd");
                break;
            case "user_sex":
                usuario.setSexo(stringValue);
                break;
        }
        usuario.save();

        return true;
    }
}