package com.example.android.ezonequiz;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * This class controls showing page contents to the user.
 */
public class BookActivity_Text extends AppCompatActivity {

    public TextView view_body;
    public TextView view_subtitle;
    public TextView view_quoteText;
    public TextView view_quoteSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*Theme must be changed before setContentView to be fully applied
        others say before super.onCreate, but it seems to work for me if that is before this.
        I will do it before that as well to be safe though.
         */
        Intent intent = getIntent();
        setTheme(intent.getIntExtra("theme", R.style.Theme_AppCompat_EZoneQuiz));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_text);
        view_body = findViewById(R.id.book_body);
        view_subtitle = findViewById(R.id.book_subtitle);
        view_quoteText = findViewById(R.id.book_quoteText);
        view_quoteSource = findViewById(R.id.book_quoteSource);

        applyExtras_intent(intent);
        applyExtras_theme();

    }

    /**
     * Set up the activity based on extras passed to the intent.
     * If the given extra was not passed in, a default value will be used instead.
     * <p>
     * See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
     */
    private void applyExtras_intent(Intent intent) {
        setTitle(intent.getIntExtra("title", R.string.app_name));

        view_body.setText(intent.getIntExtra("text_body", R.string.empty));
        view_subtitle.setText(intent.getIntExtra("text_subtitle", R.string.empty));
        view_quoteText.setText(intent.getIntExtra("text_quote", R.string.empty));
        view_quoteSource.setText(intent.getIntExtra("text_quote_source", R.string.empty));
    }

    /**
     * Setup the activity based on the theme.
     * <p>
     * Use: https://stackoverflow.com/questions/17277618/get-color-value-programmatically-when-its-a-reference-theme/17277714#17277714
     * See: https://developer.android.com/reference/android/content/res/Resources.Theme#resolveAttribute(int,%20android.util.TypedValue,%20boolean)
     */
    private void applyExtras_theme() {

        Resources.Theme myTheme = this.getTheme();
        TypedValue typedValue = new TypedValue();

        if (!(myTheme.resolveAttribute(R.attr.colorAccent, typedValue, true))) {
            typedValue.data = R.color.colorPrimaryLight;
        }
        view_body.setBackgroundColor(typedValue.data);

        if (!(myTheme.resolveAttribute(R.attr.colorPrimary, typedValue, true))) {
            typedValue.data = R.color.colorPrimary;
        }
        view_quoteText.setBackgroundColor(typedValue.data);
        view_quoteSource.setBackgroundColor(typedValue.data);
    }
}
