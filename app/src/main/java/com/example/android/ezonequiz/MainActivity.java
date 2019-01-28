package com.example.android.ezonequiz;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioButton[] view_radioButton = new RadioButton[5];
    private NumberPicker view_numberPicker;
    private Spinner view_spinner;
    private XmlResourceParser xml_parser;
    private int xml_currentTag;

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
        xml_parser = getResources().getXml(R.xml.quiz);

        //Defaults
        xml_currentTag = -1;

        showNext();
    }

    /**
     * A container to store data about the question in.
     */
    private class Question {
        String text; //What the question is asking
        List<Answer> answers = new ArrayList<>(); //The answers associated with this question

        Question(){
        }
    }
    /**
     * A container to store data about an answer in.
     */
    private class Answer {
        View view; //The view tied to this answer
        String text; //What the answer says

        Answer() {
        }
    }

    /**
     * The user wants to submit their answer to this question and see the next question.
     * @param view - The button view that fired this method
     */
    public void onNext(View view) {
        reset();
    }

    /**
     * Increments the xml iterator.
     */
    private int nextXmlTag(){
        try {
            xml_currentTag = xml_parser.next();
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            xml_currentTag = -1;
        }
        return xml_currentTag;
    }
    /**
     * Shows the next question to the user.
     * The xml parser is used as an iterator.
     * Use: https://itekblog.com/load-your-custom-xml-from-resources-android/
     * See: https://developer.android.com/training/basics/network-ops/xml
     * See: http://qtcstation.com/2011/01/parsing-xml-from-the-sdcard-using-xmlpullparser/
     */
    private void showNext() {
        switch (nextXmlTag()) {
            case XmlPullParser.START_TAG:
                switch (xml_parser.getName()) {
                    case "question":
                        Question question = showNext_formatQuestion();
                        return;
                    default:
                        showNext();
                        return;
                }
            case XmlPullParser.END_DOCUMENT:
                return;
            case XmlPullParser.START_DOCUMENT:
                showNext();
                return;
            default:
                Log.v("XML_Parser", "Unknown error encountered in showNext(); name = " + xml_parser.getName() + "; text = " + xml_parser.getText());
                showNext();
        }
    }
    /**
     * Uses the xml iterator to compose the question.
     * @return The question that should be asked
     */
    private Question showNext_formatQuestion() {
        Question question = new Question();

        //Step through each internal tag until the question tag is closed
        while (true) {
            nextXmlTag();
            switch (xml_parser.getName()) {
                case "text":
                    question.text = xml_parser.getText();
                    continue;
                case "options":
                    showNext_formatAnswerList(question);
                    continue;
                case "question":
                    return question;
                default:
                    Log.v("XML_Parser", "Unknown error encountered in showNext_formatQuestion(); name = " + xml_parser.getName() + "; text = " + xml_parser.getText());
            }
        }
    }
    /**
     * Uses the xml iterator to compose the answers.
     * Stores the answers in the provided question.
     * @param question - What question these answers belong to
     */
    private void showNext_formatAnswerList(Question question) {
        //Step through each internal tag until the options tag is closed
        while (true) {
            nextXmlTag();
            switch (xml_parser.getName()) {
                case "answer":
                    question.answers.add(showNext_formatAnswer(question));
                case "options":
                    return;
                default:
                    Log.v("XML_Parser", "Unknown error encountered in showNext_formatAnswers(); name = " + xml_parser.getName() + "; text = " + xml_parser.getText());
            }
        }
    }
    /**
     * Uses the xml iterator to compose an answer.
     * @param question - What question this answer belongs to
     * @return The answer that should be used
     */
    private Answer showNext_formatAnswer(Question question) {
        Answer answer = new Answer();

        //Step through each internal tag until the answer tag is closed
        while (true) {
            nextXmlTag();
            switch (xml_parser.getName()) {
                case "text":
                    answer.text = xml_parser.getText();
                    continue;
                case "type":
                    switch (xml_parser.getText()) {
                        case "int":
                            answer.view = view_numberPicker;
                            continue;
                        case "bool":
                            assert question.answers.size() < view_radioButton.length;
                            answer.view = view_radioButton[question.answers.size()];
                        default:
                            Log.v("XML_Parser", "Unknown answer type " + xml_parser.getText() + "; text = " + xml_parser.getText());
                            continue;
                    }
                case "minimum":
//                    answer.view.setMinValue(xml_parser.getText());
                    continue;
                case "maximum":
//                    answer.view.setMaxValue(xml_parser.getText());
                    continue;
                case "answer":
                    return answer;
                default:
                    Log.v("XML_Parser", "Unknown error encountered in showNext_formatAnswers(); name = " + xml_parser.getName() + "; text = " + xml_parser.getText());
            }
        }
    }

    /**
     * Hides all answer widgets.
     */
    private void reset() {
        //See: https://stackoverflow.com/questions/17805040/how-to-create-a-number-picker-dialog/17806895#17806895
        view_numberPicker.setMaxValue(10);
        view_numberPicker.setMinValue(0);
    }
}

