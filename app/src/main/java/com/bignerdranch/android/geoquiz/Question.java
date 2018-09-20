package com.bignerdranch.android.geoquiz;

public class Question
{
    private int mTextResId;
    private boolean mAnswerTrue;

    public void setTextResId(int textResId)
    {
        mTextResId = textResId;
    }

    public int getTextResId()
    {
        return mTextResId;
    }

    public void setAnswerTrue(boolean answerTrue)
    {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswerTrue()
    {
        return mAnswerTrue;
    }

    public Question(int textResId, boolean answerTrue)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}