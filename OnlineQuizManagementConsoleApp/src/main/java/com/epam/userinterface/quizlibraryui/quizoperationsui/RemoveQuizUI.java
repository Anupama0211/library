package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.services.quizservices.QuizListSize;
import com.epam.services.quizservices.QuizTitles;
import com.epam.services.quizservices.RemoveQuiz;
import com.epam.userinterface.questionlibraryui.questionoperationsui.QuestionOperationsUI;
import com.epam.userinterface.quizlibraryui.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuizUI implements QuestionOperationsUI, QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuizUI.class);

    @Override
    public void perform() {
        if (new QuizListSize().get() > 0) {
            LOGGER.info(new QuizTitles().get());
            int quizIndex = GetQuizIndex.get();
            new RemoveQuiz().perform(quizIndex);
            LOGGER.info("Quiz is deleted..");
        } else {
            LOGGER.warn("Quiz Library is empty");
        }
    }

}
