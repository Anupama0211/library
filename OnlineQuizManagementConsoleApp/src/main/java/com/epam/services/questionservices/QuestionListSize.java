package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;

public class QuestionListSize {
    public int get() {
        return QuestionsDatabase.size();
    }
}
