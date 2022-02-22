package com.epam.userinterface.questionlibraryui;

import com.epam.entities.Question;
import com.epam.services.questionservices.QuestionBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class QuestionGeneratorUI {
    private static final Logger LOGGER= LogManager.getLogger(QuestionGeneratorUI.class);
    private QuestionGeneratorUI() {
    }

    public static Question createAQuestion() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Enter the Question");
        String title = scanner.nextLine();
        LOGGER.info("Enter the difficulty of the question");
        String difficulty = scanner.nextLine();
        LOGGER.info("Enter the topic to which the question belongs");
        String topic = scanner.nextLine();
        LOGGER.info("Enter the  options of the questions");
        String[] options = new String[4];
        char c = 'a';
        for (int i = 0; i < 4; i++) {
            LOGGER.info("Option {}->", (i + 1));
            options[i] = (char) (c + 1) + scanner.nextLine();
        }
        LOGGER.info("Enter the answer of the question");
        String answer = scanner.nextLine();
        int marks = 0;
        while (true) {
            try {
                LOGGER.info("Enter the marks of the question");
                marks = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                LOGGER.error("Enter Valid number for marks");
            }
        }

        return new QuestionBuilder().setAnswer(answer)
                .setDifficulty(difficulty)
                .setMarks(marks)
                .setOptions(options)
                .setTopic(topic)
                .setTitle(title)
                .build();
    }
}
