package org.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class GameLogic {
    private Logger logger = Logger.getLogger(GameLogic.class.getName());
    private char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private Scanner scanner = new Scanner(System.in);

    public void playGame() {
        logger.info("Enter box number to select. Enjoy!\n");
        while (true) {
            gameBackground();
            userStep();
            if (checkWinner('X')) {
                resultGame((byte) 1);
                break;
            }
            if (!boxAvailable()) {
                resultGame((byte) 3);
                break;
            }
            compStep();
            if (checkWinner('O')) {
                resultGame((byte) 2);
                break;
            }
            if (!boxAvailable()) {
                resultGame((byte) 3);
                break;
            }
        }
    }
    private void gameBackground() {
        System.out.println(String.format("%n%n %s | %s | %s %n %s | %s | %s %n %s | %s | %s %n", box[0], box[1], box[2], box[3], box[4], box[5], box[6], box[7], box[8]));
    }

    private void userStep() {
        byte input;
        while (true) {
            input = scanner.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O') {
                    logger.info("That one is already in use. Enter another.");
                } else {
                    box[input - 1] = 'X';
                    break;
                }
            } else {
                logger.info("Invalid input. Enter again.");
            }
        }
    }

    private void compStep() {
        byte rand;
        while (true) {
            rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
            if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                box[rand - 1] = 'O';
                logger.info("The next step of user. Please select number:");
                break;
            }
        }
    }

    private boolean boxAvailable() {
        for (byte i = 0; i < 9; i++) {
            if (box[i] != 'X' && box[i] != 'O') {
                return true;
            }
        }
        return false;
    }

    private boolean checkWinner(char symbol) {
        return (box[0] == symbol && box[1] == symbol && box[2] == symbol) ||
                (box[3] == symbol && box[4] == symbol && box[5] == symbol) ||
                (box[6] == symbol && box[7] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[3] == symbol && box[6] == symbol) ||
                (box[1] == symbol && box[4] == symbol && box[7] == symbol) ||
                (box[2] == symbol && box[5] == symbol && box[8] == symbol) ||
                (box[0] == symbol && box[4] == symbol && box[8] == symbol) ||
                (box[2] == symbol && box[4] == symbol && box[6] == symbol);
    }

    private void resultGame(byte winner) {
        if (winner == 1) {
            logger.info("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 2) {
            logger.info("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
        } else if (winner == 3) {
            logger.info("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
        }
        System.exit(0);
    }
}
