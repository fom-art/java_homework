package cz.cvut.fel.pjv;

import java.util.Arrays;

public class BruteForceAttacker extends Thief {
    private char[] characters;
    private char[] passwordSketch;
    private boolean isStopped = false;

    @Override
    public void breakPassword(int sizeOfPassword) {
        setUp(sizeOfPassword);
        guess();
    }

    private void setUp(int sizeOfPassword) {
        characters = getCharacters();
        passwordSketch = new char[sizeOfPassword];
        initializePasswordSketch();
    }

    private void initializePasswordSketch() {
        if (characters.length != 0) {
            Arrays.fill(passwordSketch, characters[0]);
        }
    }

    private void guess() {
        while (!tryOpen(passwordSketch) && !isStopped) {
            goToTheNextVariant();
        }
    }

    private void goToTheNextVariant() {
        increaseTheIndexArrayBy1(0);
    }

    private void increaseTheIndexArrayBy1(int cellIndex) {
        //I want to find out, id cell's value is the characters[]
        // value with the last index
        if (findValueIndexFromPossibleCharactersList(passwordSketch[cellIndex]) ==
                characters.length - 1) {
            //Reset the current cell value to characters[0]
            //Increase the next cell's value index by 1
            passwordSketch[cellIndex] = characters[0];
            if (cellIndex + 1 != passwordSketch.length){
                increaseTheIndexArrayBy1(cellIndex + 1);

            } else {
                isStopped = true;
            }
        } else {
            //Just increase current cell's value index by 1
            passwordSketch[cellIndex] = characters[findValueIndexFromPossibleCharactersList(
                    passwordSketch[cellIndex]) + 1];
        }

    }

    //I give you the character, you find out it's index in characters list
    private int findValueIndexFromPossibleCharactersList(char cellValue) {
        int result = 0;
        char[] charactersList = getCharacters();
        while (charactersList[result] != cellValue) {
            result++;
        }
        return result;
    }
}
