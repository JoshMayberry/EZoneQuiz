package com.example.android.ezonequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This class controls what the current book page looks like.
 */
public class BookActivity extends AppCompatActivity {

    public int bodyId;
    public int subtitleId;
    public int quoteTextId;
    public int quoteSourceId;

    public TextView view_body;
    public TextView view_subtitle;
    public TextView view_quoteText;
    public TextView view_quoteSource;

    private Page currentPage;

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
                show_section1_s1();
                break;
            case R.id.section2:
                this.setTheme(R.style.AppTheme_ChapterOne);
                this.setTitle(R.string.section2);
                updateColors(R.color.colorChapterOneLight, R.color.colorChapterOne);
                show_section2_s1();
                break;
            case R.id.section3:
                this.setTheme(R.style.AppTheme_ChapterTwo);
                this.setTitle(R.string.section3);
                updateColors(R.color.colorChapterTwoLight, R.color.colorChapterTwo);
                show_section3_s1();
                break;
            case R.id.section4:
                this.setTheme(R.style.AppTheme_ChapterThree);
                this.setTitle(R.string.section4);
                updateColors(R.color.colorChapterThreeLight, R.color.colorChapterThree);
                break;
        }
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
                .set_body(R.string.section2_s1_text);
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
                .set_body(R.string.section2_s3_text)
                .set_quote(R.string.section2_s3_quote_text, R.string.section2_s3_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 4.
     */
    private void show_section2_s4() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s4)
                .set_body(R.string.section2_s4_text);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 5.
     */
    private void show_section2_s5() {
        //This will be an interactive template thing
    }
    /**
     * Populates the page with section 2, sub-section 6.
     */
    private void show_section2_s6() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s6)
                .set_body(R.string.section2_s6_text);
        updateGUI();
    }
    /**
     * Populates the page with section 2, sub-section 7.
     */
    private void show_section2_s7() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s7)
                .set_body(R.string.section2_s7_text)
                .set_quote(R.string.section2_s7_quote_text, R.string.section2_s7_quote_source);
        updateGUI();
    }
    /**
     * Populates the page with section 3, sub-section 1.
     */
    private void show_section3_s1() {
        currentPage.newPage()
                .set_body(R.string.section3_s1_text);
        updateGUI();
    }
    /**
     * Populates the page with section 3, sub-section 2.
     */
    private void show_section3_s2() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s2)
                .set_body(R.string.section3_s2_text)
                .set_quote(R.string.section3_s2_quote_text, R.string.section3_s2_quote_source);
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
    /**
     * Populates the page with section 3, sub-section 4.
     */
    private void show_section3_s4() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s4)
                .set_body(R.string.section3_s4_text);
        updateGUI();
    }

    private void updateColors(int bodyIdColor, int quoteIdColor) {
        view_body.setBackgroundColor(ContextCompat.getColor(this, bodyIdColor));
        view_quoteText.setBackgroundColor(ContextCompat.getColor(this, quoteIdColor));
        view_quoteSource.setBackgroundColor(ContextCompat.getColor(this, quoteIdColor));
    }
    private void updateGUI() {
        view_body.setText(bodyId);
        view_subtitle.setText(subtitleId);
        view_quoteText.setText(quoteTextId);
        view_quoteSource.setText(quoteSourceId);
    }
}
