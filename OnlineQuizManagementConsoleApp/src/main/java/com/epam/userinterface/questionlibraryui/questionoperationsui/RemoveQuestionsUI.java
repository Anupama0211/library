package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.services.questionservices.RemoveQuestions;
import com.epam.services.questionservices.QuestionListSize;
import com.epam.services.questionservices.ViewQuestions;
import com.epam.userinterface.GetQuestionIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuestionsUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionsUI.class);
    @Override
    public void perform() {
        if (new QuestionListSize().get() > 0) {
            LOGGER.info("Following are the questions in the Library");
            new PrintQuestionsUI().perform();
            int questionIndex = GetQuestionIndex.get(new ViewQuestions().perform());
            LOGGER.info("The question is removed.....");
            new RemoveQuestions().perform(questionIndex);
        } else {
            LOGGER.warn("Question Library is empty!!!");
        }
    }
}
