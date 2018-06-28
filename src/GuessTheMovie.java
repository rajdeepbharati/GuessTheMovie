import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class GuessTheMovie {
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("movies.txt"); // create a new File object, and pass "movies.txt" into its constructor
            Scanner scanner = new Scanner(file); // scanner scans our file

            int lines = 0;
            String mv = "";
            // count the number of lines in the file
            while (scanner.hasNextLine()) {
                mv = scanner.nextLine();
                lines++;
            }
            
            // generate a random number (line) that is less than or equal to the number of lines in the file
            int line = (int) (Math.random() * lines) + 1;
            String movie = "";
            int i = 0;
            Scanner scanner1 = new Scanner(file);
            // assign the movie variable a random movie title (that lies on line)
            while (scanner1.hasNextLine()) {
                movie = scanner1.nextLine();
                i++;
                if (i == line)
                    break;
            }
            
            // convert String into a char array, so that we can compare individual characters
            char[] movieC = movie.toCharArray();
            // generate blanks representing letters of the movie, or a space if the movie's name consists of several words
            char[] code = new char[movie.length()];
            for (int j = 0; j < movie.length(); j++) {
                if (movieC[j] == ' ')
                    code[j] = ' ';
                else
                    code[j] = '_';
            }
            
            int wrong = 0; // initially the number of wrong guesses is zero
            while (true) {
                System.out.print("You are guessing: ");
                System.out.println(code);
                System.out.println("You have guessed (" + wrong + ") wrong letters");
                System.out.print("Guess a letter: ");
                Scanner reader = new Scanner(System.in);
                char guess = reader.next().charAt(0);
                System.out.println(guess);
                boolean guessValue = false;
                for (int k = 0; k < code.length; k++) {
                    if (movieC[k] == guess) {
                        code[k] = guess;
                        guessValue = true;
                    }
                }
                if (!guessValue)
                    wrong++;
                if (wrong >= 10) {
                    System.out.println("The movie was: " + movie);
                    System.out.println("Sorry, You Lose!!!");
                    break;
                }
                if (Arrays.equals(code, movieC)) {
                    System.out.println("You guessed it right: " + movie);
                    System.out.println("You Win!!!");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
        }
    }
}
