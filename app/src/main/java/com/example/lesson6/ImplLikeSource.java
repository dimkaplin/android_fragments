package com.example.lesson6;

import android.content.res.Resources;
import android.content.res.TypedArray;

import java.util.ArrayList;
import java.util.List;

public class ImplLikeSource implements LikeSource {
    private List<DataLike> listDataLike;
    private Resources resources;

    public ImplLikeSource(Resources resources) {
        this.resources = resources;
        listDataLike = new ArrayList<>(4);
    }

    public ImplLikeSource init() {
        String[] desriptions = resources.getStringArray(R.array.description);
        String[] dictionaries = resources.getStringArray(R.array.dictionary);
        TypedArray pictures = resources.obtainTypedArray(R.array.pictures);
        int[] images = new int[pictures.length()];
        for (int i = 0; i<images.length; i++) {
            images[i] = pictures.getResourceId(i, 0);
        }
        for (int i =0; i < dictionaries.length; i++) {
            listDataLike.add(new DataLike(dictionaries[i], desriptions[i], images[i], false));
        }

        return this;
    }

    @Override
    public DataLike getDataLike(int position) {
        return listDataLike.get(position);
    }

    @Override
    public int size() {
        return listDataLike.size();
    }
}
