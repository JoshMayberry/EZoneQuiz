package com.example.android.ezonequiz;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.ArrayList;

public class BookListener_ToC implements View.OnClickListener {

    private int lastIndex = -1;
    private int extra_theme = R.style.Theme_AppCompat_EZoneQuiz;
    private int extra_title = R.string.app_name;
    private ArrayList<SectionParcel> extra_sections = new ArrayList<>();

    BookListener_ToC() {

    }

    BookListener_ToC set_theme(int themeId) {
        this.extra_theme = themeId;
        return this;
    }

    BookListener_ToC set_title(int textId) {
        this.extra_title = textId;
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
        return add_section(this.extra_title);
    }

    BookListener_ToC add_section(int textId) {
        return add_section(textId, this.extra_theme);
    }

    BookListener_ToC add_section(int textId, int themeId) {
        SectionParcel sectionDetails = new SectionParcel();
        sectionDetails.set_theme(themeId);
        sectionDetails.set_title(textId);

        lastIndex = this.extra_sections.size();
        this.extra_sections.add(sectionDetails);
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
        return set_section_theme(lastIndex, themeId);
    }

    /**
     * Changes the theme for the given section.
     *
     * @param index   - The position of the section in 'extra_sections'
     * @param themeId - The resource id of the theme to use
     * @return - Returns 'this' so methods can be chained.
     * @see this.set_section_theme(int)
     */
    BookListener_ToC set_section_theme(int index, int themeId) {
        this.extra_sections.get(index).set_theme(themeId);
        return this;
    }

    BookListener_ToC set_section_title(int textId) {
        return set_section_title(lastIndex, textId);
    }

    BookListener_ToC set_section_title(int index, int textId) {
        this.extra_sections.get(index).set_title(textId);
        return this;
    }

    BookListener_ToC set_section_body(int textId) {
        return set_section_body(lastIndex, textId);
    }

    BookListener_ToC set_section_body(int index, int textId) {
        this.extra_sections.get(index).set_body(textId);
        return this;
    }

    BookListener_ToC set_section_subtitle(int textId) {
        return set_section_subtitle(lastIndex, textId);
    }

    BookListener_ToC set_section_subtitle(int index, int textId) {
        this.extra_sections.get(index).set_subtitle(textId);
        return this;
    }

    BookListener_ToC set_section_quote(int textId, int sourceId) {
        return set_section_quote(lastIndex, textId, sourceId);
    }

    BookListener_ToC set_section_quote(int index, int textId, int sourceId) {
        this.extra_sections.get(index).set_quote(textId, sourceId);
        return this;
    }

    BookListener_ToC set_section_image(int imageId) {
        return set_section_image(lastIndex, imageId);
    }

    BookListener_ToC set_section_image(int index, int imageId) {
        this.extra_sections.get(index).set_image(imageId);
        return this;
    }

    BookListener_ToC set_section_image_scaleType(int scaleType) {
        return set_section_image_scaleType(lastIndex, scaleType);
    }

    BookListener_ToC set_section_image_scaleType(int index, int scaleType) {
        this.extra_sections.get(index).set_image_scaleType(scaleType);
        return this;
    }

    @Override
    public void onClick(View view) {
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivity_ToC.class);

        intent.putExtra("theme", this.extra_theme);
        intent.putExtra("title", this.extra_title);
        intent.putExtra("sections", this.extra_sections);

        context.startActivity(intent);
    }
}
