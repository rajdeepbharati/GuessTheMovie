import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class GuessTheMovie {
    public static void main(String[] args) throws Exception {
        try {
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);

            int lines = 0;
            String mv = "";
            while (scanner.hasNextLine()) {
                mv = scanner.nextLine();
                lines++;
            }
            int line = (int) (Math.random() * lines) + 1;
            String movie = "";
            int i = 0;
            Scanner scanner1 = new Scanner(file);
            while (scanner1.hasNextLine()) {
                movie = scanner1.nextLine();
                i++;
                if (i == line)
                    break;
            }
            char[] movieC = movie.toCharArray();
            char[] code = new char[movie.length()];
            for (int j = 0; j < movie.length(); j++) {
                if (movieC[j] == ' ')
                    code[j] = ' ';
                else
                    code[j] = '_';
            }
            int wrong = 0;
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
