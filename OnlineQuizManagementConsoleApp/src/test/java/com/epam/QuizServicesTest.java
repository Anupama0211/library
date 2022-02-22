package com.epam;

import com.epam.database.QuizDatabase;
import com.epam.entities.Question;
import com.epam.entities.Quiz;
import com.epam.services.questionservices.QuestionBuilder;
import com.epam.services.quizservices.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class QuizServicesTest {
    Question question = new QuestionBuilder()
            .setTitle("Capital Of India")
            .setOptions(new String[]{"Kolkata", "Delhi", "Hyderabad", "Bhopal"})
            .setAnswer("Delhi")
            .setTopic("GK")
            .setDifficulty("Easy")
            .setMarks(2)
            .build();

    Quiz quiz = new Quiz();
    MockedStatic<QuizDatabase> quizDatabaseMockedStatic;

    @BeforeEach
    public void setUp() {
        quizDatabaseMockedStatic =
                Mockito.mockStatic(QuizDatabase.class);
        quizDatabaseMockedStatic.when(() -> QuizDatabase.size()).thenReturn(4);
    }

    @AfterEach
    public void close() {
        quizDatabaseMockedStatic.close();
    }


    @Test
    void addQuestionInQuizTest() {
        assertFalse(new AddQuestionInQuiz().perform(1, null));
        assertTrue(new AddQuestionInQuiz().perform(1, question));
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.addQuestionInAQuiz(0, question));
    }

    @Test
    void addQuizTest() {
        assertTrue(new AddQuiz().perform(quiz));
        assertFalse(new AddQuiz().perform(null));
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.addQuiz(quiz));
    }

    @Test
    void quizListSizeTest() {
        assertEquals(QuizDatabase.size(), new QuizListSize().get());
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.size(),times(2));
    }

    @Test
    void viewAQuizTest() {
        assertEquals(QuizDatabase.getQuiz(0), new ViewAQuiz().perform(1));
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.getQuiz(0),times(2));
    }

    @Test
    void removeQuestionInQuizTest() {
        assertTrue(new RemoveQuestionInQuiz().perform(1, 1));
        assertFalse(new RemoveQuestionInQuiz().perform(0, 1));
        assertFalse(new RemoveQuestionInQuiz().perform(1, 0));
        assertFalse(new RemoveQuestionInQuiz().perform(0, 0));
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.deleteQuestionInAQuiz(0, 0));
    }

    @Test
    void quizTitlesTest() {

        quizDatabaseMockedStatic.when(() -> QuizDatabase.get()).thenReturn(Arrays.asList(quiz));
        assertEquals("1. Default Java Quiz\n", new QuizTitles().get());
    }

    @Test
    void removeQuizTest() {
        assertTrue(new RemoveQuiz().perform(1));
        assertFalse(new RemoveQuiz().perform(QuizDatabase.size() + 1));
        quizDatabaseMockedStatic.verify(() -> QuizDatabase.deleteQuiz(0));
    }

}
