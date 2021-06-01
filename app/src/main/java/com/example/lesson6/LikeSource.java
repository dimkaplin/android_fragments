package com.example.lesson6;

public interface LikeSource {
    DataLike getDataLike(int position);
    int size();
    void deleteDataLike(int position);
    void updateDataLike(int position, DataLike data);
    void addDataLike(DataLike data);
    void clearDataLike(int position);
}
