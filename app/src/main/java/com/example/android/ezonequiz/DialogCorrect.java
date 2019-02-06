package com.example.android.ezonequiz;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * A dialog box that lets the user know they got the answer correct.
 * Use: https://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android/13342157#13342157
 * See: https://developer.android.com/guide/topics/ui/dialogs#PassingEvents
 */
public class DialogCorrect extends Dialog implements android.view.View.OnClickListener {

    private QuizActivity activity;
    private String message;
    private String button_text;
    private boolean lastQuestion;

    public Button view_next;
    public TextView view_message;

    DialogCorrect(QuizActivity activity, String message) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.button_text = "Next";
        this.lastQuestion = false;
    }
    DialogCorrect(QuizActivity activity, String message, String button_text) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.button_text = button_text;
        this.lastQuestion = false;
    }
    DialogCorrect(QuizActivity activity, String message, String button_text, boolean lastQuestion) {
        super(activity);
        this.activity = activity;
        this.message = message;
        this.button_text = button_text;
        this.lastQuestion = lastQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quiz_dialog_good_job);

        view_next = findViewById(R.id.dialog_button_next);
        view_next.setOnClickListener(this);
        view_next.setText(this.button_text);

        view_message = findViewById(R.id.dialog_message_correct);
        view_message.setText(this.message);
    }

    @Override
    public void onClick(View view) {
        if (this.lastQuestion) {
            activity.finish();
            return;
        }
        activity.showNext();
        dismiss();
    }
}