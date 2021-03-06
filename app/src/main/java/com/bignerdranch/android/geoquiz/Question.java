package com.bignerdranch.android.geoquiz;

public class Question
{
    private boolean mAnswerTrue;
    private boolean mAlreadyAnswered;
    private boolean mCheatedOn;
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

    public void setAlreadyAnswered(boolean alreadyAnswered)
    {
        mAlreadyAnswered = alreadyAnswered;
    }

    public void setHasBeenCheatedOn(boolean cheatedOn)
    {
        mCheatedOn = cheatedOn;
    }

    public boolean hasBeenCheatedOn()
    {
        return mCheatedOn;
    }

    public boolean isNotAnswered()
    {
        return !mAlreadyAnswered;
    }

    public boolean isAnswered()
    {
        return mAlreadyAnswered;
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