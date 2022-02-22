package com.epam.userinterface.quizlibraryui.quizoperationsui;

import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.QuestionListSize;
import com.epam.services.questionservices.ViewQuestions;
import com.epam.services.quizservices.AddQuiz;
import com.epam.userinterface.GetQuestionIndex;
import com.epam.userinterface.questionlibraryui.questionoperationsui.PrintQuestionsUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateAndAddQuizFromLibraryUI implements QuizOperationsUI {
    private static final Logger LOGGER = LogManager.getLogger(CreateAndAddQuizFromLibraryUI.class);

    @Override
    public void perform() {
        Quiz quiz = createAQuizFromQuestionLibrary();

        if (new AddQuiz().perform(quiz)) {
            LOGGER.info("Quiz Added!!!");
        } else {
            LOGGER.warn("Quiz cannot be added");
        }
    }

    public Quiz createAQuizFromQuestionLibrary() {
        Scanner scanner = new Scanner(System.in);
        List<Question> questionList = new ViewQuestions().perform();
        String display = "Enter a valid number";
        LOGGER.info("The following are the questions in the library");
        new PrintQuestionsUI().perform();
        List<Question> questionsToBeAddedInQuiz = new ArrayList<>();
        LOGGER.info("Enter the title of the quiz");
        String title = scanner.nextLine();
        int noOfQuestions;
        int indexOfQuestion;
        do {
            try {
                LOGGER.info("Enter how many questions you want to add");
                noOfQuestions = Integer.parseInt(scanner.nextLine());
                if (noOfQuestions <= new QuestionListSize().get()) {
                    LOGGER.info("Chose the questions number  you want to add in your Quiz.");
                    for (int i = 0; i < noOfQuestions; i++) {
                        indexOfQuestion = GetQuestionIndex.get(questionList);
                        questionsToBeAddedInQuiz.add(questionList.get(indexOfQuestion));
                    }
                } else {
                    LOGGER.info("We have only {}   questions in the library ", new QuestionListSize().get());
                    noOfQuestions = -1;
                    LOGGER.warn(display);
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
