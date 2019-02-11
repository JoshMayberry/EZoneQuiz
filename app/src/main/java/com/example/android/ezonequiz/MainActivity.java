package com.example.android.ezonequiz;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Apply main screen image
        //TO DO: Make this randomly pick from a selection of images?
        ImageView view_image = findViewById(R.id.main_image);
        view_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.main_1));

        //Link Activities
        findViewById(R.id.main_section1).setOnClickListener(
                new BookListenerText()
                        .setTheme(R.style.Theme_AppCompat_EZoneQuiz_Introduction)
                        .setTitle(R.string.section1)
                        .setImage(R.drawable.section1_s1)
                        .setImageScaleType(1)
                        .setSubtitle(R.string.section1_s1)
                        .setBody(R.string.section1_s1_text)
                        .setQuote(R.string.section1_s1_quote_text, R.string.section1_s1_quote_source)
        );
        findViewById(R.id.main_section2).setOnClickListener(
                new BookListenerToC()
                        .setTheme(R.style.Theme_AppCompat_EZoneQuiz_ChapterOne)
                        .setTitle(R.string.section2)
                        .addSection(R.string.summary)
                        .setSectionImage(R.drawable.section2_summary)
                        .setSectionImageScaleType(1)
                        .setSectionBody(R.string.section2_summary_text)

                        .addSection(R.string.section2_s1)
                        .setSectionImage(R.drawable.section2_s1)
                        .setSectionBody(R.string.section2_s1_text)
                        .setSectionQuote(R.string.section2_s1_quote_text, R.string.section2_s1_quote_source)

                        .addSection(R.string.section2_s2)
                        .setSectionImage(R.drawable.section2_s2)
                        .setSectionBody(R.string.section2_s2_text)
                        .setSectionQuote(R.string.section2_s2_quote_text, R.string.section2_s2_quote_source)

                        .addSection(R.string.section2_s3)
                        .setSectionImage(R.drawable.section2_s3)
                        .setSectionBody(R.string.section2_s3_text)

                        .addSection(R.string.section2_s4)

                        .addSection(R.string.section2_s5)
                        .setSectionImage(R.drawable.section2_s5)
                        .setSectionBody(R.string.section2_s5_text)

                        .addSection(R.string.section2_s6)
                        .setSectionImage(R.drawable.section2_s6)
                        .setSectionBody(R.string.section2_s6_text)
                        .setSectionQuote(R.string.section2_s6_quote_text, R.string.section2_s6_quote_source)
        );
        findViewById(R.id.main_section3).setOnClickListener(
                new BookListenerToC()
                        .setTheme(R.style.Theme_AppCompat_EZoneQuiz_ChapterTwo)
                        .setTitle(R.string.section3)
                        .addSection(R.string.summary)
                        .setSectionImage(R.drawable.section3_summary)
                        .setSectionImageScaleType(1)
                        .setSectionBody(R.string.section3_summary_text)

                        .addSection(R.string.section3_s1)
                        .setSectionImage(R.drawable.section3_s1)
                        .setSectionBody(R.string.section3_s1_text)
                        .setSectionQuote(R.string.section3_s1_quote_text, R.string.section3_s1_quote_source)

                        .addSection(R.string.section3_s2)
                        .setSectionImage(R.drawable.section3_s2)
                        .setSectionBody(R.string.section3_s2_text)

                        .addSection(R.string.section3_s3)
                        .setSectionImage(R.drawable.section3_s3)
                        .setSectionBody(R.string.section3_s3_text)
        );
        findViewById(R.id.main_section4).setOnClickListener(
                new BookListenerToC()
                        .setTheme(R.style.Theme_AppCompat_EZoneQuiz_ChapterThree)
                        .setTitle(R.string.section4)

                        .addSection(R.string.section4_s1)

                        .addSection(R.string.section4_s2)

                        .addSection(R.string.section4_s3)
        );
        findViewById(R.id.main_quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}

