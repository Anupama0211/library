package com.epam.services.quizservices;

import com.epam.database.QuizDatabase;
import com.epam.entities.Quiz;

import java.util.List;

public class QuizTitles {
    public String get() {
        List<String> quizTiltles = QuizDatabase
                .get()
                .stream()
                .map(Quiz::getTitle)
                .toList();
        StringBuilder titles =new StringBuilder();
        int index = 0;
        for (String title : quizTiltles) {
            index++;
            titles.append(index)
                    .append(". ")
                    .append(title)
                    .append("\n");
        }
        return titles.toString();
    }
}
