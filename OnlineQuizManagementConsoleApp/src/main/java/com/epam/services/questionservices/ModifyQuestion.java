package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;

public class ModifyQuestion {

    public boolean perform(Question question, int questionIndex) {
        boolean status = false;
        if (question!=null) {
            QuestionsDatabase.modifyQuestion(question, questionIndex - 1);
            status = true;
        }
        return status;
    }
}
