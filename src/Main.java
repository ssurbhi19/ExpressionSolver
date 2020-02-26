import evaluator.EvaluateController;
import parser.ParseController;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String c;
        do {

            //Take expression as an input.
            System.out.print("Enter an expression : ");
            String input = sc.next();

            //Used to populate the hashmaps used.
            Initializer initializer = new Initializer();
            initializer.init();

            //Used to give input.
            ParseController parseController = new ParseController();
            parseController.init(input);

            //Obtain Output.
            EvaluateController evaluateController = new EvaluateController();
            System.out.println("RESULT AFTER SOLVING " + input + " is " + evaluateController.result());

            System.out.println("Do you want to continue? If yes, press 'y'.");
            c = sc.next();

        } while (c.equals("y"));
    }
}
