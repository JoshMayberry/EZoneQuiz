package com.example.android.ezonequiz;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class controls showing page contents to the user.
 */
public class BookActivityText extends AppCompatActivity {

    public TextView viewBody;
    public TextView viewSubtitle;
    public TextView viewQuoteText;
    public TextView viewQuoteSource;
    public ImageView viewImage;

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
        viewBody = findViewById(R.id.book_body);
        viewSubtitle = findViewById(R.id.book_subtitle);
        viewQuoteText = findViewById(R.id.book_quoteText);
        viewQuoteSource = findViewById(R.id.book_quoteSource);
        viewImage = findViewById(R.id.book_image);

        applyExtrasFromIntent(intent);
        applyExtrasFromTheme();
    }

    /**
     * Set up the activity based on extras passed to the intent.
     * If the given extra was not passed in, a default value will be used instead.
     * <p>
     * See: https://stackoverflow.com/questions/4233873/how-do-i-get-extra-data-from-intent-on-android/4233898#4233898
     */
    private void applyExtrasFromIntent(Intent intent) {
        setTitle(intent.getIntExtra("title", R.string.app_name));
        viewImage.setImageResource(intent.getIntExtra("image", R.drawable.empty));

        switch (intent.getIntExtra("image_scaleType", 0)) {
            case 0:
                viewImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                break;
            case 1:
                viewImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                break;
        }

        viewBody.setText(intent.getIntExtra("text_body", R.string.future_content));
        viewSubtitle.setText(intent.getIntExtra("text_subtitle", R.string.future_content));
        viewQuoteText.setText(intent.getIntExtra("text_quote", R.string.empty));
        viewQuoteSource.setText(intent.getIntExtra("text_quote_source", R.string.empty));
    }

    /**
     * Setup the activity based on the theme.
     * <p>
     * Use: https://stackoverflow.com/questions/17277618/get-color-value-programmatically-when-its-a-reference-theme/17277714#17277714
     * See: https://developer.android.com/reference/android/content/res/Resources.Theme#resolveAttribute(int,%20android.util.TypedValue,%20boolean)
     */
    private void applyExtrasFromTheme() {

        Resources.Theme myTheme = this.getTheme();
        TypedValue typedValue = new TypedValue();

        if (!(myTheme.resolveAttribute(R.attr.colorAccent, typedValue, true))) {
            typedValue.data = R.color.colorPrimaryLight;
        }
        viewBody.setBackgroundColor(typedValue.data);
        viewImage.setBackgroundColor(typedValue.data);

        if (!(myTheme.resolveAttribute(R.attr.colorPrimary, typedValue, true))) {
            typedValue.data = R.color.colorPrimary;
        }
        viewQuoteText.setBackgroundColor(typedValue.data);
        viewQuoteSource.setBackgroundColor(typedValue.data);
    }
}
