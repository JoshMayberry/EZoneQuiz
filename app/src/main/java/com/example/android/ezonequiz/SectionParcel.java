package com.example.android.ezonequiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This is used to transfer data about what each Table of Contents item does.
 * One should be created for each item, and then stored in an Array or ArrayList.
 * @see BookListenerToC
 * @see BookActivityText
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
        out.writeInt(this.extraTheme);
        out.writeInt(this.extraTitle);
        out.writeInt(this.extraImage);
        out.writeInt(this.extraTextBody);
        out.writeInt(this.extraTextQuote);
        out.writeInt(this.extraTextSubtitle);
        out.writeInt(this.extraImageScaleType);
        out.writeInt(this.extraTextQuoteSource);
    }

    private SectionParcel(Parcel in) {
        this.extraTheme = in.readInt();
        this.extraTitle = in.readInt();
        this.extraImage = in.readInt();
        this.extraTextBody = in.readInt();
        this.extraTextQuote = in.readInt();
        this.extraTextSubtitle = in.readInt();
        this.extraImageScaleType = in.readInt();
        this.extraTextQuoteSource = in.readInt();
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
