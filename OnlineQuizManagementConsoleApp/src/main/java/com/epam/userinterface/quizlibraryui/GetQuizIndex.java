package com.epam.userinterface.quizlibraryui;

import com.epam.services.quizservices.QuizListSize;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class GetQuizIndex {
    private static final Logger LOGGER= LogManager.getLogger(GetQuizIndex.class);
    private GetQuizIndex() {
    }

    public static int get() {
        Scanner scanner = new Scanner(System.in);
        int quizIndex = 0;
        do {
            try {
                LOGGER.info("Enter the quiz number");
                quizIndex = Integer.parseInt(scanner.nextLine());
                if (quizIndex > new QuizListSize().get() || quizIndex < 0) {
                    LOGGER.info("Enter the correct quiz number");
                } else {
                    return quizIndex;
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Enter the correct quiz number");
            }
        } while (true);
    }
}
