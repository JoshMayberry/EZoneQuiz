package com.example.android.ezonequiz;

/**
 * This class keeps shared methods in one place.
 *
 * @param <T> - The class type of the child class that extends this
 *            Use: https://stackoverflow.com/questions/18204190/java-abstract-classes-returning-this-pointer-for-derived-classes/39897781#39897781
 * @see SectionParcel
 * @see BookListener_Text
 */
public class SectionBase<T extends SectionBase<T>> {

    int extra_theme = R.style.Theme_AppCompat_EZoneQuiz;
    int extra_title = R.string.app_name;
    int extra_image = R.drawable.empty;
    int extra_image_scaleType = 0;
    int extra_text_body = R.string.future_content;
    int extra_text_quote = R.string.future_content;
    int extra_text_subtitle = R.string.empty;
    int extra_text_quote_source = R.string.empty;

    SectionBase() {

    }

    /**
     * This is used to convert a parcel into a more stable object.
     * See: https://guides.codepath.com/android/using-parcelable#what-it-is-not
     *
     * @param parcel - A SectionParcel object obtained as an extra from an intent
     */
    SectionBase(SectionParcel parcel) {
        this.extra_theme = parcel.extra_theme;
        this.extra_title = parcel.extra_title;
        this.extra_image = parcel.extra_image;
        this.extra_text_body = parcel.extra_text_body;
        this.extra_text_quote = parcel.extra_text_quote;
        this.extra_text_subtitle = parcel.extra_text_subtitle;
        this.extra_image_scaleType = parcel.extra_image_scaleType;
        this.extra_text_quote_source = parcel.extra_text_quote_source;
    }

    /**
     * Sets the theme for the new activity.
     *
     * @param themeId - The resource id for the app theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see BookListener_Text
     * @see SectionParcel
     */
    T set_theme(int themeId) {
        this.extra_theme = themeId;
        return (T) this;
    }

    /**
     * Sets the title for the new activity.
     *
     * @param textId - The resource id for the activity label to use
     * @return - Returns 'this' so methods can be chained.
     * @see BookListener_Text
     * @see SectionParcel
     */
    T set_title(int textId) {
        this.extra_title = textId;
        return (T) this;
    }

    T set_body(int textId) {
        this.extra_text_body = textId;
        return (T) this;
    }

    T set_subtitle(int textId) {
        this.extra_text_subtitle = textId;
        return (T) this;
    }

    T set_quote(int textId, int sourceId) {
        this.extra_text_quote = textId;
        this.extra_text_quote_source = sourceId;
        return (T) this;
    }

    T set_image(int imageId) {
        this.extra_image = imageId;
        return (T) this;
    }

    /**
     * Changes how the image is scaled.
     * 0: centerCrop
     * 1: centerInside
     * @param scaleType - Which ScaleType to use for the image
     * @return
     */
    T set_image_scaleType(int scaleType) {
        this.extra_image_scaleType = scaleType;
        return (T) this;
    }
}
