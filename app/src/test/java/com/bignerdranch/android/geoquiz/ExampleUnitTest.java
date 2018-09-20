package com.bignerdranch.android.geoquiz;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        QuizActivity quizActivity = new QuizActivity();
        Question[] mQuestionBank = quizActivity.mQuestionBank;
        int mCurrentIndex = 5;
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        assertEquals(mCurrentIndex, 0);
    }
}