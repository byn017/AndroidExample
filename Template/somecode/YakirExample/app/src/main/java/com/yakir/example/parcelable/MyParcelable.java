package com.yakir.example.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Yakir Nahum on 6/26/2018.
 */

public class MyParcelable implements Parcelable {

    private int age;
    private String name;
    private ArrayList<String> description;

    public MyParcelable(String name, int age, ArrayList<String> description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }

    public MyParcelable(Parcel source) {
        age = source.readInt();
        name = source.readString();
        description = source.createStringArrayList();
    }

    public static final Creator<MyParcelable> CREATOR = new Creator<MyParcelable>() {
        @Override
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        @Override
        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeStringList(description);
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getDescription() {
        if (!(description == null))
            return description;
        else
            return new ArrayList<String>();
    }

}
