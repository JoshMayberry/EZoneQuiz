package com.example.android.ezonequiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This class controls what options are displayed to the user for pages to view.
 *
 * @see SectionParcel
 * @see BookListener_Text
 */
public class BookActivity_ToC extends AppCompatActivity {

    public LinearLayout viewRoot;
    public ListView viewSectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Theme must be changed before setContentView to be fully applied
        others say before super.onCreate, but it seems to work for me if that is before this.
        I will do it before that as well to be safe though.
         */
        Intent intent = getIntent();
        setTheme(intent.getIntExtra("theme", R.style.Theme_AppCompat_EZoneQuiz));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_sections);
        viewSectionList = findViewById(R.id.book_ToC_list);
        viewRoot = findViewById(R.id.book_ToC_root);

        applyExtras_intent(intent);
        applyExtras_theme();
    }

    /**
     * Set up the activity based on extras passed to the intent.
     * If the given extra was not passed in, a default value will be used instead.
     * <p>
     * See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
     */
    private void applyExtras_intent(Intent intent) {
        assert intent.hasExtra("sections");

        setTitle(intent.getIntExtra("title", R.string.app_name));

        //Data from the parcels should be moved to a form that can persist during the activity
        //See: https://guides.codepath.com/android/using-parcelable#what-it-is-not
        final ArrayList<SectionBase> sections = new ArrayList<>();
        ArrayList<SectionParcel> schema = intent.getParcelableArrayListExtra("sections"); //This needs to be two lines, or each parcel would have to be cast individually into a 'SectionParcel'
        for (SectionParcel parcel : schema) {
            sections.add(new SectionBase(parcel));
        }

        //Place resources in adapter
        ListView listView = findViewById(R.id.book_ToC_list);
        listView.setAdapter(new SectionAdapter(this, sections));

        //Connect onClick behavior
        listView.setOnItemClickListener(new BookListener_Multiple_Text(sections));
    }

    /**
     * Setup the activity based on the theme.
     * <p>
     * Use: https://stackoverflow.com/questions/17277618/get-color-value-programmatically-when-its-a-reference-theme/17277714#17277714
     * See: https://developer.android.com/reference/android/content/res/Resources.Theme#resolveAttribute(int,%20android.util.TypedValue,%20boolean)
     */
    private void applyExtras_theme() {

        Resources.Theme myTheme = getTheme();
        TypedValue typedValue = new TypedValue();
        if (!(myTheme.resolveAttribute(R.attr.colorAccent, typedValue, true))) {
            typedValue.data = R.color.colorPrimaryLight;
        }
        viewSectionList.setBackgroundColor(typedValue.data);
        viewRoot.setBackgroundColor(typedValue.data); //Fill in background in case 'viewSectionList' is smaller than the screen
    }
}