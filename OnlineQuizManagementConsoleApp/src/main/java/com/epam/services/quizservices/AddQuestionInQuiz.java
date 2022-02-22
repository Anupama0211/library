package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;
import com.epam.entities.Question;

public class AddQuestionInQuiz  {
    public boolean perform(int quizIndex, Question question) {
        boolean status = false;
        if (question != null) {
            QuizDatabase.addQuestionInAQuiz(quizIndex - 1, question);
            status = true;
        }
        return status;
    }
}
