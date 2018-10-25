package com.bignerdranch.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity
{
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String ANSWER_SHOWN_INDEX = "wasAnswerShown";
    private static final String CHEATS_AVAILABLE = "cheatsAvailable";

    private static int mCheatsAvailable = 3;

    private boolean mAnswerIsTrue;
    private boolean mWasAnswerShown;
    private TextView mAnswerTextView;
    private TextView mApiLevelTextView;
    private TextView mCheatsAvailableTextView;
    private Button mShowAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null)
        {
            mWasAnswerShown = savedInstanceState.getBoolean(ANSWER_SHOWN_INDEX, false);
            mCheatsAvailable = savedInstanceState.getInt(CHEATS_AVAILABLE, 3);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mCheatsAvailableTextView = (TextView) findViewById(R.id.cheats_available_text_view);
        mApiLevelTextView = (TextView) findViewById(R.id.api_level_text_view);
        mApiLevelTextView.setText("API Level: " + String.valueOf(Build.VERSION.SDK_INT));

        buildShowAnswerButton();
        updateAvailableCheats();

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
        savedInstanceState.putInt(CHEATS_AVAILABLE, mCheatsAvailable);
    }

    protected void buildShowAnswerButton()
    {
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mCheatsAvailable != 0)
                {
                    showAnswer();
                    mWasAnswerShown = true;
                    setAnswerShownResult();
                    hideButtonAnimation();
                }
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
        mCheatsAvailable--;
        updateAvailableCheats();
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mWasAnswerShown);
        setResult(Activity.RESULT_OK, data);
    }

    protected void hideButtonAnimation()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            int cx = mShowAnswerButton.getWidth() / 2;
            int cy = mShowAnswerButton.getHeight() / 2;
            float radius = mShowAnswerButton.getWidth();
            Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
            anim.addListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation)
                {
                    super.onAnimationEnd(animation);
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }
            });
            anim.start();
        }
        else
        {
            mShowAnswerButton.setVisibility(View.INVISIBLE);
        }
    }

    protected void updateAvailableCheats()
    {
        if (mCheatsAvailable == 0)
        {
            mShowAnswerButton.setText(R.string.show_answer_button_out_of_cheats);
        }
        String cheatsAvailable = "Cheats Available: " + String.valueOf(mCheatsAvailable);
        mCheatsAvailableTextView.setText(cheatsAvailable);
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