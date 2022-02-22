package com.epam.userinterface.questionlibraryui.questionoperationsui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestionOperationsUIFactory {
    Map<Integer, QuestionOperationsUI> questionOperationsUITypes = new HashMap<>();

    public QuestionOperationsUIFactory() {
        questionOperationsUITypes.put(1, new CreateAndAddQuestionUI());
        questionOperationsUITypes.put(2, new ModifyQuestionUI());
        questionOperationsUITypes.put(3, new RemoveQuestionsUI());
        questionOperationsUITypes.put(4, new PrintQuestionsUI());
    }

    public Optional<QuestionOperationsUI> getQuestionOperations(int choice) {

        return Optional.ofNullable(questionOperationsUITypes.get(choice));
    }
}
