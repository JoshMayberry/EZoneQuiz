package com.example.android.ezonequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

    private RadioButton[] view_radioButton = new RadioButton[5];
    private CheckBox[] view_checkBox = new CheckBox[5];
    private RadioGroup view_radioGroup;
    private NumberPicker view_numberPicker;
    private TextView view_questionText;
    private ProgressBar view_progress;
    private Spinner view_spinner;

    private List<Question> questions = new ArrayList<>();
    private Question currentQuestion;

    private int score;
    private Random random = new Random();
    private String[] correctList = new String[3]; //Different ways to say correct
    private String[] incorrectList = new String[2]; //Different ways to say incorrect

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Get Resources
        view_progress = findViewById(R.id.quizProgress);
        view_radioButton[0] = findViewById(R.id.radioButton_0);
        view_radioButton[1] = findViewById(R.id.radioButton_1);
        view_radioButton[2] = findViewById(R.id.radioButton_2);
        view_radioButton[3] = findViewById(R.id.radioButton_3);
        view_radioButton[4] = findViewById(R.id.radioButton_4);
        view_checkBox[0] = findViewById(R.id.checkBox_0);
        view_checkBox[1] = findViewById(R.id.checkBox_1);
        view_checkBox[2] = findViewById(R.id.checkBox_2);
        view_checkBox[3] = findViewById(R.id.checkBox_3);
        view_checkBox[4] = findViewById(R.id.checkBox_4);
        view_numberPicker = findViewById(R.id.numberPicker);
        view_spinner = findViewById(R.id.spinner);
        view_questionText = findViewById(R.id.quiz_title);
        view_radioGroup = findViewById(R.id.radioGroup);

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
    public enum QuestionType {
        Single, //Uses radio buttons
        Multiple, //Uses check boxes
        Integer, //Uses a number spinner
        String, //Uses an edit text view
    }

    /**
     * A container to store data about the question in.
     */
    private class Question {
        QuestionType type; //Which type of question this is
        int textId; //Which question to ask
        int correctId; //What text to show when the answer was correct
        int incorrectId; //What text to show when the answer was incorrect
        int rangeMin; //Used for Number Spinners
        int rangeMax; //Used for Number Spinners
        int correctValue; //Used for Number Spinners
        List<Integer> correctIndex = new ArrayList<>(); //The index numbers of the correct answers
        List<Integer> answerList = new ArrayList<>(); //Which answers belong to this question
        List<View> viewList = new ArrayList<>(); //Which widgets to use for the answers

        /**
         * Used for questions that use the radio buttons.
         * @param textId - The resource id for the string to use for the question
         * @param correctId - The resource id for the string to use for the correct answer message
         * @param incorrectId - The resource id for the string to use for the incorrect answer message
         * @param answerIdList - The resource ids for the strings to use for the answer widgets
         * @param correctIndex - The index of the correct answer
         */
        Question(int textId, int correctId, int incorrectId, int[] answerIdList, int correctIndex){
            this.type = QuestionType.Single;
            this.textId = textId;
            this.correctId = correctId;
            this.incorrectId = incorrectId;

            for (int i=0; i<answerIdList.length; i++) {
                this.answerList.add(answerIdList[i]);
                this.viewList.add(view_radioButton[i]);
            }

            assert this.answerList.size() > correctIndex;
            this.correctIndex.add(correctIndex);

        }
        /**
         * Used for questions that use the check boxes.
         * @param textId - The resource id for the string to use for the question
         * @param correctId - The resource id for the string to use for the correct answer message
         * @param incorrectId - The resource id for the string to use for the incorrect answer message
         * @param answerIdList - The resource ids for the strings to use for the answer widgets
         * @param correctIndex - The indexes of the correct answers
         */
        Question(int textId, int correctId, int incorrectId, int[] answerIdList, int[] correctIndex){
            this.type = QuestionType.Multiple;
            this.textId = textId;
            this.correctId = correctId;
            this.incorrectId = incorrectId;

            for (int i=0; i<answerIdList.length; i++) {
                this.answerList.add(answerIdList[i]);
                this.viewList.add(view_checkBox[i]);
            }

            for (int i: correctIndex) {
                assert this.answerList.size() > i;
                this.correctIndex.add(i);
            }
        }

        /**
         * Used for questions with a number spinner.
         * @param textId - The resource id for the string to use for the question
         * @param correctId - The resource id for the string to use for the correct answer message
         * @param incorrectId - The resource id for the string to use for the incorrect answer message
         * @param rangeMin - The lowest value the number spinner can have
         * @param rangeMax - The highest value the number spinner can have
         * @param correctValue - The correct value to select
         */
        Question(int textId, int correctId, int incorrectId, int rangeMin, int rangeMax, int correctValue) {
            this.type = QuestionType.Integer;
            this.textId = textId;
            this.correctId = correctId;
            this.incorrectId = incorrectId;
            this.rangeMin = rangeMin;
            this.rangeMax = rangeMax;

            assert rangeMin <= correctValue;
            assert rangeMax >= correctValue;
            this.correctValue = correctValue;
            this.viewList.add(view_numberPicker);
        }

        /**
         * Hides this question on the screen.
         */
        public void hide() {
            for (View view: currentQuestion.viewList) {
                view.setVisibility(View.GONE);
            }
        }
        /**
         * Shows this question on the screen.
         */
        public void show() {
            for (View view : currentQuestion.viewList) {
                view.setVisibility(View.VISIBLE);
            }
            view_questionText.setText(currentQuestion.textId);

            switch (this.type) {
                case Single:
                    for (int i = 0; i < this.viewList.size(); i++) {
                        RadioButton view = (RadioButton) viewList.get(i);
                        view.setText(this.answerList.get(i));
                    }
                    view_radioGroup.clearCheck();
                    break;
                case Multiple:
                    for (int i = 0; i < this.viewList.size(); i++) {
                        CheckBox view = (CheckBox) viewList.get(i);
                        view.setText(this.answerList.get(i));
                        view.setChecked(false);
                    }
                    break;
                case Integer:
                    //See: https://stackoverflow.com/questions/17805040/how-to-create-a-number-picker-dialog/17806895#17806895
                    view_numberPicker.setMinValue(this.rangeMin);
                    view_numberPicker.setMaxValue(this.rangeMax);
                    //Use: https://stackoverflow.com/questions/12979643/change-the-step-size-of-a-numberpicker/30469826#30469826
            }
        }
        /**
         * Checks the user's answer.
         * @return - Whether or not the user chose correctly
         */
        public boolean check() {
            switch (this.type) {
                case Single:
                    for (int i = 0; i < this.viewList.size(); i++) {
                        RadioButton view = (RadioButton) viewList.get(i);
                        if (view.isChecked() != this.correctIndex.contains(i)) {
                            return false;
                        }
                    }
                    return true;
                case Multiple:
                    for (int i = 0; i < this.viewList.size(); i++) {
                        CheckBox view = (CheckBox) viewList.get(i);
                        if (view.isChecked() != this.correctIndex.contains(i)) {
                            return false;
                        }
                    }
                    return true;
                case Integer:
                    return view_numberPicker.getValue() == this.correctValue;
            }
            return false;
        }
        /**
         * Returns a formatted message saying that the user chose correctly
         * @return The message to show in the dialog box
         * See: https://www.baeldung.com/java-random-list-element#highlighter_812240
         */
        public String getMessage_correct() {
            return getResources().getString(this.correctId, correctList[random.nextInt(correctList.length)]);
        }
        /**
         * Returns a formatted message saying that the user chose incorrectly
         * @return The message to show in the dialog box
         */
        public String getMessage_incorrect() {
            return getResources().getString(this.incorrectId, incorrectList[random.nextInt(incorrectList.length)]);
        }
    }

    /**
     * Fills the question array with structured question objects.
     */
    private void generateQuestions() {
        questions.clear();
        questions.add(new Question(
                R.string.quiz1_1,
                R.string.quiz1_1_correct,
                R.string.quiz1_1_incorrect,
                new int[] {R.string.quiz1_1_a1, R.string.quiz1_1_a2},
                0
        ));
        questions.add(new Question(
                R.string.quiz1_2,
                R.string.quiz1_2_correct,
                R.string.quiz1_2_incorrect,
                new int[] {R.string.quiz1_2_a1, R.string.quiz1_2_a2},
                1
        ));
        questions.add(new Question(
                R.string.quiz1_3,
                R.string.quiz1_3_correct,
                R.string.quiz1_3_incorrect,
                new int[] {R.string.quiz1_3_a1, R.string.quiz1_3_a2},
                1
        ));
        questions.add(new Question(
                R.string.quiz2_1,
                R.string.quiz2_1_correct,
                R.string.quiz2_1_incorrect,
                new int[] {R.string.quiz2_1_a1, R.string.quiz2_1_a2, R.string.quiz2_1_a3, R.string.quiz2_1_a4},
                new int[] {0, 1, 2, 3}
        ));
        questions.add(new Question(
                R.string.quiz3_1,
                R.string.quiz3_1_correct,
                R.string.quiz3_1_incorrect,
                1,
                6,
                2
        ));
        questions.add(new Question(
                R.string.quiz3_2,
                R.string.quiz3_2_correct,
                R.string.quiz3_2_incorrect,
                1,
                12,
                3
        ));
        questions.add(new Question(
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
     * @param view - The button view that fired this method
     */
    public void onNext(View view) {
        if (questions.size() <= 0) {
            score += 1;
            DialogCorrect dialogCorrect = new DialogCorrect(this,
                    getResources().getString(R.string.dialog_score, score, view_numberPicker.getMaxValue()),
                    getResources().getString(R.string.dialog_finish),
                    true
                    );
            dialogCorrect.show();
            return;
        }
        showDialog();
    }
    /**
     * Shows the dialog box for moving on to the next question.
     */
    private void showDialog() {
        if (currentQuestion != null) {
            if (!currentQuestion.check()) {
                DialogIncorrect dialogIncorrect = new DialogIncorrect(this, currentQuestion.getMessage_incorrect());
                dialogIncorrect.show();
            } else {
                DialogCorrect dialogCorrect = new DialogCorrect(this, currentQuestion.getMessage_correct());
                dialogCorrect.show();
                score += 1;
            }
        }
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
