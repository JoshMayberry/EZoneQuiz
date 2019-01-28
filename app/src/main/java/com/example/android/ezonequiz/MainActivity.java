package com.example.android.ezonequiz;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private RadioButton[] view_radioButton = new RadioButton[5];
    private NumberPicker view_numberPicker;
    private Spinner view_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Resources
        view_radioButton[0] = findViewById(R.id.radioButton_0);
        view_radioButton[1] = findViewById(R.id.radioButton_1);
        view_radioButton[2] = findViewById(R.id.radioButton_2);
        view_radioButton[3] = findViewById(R.id.radioButton_3);
        view_radioButton[4] = findViewById(R.id.radioButton_4);
        view_numberPicker = findViewById(R.id.numberPicker);
        view_spinner = findViewById(R.id.spinner);

        try {
            parseXML();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showNext();
    }

    /**
     * The user wants to submit their answer to this question and see the next question.
     * @param view - The button view that fired this method
     */
    public void onNext(View view) {
        reset();
    }

    /**
     * Shows the next question to the user
     */
    private void showNext() {
    }

    /**
     * Hides all answer widgets.
     */
    private void reset() {
        //See: https://stackoverflow.com/questions/17805040/how-to-create-a-number-picker-dialog/17806895#17806895
        view_numberPicker.setMaxValue(10);
        view_numberPicker.setMinValue(0);
    }

    /**
     * Returns a list of questions by parseing an xml file.
     *  Use: https://itekblog.com/load-your-custom-xml-from-resources-android/
     *  See: https://developer.android.com/training/basics/network-ops/xml
     *  See: http://qtcstation.com/2011/01/parsing-xml-from-the-sdcard-using-xmlpullparser/
     */
    public void parseXML() throws IOException, XmlPullParserException {
        XmlResourceParser xpp = getResources().getXml(R.xml.quiz);
        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("Start document");
                        break;
                    case XmlPullParser.START_TAG:
                        System.out.println("Start tag " + xpp.getName());
                        break;
                    case XmlPullParser.END_TAG:
                        System.out.println("End tag " + xpp.getName());
                        break;
                    case XmlPullParser.TEXT:
                        System.out.println("Text " + xpp.getText());
                        break;
                }
                eventType = xpp.next();
            }
        } finally {
            xpp.close();
        }
    }

}

