package com.example.android.ezonequiz;
//
//import android.util.Xml;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
////See: https://developer.android.com/training/basics/network-ops/xml
//public class QuestionParser {
//    private static final String ns = null; //We don't use namespaces
//
//    /**
//     * A container for the text and answers for a question.
//     */
//    public static class Question {
//        final String text;
//        final Answer[] answer;
//
//        private Question(String text, Answer[] answer) {
//            this.text = text;
//            this.answer = answer;
//        }
//    }
//
//    /**
//     * A container for an answer.
//     */
//    public static class Answer {
//        final String text;
//
//        private Answer(String text, String indexId) {
//            this.text = text;
//            this.indexId = indexId;
//
//        }
//
//    }
//
//    //Instantiate the parser
//    public List<Question> parse(InputStream in) throws XmlPullParserException, IOException {
//        try {
//            XmlPullParser parser = Xml.newPullParser();
//            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//            parser.setInput(in, null);
//            parser.nextTag();
//            return readQuestions(parser);
//        } finally {
//            in.close();
//        }
//    }
//
//    /**
//     * Skips the current tag in the xml file.
//     */
//    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
//        if (parser.getEventType() != XmlPullParser.START_TAG) {
//            throw new IllegalStateException();
//        }
//        int depth = 1;
//        while (depth != 0) {
//            switch (parser.next()) {
//                case XmlPullParser.END_TAG:
//                    depth--;
//                    break;
//                case XmlPullParser.START_TAG:
//                    depth++;
//                    break;
//            }
//        }
//    }
//
//    /**
//     * Reads quiz.xml. Each question should be accompanied by the different answers.
//     */
//    private List<Question> readQuestions(XmlPullParser parser) throws XmlPullParserException, IOException {
//        List<Question> questions = new ArrayList<>();
//
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            // Starts by looking for the question tag
//            if (name.equals("question")) {
//                questions.add(readQuestion(parser));
//            } else {
//                skip(parser);
//            }
//        }
//        return questions;
//    }
//
//    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
//// to their respective "read" methods for processing. Otherwise, skips the tag.
//    private Question readQuestion(XmlPullParser parser) throws XmlPullParserException, IOException {
//        parser.require(XmlPullParser.START_TAG, ns, "question");
//        String title = null;
//        String summary = null;
//        String link = null;
//        while (parser.next() != XmlPullParser.END_TAG) {
//            if (parser.getEventType() != XmlPullParser.START_TAG) {
//                continue;
//            }
//            String name = parser.getName();
//            switch (name) {
//                case "title":
//                    title = readTitle(parser);
//                    break;
//                case "summary":
//                    summary = readSummary(parser);
//                    break;
//                case "link":
//                    link = readLink(parser);
//                    break;
//                default:
//                    skip(parser);
//                    break;
//            }
//        }
//        return new Question(title, summary, link);
//    }
//
//    // Processes title tags in the feed.
//    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "title");
//        String title = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "title");
//        return title;
//    }
//
//    // Processes link tags in the feed.
//    private String readLink(XmlPullParser parser) throws IOException, XmlPullParserException {
//        String link = "";
//        parser.require(XmlPullParser.START_TAG, ns, "link");
//        String tag = parser.getName();
//        String relType = parser.getAttributeValue(null, "rel");
//        if (tag.equals("link")) {
//            if (relType.equals("alternate")){
//                link = parser.getAttributeValue(null, "href");
//                parser.nextTag();
//            }
//        }
//        parser.require(XmlPullParser.END_TAG, ns, "link");
//        return link;
//    }
//
//    // Processes summary tags in the feed.
//    private String readSummary(XmlPullParser parser) throws IOException, XmlPullParserException {
//        parser.require(XmlPullParser.START_TAG, ns, "summary");
//        String summary = readText(parser);
//        parser.require(XmlPullParser.END_TAG, ns, "summary");
//        return summary;
//    }
//
//    // For the tags title and summary, extracts their text values.
//    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
//        String result = "";
//        if (parser.next() == XmlPullParser.TEXT) {
//            result = parser.getText();
//            parser.nextTag();
//        }
//        return result;
//    }
//}
