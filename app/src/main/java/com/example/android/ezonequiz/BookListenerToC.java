package com.example.android.ezonequiz;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class BookListenerToC implements View.OnClickListener {

    private int mLastIndex = -1;
    private int mExtraTheme = R.style.Theme_AppCompat_EZoneQuiz;
    private int mExtraTitle = R.string.app_name;
    private ArrayList<SectionParcel> mExtraSections = new ArrayList<>();

    BookListenerToC() {

    }

    BookListenerToC setTheme(int themeId) {
        this.mExtraTheme = themeId;
        return this;
    }

    BookListenerToC setTitle(int textId) {
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
    BookListenerToC addSection() {
        return addSection(this.mExtraTitle);
    }

    BookListenerToC addSection(int textId) {
        return addSection(textId, this.mExtraTheme);
    }

    BookListenerToC addSection(int textId, int themeId) {
        SectionParcel sectionDetails = new SectionParcel();
        sectionDetails.setTheme(themeId);
        sectionDetails.setTitle(textId);

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
    BookListenerToC setSectionTheme(int themeId) {
        return setSectionTheme(mLastIndex, themeId);
    }

    /**
     * Changes the theme for the given section.
     *
     * @param index   - The position of the section in 'mExtraSections'
     * @param themeId - The resource id of the theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see this.set_section_theme(int)
     */
    BookListenerToC setSectionTheme(int index, int themeId) {
        this.mExtraSections.get(index).setTheme(themeId);
        return this;
    }

    BookListenerToC setSectionTitle(int textId) {
        return setSectionTitle(mLastIndex, textId);
    }

    BookListenerToC setSectionTitle(int index, int textId) {
        this.mExtraSections.get(index).setTitle(textId);
        return this;
    }

    BookListenerToC setSectionBody(int textId) {
        return setSectionBody(mLastIndex, textId);
    }

    BookListenerToC setSectionBody(int index, int textId) {
        this.mExtraSections.get(index).setBody(textId);
        return this;
    }

    BookListenerToC setSectionSubtitle(int textId) {
        return setSectionSubtitle(mLastIndex, textId);
    }

    BookListenerToC setSectionSubtitle(int index, int textId) {
        this.mExtraSections.get(index).setSubtitle(textId);
        return this;
    }

    BookListenerToC setSectionQuote(int textId, int sourceId) {
        return setSectionQuote(mLastIndex, textId, sourceId);
    }

    BookListenerToC setSectionQuote(int index, int textId, int sourceId) {
        this.mExtraSections.get(index).setQuote(textId, sourceId);
        return this;
    }

    BookListenerToC setSectionImage(int imageId) {
        return setSectionImage(mLastIndex, imageId);
    }

    BookListenerToC setSectionImage(int index, int imageId) {
        this.mExtraSections.get(index).setImage(imageId);
        return this;
    }

    BookListenerToC setSectionImageScaleType(int scaleType) {
        return setSectionImageScaleType(mLastIndex, scaleType);
    }

    BookListenerToC setSectionImageScaleType(int index, int scaleType) {
        this.mExtraSections.get(index).setImageScaleType(scaleType);
        return this;
    }

    @Override
    public void onClick(View view) {
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivityToC.class);

        intent.putExtra("theme", this.mExtraTheme);
        intent.putExtra("title", this.mExtraTitle);
        intent.putExtra("sections", this.mExtraSections);

        context.startActivity(intent);
    }
}
