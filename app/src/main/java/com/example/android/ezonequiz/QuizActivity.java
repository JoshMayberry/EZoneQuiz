package com.example.android.ezonequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    public RadioButton[] view_radioButton = new RadioButton[5];
    public CheckBox[] view_checkBox = new CheckBox[5];
    public NumberPicker view_numberPicker;
    public RadioGroup view_radioGroup;
    public TextView view_questionText;
    public Spinner view_spinner;
    public ImageView view_image;

    public ProgressBar view_progress;

    private List<Question> questions = new ArrayList<>();
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
        view_progress = findViewById(R.id.quiz_progress);
        view_radioButton[0] = findViewById(R.id.quiz_radioButton_0);
        view_radioButton[1] = findViewById(R.id.quiz_radioButton_1);
        view_radioButton[2] = findViewById(R.id.quiz_radioButton_2);
        view_radioButton[3] = findViewById(R.id.quiz_radioButton_3);
        view_radioButton[4] = findViewById(R.id.quiz_radioButton_4);
        view_checkBox[0] = findViewById(R.id.quiz_checkBox_0);
        view_checkBox[1] = findViewById(R.id.quiz_checkBox_1);
        view_checkBox[2] = findViewById(R.id.quiz_checkBox_2);
        view_checkBox[3] = findViewById(R.id.quiz_checkBox_3);
        view_checkBox[4] = findViewById(R.id.quiz_checkBox_4);
        view_numberPicker = findViewById(R.id.quiz_numberPicker);
        view_spinner = findViewById(R.id.quiz_spinner);
        view_questionText = findViewById(R.id.quiz_title);
        view_radioGroup = findViewById(R.id.quiz_radioGroup);
        view_image = findViewById(R.id.quiz_image);

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
        questions.clear();
        questions.add(new Question(this,
                R.drawable.quiz1_1,
                R.string.quiz1_1,
                R.string.quiz1_1_correct,
                R.string.quiz1_1_incorrect,
                new int[]{R.string.quiz1_1_a1, R.string.quiz1_1_a2},
                0
        ));
        questions.add(new Question(this,
                R.drawable.quiz1_2,
                R.string.quiz1_2,
                R.string.quiz1_2_correct,
                R.string.quiz1_2_incorrect,
                new int[]{R.string.quiz1_2_a1, R.string.quiz1_2_a2},
                1
        ));
        questions.add(new Question(this,
                R.drawable.quiz1_3,
                R.string.quiz1_3,
                R.string.quiz1_3_correct,
                R.string.quiz1_3_incorrect,
                new int[]{R.string.quiz1_3_a1, R.string.quiz1_3_a2},
                1
        ));
        questions.add(new Question(this,
                R.drawable.quiz2_1,
                R.string.quiz2_1,
                R.string.quiz2_1_correct,
                R.string.quiz2_1_incorrect,
                new int[]{R.string.quiz2_1_a1, R.string.quiz2_1_a2, R.string.quiz2_1_a3, R.string.quiz2_1_a4},
                new int[]{0, 1, 2, 3}
        ));
        questions.add(new Question(this,
                R.drawable.quiz3_1,
                R.string.quiz3_1,
                R.string.quiz3_1_correct,
                R.string.quiz3_1_incorrect,
                1,
                6,
                2
        ));
        questions.add(new Question(this,
                R.drawable.quiz3_2,
                R.string.quiz3_2,
                R.string.quiz3_2_correct,
                R.string.quiz3_2_incorrect,
                1,
                12,
                3
        ));
        questions.add(new Question(this,
                R.drawable.quiz_3_3,
                R.string.quiz3_3,
                R.string.quiz3_3_correct,
                R.string.quiz3_3_incorrect,
                1,
                100,
                72
        ));
    }

    /**
     * Hides all answer widgets.
     */
    private void reset() {
        score = 0;

        //Hide all widgets
        view_spinner.setVisibility(View.GONE);
        view_numberPicker.setVisibility(View.GONE);
        view_radioButton[0].setVisibility(View.GONE);
        view_radioButton[1].setVisibility(View.GONE);
        view_radioButton[2].setVisibility(View.GONE);
        view_radioButton[3].setVisibility(View.GONE);
        view_radioButton[4].setVisibility(View.GONE);
        view_checkBox[0].setVisibility(View.GONE);
        view_checkBox[1].setVisibility(View.GONE);
        view_checkBox[2].setVisibility(View.GONE);
        view_checkBox[3].setVisibility(View.GONE);
        view_checkBox[4].setVisibility(View.GONE);

        //Scramble question order
//        Collections.shuffle(questions);

        //Reset progress bar
        view_progress.setMax(questions.size());
        view_progress.setProgress(0);

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
                score += 1;
                currentQuestion.pointCounted = true;
            }
        } else {
            if (currentQuestion.pointCounted) {
                score -= 1;
                currentQuestion.pointCounted = false;
            }
        }
        QuizDialog quizDialog = new QuizDialog(this, questions.size() <= 0, correct);
        quizDialog.show();
    }

    /**
     * Shows the next question.
     */
    public void showNext() {
        if (currentQuestion != null) {
            currentQuestion.hide();
        }
        view_progress.setProgress(view_progress.getProgress() + 1);
        currentQuestion = questions.remove(0);
        currentQuestion.show();
    }
}
