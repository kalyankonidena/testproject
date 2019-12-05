package com.urban.dictionary.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UrbanDictionaryListModel implements Parcelable {


    private String definition;
    private String permalink;

    public UrbanDictionaryListModel(Parcel in) {
        definition = in.readString();
        permalink = in.readString();
        thumbs_up = in.readInt();
        sound_url = in.createStringArrayList();
        word = in.readString();
        defid = in.readString();
        current_vote = in.readString();
        written_on = in.readString();
        example = in.readString();
        thumbs_down = in.readInt();
    }

    public static final Creator<UrbanDictionaryListModel> CREATOR = new Creator<UrbanDictionaryListModel>() {
        @Override
        public UrbanDictionaryListModel createFromParcel(Parcel in) {
            return new UrbanDictionaryListModel(in);
        }

        @Override
        public UrbanDictionaryListModel[] newArray(int size) {
            return new UrbanDictionaryListModel[size];
        }
    };

    public UrbanDictionaryListModel() {

    }

    public String getDefinition() {
        return definition;
    }

    public String getPermalink() {
        return permalink;
    }

    public Integer getThumbs_up() {
        return thumbs_up;
    }

    public ArrayList<String> getSound_url() {
        return sound_url;
    }

    public String getWord() {
        return word;
    }

    public String getDefid() {
        return defid;
    }

    public String getCurrent_vote() {
        return current_vote;
    }

    public String getWritten_on() {
        return written_on;
    }

    public String getExample() {
        return example;
    }

    public Integer getThumbs_down() {
        return thumbs_down;
    }

    private Integer thumbs_up;
    private ArrayList<String> sound_url;
    private String word;
    private String defid;
    private String current_vote;
    private String written_on;
    private String example;
    private Integer thumbs_down;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(definition);
        dest.writeString(permalink);
        dest.writeInt(thumbs_up);
        dest.writeStringList(sound_url);
        dest.writeString(word);
        dest.writeString(defid);
        dest.writeString(current_vote);
        dest.writeString(written_on);
        dest.writeString(example);
        dest.writeInt(thumbs_down);
    }
}
