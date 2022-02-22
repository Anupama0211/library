package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;

public class QuizListSize {
    public int get(){
        return QuizDatabase.size();
    }
}
