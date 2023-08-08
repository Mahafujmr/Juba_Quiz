package com.mahafujur.TUHINQuiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionCollection extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    public static List<QuestionModule> question_list;
    int score;
    public static String SUBJECT_NAME = "";
    public static ArrayList <ArrayList<QuestionModule>> questionBank = new ArrayList<>();
    public static ArrayList <HashMap<String, String>> subjectList = new ArrayList<>();
    LinearLayout rootLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rootLay = findViewById(R.id.rootLay);
        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        loadQuestion();

    }




    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rootLay!=null) rootLay.startAnimation(AnimationUtils.loadAnimation(QuestionCollection.this, R.anim.middle_to_top));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    private void loadQuestion(){
        //Toast.makeText(getApplicationContext(), "Total Questions: "+question_list.size(), Toast.LENGTH_SHORT).show();
        if(question_list.size() > 0) {
            QuestionModule q = question_list.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));
            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }



    //===============================================================================================







    //====================================================================
    //====================================================================
    public static  ArrayList <QuestionModule> questions;
    public static void createQuestionBank(){
        QuestionCollection.subjectList = new ArrayList<>();
        QuestionCollection.questionBank = new ArrayList<>();



        //------------- Subject 1
        questions = new ArrayList(){
            {
                add(new QuestionModule("বিশ্বের প্রথম ধুমপান মুক্ত দেশ কোনটি ?", "A", " ভুটান ", "নেপাল", "নরওয়ে", "বেলজিয়াম"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("সাধারন জ্ঞান", R.drawable.category_icon1, questions);



        //------------- Subject 2
        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশ ও ভারতের মধ্যে চলাচলকারী ট্রেনের নাম কী?", "D", "মৈত্রী এক্সপ্রেস", "বন্ধন এক্সপ্রেস", "মিতালী এক্সপ্রেস", "ওপরের সবগুলাে"));
                add(new QuestionModule("বঙ্গবন্ধু শেখ মুজিব ভ্রাম্যমাণ রেল জাদুঘর কবে উদ্বোধন করা হয়?", "A", "২৭ এপ্রিল ২০২২", "৩০ এপ্রিল ২০২২","২ মে ২০২২", "৫ মে ২০২২"));
                add(new QuestionModule("বাংলাদেশ সিকিউরিটিজ অ্যান্ড এক্সচেঞ্জ কমিশনের (BSEC) প্রথম নারী কমিশনার কে?", "C", "কবিতা খানম", "রাশিদা সুলতানা","রুমানা ইসলাম", "রাজিয়া বেগম"));
                add(new QuestionModule("২৫ মে ২০২২ পর্যন্ত দেশের GI পণ্য কতটি?", "A", "১০ টি", "১২ টি","১৪ টি", "১৭ টি"));
                add(new QuestionModule("সপদ্মা বহুমুখী সেতুর দৈর্ঘ্য কত ?", "B", "৬ কিমি", "৬.১৫ কিমি", "৭.১৫ কিমি" , "৮.১৫ কিমি"));
                add(new QuestionModule("বর্তমানে দেশে সরকারি বিশ্ববিদ্যালয় কতটি?", "D", "৪৮ টি", "৪৯ টি", "৫০ টি", "৫১ টি"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী মাথাপিছু GDP কত?", "D", "২,৪৯৭ মার্কিন ডলার", "২,৫৯৭ মার্কিন ডলার", "২,৬৯৭ মার্কিন ডলার", "২,৭২৩ মার্কিন ডলার"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী GDP’র প্রবৃদ্ধির হার কত?", "B", "৫.৪৩%", "৭.২৫%", "৬.৯৪%", "৭.৪৭%"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী কৃষি খাতের অবদানের হার কত?", "A", "১১.৫০%", "১২.১৫%", "১৩. ৪৭%", "১৪.৭৯%"));
                add(new QuestionModule("দেশের ৫১তম সরকারি বিশ্ববিদ্যালয় কোনটি?", "A", "কুড়িগ্রাম কৃষি বিশ্ববিদ্যালয়", "ঠাকুরগাঁও বিশ্ববিদ্যালয়", "বঙ্গবন্ধু শেখ মুজিবুর রহমান প্রযুক্তি বিশ্ববিদ্যালয়", " সুনামগঞ্জ বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়"));
            }
        };
        QuestionModule.createQuestionsForSubject("বাংলাদেশ", R.drawable.category_icon2, questions);







        //------------- Subject 3
        questions = new ArrayList(){
            {
                add(new QuestionModule("এই অ্যাপটি কার স্মরণে তৈরি?", "A", "অফসারুল ইসলাম আদর", "তৌফিক আলম", "মাসুমুল হক", "জুবায়ের হোসেন"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("পদার্থ বিজ্ঞান", R.drawable.category_icon3, questions);



        //------------- Subject 4
        questions = new ArrayList(){
            {
                add(new QuestionModule("বাংলাদেশ ও ভারতের মধ্যে চলাচলকারী ট্রেনের নাম কী?", "D", "মৈত্রী এক্সপ্রেস", "বন্ধন এক্সপ্রেস", "মিতালী এক্সপ্রেস", "ওপরের সবগুলাে"));
                add(new QuestionModule("বঙ্গবন্ধু শেখ মুজিব ভ্রাম্যমাণ রেল জাদুঘর কবে উদ্বোধন করা হয়?", "A", "২৭ এপ্রিল ২০২২", "৩০ এপ্রিল ২০২২","২ মে ২০২২", "৫ মে ২০২২"));
                add(new QuestionModule("বাংলাদেশ সিকিউরিটিজ অ্যান্ড এক্সচেঞ্জ কমিশনের (BSEC) প্রথম নারী কমিশনার কে?", "C", "কবিতা খানম", "রাশিদা সুলতানা","রুমানা ইসলাম", "রাজিয়া বেগম"));
                add(new QuestionModule("২৫ মে ২০২২ পর্যন্ত দেশের GI পণ্য কতটি?", "A", "১০ টি", "১২ টি","১৪ টি", "১৭ টি"));
                add(new QuestionModule("সপদ্মা বহুমুখী সেতুর দৈর্ঘ্য কত ?", "B", "৬ কিমি", "৬.১৫ কিমি", "৭.১৫ কিমি" , "৮.১৫ কিমি"));
                add(new QuestionModule("বর্তমানে দেশে সরকারি বিশ্ববিদ্যালয় কতটি?", "D", "৪৮ টি", "৪৯ টি", "৫০ টি", "৫১ টি"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী মাথাপিছু GDP কত?", "D", "২,৪৯৭ মার্কিন ডলার", "২,৫৯৭ মার্কিন ডলার", "২,৬৯৭ মার্কিন ডলার", "২,৭২৩ মার্কিন ডলার"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী GDP’র প্রবৃদ্ধির হার কত?", "B", "৫.৪৩%", "৭.২৫%", "৬.৯৪%", "৭.৪৭%"));
                add(new QuestionModule("GDP’র সাময়িক হিসাব ২০২১-২২ অনুযায়ী কৃষি খাতের অবদানের হার কত?", "A", "১১.৫০%", "১২.১৫%", "১৩. ৪৭%", "১৪.৭৯%"));
                add(new QuestionModule("দেশের ৫১তম সরকারি বিশ্ববিদ্যালয় কোনটি?", "A", "কুড়িগ্রাম কৃষি বিশ্ববিদ্যালয়", "ঠাকুরগাঁও বিশ্ববিদ্যালয়", "বঙ্গবন্ধু শেখ মুজিবুর রহমান প্রযুক্তি বিশ্ববিদ্যালয়", " সুনামগঞ্জ বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়"));
            }
        };
        QuestionModule.createQuestionsForSubject("রসায়ন", R.drawable.category_icon4, questions);






        //------------- Subject 5
        questions = new ArrayList(){
            {
                add(new QuestionModule("এই অ্যাপটি কার স্মরণে তৈরি?", "A", "অফসারুল ইসলাম আদর", "তৌফিক আলম", "মাসুমুল হক", "জুবায়ের হোসেন"));
                add(new QuestionModule("বাংলাদেশের রাজধানীর নাম কি?", "A", "ঢাকা", "খুলনা","বরিশাল", "ফেনী"));
                add(new QuestionModule("বাংলাদেশের মুদ্রার নাম কি?", "A", "টাকা", "ডলার","রুপি", "বাথ"));
                add(new QuestionModule("বিজয় দিবস কত তারিখে পালন করা হয়?", "D", "২৬ মার্চ", "২১ ফেব্রুয়ারি","১৪ ডিসেম্বর", "১৬ ডিসেম্বর"));
                add(new QuestionModule("স্মৃতিসৌধের কয়টি স্তম্ভ?", "B", "৫ টি", "৭ টি", "৯ টি" , "১০ টি"));
                add(new QuestionModule("ঢাকার পূর্ব নাম কি ছিল?", "D", "ইসলামাবাদ", "আগরতলা", "বিজয়নগর", "জাহাঙ্গিরনগর"));
                add(new QuestionModule("রোকেয়া দিবস কত তারিখে পালন করা হয়?", "C", "১৭ ফেব্রুয়ারি", "১৯ ফেব্রুয়ারি", "৯ ডিসেম্বর", "২২ ফেব্রুয়ারি"));
                add(new QuestionModule("দিল্লির হিংহাসনে অধিষ্ঠিত প্রথম মুসলিম নারী কে?", "B", "রোকেয়া ইসলাম", "সুলতানা রাজিয়া", "সালেহা বেগম", "পারভিন সুলতানা"));
                add(new QuestionModule("উপমহাদেশের প্রথম অস্কার জয়ী কে?", "B", "রবীন্দ্রনাথ ঠাকুর", "সত্যজিৎ রায়", "জুবায়ের হোসেন", "নুহাশ হুমায়ুন"));
                add(new QuestionModule("বাংলাদেশের গভীরতম নদী কোনটি?", "D", "পদ্মা", "যমুনা", "ব্রহ্মপুত্র", "মেধনা"));
            }
        };
        QuestionModule.createQuestionsForSubject("জীব বিজ্ঞান", R.drawable.category_icon5, questions);



        //------------- Subject 6
        questions = new ArrayList(){
            {
                add(new QuestionModule("কোনো ওয়ার্কশিট তৈরি করে Open করলে ওয়ার্কশিট ফাইলটির নাম কোথায় দেখা যায়?","A", "টাইটেল বারে", "শিট ট্যাবে", "ফর্মুলা বারে", "অফিস বাটনে"));
                add(new QuestionModule("বর্তমানে সবচেয়ে জনপ্রিয় স্প্রেডশিট সফটওয়্যার কোনটি?", "D", "অ্যাকসেস", "ডিমওয়েভার","নেটবিনস", "এক্সেল"));
                add(new QuestionModule("মিনিমাইজ বাটন কোথায় থাকে?", "B", "টুল বারে", "টাইটেল বারে","ফর্মুলা বারে", "রিবনে"));
                add(new QuestionModule("ওয়ার্কবুক উইন্ডোর অবস্থা প্রদর্শিত হয় কোন বারে?", "B", "ফর্মুলা বারে", "স্ট্যাটাস বারে","শিট ট্যাবে", "ফর্মেটিং টুল বারে"));
                add(new QuestionModule("স্প্রেডশিটের আভিধানিক অর্থ কী? ", "C", "সফটওয়্যার প্রোগ্রাম", "প্রোগ্রাম কোড", "ছড়ানো বড় মাপের কাগজ" , "চিঠি"));
                add(new QuestionModule("স্বয়ংক্রিয়ভাবে যোগ করা যায় কোন প্রোগ্রামের সাহায্যে?", "A", "স্প্রেডশিট প্রোগ্রাম", "মাইক্রোসফট ওয়ার্ডে", "ওয়ার্ড প্রসেসর", "এক্সেস প্রোগ্রামের সাহায্যে"));
                add(new QuestionModule("যে বাটনগুলো বেশি ব্যবহৃত হয় সেগুলো কোথায় থাকে?", "B", "স্ট্যাটাস বার", "কুইক অ্যাকসেস টুলবার", "টাইটেল বার", "শিট ট্যাব"));
                add(new QuestionModule("কোনটি চিত্রভিত্তিক স্প্রেডশিট প্রোগ্রাম? ", "A", "মাইক্রোসফট", "ভিসিক্যালক", "ওপেন অপিস ক্যালক", "অ্যাকসেস"));
                add(new QuestionModule("হিসাব রাখার চেষ্টা থেকে মানুষের প্রথম আবিষ্কার কী", "A", "অ্যাবাকাস", "পাস্কালেন", "স্লাইডিরুল", "ক্যালকুলেটর"));
                add(new QuestionModule("অ্যাবাকাস কী? ", "B", "এক ধরনের লেখার উপায়", "এক ধরনের গণনার যন্ত্র", "এক ধরনের কথা বলার যন্ত্র", " এক ধরনের গান শোনার যন্ত্র"));
            }
        };
        QuestionModule.createQuestionsForSubject("কম্পিউটার", R.drawable.category_icon6, questions);





        //------------- Subject 6
        questions = new ArrayList(){
            {
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট কত সালে প্রতিষ্ঠিত হয়?","A", "২০০১", "২০০০", "২০০২", "১৯৯৯"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট কোন বিভাগে অবস্থিত?", "A", "ঢাকা", "খুলনা", " সিলেট", "রংপুর"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এর সংক্ষিপ্ত নাম কী ?", "A", "SPI", "SPPI", "DPI", "KPI"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এর জমির পরিমাণ কত?", "A", "২.০২ একর", "২.০০  একর","২.২২ একর", "২.৩৩ একর"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট কতটি টেকনোলজি নিয়ে গঠিত ? ", "A", "৪", "৫", "২" , "৩"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এ ল্যাব এর সংখ্যা কতটি", "A", "১৪", "১০", "১৫", "১৬"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট কে উদ্বোধন করেন?", "A", "মরহুম আলহাজ্ব আব্দুর রাজ্জাক ", "এ জেড মোস্তাক হোসেন", "আবিদুর রেজা খান", "আবদুল মোতালেব সরদার"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এর গ্রন্থাগারে বইয়ের সংখ্যা কত? ", "A", "প্রায় ছয় হাজার", "প্রায় দুই হাজার", "প্রায় তিন হাজার", "প্রায়ে এক  হাজার"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এর প্রশানিক ভবন কয়টি ?", "A", "১", "২", "৩", "৪"));
                add(new QuestionModule("শরিয়তপুর পলিটেনিক ইনস্টিটিউট এর ওয়ার্কশপ ভবন কয়টি ? ", "A", "২", "১", "৩", " ৪"));
            }
        };
        QuestionModule.createQuestionsForSubject("শরিয়তপুর পলিটেনিক ", R.drawable.category_icon2, questions);











    }




//====================================================================
// ====================================================================
//====================================================================
// ====================================================================

}
