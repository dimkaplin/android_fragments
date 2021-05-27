package com.example.lesson6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

public class DictionaryFragment extends Fragment {

    private boolean isLandscape;

    public  final int DefIndex = 0;


    public DictionaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        SwitchCompat switchCompat = v.findViewById(R.id.switchFrame);

        switchCompat.setChecked(DictionarySettings.isSwitchDictionaryFrame);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                DictionarySettings.isSwitchDictionaryFrame = b;
                //addFragment(new ContactsFragment());
            }
        });

        return v;
    }







    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
       if(isLandscape) {
            showImageFragment(DefIndex);
       }
    }

    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout constraintLayout = (LinearLayout) view;
        String[] dictionary = getResources().getStringArray(R.array.dictionary);
        for (int i=0; i < dictionary.length; i++) {
            String item = dictionary[i];
            TextView tv = new TextView(getContext());
            tv.setText(item);
            tv.setLines(3);
            tv.setTextSize(25);
            constraintLayout.addView(tv);
            final int currentIndex = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Intent intent = new Intent();
                    intent.setClass(getActivity(), ToolDictionaryActivity.class);
                    intent.putExtra(ImageFragment.PARAM_INDEX, currentIndex);
                    startActivity(intent);*/
                    showImageFragment(currentIndex);
                }
            });
        }
    }
    void showPortImageFragment(int defIndex) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), ToolDictionaryActivity.class);
        intent.putExtra(ImageFragment.PARAM_INDEX, defIndex);
        startActivity(intent);
    }

    void showImageFragment(int index) {
        if(isLandscape) {
            showLandImageFragment(index);
        }
        else{
            showPortImageFragment(index);
        }
    }

    void showLandImageFragment(int index) {
        ImageFragment imageFragment = ImageFragment.newInstance(index);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.image_landscape, imageFragment)
                .commit();
    }
}