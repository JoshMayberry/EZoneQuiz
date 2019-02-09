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
        ImageView view_image = findViewById(R.id.mainImage);
        view_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.storm));

        //Link Activities
        findViewById(R.id.section1).setOnClickListener(
                new BookListener_Text()
                        .set_theme(R.style.AppTheme_Introduction)
                        .set_title(R.string.section1)
                        .set_subtitle(R.string.section1_s1)
                        .set_body(R.string.section1_s1_text)
                        .set_quote(R.string.section1_s1_quote_text, R.string.section1_s1_quote_source)
        );
        findViewById(R.id.section2).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.AppTheme_ChapterOne)
                        .set_title(R.string.section2)
                        .set_summary(R.string.section2_summary_text)

                        .add_section(R.string.section2_s1)
                        .set_section_body(R.string.section2_s1_text)
                        .set_section_quote(R.string.section2_s1_quote_text, R.string.section2_s1_quote_source)

                        .add_section(R.string.section2_s2)
                        .set_section_body(R.string.section2_s2_text)
                        .set_section_quote(R.string.section2_s2_quote_text, R.string.section2_s2_quote_source)

                        .add_section(R.string.section2_s3)
                        .set_section_body(R.string.section2_s3_text)

                        .add_section(R.string.section2_s4)

                        .add_section(R.string.section2_s5)
                        .set_section_body(R.string.section2_s5_text)

                        .add_section(R.string.section2_s6)
                        .set_section_body(R.string.section2_s6_text)
                        .set_section_quote(R.string.section2_s6_quote_text, R.string.section2_s6_quote_source)
        );
        findViewById(R.id.section3).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.AppTheme_ChapterTwo)
                        .set_title(R.string.section3)
                        .set_summary(R.string.section3_summary_text)

                        .add_section(R.string.section3_s1)
                        .set_section_body(R.string.section3_s1_text)
                        .set_section_quote(R.string.section3_s1_quote_text, R.string.section3_s1_quote_source)

                        .add_section(R.string.section3_s2)
                        .set_section_body(R.string.section3_s2_text)

                        .add_section(R.string.section3_s3)
                        .set_section_body(R.string.section3_s3_text)
        );
        findViewById(R.id.section4).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.AppTheme_ChapterThree)
                        .set_title(R.string.section4)

                        .add_section(R.string.section4_s1)

                        .add_section(R.string.section4_s2)

                        .add_section(R.string.section4_s3)
        );
        findViewById(R.id.quiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}

