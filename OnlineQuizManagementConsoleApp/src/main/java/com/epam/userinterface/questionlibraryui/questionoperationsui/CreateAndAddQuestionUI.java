package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.services.questionservices.AddQuestion;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateAndAddQuestionUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuestionUI.class);

    @Override
    public void perform() {
        Question question = QuestionGeneratorUI.createAQuestion();
        if (new AddQuestion().perform(question)) {
            LOGGER.info("Question Added!!!");
        } else {
            LOGGER.info("EMPTY QUESTIONS CANNOT BE ADDED!!!");
        }
    }
}
