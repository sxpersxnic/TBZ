import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner questionScanner = new Scanner (new File("Q\\Q1\\QuizApp\\src\\questions.txt"));
        Scanner userInputScanner;
        ArrayList<Question> questionList = new ArrayList<>();
        ArrayList<Integer> usedQuestions = new ArrayList<>();

        int score = 0;
        int randomIndex;
        Random rand = new Random();
        int answer;

        while(questionScanner.hasNextLine())
        {
            String line = questionScanner.nextLine();
            String [] parts = line.split(",");

            try {
                Integer.parseInt(parts[5].trim());
            } catch (NumberFormatException e) {
                System.out.println("Error parsing this line: " + line);
                continue;
            }

            Question nextQuestion = new Question(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            questionList.add(nextQuestion);
        }

        System.out.println("Welcome Player! Enter your name:");
        userInputScanner = new Scanner(System.in);
        String userName = userInputScanner.nextLine();

        for(int i = 0; i < 5; i++)
        {
            do
            {
                randomIndex = rand.nextInt(questionList.size());
            }
            while(usedQuestions.contains(randomIndex));

            usedQuestions.add(randomIndex);

            Question q = questionList.get(randomIndex);

            System.out.println(" ");
            System.out.println(q.Question);
            System.out.println(q.Answer1);
            System.out.println(q.Answer2);
            System.out.println(q.Answer3);
            System.out.println(q.Answer4);

            userInputScanner = new Scanner(System.in);
            answer = Integer.parseInt(userInputScanner.next());

            if (answer == q.CorrectAnswer) {
                System.out.println(" ");
                System.out.println("Correct!");
                score += 1;
            } else {
                System.out.println(" ");
                System.out.println("Wrong!");
            }
        }

        System.out.println("Congratualtions " + userName + ", your Score is: " + score + "/5");

        //System.out.println("Working Directory = " + System.getProperty("user.dir"));


    }
}