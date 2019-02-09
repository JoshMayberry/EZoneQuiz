package com.example.android.ezonequiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is used to transfer data about what each Table of Contents item does.
 * One should be created for each item, and then stored in an Array or ArrayList.
 * @see BookListener_ToC
 * @see BookActivity_Text
 * <p>
 * Use: https://guides.codepath.com/android/using-parcelable#defining-a-parcelable-object
 */
public class SectionParcel extends SectionBase<SectionParcel> implements Parcelable {

    SectionParcel() {
        // Normal actions performed by class, since this is still a normal object!
    }

    //Parcelable Methods
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.extra_theme);
        out.writeInt(this.extra_title);
        out.writeInt(this.extra_text_body);
        out.writeInt(this.extra_text_quote);
        out.writeInt(this.extra_text_subtitle);
        out.writeInt(this.extra_text_quote_source);
    }

    private SectionParcel(Parcel in) {
        this.extra_theme = in.readInt();
        this.extra_title = in.readInt();
        this.extra_text_body = in.readInt();
        this.extra_text_quote = in.readInt();
        this.extra_text_subtitle = in.readInt();
        this.extra_text_quote_source = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<SectionParcel> CREATOR = new Parcelable.Creator<SectionParcel>() {
        @Override
        public SectionParcel createFromParcel(Parcel in) {
            return new SectionParcel(in);
        }

        @Override
        public SectionParcel[] newArray(int size) {
            return new SectionParcel[size];
        }
    };
}
