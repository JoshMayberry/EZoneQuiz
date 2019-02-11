package com.example.android.ezonequiz;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * A dialog box that lets the user know they got the answer mCorrect.
 * Use: https://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android/13342157#13342157
 * See: https://developer.android.com/guide/topics/ui/dialogs#PassingEvents
 */
public class QuizDialog extends Dialog implements android.view.View.OnClickListener {

    private QuizActivity mActivity;
    private boolean mLastQuestion;
    private boolean mCorrect;

    QuizDialog(QuizActivity activity, boolean lastQuestion, boolean correct) {
        super(activity);
        this.mActivity = activity;
        this.mCorrect = correct;
        this.mLastQuestion = lastQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        String message;
        if (this.mCorrect) {
            setContentView(R.layout.quiz_dialog_good_job);
            message = mActivity.currentQuestion.getMessageCorrect();
        } else {
            setContentView(R.layout.quiz_dialog_try_again);
            findViewById(R.id.dialog_button_cancel).setOnClickListener(this);
            message = mActivity.currentQuestion.getMessageIncorrect();
        }

        Button viewNext = findViewById(R.id.dialog_button_next);
        viewNext.setOnClickListener(this);
        if (this.mLastQuestion) {
            viewNext.setText(R.string.dialog_finish);
            message += "\n\n" + mActivity.getString(R.string.dialog_score, mActivity.score, mActivity.viewProgress.getMax());
        } else {
            if (this.mCorrect) {
                viewNext.setText(R.string.dialog_next);
            } else {
                viewNext.setText(R.string.dialog_no);
            }
        }

        TextView view_message = findViewById(R.id.dialog_message);
        view_message.setText(message);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_button_next) {
            if (this.mLastQuestion) {
                mActivity.finish();
                return;
            }
            mActivity.showNext();
        }
        dismiss();
    }
}