package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.AddQuestion;
import com.epam.services.quizservices.AddQuiz;
import com.epam.userinterface.questionlibraryui.QuestionGeneratorUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateAndAddQuizYourselfUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizYourselfUI.class);

    @Override
    public void perform() {
        Quiz quiz = createQuizQuestionsOwnYourOwn();
        if (new AddQuiz().perform(quiz)) {
            LOGGER.info("Quiz Added!!!");
        } else {
            LOGGER.warn("Quiz cannot be added");
        }
    }

    public Quiz createQuizQuestionsOwnYourOwn() {
        String display = "Enter a valid number";
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the title of the quiz");
        String title = scanner.nextLine();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        int noOfQuestions;
        do {
            try {
                LOGGER.info("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < noOfQuestions; i++) {
                    Question question = QuestionGeneratorUI.createAQuestion();
                    questionsToBeAddedInQuiz.add(question);
                    new AddQuestion().perform(question);
                }
            } catch (NumberFormatException e) {
                LOGGER.error(display);
                noOfQuestions = -1;
            }
        } while (noOfQuestions < 0);

        LOGGER.info("Quiz Made!");
        return new Quiz(title, questionsToBeAddedInQuiz);
    }

}

