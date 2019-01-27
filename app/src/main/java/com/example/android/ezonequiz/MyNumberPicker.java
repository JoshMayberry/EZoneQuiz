package com.example.android.ezonequiz;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

/**
 * The text is too small in the native NumberPicker.
 * This increases the text size so it is more visible.
 *
 * I would rather modify the text size of android.widget.NumberPicker.mInputText before the number picker's constructor runs, but I cannot figure out how to do it.
 * (The text size is inherited by the text size of mInputText when the widget is created).
 *
 * This code is from aheuermann on: https://stackoverflow.com/questions/6958460/android-can-i-increase-the-textsize-for-the-numberpicker-widget/12084420#12084420
 */
public class MyNumberPicker extends android.widget.NumberPicker {

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    /**
     * This method modifies the text size and color when the view is updated (like every time the screen is rotated).
     * As aheuermann put it, "Not ideal, but it works".
     */
    private void updateView(View view) {
        if (view instanceof EditText){
            // Log.v("MyNumberPicker", "Updated");
            ((EditText) view).setTextSize(getResources().getDimension(R.dimen.text_answerSize));
            ((EditText) view).setTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryText));
        }
    }

}