package com.epam.userinterface.quizlibraryui.quizoperationsui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuizOperationsUIFactory {
    Map<Integer, QuizOperationsUI> quizOperationsUITypes = new HashMap<>();

    public QuizOperationsUIFactory() {
        quizOperationsUITypes.put(1, new PrintAQuizUI());
        quizOperationsUITypes.put(2, new CreateAndAddQuizYourselfUI());
        quizOperationsUITypes.put(3, new CreateAndAddQuizFromLibraryUI());
        quizOperationsUITypes.put(4, new RemoveQuizUI());
        quizOperationsUITypes.put(5, new AddQuestionInQuizUI());
        quizOperationsUITypes.put(6, new RemoveQuestionInQuizUI());
    }


    public Optional<QuizOperationsUI> getQuizOperations(int choice) {
        return Optional.ofNullable(quizOperationsUITypes.get(choice));
    }
}
