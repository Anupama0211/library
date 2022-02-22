package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.services.questionservices.ModifyQuestion;
import com.epam.services.questionservices.QuestionListSize;
import com.epam.services.questionservices.ViewQuestions;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModifyQuestionUI implements QuestionOperationsUI {

    private static final Logger LOGGER = LogManager.getLogger(ModifyQuestionUI.class);

    @Override
    public void perform() {
        int questionIndex;
        Question question;
        if (new QuestionListSize().get() > 0) {
            LOGGER.info(new ViewQuestions().perform());
            questionIndex = GetQuestionIndex.get(QuestionsDatabase.getQuestions());
            question = QuestionGeneratorUI.createAQuestion();
            if (new ModifyQuestion().perform(question, questionIndex)) {
                LOGGER.info("Question Modified!");
            } else {
                LOGGER.info("Null Question cannot be added");
            }
        } else {
            LOGGER.warn("The question library is empty");
        }
    }
}
