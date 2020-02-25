package parser;
import java.util.ArrayList;
import java.util.List;
import exceptions.WrongTokenException;
import operator.Operator;
import operator.OperatorMetaData;
import validator.ValidNumber;

import static java.lang.System.exit;


public class ArrayListGenerator {
    private String input;

    ArrayListGenerator(String str) {
        this.input = str;
    }

    ValidNumber validNumber = new ValidNumber();

    public List<String> generate()
    {
        List<String> list = new ArrayList<String>();
        int i = 0;
        boolean flag = false;
        String tempLetter = "";

        char[] arr = input.toCharArray();

        boolean negativeLiteral = false, traverseLetter = false, traverseDigit = false;
        try {
            if (!(Character.isDigit(arr[arr.length - 1]) && arr[arr.length - 1] != ')'))
                throw new WrongTokenException("You have entered a wrong expression.(Last character is invalid)");
            else {
                for (i = 0; i < arr.length; i++)
                {
                    if (Character.isLetter(arr[i]) || (arr[i] == '-' && Character.isLetter(arr[i + 1]))) {
                        if (arr[i] == '-') {
                            if (input.indexOf('-') == 0 && Character.isLetter(arr[i + 1])) {
                                list.add("-1");
                                list.add("*");

                            } else if (Character.isLetter(arr[i + 1]) && OperatorMetaData.binary.containsKey(String.valueOf(arr[i - 1]))) {
                                list.add("-1");
                                list.add("*");

                            } else if (input.indexOf('-') != 0 && !Character.isDigit(arr[i - 1]) && !Character.isLetter(arr[i - 1])) {
                                list.add("-1");
                                list.add("*");

                            }
                        }

                        while (Character.isLetter(arr[i])) {
                            traverseLetter = true;
                            tempLetter += arr[i];
                            i++;
                            if (i >= arr.length)
                                break;
                        }

                        if (traverseLetter)
                            i--;
                        try {
                            if (tempLetter.length() != 0 && !(OperatorMetaData.unary.containsKey(tempLetter) || OperatorMetaData.binary.containsKey(tempLetter))) {
                                throw new WrongTokenException("Invalid operator");
                            }
                        } catch (WrongTokenException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    else if (i > 0 && arr[i] == '-' && Character.isDigit(arr[i - 1]) && Character.isDigit(arr[i + 1])) {
                        tempLetter = String.valueOf(arr[i]);
                        try {
                            if (!(OperatorMetaData.unary.containsKey(tempLetter) || OperatorMetaData.binary.containsKey(tempLetter))) {
                                throw new WrongTokenException("Invalid operator");
                            }
                        } catch (WrongTokenException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (Character.isDigit(arr[i]) || arr[i] == '.' || (arr[i] == '-' && Character.isDigit(arr[i + 1]))) {
                        if (arr[i] == '-') {
                            if (input.indexOf('-') == 0 && Character.isDigit(input.charAt(i + 1))) {
                                negativeLiteral = true;
                                i++;
                            } else if (Character.isDigit(arr[i + 1]) && OperatorMetaData.binary.containsKey(String.valueOf(arr[i - 1]))) {
                                negativeLiteral = true;
                                i++;
                            } else if (input.indexOf('-') != 0 && !Character.isDigit(arr[i - 1])) {
                                negativeLiteral = true;
                                i++;
                            }
                        }
                        while (Character.isDigit(arr[i]) || arr[i] == '.')
                        {
                            traverseDigit = true;
                            tempLetter += arr[i];
                            i++;
                            if (i >= arr.length)
                                break;
                        }
                        if (traverseDigit)
                            i--;
                        if (negativeLiteral) {
                            tempLetter = "-" + tempLetter;
                        }
                        if (tempLetter.length() != 0 && validNumber.isValidNumber(tempLetter)) ;
                    }
                    else if (arr[i] == '(' || arr[i] == ')')
                    {
                        tempLetter = String.valueOf(arr[i]);
                    }
                    else {
                        tempLetter = String.valueOf(arr[i]);
                        try {
                            if (!(OperatorMetaData.unary.containsKey(tempLetter) || OperatorMetaData.binary.containsKey(tempLetter))) {
                                throw new WrongTokenException("Invalid operator");
                            }
                        } catch (WrongTokenException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                        if (tempLetter.length() != 0)
                            list.add(tempLetter);
                        tempLetter = "";
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

        return list;
    }
}