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
        setupListener(R.id.section1, BookActivity.class);
        setupListener(R.id.section2, BookActivity.class);
        setupListener(R.id.section3, BookActivity.class);
        setupListener(R.id.section4, BookActivity.class);
        setupListener(R.id.quiz, QuizActivity.class);
    }

    /**
     * A helper function that connects buttons to other activities.
     * @param view - What view to add a listener to
     * @param cls - What class to listen for
     * Example Input: setupListener(findViewById(R.id.quiz), QuizActivity.class);
     */
    public void setupListener(final int viewId, final Class cls) {
        View view = findViewById(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
                Intent intent = new Intent(MainActivity.this, cls);
                intent.putExtra("resourceId", viewId);
                startActivity(intent);
            }
        });
    }
}

