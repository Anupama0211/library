package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.services.quizservices.QuizListSize;
import com.epam.services.quizservices.QuizTitles;
import com.epam.services.quizservices.ViewAQuiz;
import com.epam.userinterface.quizlibraryui.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintAQuizUI implements QuizOperationsUI{
    private static final Logger LOGGER = LogManager.getLogger(PrintAQuizUI.class);
    @Override
    public void perform() {
        if (new QuizListSize().get() > 0) {
            LOGGER.info(new QuizTitles().get());
            int quizIndex = GetQuizIndex.get();
            LOGGER.info(new ViewAQuiz().perform(quizIndex));
        } else {
            LOGGER.warn("Quiz Library is Empty");
        }
    }
}
