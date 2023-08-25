package cz.cvut.fel.pjv;

import java.util.Objects;

public class Lab02 extends TextIO {
    private static String FILE_END_TEXT = "End of input detected!";
    private double[] list = new double[10];
    private int index = 0;
    private String input;
    private int lineCounter = 0;
    private int lineCounterForErrorOutputs = 0;

    public void start(String[] args) {
        while (!Objects.equals(input = super.getLine(), "")) {
            processTheLine();
            if (index == 10) {
                printResult();

                resetTheList();
            }
        }
        System.err.println(FILE_END_TEXT);
        if (lineCounter > 1) {
            printResult();
        }
    }

    private void processTheLine() {
        if (getIfNumber(input)) {
            list[index] = Double.parseDouble(input);
            index++;
            lineCounter++;
            lineCounterForErrorOutputs++;
        } else {
            lineCounterForErrorOutputs++;
            System.err.println("A number has not been parsed from line " + lineCounterForErrorOutputs);
        }
    }

    private boolean getIfNumber(String input) {
        return isDouble(input) || isFloat(input) || isInteger(input);
    }

    private void printResult() {
        double average = average();
        double deviation = deviation(average);
//        System.out.printf("%2d %6.3f %6.3f%n", lineCounter, average, deviation);
        String result = String.format("%2d %6.3f %6.3f", lineCounter, average, deviation);
        result = result.replace("  ", " ");
        System.out.println(result);
    }

    private double average() {
        double sum = 0.0;
        for (double number : list) {
            sum += number;
        }
        return sum / index;
    }

    private double deviation(double average) {
        double sum = 0.0;
        for (int i = 0; i < index; i++) {
            double deviation = Math.pow(average - list[i], 2);
            sum += deviation;
        }
        return Math.sqrt(sum / index);
    }

    private void resetTheList() {
        list = new double[10];
        index = 0;
        lineCounter = 0;
    }
}
