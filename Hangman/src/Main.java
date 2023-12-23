import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner yesNo = new Scanner(System.in);
        do {
            File f = new File("hangmanAnswers.txt");
            Scanner scan = new Scanner(f);
            int allowedGuesses = 7;
            int guessesSoFar = 0;
            int numOfAnswers = Integer.parseInt(scan.nextLine());
            final String[] possibleAnswers = new String[numOfAnswers];
            for (int i = 0; i < numOfAnswers; i++)
                possibleAnswers[i] = scan.nextLine();
            //^ possible answers is loaded, int  with number of guesses allowed, int  with number of guesses used


            Scanner getGuess = new Scanner(System.in);
            System.out.println("Hangman!\n");
            //^ keyboard scanner initialized
            final StringBuilder answer = new StringBuilder(possibleAnswers[(int) (Math.random() * 9) + 1]); //answer chosen
            ArrayList<String> lettersGuessed = new ArrayList<String>(); //to keep track of guessed letters
            StringBuilder guess = new StringBuilder(); //each letter of the current state of board
            for (int i = 0; i < answer.length(); i++) {
                guess.append(("_"));       //set up board as visible blanks("_"); TESTED
            }

            StringBuilder currentInput; //
            //until run out of guesses
            while (guessesSoFar < allowedGuesses && !answer.toString().equals(guess.toString())) {

                //print letters already guessed
                System.out.print("Incorrect Guesses: ");
                for (String item : lettersGuessed)
                    System.out.print(item + " ");
                System.out.println("\n"); //two new lines
                //print guess
                for (int i = 0; i < guess.length(); i++)
                    System.out.print(guess.substring(i, i + 1));
                System.out.println("\n"); //two new lines

                System.out.println("Guess a letter");
                currentInput = new StringBuilder(getGuess.nextLine());
                //check the guessed letter OR word
                if (lettersGuessed.contains(currentInput.toString()))
                    System.out.println("That letter has already been guessed.");
                else if (currentInput.length() == 1) {

                    lettersGuessed.add(currentInput.toString());
                    for (int j = 0; j < answer.length(); j++) {
                        if (answer.substring(j, j + 1).equals(currentInput.toString())) {
                            guess.replace(j, j + 1, currentInput.toString());
                            lettersGuessed.remove(currentInput.toString());
                        }
                    }
                    if (lettersGuessed.contains(currentInput.toString()))
                        guessesSoFar++;
                } else if (answer.toString().equals(currentInput.toString())) {

                    break;
                    
                } else {
                    System.out.println("That word was wrong");
                    guessesSoFar++;
                }

            }


            if (guessesSoFar >= 7)
                System.out.println("Sorry. You Lost. The word was " + answer + ".");
            else
                System.out.println("Congratulations! The word was \"" + answer + "\"! You won!");
            System.out.println("Would you like to play again? (y/n)");
        }

        while (yesNo.nextLine().equalsIgnoreCase("y"));
        System.exit(1);

    }
}
