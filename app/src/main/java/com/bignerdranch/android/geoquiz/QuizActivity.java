package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity
{
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String SCORE_INDEX = "score";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private TextView mScoreTextView;

    private int mScore = 0;

    private int mCurrentIndex = 0;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null)
        {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mScore = savedInstanceState.getInt(SCORE_INDEX, 0);
        }

        buildQuestions();
        buildScore();

        buildTrueButton();
        buildFalseButton();

        buildNextButton();
        buildPreviousButton();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(TAG, "OnStart() called");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG, "OnStart() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(SCORE_INDEX, mScore);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    protected void buildQuestions()
    {
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mNextButton.performClick();
            }
        });
    }

    protected void buildScore()
    {
        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
        updateScore();
    }

    protected void buildTrueButton()
    {
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });
    }

    protected void buildFalseButton()
    {
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });
    }

    protected void buildNextButton()
    {
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    protected void buildPreviousButton()
    {
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mCurrentIndex == 0)
                {
                    mCurrentIndex = mQuestionBank.length;
                }
                mCurrentIndex--;
                updateQuestion();
            }
        });
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        Question currentQuestion = mQuestionBank[mCurrentIndex];
        int messageResId = 0;

        if (currentQuestion.isAnswered())
        {
            messageResId = R.string.already_answered_toast;
        }
        else if (userPressedTrue == currentQuestion.isAnswerTrue())
        {
            currentQuestion.setAlreadyAnswered(true);
            messageResId = R.string.correct_toast;
            mScore++;
        }
        else
        {
            currentQuestion.setAlreadyAnswered(true);
            messageResId = R.string.incorrect_toast;
        }

        updateScore();
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

        if (allQuestionsAnswered())
        {
            float percentage = (float) mScore!=0? ((mScore*100)/mQuestionBank.length) : 0;
            String overallScore = "Overall score: " + percentage + "%";
            Toast.makeText(this, overallScore, Toast.LENGTH_SHORT).show();
        }
        else
        {
            mNextButton.performClick();
        }
    }

    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void updateScore()
    {
        mScoreTextView.setText("Score: " + mScore);
    }

    private boolean allQuestionsAnswered()
    {
        boolean allAnswered = true;
        for(Question question : mQuestionBank)
        {
            if (question.isNotAnswered())
            {
                allAnswered = false;
                break;
            }
        }
        return allAnswered;
    }
}