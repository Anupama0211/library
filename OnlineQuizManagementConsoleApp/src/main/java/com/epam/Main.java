package com.epam;

import com.epam.userinterface.QuizPortalUI;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new QuizPortalUI().launch(scanner);
        scanner.close();
    }
}

