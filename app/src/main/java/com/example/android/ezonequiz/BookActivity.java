package com.example.android.ezonequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * This class controls what the current book page looks like.
 *
 * If a section has more than one sub section, the user should be presented with a list of each sub section.
 * When they choose which sub section to view, the index of that list item is used to run a function
 * that is stored in an array; the function that runs thus depends on the index of the item selected.
 * That function should then populate the display_text page with the correct text, picture, etc.
 */
public class BookActivity extends AppCompatActivity {

    public TextView view_body;
    public TextView view_subtitle;
    public TextView view_quoteText;
    public TextView view_quoteSource;

    private Page currentPage;

    private int[] section2_stringList = {R.string.section2_s1, R.string.section2_s2, R.string.section2_s3, R.string.section2_s4, R.string.section2_s5, R.string.section2_s6};
    private int[] section3_stringList = {R.string.section3_s1, R.string.section3_s2, R.string.section3_s3};
    private ArrayList<Method> section2_functionList = new ArrayList<>();
    private ArrayList<Method> section3_functionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_text);
        view_body = findViewById(R.id.bookBody);
        view_subtitle = findViewById(R.id.bookSubtitle);
        view_quoteText = findViewById(R.id.bookQuoteText);
        view_quoteSource = findViewById(R.id.bookQuoteSource);


        //See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
        Intent intent = getIntent();
        currentPage = new Page();

        if (intent.hasExtra("currentPage")) {
            currentPage.bodyId = intent.getIntExtra("currentPage_bodyId", -1);
            view_body.setText(currentPage.bodyId);
            view_subtitle.setText(currentPage.subtitleId);
            view_quoteText.setText(currentPage.quoteTextId);
            view_quoteSource.setText(currentPage.quoteSourceId);

        } else {
            //See: https://www.baeldung.com/java-method-reflection#highlighter_256261
            try {
                section2_functionList.add(this.getClass().getMethod("show_section2_s1"));
                section2_functionList.add(this.getClass().getMethod("show_section2_s2"));
                section2_functionList.add(this.getClass().getMethod("show_section2_s3"));
                section2_functionList.add(this.getClass().getMethod("show_section2_s4"));
                section2_functionList.add(this.getClass().getMethod("show_section2_s5"));
                section2_functionList.add(this.getClass().getMethod("show_section2_s6"));

                section3_functionList.add(this.getClass().getMethod("show_section3_s1"));
                section3_functionList.add(this.getClass().getMethod("show_section3_s2"));
                section3_functionList.add(this.getClass().getMethod("show_section3_s3"));

            } catch (NoSuchMethodException error) {
                error.printStackTrace();
            }
        }

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
     * Populates the page with section 1.
     */
    private void show_section1() {
        show_section1_s1();
    }

    /**
     * Populates the page with section 2.
     */
    private void show_section2() {
        setContentView(R.layout.display_sections);
        populateSections(section2_stringList, section2_functionList);
    }

    /**
     * Populates the page with section 3.
     */
    private void show_section3() {
        setContentView(R.layout.display_sections);
        populateSections(section3_stringList, section3_functionList);
    }

    /**
     * Populates the page with section 4.
     */
    private void show_section4() {
        setContentView(R.layout.display_sections);
    }

    /**
     * Places the given sections in the contents list
     * @param sectionIdList - What resource ids to use for the buttons
     */
    private void populateSections(int[] sectionIdList, final ArrayList<Method> functionList) {
        assert sectionIdList.length == functionList.size();

        //Gather resource strings
        ArrayList<String> sections = new ArrayList<>();
        for (int i : sectionIdList) {
            sections.add(getString(i));
        }

        //Place resources in adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sections);
        final ListView listView = findViewById(R.id.sectionList);
        listView.setAdapter(itemsAdapter);

        final BookActivity myInstance = this; //If I use the variable 'this' in the sub function, it make it BookActivity$1, which throws an error.

        //Make list items respond to click
        //See: http://ezzylearning.com/tutorial/handling-android-listview-onitemclick-event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //See: https://www.baeldung.com/java-method-reflection#highlighter_738819
                Method showFunction = functionList.get(position);
                try {
                    showFunction.invoke(myInstance, null);
                } catch (IllegalAccessException | InvocationTargetException error) {
                    error.printStackTrace();
                }
            }
        });
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
    public void show_section2_s1() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s1)
                .set_body(R.string.section2_s1_text)
                .set_quote(R.string.section2_s1_quote_text, R.string.section2_s1_quote_source);
        updateGUI();
    }

    /**
     * Populates the page with section 2, sub-section 2.
     */
    public void show_section2_s2() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s2)
                .set_body(R.string.section2_s2_text)
                .set_quote(R.string.section2_s2_quote_text, R.string.section2_s2_quote_source);
        updateGUI();
    }

    /**
     * Populates the page with section 2, sub-section 3.
     */
    public void show_section2_s3() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s3)
                .set_body(R.string.section2_s3_text);
        updateGUI();
    }

    /**
     * Populates the page with section 2, sub-section 4.
     */
    public void show_section2_s4() {
        //This will be an interactive template thing
    }

    /**
     * Populates the page with section 2, sub-section 5.
     */
    public void show_section2_s5() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s5)
                .set_body(R.string.section2_s5_text);
        updateGUI();
    }

    /**
     * Populates the page with section 2, sub-section 6.
     */
    public void show_section2_s6() {
        currentPage.newPage()
                .set_subtitle(R.string.section2_s6)
                .set_body(R.string.section2_s6_text)
                .set_quote(R.string.section2_s6_quote_text, R.string.section2_s6_quote_source);
        updateGUI();
    }

    /**
     * Populates the page with section 3, sub-section 1.
     */
    public void show_section3_s1() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s1)
                .set_body(R.string.section3_s1_text)
                .set_quote(R.string.section3_s1_quote_text, R.string.section3_s1_quote_source);
        updateGUI();
    }

    /**
     * Populates the page with section 3, sub-section 2.
     */
    public void show_section3_s2() {
        currentPage.newPage()
                .set_subtitle(R.string.section3_s2)
                .set_body(R.string.section3_s2_text);
        updateGUI();
    }

    /**
     * Populates the page with section 3, sub-section 3.
     */
    public void show_section3_s3() {
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
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Intent intent = new Intent(this, BookActivity.class);
        intent.putExtra("currentPage_bodyId", currentPage.bodyId);
        intent.putExtra("currentPage_subtitleId", currentPage.subtitleId);
        intent.putExtra("currentPage_quoteTextId", currentPage.quoteTextId);
        intent.putExtra("currentPage_quoteSourceId", currentPage.quoteSourceId);
        startActivity(intent);
    }
}
