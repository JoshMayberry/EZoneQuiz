package com.example.android.ezonequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    public RadioButton[] viewRadioButton = new RadioButton[5];
    public CheckBox[] viewCheckBox = new CheckBox[5];
    public NumberPicker viewNumberPicker;
    public RadioGroup viewRadioGroup;
    public TextView viewQuestionText;
    public ProgressBar viewProgress;
    public EditText viewEditText;
    public ImageView viewImage;

    private List<Question> mQuestions = new ArrayList<>();
    public Question currentQuestion;

    public int score;
    public Random random = new Random();
    public String[] correctList = new String[3]; //Different ways to say correct
    public String[] incorrectList = new String[2]; //Different ways to say incorrect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Get Resources
        viewProgress = findViewById(R.id.quiz_progress);
        viewRadioButton[0] = findViewById(R.id.quiz_radioButton_0);
        viewRadioButton[1] = findViewById(R.id.quiz_radioButton_1);
        viewRadioButton[2] = findViewById(R.id.quiz_radioButton_2);
        viewRadioButton[3] = findViewById(R.id.quiz_radioButton_3);
        viewRadioButton[4] = findViewById(R.id.quiz_radioButton_4);
        viewCheckBox[0] = findViewById(R.id.quiz_checkBox_0);
        viewCheckBox[1] = findViewById(R.id.quiz_checkBox_1);
        viewCheckBox[2] = findViewById(R.id.quiz_checkBox_2);
        viewCheckBox[3] = findViewById(R.id.quiz_checkBox_3);
        viewCheckBox[4] = findViewById(R.id.quiz_checkBox_4);
        viewNumberPicker = findViewById(R.id.quiz_numberPicker);
        viewRadioGroup = findViewById(R.id.quiz_radioGroup);
        viewEditText = findViewById(R.id.quiz_edit_text);
        viewQuestionText = findViewById(R.id.quiz_title);
        viewImage = findViewById(R.id.quiz_image);

        correctList[0] = getResources().getString(R.string.correct_1);
        correctList[1] = getResources().getString(R.string.correct_2);
        correctList[2] = getResources().getString(R.string.correct_3);
        incorrectList[0] = getResources().getString(R.string.incorrect_1);
        incorrectList[1] = getResources().getString(R.string.incorrect_2);

        generateQuestions();
        reset();
    }

    /**
     * Used to distinguish question types from each other.
     * This is needed because a question with check boxes should behave differently than a question with a number spinner.
     * See: https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
     * See: https://stackoverflow.com/questions/38758220/android-enum-set-enum-programatically-from-integer-value/38758335#38758335
     */

    /**
     * Fills the question array with structured question objects.
     */
    private void generateQuestions() {
        mQuestions.clear();
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz1_1)
                .setType_single(R.string.quiz1_1, new int[]{R.string.quiz1_1_a1, R.string.quiz1_1_a2}, 0)
                .setMessage_correct(R.string.quiz1_1_correct)
                .setMessage_incorrect(R.string.quiz1_1_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz1_2)
                .setType_single(R.string.quiz1_2, new int[]{R.string.quiz1_2_a1, R.string.quiz1_2_a2}, 1)
                .setMessage_correct(R.string.quiz1_2_correct)
                .setMessage_incorrect(R.string.quiz1_2_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz1_3)
                .setType_single(R.string.quiz1_3, new int[]{R.string.quiz1_3_a1, R.string.quiz1_3_a2}, 1)
                .setMessage_correct(R.string.quiz1_3_correct)
                .setMessage_incorrect(R.string.quiz1_3_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz2_1)
                .setType_multiple(R.string.quiz2_1, new int[]{R.string.quiz2_1_a1, R.string.quiz2_1_a2, R.string.quiz2_1_a3, R.string.quiz2_1_a4}, new int[]{0, 1, 2, 3})
                .setMessage_correct(R.string.quiz2_1_correct)
                .setMessage_incorrect(R.string.quiz2_1_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz3_1)
                .setType_integer(R.string.quiz3_1, 1, 6, 2)
                .setMessage_correct(R.string.quiz3_1_correct)
                .setMessage_incorrect(R.string.quiz3_1_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz3_2)
                .setType_integer(R.string.quiz3_2, 1, 12, 3)
                .setMessage_correct(R.string.quiz3_2_correct)
                .setMessage_incorrect(R.string.quiz3_2_incorrect)
        );
        mQuestions.add(new Question(this)
                .setImage(R.drawable.quiz3_3)
                .setType_string(R.string.quiz3_3, new int[]{R.string.quiz3_3_a1, R.string.quiz3_3_a2, R.string.quiz3_3_a3, R.string.quiz3_3_a4, R.string.quiz3_3_a5, R.string.quiz3_3_a6, R.string.quiz3_3_a7, R.string.quiz3_3_a8, R.string.quiz3_3_a9, R.string.quiz3_3_a10, R.string.quiz3_3_a11, R.string.quiz3_3_a12, R.string.quiz3_3_a13, R.string.quiz3_3_a14, R.string.quiz3_3_a15, R.string.quiz3_3_a16, R.string.quiz3_3_a17, R.string.quiz3_3_a18, R.string.quiz3_3_a19, R.string.quiz3_3_a20, R.string.quiz3_3_a21, R.string.quiz3_3_a22, R.string.quiz3_3_a23, R.string.quiz3_3_a24, R.string.quiz3_3_a25, R.string.quiz3_3_a26, R.string.quiz3_3_a27, R.string.quiz3_3_a28, R.string.quiz3_3_a29, R.string.quiz3_3_a30, R.string.quiz3_3_a31, R.string.quiz3_3_a32, R.string.quiz3_3_a33, R.string.quiz3_3_a34, R.string.quiz3_3_a35, R.string.quiz3_3_a36, R.string.quiz3_3_a37, R.string.quiz3_3_a38, R.string.quiz3_3_a39, R.string.quiz3_3_a40, R.string.quiz3_3_a41, R.string.quiz3_3_a42, R.string.quiz3_3_a43, R.string.quiz3_3_a44, R.string.quiz3_3_a45, R.string.quiz3_3_a46, R.string.quiz3_3_a47, R.string.quiz3_3_a48, R.string.quiz3_3_a49, R.string.quiz3_3_a50})
                .setMessage_correct(R.string.quiz3_3_correct)
                .setMessage_incorrect(R.string.quiz3_3_incorrect)
        );
    }

    /**
     * Hides all answer widgets.
     */
    private void reset() {
        score = 0;

        //Hide all widgets
        viewEditText.setVisibility(View.GONE);
        viewNumberPicker.setVisibility(View.GONE);
        viewRadioButton[0].setVisibility(View.GONE);
        viewRadioButton[1].setVisibility(View.GONE);
        viewRadioButton[2].setVisibility(View.GONE);
        viewRadioButton[3].setVisibility(View.GONE);
        viewRadioButton[4].setVisibility(View.GONE);
        viewCheckBox[0].setVisibility(View.GONE);
        viewCheckBox[1].setVisibility(View.GONE);
        viewCheckBox[2].setVisibility(View.GONE);
        viewCheckBox[3].setVisibility(View.GONE);
        viewCheckBox[4].setVisibility(View.GONE);

        //Scramble question order
        Collections.shuffle(mQuestions);

        //Reset progress bar
        viewProgress.setMax(mQuestions.size());
        viewProgress.setProgress(0);

        //Show the first question
        currentQuestion = null;
        showNext();
    }

    /**
     * The user wants to submit their answer to this question and see the next question.
     *
     * @param view - The button view that fired this method
     */
    public void onNext(View view) {
        boolean correct = currentQuestion.check();
        if (correct) {
            if (!(currentQuestion.pointCounted)) {
                score++;
                currentQuestion.pointCounted = true;
            }
        } else {
            if (currentQuestion.pointCounted) {
                score--;
                currentQuestion.pointCounted = false;
            }
        }
        QuizDialog quizDialog = new QuizDialog(this, mQuestions.size() <= 0, correct);
        quizDialog.show();
    }

    /**
     * Shows the next question.
     */
    public void showNext() {
        if (currentQuestion != null) {
            currentQuestion.hide();
        }
        viewProgress.setProgress(viewProgress.getProgress() + 1);
        currentQuestion = mQuestions.remove(0);
        currentQuestion.show();
    }
}
