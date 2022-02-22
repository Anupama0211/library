package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
public class AddQuestion {
    public boolean perform(Question question) {
        boolean status = false;
        if (question != null) {
            QuestionsDatabase.addQuestion(question);
            status = true;
        }
        return status;
    }
}
