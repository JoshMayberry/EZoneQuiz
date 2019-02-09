package com.example.android.ezonequiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class controls what options are displayed to the user for pages to view.
 * @see SectionParcel
 * @see BookListener_Text
 */
public class BookActivity_ToC extends AppCompatActivity {

    public TextView view_summary;
    public ListView view_sectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.display_sections);
        view_summary = findViewById(R.id.summaryText);
        view_sectionList = findViewById(R.id.sectionList);

        applyExtras_intent();
        applyExtras_theme();
    }

    /**
     * Set up the activity based on extras passed to the intent.
     * If the given extra was not passed in, a default value will be used instead.
     * <p>
     * See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
     */
    private void applyExtras_intent() {
        Intent intent = getIntent();
        assert intent.hasExtra("text_sections");

        setTheme(intent.getIntExtra("theme", R.style.AppTheme));
        setTitle(intent.getIntExtra("title", R.string.app_name));

        view_summary.setText(intent.getIntExtra("text_summary", R.string.empty));

        //Data from the parcels should be moved to a form that can persist during the activity
        //See: https://guides.codepath.com/android/using-parcelable#what-it-is-not
        final ArrayList<SectionBase> sections = new ArrayList<>();
        ArrayList<SectionParcel> schema = intent.getParcelableArrayListExtra("sections"); //This needs to be two lines, or each parcel would have to be cast individually into a 'SectionParcel'
        for (SectionParcel parcel: schema) { sections.add(new SectionBase(parcel)); }

        //Place resources in adapter
        ListView listView = findViewById(R.id.sectionList);
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
        view_sectionList.setBackgroundColor(typedValue.data);

        if (!(myTheme.resolveAttribute(R.attr.colorPrimary, typedValue, true))) {
            typedValue.data = R.color.colorPrimary;
        }
        view_summary.setBackgroundColor(typedValue.data);
    }
}