package com.example.sam.braintrainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.widget.GridLayout;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    TextView resultText;
    TextView result;
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestion=0;
    TextView sumText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgain;
    TextView timertextView;
    RelativeLayout relative;
    boolean gameIsActive=true;




    public void playAgain(View view)
    {
        gameIsActive=true;
        Log.i("info " ," play Again button");
        score=0;
        numberOfQuestion=0;
        timertextView.setText("30s");
        result.setText("0/0");
      resultText.setText("");
        playAgain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timertextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                gameIsActive=false;
                playAgain.setVisibility(View.VISIBLE);
                timertextView.setText("0s");
                resultText.setText("Your Score: "+ Integer.toString((score))+ "/" + Integer.toString(numberOfQuestion));
            }
        }.start();
    }

    public void generateQuestion()
    {
        Random random=new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);

        sumText.setText(Integer.toString(a)+ "+" +Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);
        answer.clear();

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
        if(gameIsActive) {
            button0.setText(Integer.toString(answer.get(0)));
            button1.setText(Integer.toString(answer.get(1)));
            button2.setText(Integer.toString(answer.get(2)));
            button3.setText(Integer.toString(answer.get(3)));
        }
    }

    public void chooseAnswer(View view)
    {
        if(gameIsActive) {
            Log.i("Tag", (String) view.getTag());
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
                score++;
                resultText.setText("CORRECT!");
            } else {
                resultText.setText("INCORRECT!");
            }
            numberOfQuestion++;
            result.setText(Integer.toString((score)) + "/" + Integer.toString(numberOfQuestion));
            generateQuestion();
        }
    }

    public void go(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        relative.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.playAgain));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button) findViewById(R.id.go);

        sumText=(TextView)findViewById(R.id.sumText);
        if(gameIsActive) {
            button0 = (Button) findViewById(R.id.button0);
            button1 = (Button) findViewById(R.id.button1);
            button2 = (Button) findViewById(R.id.button2);
            button3 = (Button) findViewById(R.id.button3);
        }
        resultText=(TextView)findViewById(R.id.resultText);
        result=(TextView)findViewById(R.id.result);
        timertextView=(TextView)findViewById(R.id.timertextView);
        playAgain=(Button)findViewById(R.id.playAgain);
        relative=(RelativeLayout)findViewById(R.id.relative);

       generateQuestion();




    }
}
