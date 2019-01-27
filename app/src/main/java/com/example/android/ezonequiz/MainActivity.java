package com.example.android.ezonequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private RadioButton[] view_radioButton = new RadioButton[5];
    private NumberPicker view_numberPicker;
    private Spinner view_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_radioButton[0] = findViewById(R.id.radioButton_0);
        view_radioButton[1] = findViewById(R.id.radioButton_1);
        view_radioButton[2] = findViewById(R.id.radioButton_2);
        view_radioButton[3] = findViewById(R.id.radioButton_3);
        view_radioButton[4] = findViewById(R.id.radioButton_4);
        view_numberPicker = findViewById(R.id.numberPicker);
        view_spinner = findViewById(R.id.spinner);
    }

    /**
     * The user wants to submit their answer to this question and see the next question.
     * @param view - The button view that fired this method
     */
    public void onNext(View view) {
        reset();
    }

    /**
     * Hides all answer widgets.
     */
    private void reset() {
        //See: https://stackoverflow.com/questions/17805040/how-to-create-a-number-picker-dialog/17806895#17806895
        view_numberPicker.setMaxValue(10);
        view_numberPicker.setMinValue(0);
    }



}

