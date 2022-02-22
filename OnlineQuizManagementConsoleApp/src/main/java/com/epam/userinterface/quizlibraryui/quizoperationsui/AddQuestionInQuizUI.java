package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.services.questionservices.QuestionListSize;
import com.epam.services.quizservices.AddQuestionInQuiz;
import com.epam.services.quizservices.QuizTitles;
import com.epam.userinterface.quizlibraryui.GetQuizIndex;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddQuestionInQuizUI implements QuizOperationsUI{
    private static final Logger LOGGER= LogManager.getLogger(AddQuestionInQuizUI.class);
    @Override
    public void perform() {
        if (new QuestionListSize().get() > 0) {
            LOGGER.info(new QuizTitles().get());
            LOGGER.info("In which quiz do you want to add the question");
            int quizIndex = GetQuizIndex.get();
            Question question = QuestionGeneratorUI.createAQuestion();
            new AddQuestionInQuiz().perform(quizIndex, question);
            LOGGER.info("Question added in the Quiz!!!");
        } else {
            LOGGER.warn("The Quiz library is empty!!!Make a new Quiz");
        }
    }
}
