package com.example.android.ezonequiz;

/**
 * This class keeps shared methods in one place.
 *
 * @param <T> - The class type of the child class that extends this
 *            Use: https://stackoverflow.com/questions/18204190/java-abstract-classes-returning-this-pointer-for-derived-classes/39897781#39897781
 * @see SectionParcel
 * @see BookListenerText
 */
public class SectionBase<T extends SectionBase<T>> {

    int extraTheme = R.style.Theme_AppCompat_EZoneQuiz;
    int extraTitle = R.string.app_name;
    int extraImage = R.drawable.empty;
    int extraImageScaleType = 0;
    int extraTextBody = R.string.future_content;
    int extraTextQuote = R.string.future_content;
    int extraTextSubtitle = R.string.empty;
    int extraTextQuoteSource = R.string.empty;

    SectionBase() {

    }

    /**
     * This is used to convert a parcel into a more stable object.
     * See: https://guides.codepath.com/android/using-parcelable#what-it-is-not
     *
     * @param parcel - A SectionParcel object obtained as an extra from an intent
     */
    SectionBase(SectionParcel parcel) {
        this.extraTheme = parcel.extraTheme;
        this.extraTitle = parcel.extraTitle;
        this.extraImage = parcel.extraImage;
        this.extraTextBody = parcel.extraTextBody;
        this.extraTextQuote = parcel.extraTextQuote;
        this.extraTextSubtitle = parcel.extraTextSubtitle;
        this.extraImageScaleType = parcel.extraImageScaleType;
        this.extraTextQuoteSource = parcel.extraTextQuoteSource;
    }

    /**
     * Sets the theme for the new activity.
     *
     * @param themeId - The resource id for the app theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see BookListenerText
     * @see SectionParcel
     */
    T setTheme(int themeId) {
        this.extraTheme = themeId;
        return (T) this;
    }

    /**
     * Sets the title for the new activity.
     *
     * @param textId - The resource id for the activity label to use
     * @return - Returns 'this' so methods can be chained.
     * @see BookListenerText
     * @see SectionParcel
     */
    T setTitle(int textId) {
        this.extraTitle = textId;
        return (T) this;
    }

    T setBody(int textId) {
        this.extraTextBody = textId;
        return (T) this;
    }

    T setSubtitle(int textId) {
        this.extraTextSubtitle = textId;
        return (T) this;
    }

    T setQuote(int textId, int sourceId) {
        this.extraTextQuote = textId;
        this.extraTextQuoteSource = sourceId;
        return (T) this;
    }

    T setImage(int imageId) {
        this.extraImage = imageId;
        return (T) this;
    }

    /**
     * Changes how the image is scaled.
     * 0: centerCrop
     * 1: centerInside
     * @param scaleType - Which ScaleType to use for the image
     * @return
     */
    T setImageScaleType(int scaleType) {
        this.extraImageScaleType = scaleType;
        return (T) this;
    }
}
