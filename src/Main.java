import evaluator.EvaluateController;
import parser.ParseController;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String c;
        do {

            System.out.print("Enter an expression : ");
            String input = sc.next();

            Initializer initializer = new Initializer();
            initializer.init();

            ParseController parseController = new ParseController();
            parseController.init(input);

            EvaluateController evaluateController = new EvaluateController();
            System.out.println("RESULT AFTER SOLVING " + input + " is " + evaluateController.result());

            System.out.println("Do you want to continue? If yes, press 'y'.");
            c = sc.next();

        } while (c.equals("y"));
    }
}
