package com.example.android.ezonequiz;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

enum QuestionType {
    Single, //Uses radio buttons
    Multiple, //Uses check boxes
    Integer, //Uses a number spinner
    String, //Uses an edit text view
}

/**
 * A container to store data about the question in.
 */
class Question {
    private QuizActivity activity;
    private QuestionType type; //Which type of question this is
    private int textId; //Which question to ask
    private int correctId; //What text to show when the answer was correct
    private int incorrectId; //What text to show when the answer was incorrect
    private int rangeMin; //Used for Number Spinners
    private int rangeMax; //Used for Number Spinners
    private int correctValue; //Used for Number Spinners
    private List<Integer> correctIndex = new ArrayList<>(); //The index numbers of the correct answers
    private List<Integer> answerList = new ArrayList<>(); //Which answers belong to this question
    private List<View> viewList = new ArrayList<>(); //Which widgets to use for the answers
    private int imageId; //Which image to use for this question

    boolean pointCounted = false; //Makes it so the user cannot increase their score by dismissing the next question dialog

    /**
     * Used for questions that use the radio buttons.
     *
     * @param textId       - The resource id for the string to use for the question
     * @param correctId    - The resource id for the string to use for the correct answer message
     * @param incorrectId  - The resource id for the string to use for the incorrect answer message
     * @param answerIdList - The resource ids for the strings to use for the answer widgets
     * @param correctIndex - The index of the correct answer
     */
    Question(QuizActivity activity, int imageId, int textId, int correctId, int incorrectId, int[] answerIdList, int correctIndex) {
        this.activity = activity;
        this.type = QuestionType.Single;
        this.textId = textId;
        this.imageId = imageId;
        this.correctId = correctId;
        this.incorrectId = incorrectId;

        for (int i = 0; i < answerIdList.length; i++) {
            this.answerList.add(answerIdList[i]);
            this.viewList.add(activity.view_radioButton[i]);
        }

        assert this.answerList.size() > correctIndex;
        this.correctIndex.add(correctIndex);

    }

    /**
     * Used for questions that use the check boxes.
     *
     * @param textId       - The resource id for the string to use for the question
     * @param correctId    - The resource id for the string to use for the correct answer message
     * @param incorrectId  - The resource id for the string to use for the incorrect answer message
     * @param answerIdList - The resource ids for the strings to use for the answer widgets
     * @param correctIndex - The indexes of the correct answers
     */
    Question(QuizActivity activity, int imageId, int textId, int correctId, int incorrectId, int[] answerIdList, int[] correctIndex) {
        this.activity = activity;
        this.type = QuestionType.Multiple;
        this.textId = textId;
        this.imageId = imageId;
        this.correctId = correctId;
        this.incorrectId = incorrectId;

        for (int i = 0; i < answerIdList.length; i++) {
            this.answerList.add(answerIdList[i]);
            this.viewList.add(this.activity.view_checkBox[i]);
        }

        for (int i : correctIndex) {
            assert this.answerList.size() > i;
            this.correctIndex.add(i);
        }
    }

    /**
     * Used for questions with a number spinner.
     *
     * @param textId       - The resource id for the string to use for the question
     * @param correctId    - The resource id for the string to use for the correct answer message
     * @param incorrectId  - The resource id for the string to use for the incorrect answer message
     * @param rangeMin     - The lowest value the number spinner can have
     * @param rangeMax     - The highest value the number spinner can have
     * @param correctValue - The correct value to select
     */
    Question(QuizActivity activity, int imageId, int textId, int correctId, int incorrectId, int rangeMin, int rangeMax, int correctValue) {
        this.activity = activity;
        this.type = QuestionType.Integer;
        this.textId = textId;
        this.imageId = imageId;
        this.correctId = correctId;
        this.incorrectId = incorrectId;
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;

        assert rangeMin <= correctValue;
        assert rangeMax >= correctValue;
        this.correctValue = correctValue;
        this.viewList.add(this.activity.view_numberPicker);
    }

    /**
     * Hides this question on the screen.
     */
    void hide() {
        for (View view : this.activity.currentQuestion.viewList) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Shows this question on the screen.
     */
    void show() {
        for (View view : this.activity.currentQuestion.viewList) {
            view.setVisibility(View.VISIBLE);
        }

        this.activity.view_image.setImageDrawable(ContextCompat.getDrawable(activity, this.imageId));
        this.activity.view_questionText.setText(this.activity.currentQuestion.textId);

        switch (this.type) {
            case Single:
                for (int i = 0; i < this.viewList.size(); i++) {
                    RadioButton view = (RadioButton) viewList.get(i);
                    view.setText(this.answerList.get(i));
                }
                this.activity.view_radioGroup.clearCheck();
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
                this.activity.view_numberPicker.setMinValue(this.rangeMin);
                this.activity.view_numberPicker.setMaxValue(this.rangeMax);
                //Use: https://stackoverflow.com/questions/12979643/change-the-step-size-of-a-numberpicker/30469826#30469826
        }
    }

    /**
     * Checks the user's answer.
     *
     * @return - Whether or not the user chose correctly
     */
    boolean check() {
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
                return this.activity.view_numberPicker.getValue() == this.correctValue;
        }
        return false;
    }

    /**
     * Returns a formatted message saying that the user chose correctly
     *
     * @return The message to show in the dialog box
     * See: https://www.baeldung.com/java-random-list-element#highlighter_812240
     */
    String getMessage_correct() {
        return this.activity.getResources().getString(this.correctId, this.activity.correctList[this.activity.random.nextInt(this.activity.correctList.length)]);
    }

    /**
     * Returns a formatted message saying that the user chose incorrectly
     *
     * @return The message to show in the dialog box
     */
    String getMessage_incorrect() {
        return this.activity.getResources().getString(this.incorrectId, this.activity.incorrectList[this.activity.random.nextInt(this.activity.incorrectList.length)]);
    }
}
