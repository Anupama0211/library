package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;


public class ViewAQuiz {

    public Quiz perform(int quizIndex) {
        return QuizDatabase.getQuiz(quizIndex - 1);
    }
}