package com.example.android.ezonequiz;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Book_IntroductionActivity extends AppCompatActivity {

    int[] subtitleIdList = {R.string.section1_s1};
    int[] bodyIdList = {R.string.section1_s1_text};
    int[] quoteTextIdList = {R.string.quote1_1_text};
    int[] quoteSourceIdList = {R.string.quote1_1_source};

    TextView view_subtitle;
    TextView view_body;
    TextView view_quoteText;
    TextView view_quoteSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_text);

        view_subtitle = findViewById(R.id.bookSubtitle);
        view_body = findViewById(R.id.bookBody);
        view_quoteText = findViewById(R.id.bookQuoteText);
        view_quoteSource = findViewById(R.id.bookQuoteSource);

        view_body.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIntroductionLight));
        view_quoteText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIntroduction));
        view_quoteSource.setBackgroundColor(ContextCompat.getColor(this, R.color.colorIntroduction));

        assert subtitleIdList.length == bodyIdList.length;
        assert subtitleIdList.length == quoteTextIdList.length;
        assert subtitleIdList.length == quoteSourceIdList.length;
        populateBook(0);
    }

    private void populateBook(int sectionIndex) {
        view_subtitle.setText(subtitleIdList[sectionIndex]);
        view_body.setText(bodyIdList[sectionIndex]);
        view_quoteText.setText(quoteTextIdList[sectionIndex]);
        view_quoteSource.setText(quoteSourceIdList[sectionIndex]);

        //Use: https://stackoverflow.com/questions/20121938/how-to-set-tint-for-an-image-view-programmatically-in-android/20121975#20121975
    }
}
