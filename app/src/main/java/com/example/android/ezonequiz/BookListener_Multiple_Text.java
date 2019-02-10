package com.example.android.ezonequiz;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

/**
 * This class is meant to be used as a listener for an adapter view that will go to a page in the book.
 * @see MainActivity
 * @see BookActivity_ToC
 * <p>
 * It will configure 'BookListener_Text' based on what data is associated with the selected adapter view item.
 * @see BookListener_Text
 * <p>
 * See: http://ezzylearning.com/tutorial/handling-android-listview-onitemclick-event
 */
public class BookListener_Multiple_Text extends SectionBase<BookListener_Multiple_Text> implements AdapterView.OnItemClickListener {

    private ArrayList<SectionBase> sections;

    BookListener_Multiple_Text(ArrayList<SectionBase> sections) {
        this.sections = sections;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookListener_Text singleListener = new BookListener_Text(sections.get(position));
        singleListener.onClick(view);
    }
}
