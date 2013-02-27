package edu.vserver.exercises.mathpath;

import java.util.ArrayList;
import java.util.Random;

public class PathModel {

    private ArrayList<Integer> list;

    private int min, max, amountOfOptions;

    private int correctAnswer;

    private static Random rnd = new Random();

    /*
     * @.pre amountOfOptions > 0
     */
    public PathModel(int min, int max, int amountOfOptions) {
        list = new ArrayList<Integer>();
        this.min = min;
        this.max = max;
        this.amountOfOptions = amountOfOptions;
        generateAnswers();

    }

    private void generateAnswers() {
        correctAnswer = rnd.nextInt(max - min + 1) + min;
        list.add(correctAnswer);
        generateWrongAnswers(correctAnswer);

        // TODO: shuffle order
    }

    private void generateWrongAnswers(int correctAnswer) {

        while (list.size() < amountOfOptions) {
            int wrongAnswer = rnd.nextInt(max - min + 1) + min;
            if (wrongAnswer != correctAnswer) {
                list.add(wrongAnswer);
            }
        }
    }

    public int getOption(int i) {
        return list.get(i);
    }

    public int getLength() {
        return list.size();
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void generateNewAnswers() {
        list.clear();

        generateAnswers();
    }

}
