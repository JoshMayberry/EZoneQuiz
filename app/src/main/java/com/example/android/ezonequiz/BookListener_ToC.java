package com.example.android.ezonequiz;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class BookListener_ToC implements View.OnClickListener {

    private int mLastIndex = -1;
    private int mExtraTheme = R.style.Theme_AppCompat_EZoneQuiz;
    private int mExtraTitle = R.string.app_name;
    private ArrayList<SectionParcel> mExtraSections = new ArrayList<>();

    BookListener_ToC() {

    }

    BookListener_ToC set_theme(int themeId) {
        this.mExtraTheme = themeId;
        return this;
    }

    BookListener_ToC set_title(int textId) {
        this.mExtraTitle = textId;
        return this;
    }

    /**
     * If the theme or title is not set for this section afterwards,
     * it will use the them and title of the table of contents.
     *
     * @return - Returns 'this' so methods can be chained.
     * @see this.set_section_theme
     * @see this.set_section_title
     */
    BookListener_ToC add_section() {
        return add_section(this.mExtraTitle);
    }

    BookListener_ToC add_section(int textId) {
        return add_section(textId, this.mExtraTheme);
    }

    BookListener_ToC add_section(int textId, int themeId) {
        SectionParcel sectionDetails = new SectionParcel();
        sectionDetails.set_theme(themeId);
        sectionDetails.set_title(textId);

        mLastIndex = this.mExtraSections.size();
        this.mExtraSections.add(sectionDetails);
        return this;
    }

    /**
     * Changes the theme for the last section created.
     *
     * @param themeId - The resource id of the theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see this.set_section_theme(int, int)
     */
    BookListener_ToC set_section_theme(int themeId) {
        return set_section_theme(mLastIndex, themeId);
    }

    /**
     * Changes the theme for the given section.
     *
     * @param index   - The position of the section in 'mExtraSections'
     * @param themeId - The resource id of the theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see this.set_section_theme(int)
     */
    BookListener_ToC set_section_theme(int index, int themeId) {
        this.mExtraSections.get(index).set_theme(themeId);
        return this;
    }

    BookListener_ToC set_section_title(int textId) {
        return set_section_title(mLastIndex, textId);
    }

    BookListener_ToC set_section_title(int index, int textId) {
        this.mExtraSections.get(index).set_title(textId);
        return this;
    }

    BookListener_ToC set_section_body(int textId) {
        return set_section_body(mLastIndex, textId);
    }

    BookListener_ToC set_section_body(int index, int textId) {
        this.mExtraSections.get(index).set_body(textId);
        return this;
    }

    BookListener_ToC set_section_subtitle(int textId) {
        return set_section_subtitle(mLastIndex, textId);
    }

    BookListener_ToC set_section_subtitle(int index, int textId) {
        this.mExtraSections.get(index).set_subtitle(textId);
        return this;
    }

    BookListener_ToC set_section_quote(int textId, int sourceId) {
        return set_section_quote(mLastIndex, textId, sourceId);
    }

    BookListener_ToC set_section_quote(int index, int textId, int sourceId) {
        this.mExtraSections.get(index).set_quote(textId, sourceId);
        return this;
    }

    BookListener_ToC set_section_image(int imageId) {
        return set_section_image(mLastIndex, imageId);
    }

    BookListener_ToC set_section_image(int index, int imageId) {
        this.mExtraSections.get(index).set_image(imageId);
        return this;
    }

    BookListener_ToC set_section_image_scaleType(int scaleType) {
        return set_section_image_scaleType(mLastIndex, scaleType);
    }

    BookListener_ToC set_section_image_scaleType(int index, int scaleType) {
        this.mExtraSections.get(index).set_image_scaleType(scaleType);
        return this;
    }

    @Override
    public void onClick(View view) {
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivity_ToC.class);

        intent.putExtra("theme", this.mExtraTheme);
        intent.putExtra("title", this.mExtraTitle);
        intent.putExtra("sections", this.mExtraSections);

        context.startActivity(intent);
    }
}
