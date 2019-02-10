package com.example.android.ezonequiz;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * This class is meant to be used as a listener for a button that will go to a page in the book.
 * @see MainActivity
 * @see BookActivity_ToC
 * <p>
 * What shows on the screen depends on what extra data the intent was given.
 * @see BookActivity_Text
 */
public class BookListener_Text extends SectionBase<BookListener_Text> implements View.OnClickListener {

    BookListener_Text() {

    }

    /**
     * This method is used to convert the item data from a selected table of contents item.
     *
     * @param selectedItem - The item that the user selected
     * @see BookListener_Multiple_Text
     * @see SectionBase
     */
    BookListener_Text(SectionBase selectedItem) {
        this.extraTheme = selectedItem.extraTheme;
        this.extraTitle = selectedItem.extraTitle;
        this.extraImage = selectedItem.extraImage;
        this.extraTextBody = selectedItem.extraTextBody;
        this.extraTextQuote = selectedItem.extraTextQuote;
        this.extraTextSubtitle = selectedItem.extraTextSubtitle;
        this.extraImageScaleType = selectedItem.extraImageScaleType;
        this.extraTextQuoteSource = selectedItem.extraTextQuoteSource;
    }

    @Override
    public void onClick(View view) {
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivity_Text.class);

        intent.putExtra("theme", this.extraTheme);
        intent.putExtra("title", this.extraTitle);
        intent.putExtra("image", this.extraImage);
        intent.putExtra("text_body", this.extraTextBody);
        intent.putExtra("text_quote", this.extraTextQuote);
        intent.putExtra("text_subtitle", this.extraTextSubtitle);
        intent.putExtra("image_scaleType", this.extraImageScaleType);
        intent.putExtra("text_quote_source", this.extraTextQuoteSource);

        context.startActivity(intent);
    }
}
