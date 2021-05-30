package com.example.lesson6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.activity_toolbar);
        setSupportActionBar(toolbar);*/
        RadioButton rContact = findViewById(R.id.radioContact);
        RadioButton rPhone = findViewById(R.id.radioPhone);
        SharedPreferences sharedPreferences = getSharedPreferences(DictionarySettings.PREF_NAME, MODE_PRIVATE);
        DictionarySettings.isSwitchDictionaryFrame = sharedPreferences.getBoolean(DictionarySettings.NAME_SWITCH, false);
        rContact.setChecked(DictionarySettings.isContactDictionaryFrame);
        rPhone.setChecked(DictionarySettings.isPhoneDictionaryFrame);
        rContact.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DictionarySettings.isContactDictionaryFrame = b;
                writeSettings();
                addFragment(new ContactsFragment());
            }
        });

        rPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DictionarySettings.isPhoneDictionaryFrame = b;
                writeSettings();
                addFragment(new PhoneContactFragment());
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem search = menu.findItem(R.id.app_bar_search);
        /*SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(MainActivity.this, newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.id_options_menu:
                //addFragment(new ContactsFragment());
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "option baby", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.id_tools_menu:
                //addFragment(new PhoneContactFragment());
                Toast.makeText(MainActivity.this, "tools click", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void writeSettings() {
        SharedPreferences sharedPreferences = getSharedPreferences(DictionarySettings.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DictionarySettings.NAME_SWITCH, DictionarySettings.isSwitchDictionaryFrame);
        editor.apply();
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragmentVisible = getVisibleFragment(fragmentManager);
        /*if (fragmentVisible != null) {
            fragmentTransaction.remove(fragmentVisible);
        }*/
        fragmentTransaction.replace(R.id.dictionary, fragment);
        fragmentTransaction.commit();
    }
    private Fragment getVisibleFragment(FragmentManager fragmentManager) {
        List<Fragment> fragmentList = fragmentManager.getFragments();
        int sizeList = fragmentList.size();
        for (int i = sizeList - 1; i >=0; i++) {
            Fragment fragment = fragmentList.get(i);
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }
}