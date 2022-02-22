package com.epam.services.questionservices;

import com.epam.entities.Question;

public class QuestionBuilder {
    private String title;
    private String difficulty;
    private String topic;
    private String[] options;
    private String answer;
    private int marks;

    public QuestionBuilder setDifficulty(String difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public QuestionBuilder setTopic(String topic) {
        this.topic = topic;
        return this;

    }

    public QuestionBuilder setOptions(String[] options) {
        this.options = options;
        return  this;
    }

    public QuestionBuilder setAnswer(String answer) {
        this.answer = answer;
        return  this;
    }

    public QuestionBuilder setMarks(int marks) {
        this.marks = marks;
        return  this;
    }

    public QuestionBuilder setTitle(String title) {
        this.title = title;
        return  this;
    }

    public Question build()
    {
        return new Question(title,difficulty,topic,options,answer,marks);
    }
}
