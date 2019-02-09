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
        this.extra_theme = selectedItem.extra_theme;
        this.extra_title = selectedItem.extra_title;
        this.extra_text_body = selectedItem.extra_text_body;
        this.extra_text_quote = selectedItem.extra_text_quote;
        this.extra_text_subtitle = selectedItem.extra_text_subtitle;
        this.extra_text_quote_source = selectedItem.extra_text_quote_source;
    }

    @Override
    public void onClick(View view) {
        //http://www.vogella.com/tutorials/AndroidIntent/article.html#starting-activities-or-services
        Context context = view.getContext();
        Intent intent = new Intent(context, BookActivity_Text.class);

        intent.putExtra("theme", this.extra_theme);
        intent.putExtra("title", this.extra_title);
        intent.putExtra("text_body", this.extra_text_body);
        intent.putExtra("text_quote", this.extra_text_quote);
        intent.putExtra("text_subtitle", this.extra_text_subtitle);
        intent.putExtra("text_quote_source", this.extra_text_quote_source);

        context.startActivity(intent);
    }
}
