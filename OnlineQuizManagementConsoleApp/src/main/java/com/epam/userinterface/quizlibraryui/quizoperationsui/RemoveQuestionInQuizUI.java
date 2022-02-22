package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Quiz;
import com.epam.services.quizservices.QuizListSize;
import com.epam.services.quizservices.QuizTitles;
import com.epam.services.quizservices.RemoveQuestionInQuiz;
import com.epam.services.quizservices.ViewAQuiz;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.quizlibraryui.GetQuizIndex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveQuestionInQuizUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(RemoveQuestionInQuizUI.class);

    @Override
    public void perform() {
        if (new QuizListSize().get() > 0) {
            LOGGER.info(new QuizTitles().get());
            LOGGER.info("In which quiz do you want to delete the question");
            int quizIndex = GetQuizIndex.get();
            Quiz quiz = new ViewAQuiz().perform(quizIndex);
            if (quiz.getQuestionList().isEmpty()) {
                LOGGER.info("Quiz is empty!Add Question in the quiz");
            } else {
                LOGGER.info(quiz);
                int questionNumber = GetQuestionIndex.get(quiz.getQuestionList());
                new RemoveQuestionInQuiz().perform(quizIndex, questionNumber);
                LOGGER.info("Question deleted in the quiz!!!");
            }
        } else {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        }
    }

}
