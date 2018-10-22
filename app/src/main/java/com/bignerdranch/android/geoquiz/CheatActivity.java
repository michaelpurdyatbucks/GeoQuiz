package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity
{
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String ANSWER_SHOWN_INDEX = "wasAnswerShown";

    private boolean mAnswerIsTrue;
    private boolean mWasAnswerShown;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null)
        {
            mWasAnswerShown = savedInstanceState.getBoolean(ANSWER_SHOWN_INDEX, false);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        buildShowAnswerButton();

        if (mWasAnswerShown)
        {
            showAnswer();
            setAnswerShownResult();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(ANSWER_SHOWN_INDEX, mWasAnswerShown);
    }

    protected void buildShowAnswerButton()
    {
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAnswer();
                mWasAnswerShown = true;
                setAnswerShownResult();
            }
        });
    }

    protected void showAnswer()
    {
        if (mAnswerIsTrue)
        {
            mAnswerTextView.setText(R.string.true_button);
        }
        else
        {
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    private void setAnswerShownResult()
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mWasAnswerShown);
        setResult(Activity.RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue)
    {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
}
