package cz.cvut.fel.pjv;

import java.util.Scanner;

public class Lab01 {

    private final String ZERO_DIVISION_RESULT_TEXT = "Pokus o deleni nulou!";
    public Scanner scanner = new Scanner(System.in);

    public void start(String[] args) {
        int operation = setOperation();
        if (operation != -1) {
            Double[] numbersArray = setNumbersForOperation(operation);
            if (numbersArray.length != 0){
                int precision = setPrecision();

                if (precision != -1) {
                    System.out.println(makeTheOperation(operation, numbersArray, precision));
                }
            }
        }
    }

    private int setOperation() {
        System.out.println("Vyber operaci (1-soucet, 2-rozdil, 3-soucin, 4-podil):");
        int input = scanner.nextInt();
        int operation = (1 <= input && 4 >= input) ? input : -1;
        checkIfInputValid(operation);
        return operation;
    }

    private Double[] setNumbersForOperation(int operation) {
        System.out.println(getFirstString(operation));
        double firstNumber = scanner.nextDouble();
        System.out.println(getSecondString(operation));
        double secondNumber = scanner.nextDouble();
        if (operation == 4 && secondNumber == 0) {
            System.out.println(ZERO_DIVISION_RESULT_TEXT);
            Double[] emptyArray = {};
            return emptyArray;
        }
        Double[] numbersArray = {firstNumber, secondNumber};
        return numbersArray;
    }

    private String getFirstString(int operation) {
        switch (operation) {
            case 1: {
                return "Zadej scitanec: ";
            }
            case 2: {
                return "Zadej mensenec: ";
            }
            case 3: {
                return "Zadej cinitel: ";
            }
            case 4: {
                return "Zadej delenec: ";
            }
            default: {
                return "-1";
            }
        }
    }

    private String getSecondString(int operation) {
        switch (operation) {
            case 1: {
                return "Zadej scitanec: ";
            }
            case 2: {
                return "Zadej mensitel: ";
            }
            case 3: {
                return "Zadej cinitel: ";
            }
            case 4: {
                return "Zadej delitel: ";
            }
            default: {
                return "-1";
            }
        }
    }

    private int setPrecision() {
        System.out.println("Zadej pocet desetinnych mist: ");
        int input = scanner.nextInt();
        int precision = (0 <= input) ? input : -1;
        checkIfPrecisionValid(precision);
        return precision;
    }

    private void checkIfInputValid(Integer result) {
        if (result == -1) {
            System.out.println("Chybna volba!");
        }
    }

    private void checkIfPrecisionValid(Integer precision) {
        if (precision < 0) {
            System.out.println("Chyba - musi byt zadane kladne cislo!");
        }
    }

    private String makeTheOperation(Integer operation, Double[] numbersArray, Integer precision) {
        String result = "";
        numbersArray = getShoretenedNumbersArray(numbersArray, precision);
        switch (operation) {
            case 1: {
                result = add(numbersArray);
                break;
            }
            case 2: {
                result = subtract(numbersArray);
                break;
            }
            case 3: {
                result = multiply(numbersArray);
                break;
            }
            case 4:
                result = divide(numbersArray);
                if (result.equals(ZERO_DIVISION_RESULT_TEXT)) {
                    return result;
                }
                break;
        }
        return getFinalEquationLine(result, precision, operation, numbersArray);
    }

    private String add(Double[] numbersArray) {
        return String.valueOf(numbersArray[0] + numbersArray[1]);
    }

    private String subtract(Double[] numbersArray) {
        return String.valueOf(numbersArray[0] - numbersArray[1]);
    }

    private String multiply(Double[] numbersArray) {
        return String.valueOf(numbersArray[0] * numbersArray[1]);
    }

    private String divide(Double[] numbersArray) {
        if (numbersArray[1] != 0.0) {
            return String.valueOf(numbersArray[0] / numbersArray[1]);
        } else {
            return ZERO_DIVISION_RESULT_TEXT;
        }
    }

    private Double[] getShoretenedNumbersArray(Double[] numbersArray, int precision) {
        for (int i = 0; i < numbersArray.length; i++) {
            numbersArray[i] = Double.parseDouble(shortenNumber(String.valueOf(numbersArray[i]), precision).replaceAll(",", "."));
        }
        return numbersArray;
    }

    private String shortenNumber(String number, Integer precision) {
//        return Double.parseDouble(String.format("%." + precision + "f" + "0", Double.parseDouble(number)).replaceAll(",", "."));
        return String.format("%."+precision+"f", Double.parseDouble(number));
    }


    private String getOperationSymbol(int operation) {
        switch (operation) {
            case 1: {
                return "+";
            }
            case 2: {
                return "-";
            }
            case 3: {
                return "*";
            }
            case 4: {
                return "/";
            }
            default: {
                return "-1";
            }
        }
    }

    private String getFinalEquationLine(String result, int precision, int operation, Double[] numbersArray) {
        result = shortenNumber(result, precision);
        String operationSymbol = getOperationSymbol(operation);
        return shortenNumber(String.valueOf(numbersArray[0]), precision) + " " + operationSymbol + " "
                + shortenNumber(String.valueOf(numbersArray[1]), precision) + " = " + result;
    }
}