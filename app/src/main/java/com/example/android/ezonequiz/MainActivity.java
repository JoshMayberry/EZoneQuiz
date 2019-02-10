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
                new BookListener_Text()
                        .set_theme(R.style.Theme_AppCompat_EZoneQuiz_Introduction)
                        .set_title(R.string.section1)
                        .set_image(R.drawable.section1_s1)
                        .set_image_scaleType(1)
                        .set_subtitle(R.string.section1_s1)
                        .set_body(R.string.section1_s1_text)
                        .set_quote(R.string.section1_s1_quote_text, R.string.section1_s1_quote_source)
        );
        findViewById(R.id.main_section2).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.Theme_AppCompat_EZoneQuiz_ChapterOne)
                        .set_title(R.string.section2)
                        .add_section(R.string.summary)
                        .set_section_image(R.drawable.section2_summary)
                        .set_section_image_scaleType(1)
                        .set_section_body(R.string.section2_summary_text)

                        .add_section(R.string.section2_s1)
                        .set_section_image(R.drawable.section2_s1)
                        .set_section_body(R.string.section2_s1_text)
                        .set_section_quote(R.string.section2_s1_quote_text, R.string.section2_s1_quote_source)

                        .add_section(R.string.section2_s2)
                        .set_section_image(R.drawable.section2_s2)
                        .set_section_body(R.string.section2_s2_text)
                        .set_section_quote(R.string.section2_s2_quote_text, R.string.section2_s2_quote_source)

                        .add_section(R.string.section2_s3)
                        .set_section_image(R.drawable.section2_s3)
                        .set_section_body(R.string.section2_s3_text)

                        .add_section(R.string.section2_s4)

                        .add_section(R.string.section2_s5)
                        .set_section_image(R.drawable.section2_s5)
                        .set_section_body(R.string.section2_s5_text)

                        .add_section(R.string.section2_s6)
                        .set_section_image(R.drawable.section2_s6)
                        .set_section_body(R.string.section2_s6_text)
                        .set_section_quote(R.string.section2_s6_quote_text, R.string.section2_s6_quote_source)
        );
        findViewById(R.id.main_section3).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.Theme_AppCompat_EZoneQuiz_ChapterTwo)
                        .set_title(R.string.section3)
                        .add_section(R.string.summary)
                        .set_section_image(R.drawable.section3_summary)
                        .set_section_image_scaleType(1)
                        .set_section_body(R.string.section3_summary_text)

                        .add_section(R.string.section3_s1)
                        .set_section_image(R.drawable.section3_s1)
                        .set_section_body(R.string.section3_s1_text)
                        .set_section_quote(R.string.section3_s1_quote_text, R.string.section3_s1_quote_source)

                        .add_section(R.string.section3_s2)
                        .set_section_image(R.drawable.section3_s2)
                        .set_section_body(R.string.section3_s2_text)

                        .add_section(R.string.section3_s3)
                        .set_section_image(R.drawable.section3_s3)
                        .set_section_body(R.string.section3_s3_text)
        );
        findViewById(R.id.main_section4).setOnClickListener(
                new BookListener_ToC()
                        .set_theme(R.style.Theme_AppCompat_EZoneQuiz_ChapterThree)
                        .set_title(R.string.section4)

                        .add_section(R.string.section4_s1)

                        .add_section(R.string.section4_s2)

                        .add_section(R.string.section4_s3)
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

