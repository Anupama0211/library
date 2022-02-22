package com.epam.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class Question {
    private String title;
    private String difficulty;
    private String topic;
    private String[] options;
    private String answer;
    private int marks;

    @Override
    public String toString() {
        StringBuilder optionDisplay = new StringBuilder();
        char optionNumber = 'a';
        for (String option : options) {
            optionDisplay.append(optionNumber)
                    .append(".")
                    .append(option)
                    .append("\n") ;
            optionNumber = (char) (optionNumber + 1);
        }
        return "\n--------------------------------------------------------------------------\n"+
                title + "\n" + optionDisplay+
                "\n--------------------------------------------------------------------------\n";
    }
}

