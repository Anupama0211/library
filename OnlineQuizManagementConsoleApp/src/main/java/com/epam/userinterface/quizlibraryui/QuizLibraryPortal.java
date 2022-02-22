package com.epam.userinterface.quizlibraryui;


import com.epam.userinterface.quizlibraryui.quizoperationsui.QuizOperationsUI;
import com.epam.userinterface.quizlibraryui.quizoperationsui.QuizOperationsUIFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Optional;
import java.util.Scanner;

public class QuizLibraryPortal {

    private static final Logger LOGGER= LogManager.getLogger(QuizLibraryPortal.class);


    public void displayOptions() {
        LOGGER.info("Welcome to the Quiz Library!!!");
        LOGGER.info("1.View a Quiz");
        LOGGER.info("2.Create and Add a Quiz On Your Own");
        LOGGER.info("3.Create and Add a Quiz From Question Library");
        LOGGER.info("4.Delete a Quiz");
        LOGGER.info("5.Add a question in a Quiz");
        LOGGER.info("6.Delete a question in a Quiz");
        LOGGER.info("7.Exit Quiz Portal");
        LOGGER.info("Enter your choice");
    }

    public void modifyTheQuizLibrary(Scanner scanner) {
        QuizOperationsUIFactory quizOperationsUIFactory = new QuizOperationsUIFactory();
        do {
            try {
                displayOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                Optional<QuizOperationsUI> quizLibraryService = quizOperationsUIFactory.getQuizOperations(choice);
                if (quizLibraryService.isPresent()) {
                    quizLibraryService.get().perform();
                } else if (choice == 7) {
                    break;
                } else {
                    LOGGER.info("Enter a valid choice!!!");
                }
            } catch (NumberFormatException e) {
                LOGGER.error("Enter a valid choice!!!");
            }
        } while (true);
    }

}

