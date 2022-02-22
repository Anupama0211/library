package com.epam.userinterface;


import com.epam.entities.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class GetQuestionIndex {

    private static final Logger LOGGER= LogManager.getLogger(GetQuestionIndex.class);

    private GetQuestionIndex() {
    }

    public static int get(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int questionIndex = 0;
        do {
            try {
                LOGGER.info("Enter the question number.");
                questionIndex = Integer.parseInt(scanner.nextLine());
                if (questionIndex > questions.size() || questionIndex < 0) {
                    LOGGER.info("Enter the correct question number");
                } else {
                    return questionIndex;
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Enter the correct question number");
            }
        } while (true);
    }

}
