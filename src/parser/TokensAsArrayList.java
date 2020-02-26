package parser;
import java.util.ArrayList;
import java.util.List;

import exceptions.NoSuchOperatorFound;
import exceptions.WrongTokenException;
import operator.Operator;
import operator.OperatorMetaData;
import validator.ValidNumber;

import static java.lang.System.exit;


public class TokensAsArrayList {
    private String input;

    TokensAsArrayList(String str) {
        this.input = str;
    }

    ValidNumber validNumber = new ValidNumber();
    private List<String> list = new ArrayList<String>();
    public List<String> arrayListGenerator()
    {

        int i = 0;
        boolean flag = false;
        String tempData = "";
        char[] arr = input.toCharArray(); //Convert the string into character array.

        //negativeLiteral : Used to see whether encountered a negative and now have to append '-' to the next operand.
        //traverseLetter : Used to see whether the last string was a letter or not.
        //traverseDigit : Used to see if the last string was a digit or not.

        boolean negativeLiteral = false, traverseLetter = false, traverseDigit = false;

        try {

            //Seeing if the last character of string is valid or not.
            if (!(Character.isDigit(arr[arr.length - 1]) && arr[arr.length - 1] != ')'))
                throw new WrongTokenException("You have entered a wrong expression.(Last character is invalid)");
            else {
                //Parsing the string from start to end.
                for (i = 0; i < arr.length; i++)
                {
                    if (Character.isLetter(arr[i]) || (arr[i] == '-' && Character.isLetter(arr[i + 1]))) {
                    //Checking the 3 combinations of - and letter.
                        //1. -(exp starting with a letter like sin cos ) op (exp)
                        if (arr[i] == '-') {
                            if (input.indexOf('-') == 0 && Character.isLetter(arr[i + 1])) {
                                list.add("-1");
                                list.add("*");

                            }
                            //2. (Binary Operator like +*/-)-(exp starting with a letter like sin cos )
                            else if (Character.isLetter(arr[i + 1]) && OperatorMetaData.binary.containsKey(String.valueOf(arr[i - 1]))) {
                                list.add("-1");
                                list.add("*");

                            }
                            //3. exp operator (-exp)
                            else if (input.indexOf('-') != 0 && !Character.isDigit(arr[i - 1]) && !Character.isLetter(arr[i - 1])) {
                                list.add("-1");
                                list.add("*");
                            }
                        }

                        //make a word of the characters.
                        while (Character.isLetter(arr[i]))
                        {
                            traverseLetter = true;
                            tempData += arr[i];
                            i++;
                            if (i >= arr.length)
                                break;
                        }

                        //If it has been in the above loop then decrement the counter variable.
                        if (traverseLetter)
                            i--;
                        //See if the new word made is a valid operator or not. If not then throw an exception.
                        try {
                            if (tempData.length() != 0 && !(OperatorMetaData.unary.containsKey(tempData) || OperatorMetaData.binary.containsKey(tempData)))
                            {
                                throw new WrongTokenException("Invalid operator");
                            }
                        } catch (WrongTokenException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    //Checking the 3 combinations of - and digit.
                    //1. (exp) + (exp)
                    else if (i > 0 && arr[i] == '-' && Character.isDigit(arr[i - 1]) && Character.isDigit(arr[i + 1]))
                    {
                        //Add the character as it is in the arrayList.
                        tempData = String.valueOf(arr[i]);
                    }
                    //If the character encountered is digit or . append it in the string of check the possibility of "-digit" pattern.
                    else if (Character.isDigit(arr[i]) || arr[i] == '.' || (arr[i] == '-' && Character.isDigit(arr[i + 1]))) {
                        //Case 1: Checking the possibility of -
                        if (arr[i] == '-') {
                            //Case 1 : (-digit) i.e., -23(operator)exp
                            if (input.indexOf('-') == 0 && Character.isDigit(input.charAt(i + 1)))
                            {
                                negativeLiteral = true;
                                i++;
                            }
                            // (binaryOperator)-(Digit eg 23)
                            else if (Character.isDigit(arr[i + 1]) && OperatorMetaData.binary.containsKey(String.valueOf(arr[i - 1]))) {
                                negativeLiteral = true;
                                i++;
                            }
                            //exp op (-
                            else if (input.indexOf('-') != 0 && !Character.isDigit(arr[i - 1])) {
                                negativeLiteral = true;
                                i++;
                            }
                        }

                        //Append the digit or '.' in the word.
                        while (Character.isDigit(arr[i]) || arr[i] == '.')
                        {
                            traverseDigit = true;
                            tempData += arr[i];
                            i++;
                            if (i >= arr.length)
                                break;
                        }

                        //See if the last character is digit or not.
                        if (traverseDigit)
                            i--;

                        //If the "negativeLiteral" is true then may have encountered on of the above case so append -with the word hence obtained.
                        if (negativeLiteral) {
                            tempData = "-" + tempData;
                        }
                        //Before adding the number to the arrayList check the validity of word.
                        if (tempData.length() != 0 && validNumber.isValidNumber(tempData)) ;
                    }

                    //If encounter opening or closing brackets while traversing add it directly into the arraylist.
                    else if (arr[i] == '(' || arr[i] == ')')
                    {
                        tempData = String.valueOf(arr[i]);
                    }
                    else {
                        //See if have encountered an operator check validity eg +*/sin etc
                        tempData = String.valueOf(arr[i]);
                        try {
                            if (!(OperatorMetaData.unary.containsKey(tempData) || OperatorMetaData.binary.containsKey(tempData))) {
                                throw new NoSuchOperatorFound("Invalid operator");
                            }
                        } catch (NoSuchOperatorFound e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    //if the string id not empty add it into the arrayList.
                        if (tempData.length() != 0)
                            list.add(tempData);

                        //Restore everything to the initial values.
                        tempData = "";
                        traverseDigit = false;
                        traverseLetter = false;
                        negativeLiteral = false;
                    }
                }
        } catch (WrongTokenException e)
        {
            System.out.println(e.getMessage());
            exit(0);
        }

        //Return the so obtained list.
        return list;
    }
}