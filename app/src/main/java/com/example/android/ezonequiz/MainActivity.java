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

        setupListener(findViewById(R.id.numbers), NumbersActivity.class);
        setupListener(findViewById(R.id.phrases), PhrasesActivity.class);
        setupListener(findViewById(R.id.family), FamilyActivity.class);
        setupListener(findViewById(R.id.colors), ColorsActivity.class);
    }

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

