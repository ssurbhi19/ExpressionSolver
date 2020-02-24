import Exceptions.EmptyExpression;
import Parser.ParseController;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main
{
    public static void main(String args[])
    {
        try
        {
            Scanner sc= new Scanner(System.in);
            if (args[0].length() <=1)
            {
                char ch[]=args[0].toCharArray();
                if(Character.isDigit(ch[0]))
                {
                    System.out.println((Double.parseDouble(args[0])));
                    exit(0);
                }
                else
                throw new EmptyExpression("You have not given an input for evaluation.");
            }
            else
            {
                Initializer initializer=new Initializer();
                initializer.init();
                ParseController parseController = new ParseController();
                parseController.init(args[0]);
            }
        }
        catch(EmptyExpression e)
        {
            System.out.println(e.getMessage());
        }
    }
}
