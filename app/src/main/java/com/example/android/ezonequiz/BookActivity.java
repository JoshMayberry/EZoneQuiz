package com.example.android.ezonequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This class controls what the current book page looks like.
 */
public class BookActivity extends AppCompatActivity {

    public TextView view_body;
    public TextView view_subtitle;
    public TextView view_quoteText;
    public TextView view_quoteSource;

    private Page currentPage;

    private int[] section2List = {R.string.section2_s1, R.string.section2_s2, R.string.section2_s3, R.string.section2_s4, R.string.section2_s5, R.string.section2_s6};
    private int[] section3List = {R.string.section3_s1, R.string.section3_s2, R.string.section3_s3};
    private int[] section4List = {R.string.section4_s1, R.string.section4_s2, R.string.section4_s3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_text);
        view_body = findViewById(R.id.bookBody);
        view_subtitle = findViewById(R.id.bookSubtitle);
        view_quoteText = findViewById(R.id.bookQuoteText);
        view_quoteSource = findViewById(R.id.bookQuoteSource);

        currentPage = new Page();

        //See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
        Intent intent = getIntent();
        switch (intent.getIntExtra("resourceId", R.id.section1)) {
            case R.id.section1:
                this.setTheme(R.style.AppTheme_Introduction);
                this.setTitle(R.string.section1);
                updateColors(R.color.colorIntroductionLight, R.color.colorIntroduction);
                show_section1();
                break;
            case R.id.section2:
                this.setTheme(R.style.AppTheme_ChapterOne);
                this.setTitle(R.string.section2);
                updateColors(R.color.colorChapterOneLight, R.color.colorChapterOne);
                show_section2();
                break;
            case R.id.section3:
                this.setTheme(R.style.AppTheme_ChapterTwo);
                this.setTitle(R.string.section3);
                updateColors(R.color.colorChapterTwoLight, R.color.colorChapterTwo);
                show_section3();
                break;
            case R.id.section4:
                this.setTheme(R.style.AppTheme_ChapterThree);
                this.setTitle(R.string.section4);
                updateColors(R.color.colorChapterThreeLight, R.color.colorChapterThree);
                show_section4();
                break;
        }
    }

    /**
     * Populates the page withs section 1.
     */
    private void show_section1() {
        show_section1_s1();
    }
    /**
     * Populates the page withs section 2.
     */
    private void show_section2() {
        setContentView(R.layout.display_sections);
        populateSections(section2List);
    }
    /**
     * Populates the page withs section 3.
     */
    private void show_section3() {
        setContentView(R.layout.display_sections);
        populateSections(section3List);
    }
    /**
     * Populates the page withs section 4.
     */
    private void show_section4() {
        setContentView(R.layout.display_sections);
        populateSections(section4List);
    }

    /**
     * Places the given sections in the contents list
     * @param sectionIdList - What resource ids to use for the buttons
     */
    private void populateSections(int[] sectionIdList) {
        //Gather resource strings
        ArrayList<String> sections = new ArrayList<>();
        for (int i: sectionIdList) {
            sections.add(getString(i));
        }

        //Place resources in adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sections);
        ListView view = findViewById(R.id.sectionList);
        view.setAdapter(itemsAdapter);
    }

    /**
     * Populates the page with section 1, sub-section 1.
     */
    private void show_section1_s1() {
        currentPage.newPage()
                .set_subtitle(R.string.section1_s1)
                .set_body(R.string.section1_s1_text)
                .set_quote(R.string.section1_s1_quote_text, R.string.section1_s1_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 1.
     */
    private void show_section2_s1() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s1)
                .set_body(R.string.section2_s1_text)
                .set_quote(R.string.section2_s1_quote_text, R.string.section2_s1_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 2.
     */
    private void show_section2_s2() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s2)
                .set_body(R.string.section2_s2_text)
                .set_quote(R.string.section2_s2_quote_text, R.string.section2_s2_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 3.
     */
    private void show_section2_s3() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s3)
                .set_body(R.string.section2_s3_text);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 4.
     */
    private void show_section2_s4() {
        //This will be an interactive template thing
    }
    /**
     * Populates the page with section 2, sub-section 5.
     */
    private void show_section2_s5() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s5)
                .set_body(R.string.section2_s5_text);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 6.
     */
    private void show_section2_s6() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s6)
                .set_body(R.string.section2_s6_text)
                .set_quote(R.string.section2_s6_quote_text, R.string.section2_s6_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 3, sub-section 1.
     */
    private void show_section3_s1() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s1)
                .set_body(R.string.section3_s1_text)
                .set_quote(R.string.section3_s1_quote_text, R.string.section3_s1_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 3, sub-section 2.
     */
    private void show_section3_s2() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s2)
                .set_body(R.string.section3_s2_text);
        updateGUI();
    }
    /**
     * Populates the page with section 3, sub-section 3.
     */
    private void show_section3_s3() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s3)
                .set_body(R.string.section3_s3_text);
        updateGUI();
    }

    private void updateColors(int bodyIdColor, int quoteIdColor) {
        view_body.setBackgroundColor(ContextCompat.getColor(this, bodyIdColor));
        view_quoteText.setBackgroundColor(ContextCompat.getColor(this, quoteIdColor));
        view_quoteSource.setBackgroundColor(ContextCompat.getColor(this, quoteIdColor));
    }
    private void updateGUI() {
        view_body.setText(currentPage.bodyId);
        view_subtitle.setText(currentPage.subtitleId);
        view_quoteText.setText(currentPage.quoteTextId);
        view_quoteSource.setText(currentPage.quoteSourceId);
    }
}
