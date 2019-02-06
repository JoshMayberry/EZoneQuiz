package com.example.android.ezonequiz;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * A dialog box that lets the user know they got the answer wrong.
 * Use: https://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android/13342157#13342157
 * See: https://developer.android.com/guide/topics/ui/dialogs#PassingEvents
 */
public class DialogIncorrect extends Dialog implements android.view.View.OnClickListener {

    private QuizActivity activity;
    private String message;

    public Button view_yes;
    public Button view_no;
    public TextView view_message;

    DialogIncorrect(QuizActivity activity, String message) {
        super(activity);
        this.activity = activity;
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quiz_dialog_try_again);

        view_yes = findViewById(R.id.dialog_button_yes);
        view_yes.setOnClickListener(this);

        view_no = findViewById(R.id.dialog_button_no);
        view_no.setOnClickListener(this);

        view_message = findViewById(R.id.dialog_message_incorrect);
        view_message.setText(this.message);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_button_no) {
            activity.showNext();
        }
        dismiss();
    }
}