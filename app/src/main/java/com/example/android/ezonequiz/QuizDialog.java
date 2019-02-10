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
public class QuizDialog extends Dialog implements android.view.View.OnClickListener {

    private QuizActivity activity;
    private boolean lastQuestion;
    private boolean correct;

    QuizDialog(QuizActivity activity, boolean lastQuestion, boolean correct) {
        super(activity);
        this.activity = activity;
        this.correct = correct;
        this.lastQuestion = lastQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        String message;
        if (this.correct) {
            setContentView(R.layout.quiz_dialog_good_job);
            message = activity.currentQuestion.getMessage_correct();
        } else {
            setContentView(R.layout.quiz_dialog_try_again);
            findViewById(R.id.dialog_button_cancel).setOnClickListener(this);
            message = activity.currentQuestion.getMessage_incorrect();
        }

        Button view_next = findViewById(R.id.dialog_button_next);
        view_next.setOnClickListener(this);
        if (this.lastQuestion) {
            view_next.setText(R.string.dialog_finish);
            message += "\n\n" + activity.getString(R.string.dialog_score, activity.score, activity.view_progress.getMax());
        } else {
            if (this.correct) {
                view_next.setText(R.string.dialog_next);
            } else {
                view_next.setText(R.string.dialog_no);
            }
        }

        TextView view_message = findViewById(R.id.dialog_message);
        view_message.setText(message);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_button_next) {
            if (this.lastQuestion) {
                activity.finish();
                return;
            }
            activity.showNext();
        }
        dismiss();
    }
}