package com.epam.userinterface.questionlibraryui.questionoperationsui;

import com.epam.entities.Question;
import com.epam.services.questionservices.QuestionListSize;
import com.epam.services.questionservices.ViewQuestions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PrintQuestionsUI implements QuestionOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(PrintQuestionsUI.class);

    @Override
    public void perform() {
        if (new QuestionListSize().get() > 0) {
            List<Question> questions = new ViewQuestions().perform();
            int index = 1;
            for (Question question : questions) {
                LOGGER.info("{} {}", index, question);
                index++;
            }
        } else {
            LOGGER.warn("The question library is empty!!!");
        }

    }
}
