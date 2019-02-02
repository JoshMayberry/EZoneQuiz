package com.example.android.ezonequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link Activities
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

