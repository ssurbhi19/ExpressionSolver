import evaluator.EvaluateController;
import parser.ParseController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main
{

    public static void main (String args[]) throws Exception
    {
        InputStreamReader r=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(r);
        String input,c;
        do {
            //Take expression as an input.
            System.out.print("Enter an expression : ");
            input=br.readLine();
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
            c = br.readLine();

        } while (c.equals("y"));
    }
}
