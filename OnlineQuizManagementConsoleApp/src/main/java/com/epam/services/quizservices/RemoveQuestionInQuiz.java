package com.epam.services.quizservices;


import com.epam.database.QuizDatabase;

public class RemoveQuestionInQuiz {

    public boolean perform(int quizIndex, int questionNumber) {

        boolean status = false;
        if (quizIndex > 0 && questionNumber > 0) {
            QuizDatabase.deleteQuestionInAQuiz(quizIndex - 1, questionNumber - 1);
            status = true;
        }
        return status;
    }
}
