package com.epam.userinterface.questionlibraryui;

import com.epam.userinterface.questionlibraryui.questionoperationsui.QuestionOperationsUI;
import com.epam.userinterface.questionlibraryui.questionoperationsui.QuestionOperationsUIFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Optional;
import java.util.Scanner;

public class QuestionLibraryPortal {
    private static final Logger LOGGER = LogManager.getLogger(QuestionLibraryPortal.class);

    public void displayOptions() {
        LOGGER.info("Welcome to the Questions Library!!!");
        LOGGER.info("1.Create and add a Question to the library");
        LOGGER.info("2.Modify a question from the library");
        LOGGER.info("3.Remove a question from the library");
        LOGGER.info("4.View Questions in the Library");
        LOGGER.info("5.Exit Questions Portal");
        LOGGER.info("Enter your choice--");
    }

    public void modifyInQuestionsLibrary(Scanner scanner) {
        String display = "Enter a valid choice!!!";
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuestionOperationsUI> questionOperationsUI = new QuestionOperationsUIFactory().getQuestionOperations(choice);
                if (questionOperationsUI.isPresent()) {
                    questionOperationsUI.get().perform();
                } else if (choice == 5) {
                    break;
                } else {
                    LOGGER.info(display);
                }
            } catch (NumberFormatException e) {
                LOGGER.warn(display);
            }
        } while (true);
    }
}

