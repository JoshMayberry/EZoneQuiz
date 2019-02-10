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
    private QuizActivity mActivity;
    private QuestionType mType; //Which mType of question this is
    private int mTextId; //Which question to ask
    private int mCorrectId; //What text to show when the answer was correct
    private int mIncorrectId; //What text to show when the answer was incorrect
    private int mRangeMin; //Used for Number Spinners
    private int mRangeMax; //Used for Number Spinners
    private int mCorrectValue; //Used for Number Spinners
    private List<Integer> mCorrectIndexList = new ArrayList<>(); //The index numbers of the correct answers
    private List<Integer> mAnswerList = new ArrayList<>(); //Which answers belong to this question
    private List<View> mViewList = new ArrayList<>(); //Which widgets to use for the answers
    private int mImageId; //Which image to use for this question

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
        this.mActivity = activity;
        this.mType = QuestionType.Single;
        this.mTextId = textId;
        this.mImageId = imageId;
        this.mCorrectId = correctId;
        this.mIncorrectId = incorrectId;

        for (int i = 0; i < answerIdList.length; i++) {
            this.mAnswerList.add(answerIdList[i]);
            this.mViewList.add(activity.viewRadioButton[i]);
        }

        assert this.mAnswerList.size() > correctIndex;
        this.mCorrectIndexList.add(correctIndex);

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
        this.mActivity = activity;
        this.mType = QuestionType.Multiple;
        this.mTextId = textId;
        this.mImageId = imageId;
        this.mCorrectId = correctId;
        this.mIncorrectId = incorrectId;

        for (int i = 0; i < answerIdList.length; i++) {
            this.mAnswerList.add(answerIdList[i]);
            this.mViewList.add(this.mActivity.viewCheckBox[i]);
        }

        for (int i : correctIndex) {
            assert this.mAnswerList.size() > i;
            this.mCorrectIndexList.add(i);
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
        this.mActivity = activity;
        this.mType = QuestionType.Integer;
        this.mTextId = textId;
        this.mImageId = imageId;
        this.mCorrectId = correctId;
        this.mIncorrectId = incorrectId;
        this.mRangeMin = rangeMin;
        this.mRangeMax = rangeMax;

        assert rangeMin <= correctValue;
        assert rangeMax >= correctValue;
        this.mCorrectValue = correctValue;
        this.mViewList.add(this.mActivity.viewNumberPicker);
    }

    /**
     * Hides this question on the screen.
     */
    void hide() {
        for (View view : this.mActivity.currentQuestion.mViewList) {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * Shows this question on the screen.
     */
    void show() {
        for (View view : this.mActivity.currentQuestion.mViewList) {
            view.setVisibility(View.VISIBLE);
        }

        this.mActivity.viewImage.setImageDrawable(ContextCompat.getDrawable(mActivity, this.mImageId));
        this.mActivity.viewQuestionText.setText(this.mActivity.currentQuestion.mTextId);

        switch (this.mType) {
            case Single:
                for (int i = 0; i < this.mViewList.size(); i++) {
                    RadioButton view = (RadioButton) mViewList.get(i);
                    view.setText(this.mAnswerList.get(i));
                }
                this.mActivity.viewRadioGroup.clearCheck();
                break;
            case Multiple:
                for (int i = 0; i < this.mViewList.size(); i++) {
                    CheckBox view = (CheckBox) mViewList.get(i);
                    view.setText(this.mAnswerList.get(i));
                    view.setChecked(false);
                }
                break;
            case Integer:
                //See: https://stackoverflow.com/questions/17805040/how-to-create-a-number-picker-dialog/17806895#17806895
                this.mActivity.viewNumberPicker.setMinValue(this.mRangeMin);
                this.mActivity.viewNumberPicker.setMaxValue(this.mRangeMax);
                //Use: https://stackoverflow.com/questions/12979643/change-the-step-size-of-a-numberpicker/30469826#30469826
        }
    }

    /**
     * Checks the user's answer.
     *
     * @return - Whether or not the user chose correctly
     */
    boolean check() {
        switch (this.mType) {
            case Single:
                for (int i = 0; i < this.mViewList.size(); i++) {
                    RadioButton view = (RadioButton) mViewList.get(i);
                    if (view.isChecked() != this.mCorrectIndexList.contains(i)) {
                        return false;
                    }
                }
                return true;
            case Multiple:
                for (int i = 0; i < this.mViewList.size(); i++) {
                    CheckBox view = (CheckBox) mViewList.get(i);
                    if (view.isChecked() != this.mCorrectIndexList.contains(i)) {
                        return false;
                    }
                }
                return true;
            case Integer:
                return this.mActivity.viewNumberPicker.getValue() == this.mCorrectValue;
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
        return this.mActivity.getResources().getString(this.mCorrectId, this.mActivity.correctList[this.mActivity.random.nextInt(this.mActivity.correctList.length)]);
    }

    /**
     * Returns a formatted message saying that the user chose incorrectly
     *
     * @return The message to show in the dialog box
     */
    String getMessage_incorrect() {
        return this.mActivity.getResources().getString(this.mIncorrectId, this.mActivity.incorrectList[this.mActivity.random.nextInt(this.mActivity.incorrectList.length)]);
    }
}
