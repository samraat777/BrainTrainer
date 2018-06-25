package com.example.sam.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    TextView resultText;
    int locationOfCorrectAnswer;
    int score=0;


    public void chooseAnswer(View view)
    {
        Log.i("Tag",(String)view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
           score++;
           resultText.setText("CORRECT!");
        }
        else
        {
            resultText.setText("INCORRECT!");
        }
    }

    public void go(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button) findViewById(R.id.go);

        TextView sumText=(TextView)findViewById(R.id.sumText);

        Button button0=(Button) findViewById(R.id.button0) ;
        Button button1=(Button) findViewById(R.id. button1) ;
        Button button2=(Button) findViewById(R.id.button2) ;
        Button button3=(Button) findViewById(R.id.button3) ;
        resultText=(TextView)findViewById(R.id.resultText);
        Random random=new Random();

        int a=random.nextInt(21);
        int b=random.nextInt(21);

        sumText.setText(Integer.toString(a)+ "+" +Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        int incorrectAnswer;

        for(int i=0;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answer.add(a+b);
            }
            else
            {
                incorrectAnswer=random.nextInt(41);
                while(incorrectAnswer==a+b)
                    incorrectAnswer=random.nextInt(41);
                answer.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }
}
