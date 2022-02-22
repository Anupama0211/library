package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;

public class AddQuiz {
    public boolean perform(Quiz quiz) {
        boolean status = false;
        if (quiz != null) {
            QuizDatabase.addQuiz(quiz);
            status = true;
        }
        return status;
    }
}
