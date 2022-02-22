package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;


public class RemoveQuiz {

    public boolean perform(int quizIndex) {
        boolean status = false;
        if (QuizDatabase.size() >=quizIndex) {
            QuizDatabase.deleteQuiz(quizIndex - 1);
            status = true;
        }
        return status;
    }
}
