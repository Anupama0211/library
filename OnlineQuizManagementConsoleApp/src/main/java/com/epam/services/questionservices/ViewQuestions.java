package com.epam.services.questionservices;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;


import java.util.List;

public class ViewQuestions {
      public List<Question> perform() {
       return QuestionsDatabase.getQuestions();
    }
}
