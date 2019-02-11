package com.example.android.ezonequiz;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * This class is meant to be used as a listener for an adapter view that will go to a page in the book.
 * @see MainActivity
 * @see BookActivityToC
 * <p>
 * It will configure 'BookListenerText' based on what data is associated with the selected adapter view item.
 * @see BookListenerText
 * <p>
 * See: http://ezzylearning.com/tutorial/handling-android-listview-onitemclick-event
 */
public class BookListenerMultipleText extends SectionBase<BookListenerMultipleText> implements AdapterView.OnItemClickListener {

    private ArrayList<SectionBase> sections;

    BookListenerMultipleText(ArrayList<SectionBase> sections) {
        this.sections = sections;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookListenerText singleListener = new BookListenerText(sections.get(position));
        singleListener.onClick(view);
    }
}
