package com.epam;

import com.epam.database.QuestionsDatabase;
import com.epam.entities.Question;
import com.epam.services.questionservices.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServicesTest {
    Question question = new QuestionBuilder()
            .setTitle("Capital Of India")
            .setOptions(new String[]{"Kolkata", "Delhi", "Hyderabad", "Bhopal"})
            .setAnswer("Delhi")
            .setTopic("GK")
            .setDifficulty("Easy")
            .setMarks(2)
            .build();
    MockedStatic<QuestionsDatabase> questionsDatabaseMockedStatic;

    @BeforeEach
    public void setUp() {
        questionsDatabaseMockedStatic =
                Mockito.mockStatic(QuestionsDatabase.class);
        questionsDatabaseMockedStatic.when(QuestionsDatabase::size).thenReturn(4);
    }

    @AfterEach
    public void close() {
        questionsDatabaseMockedStatic.close();
    }

    @Test
    void modifyQuestionTest() {
        assertTrue(new ModifyQuestion().perform(question, 1));
        assertFalse(new ModifyQuestion().perform(null, 1));
        questionsDatabaseMockedStatic.verify(() -> QuestionsDatabase.modifyQuestion(question, 0));
    }

    @Test
    void addQuestionTest() {
        assertTrue(new AddQuestion().perform(question));
        assertFalse(new AddQuestion().perform(null));
        questionsDatabaseMockedStatic.verify(() -> QuestionsDatabase.addQuestion(question));

    }

    @Test
    void viewQuestionTest() {
        assertEquals(0, new ViewQuestions().perform().size());
        questionsDatabaseMockedStatic.verify(QuestionsDatabase::getQuestions);
    }


    @Test
    void removeQuestionTest() {
        assertFalse(new RemoveQuestions().perform(0));
        assertTrue(new RemoveQuestions().perform(new QuestionListSize().get()));
        assertTrue(new RemoveQuestions().perform(1));
        questionsDatabaseMockedStatic.verify(() -> QuestionsDatabase.removeQuestion(anyInt()), times(2));
    }
}