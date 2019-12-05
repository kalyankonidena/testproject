package com.urban.dictionary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UrbanDictionaryModel implements Parcelable{


    protected UrbanDictionaryModel(Parcel in) {
        list = in.createTypedArrayList(UrbanDictionaryListModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UrbanDictionaryModel> CREATOR = new Creator<UrbanDictionaryModel>() {
        @Override
        public UrbanDictionaryModel createFromParcel(Parcel in) {
            return new UrbanDictionaryModel(in);
        }

        @Override
        public UrbanDictionaryModel[] newArray(int size) {
            return new UrbanDictionaryModel[size];
        }
    };

    public ArrayList<UrbanDictionaryListModel> getItems() {
        return list;
    }

    private ArrayList<UrbanDictionaryListModel> list;




}
