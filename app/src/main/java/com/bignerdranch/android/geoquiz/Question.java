package com.bignerdranch.android.geoquiz;

public class Question
{
    private boolean mAnswerTrue;
    private int mTextResId;

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public int getTextResId()
    {
        return mTextResId;
    }
}