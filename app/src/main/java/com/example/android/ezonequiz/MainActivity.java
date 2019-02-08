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

        ImageView view_image = findViewById(R.id.mainImage);
        view_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.storm));

        //Link Activities
        setupListener(findViewById(R.id.section1), Book_IntroductionActivity.class);
        setupListener(findViewById(R.id.section2), Book_ChapterOneActivity.class);
        setupListener(findViewById(R.id.section3), Book_ChapterTwoActivity.class);
        setupListener(findViewById(R.id.section4), Book_ChapterThreeActivity.class);
        setupListener(findViewById(R.id.quiz), QuizActivity.class);
    }

    /**
     * A helper function that connects buttons to other activities.
     * @param view - What view to add a listener to
     * @param cls - What class to listen for
     * Example Input: setupListener(findViewById(R.id.quiz), QuizActivity.class);
     */
    public void setupListener(View view, final Class cls) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
                Intent intent = new Intent(MainActivity.this, cls);
                startActivity(intent);
            }
        });
    }
}

