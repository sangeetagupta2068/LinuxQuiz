package com.sangeetagupta1998.linuxquiz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaration of View objects.
    RadioGroup radioGroup;
    RadioButton answerButton,button1,button2,button3;
    TextView textView;

    //declaration of variables.
    String result;
    int count,score;

    //declaration of String arrays.
    String[] questionaire,option1,option2,option3,answerKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariableValues();
    }

    private void setVariableValues(){

        /*
         *@description:This is used to initialize the global variables and objects.
         */

        //initialization of View objects
        button1 = findViewById(R.id.radio1);
        button2 = findViewById(R.id.radio2);
        button3 = findViewById(R.id.radio3);

        radioGroup = findViewById(R.id.radio);

        textView = findViewById(R.id.textView);

        //initialization of variables
        count = 0;
        score = 0;
        result = "";

        //initialization of String arrays
        questionaire = new String[]{getResources().getString(R.string.question2),getResources().getString(R.string.question3),getResources().getString(R.string.question4)};
        option1 = new String[] {getResources().getString(R.string.option21),getResources().getString(R.string.option31),getResources().getString(R.string.option41)};
        option2 = new String[] {getResources().getString(R.string.option22),getResources().getString(R.string.option32),getResources().getString(R.string.option42)};
        option3 = new String[] {getResources().getString(R.string.option23),getResources().getString(R.string.option33),getResources().getString(R.string.option43)};
        answerKey = new String[] {getResources().getString(R.string.option13),getResources().getString(R.string.option22),getResources().getString(R.string.option31),getResources().getString(R.string.option43)};

    }

    public void checkResult(View view){

        /*
         *@description:This is used to check if the answer provided by the user is correct and accordingly displays
         *  a message to the user regarding the same.
         *@param: View
         */

        //declaration and initialization of a local variable to store the id of the selected radio button
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        answerButton = findViewById(selectedRadioButtonId);

        if(answerButton.getText().equals(answerKey[count])){

            score = score + 5 ;
            Toast.makeText(getApplicationContext(),"Correct answer!" + score,Toast.LENGTH_SHORT).show();

        }

        else {

            Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();

        }

        if(count < 3) {

            answerButton.setChecked(false);
            setView();

            count = count + 1;

        }

        else {

            if (score > 10) {

                result = "Amazing!";

            }

            else {

                if (score > 5 && score < 10) {

                    result = "Good Try!";

                }

                else {

                    result = "No worries!\nYou learnt something new!";

                }

            }

            result = result + "\nYour score is: " + score + "\nThank you!";
            shareResult();

        }

    }

    private void setView(){

        /*
         *@description: This is used for displaying the next question.
         */

        textView.setText(questionaire[count]);

        button1.setText(option1[count]);
        button2.setText(option2[count]);
        button3.setText(option3[count]);

    }

    private void shareResult(){

        /*
         *@description: This is used for sharing the result score to email.
         */

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Linux Quiz Score");
        intent.putExtra(Intent.EXTRA_TEXT, result);

        if (intent.resolveActivity(getPackageManager()) != null) {

            startActivity(intent);

        }

    }

}
