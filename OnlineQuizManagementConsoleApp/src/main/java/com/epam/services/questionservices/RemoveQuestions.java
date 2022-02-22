package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;

public class RemoveQuestions {
    public boolean perform(int questionIndex) {
        boolean status=false;
        if (questionIndex > 0 && questionIndex<=QuestionsDatabase.size()) {
           QuestionsDatabase.removeQuestion(questionIndex - 1);
           status=true;
        }
        return status;
    }
}
