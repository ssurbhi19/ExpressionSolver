package Parser;

import java.util.ArrayList;
import java.util.List;
import Exceptions.WrongTokenException;
import Operator.OperatorMetaData;
import static java.lang.System.exit;

public class ArrayListGenerator
{
    private String input;
    ArrayListGenerator(String str)
    {
        this.input = str;
    }

    public List<String> generate()
    {
        List<String> list = new ArrayList<String>();
        int i = 0;
        boolean flag=false;
        StringBuilder tempDigit = new StringBuilder();
        StringBuilder tempLetter = new StringBuilder();
        StringBuilder operator = new StringBuilder();
        char[] arr = input.toCharArray();
        try {
            if (!Character.isDigit(arr[arr.length - 1]) && arr[arr.length-1] !=')')
                throw new WrongTokenException("You have entered a wrong expression.(Last character is invalid)");
            else if (arr[0] != '-' && arr[0] != '+' && arr[0] != '!' && arr[0] !='(' && !Character.isAlphabetic(arr[0]) && !Character.isDigit(arr[0]))
                throw new WrongTokenException("1st character is invalid.");
            else
                {
                if ((arr[0] == '-' && Character.isDigit(arr[1])) || (arr[0] == '+' && Character.isDigit(arr[1])))
                    tempDigit.append(arr[0]);
                else if (arr[0] == '-' && Character.isAlphabetic(arr[1]) || arr[0] == '+' && Character.isAlphabetic(arr[1]))
                {
                    if(arr[0]=='-')
                    {
                        list.add("-1");
                        list.add("*");
                    }
                }

                for (i = 0; i < arr.length; i++)
                {
                    if (arr[i] == '-' && i == 0)
                        continue;
                    else if (arr[i] == '+' && i == 0)
                        continue;

                    if (Character.isDigit(arr[i]) || arr[i] == '.')
                        tempDigit.append(arr[i]);
                    else if (Character.isAlphabetic(arr[i]))
                        tempLetter.append(arr[i]);
                    else
                    {
                        if (Character.isAlphabetic(arr[0]))
                        {
                            if (tempLetter.length() != 0)
                            {
                                if(OperatorMetaData.unary.containsKey(tempLetter.toString()) || OperatorMetaData.binary.containsKey(tempLetter.toString()))
                                {
                                    list.add(tempLetter.toString());
                                    tempLetter = new StringBuilder();
                                }
                                else
                                    throw new WrongTokenException("Invalid Operator");

                            }
                            if (tempDigit.length() != 0)
                            {
                                Double.parseDouble(tempDigit.toString());
                                list.add(tempDigit.toString());
                                tempDigit = new StringBuilder();
                            }
                        }
                        else
                        {
                            if (tempDigit.length() != 0)
                            {
                                Double.parseDouble(tempDigit.toString());
                                list.add(tempDigit.toString());
                                tempDigit = new StringBuilder();
                            }
                            if (tempLetter.length() != 0)
                            {
                                if(tempLetter.toString().contains(".") || !(OperatorMetaData.unary.containsKey(tempLetter.toString()) || !OperatorMetaData.binary.containsKey(tempLetter.toString())))
                                    throw new WrongTokenException("Invalid operand found during traversal");
                                list.add(tempLetter.toString());
                                tempLetter = new StringBuilder();
                            }
                        }


                        operator.append(arr[i]);
                        list.add(operator.toString());
                        operator = new StringBuilder();
                    }

                    if (i == (arr.length - 1))
                    {
                        if (tempLetter.length() != 0)
                        {
                            if(!OperatorMetaData.unary.containsKey(tempLetter.toString()) && !OperatorMetaData.binary.containsKey(tempLetter.toString()))
                                throw new WrongTokenException("Invalid operand found during traversal - " + tempLetter);
                            else
                                list.add(tempLetter.toString());

                        }
                        if (tempDigit.length() != 0)
                        {
                            Double.parseDouble(tempDigit.toString());
                            list.add(tempDigit.toString());

                        }

                        break;
                    }
                }
            }
        }
        catch(NumberFormatException | WrongTokenException e)
        {
            System.out.println(e.getMessage());
            exit(0);
        }
        System.out.println("ARRAYLIST"+list);
        return list;
    }
}
