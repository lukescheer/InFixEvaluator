import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Utkarsh on 8/30/18.
 * Revised by Geoerge Adams on 09/02/18.
 * Project completed by Luke Scheer on 09/14/18.
 */


public class InFixEvaluator
{
    public boolean isOperand(String str){
        return false;
    }

    public double evaluator(String str) throws Exception
    {
        // The input comes as a string; the operands are of type double.
        // The final output should be returned as a double.

        Stack operands = new Stack(999);
        Stack operators = new Stack(999);
        Stack paren = new Stack(999);

        Stack opafteropen = new Stack(999);
        int len = str.length() - 1;
        //check for spaces between each operand and operator
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/' || str.charAt(i) == '^'  ||
                    str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == ')' || str.charAt(i) == '}' || str.charAt(i) == ']') {
                if(!(str.charAt(i) == '-' && ( (str.charAt(i+1) >= 48 && str.charAt(i+1) <= 57) || str.charAt(i+1) == 46 ))) {
                    if (i != 0 && str.charAt(i - 1) != ' ' || (i != len && str.charAt(i + 1) != ' ')) {
                        throw new Exception("Invalid expression");
                        //System.out.println("Invalid expression");
                    }
                }
                i++;
            }
            else if(str.charAt(i) == 's' || str.charAt(i) == 'c' || str.charAt(i) == 'l') {
                if (str.substring(i, i + 3).equals("sin") || str.substring(i, i + 3).equals("cos") || str.substring(i, i + 3).equals("log")) {
                    if (str.charAt(i + 3) != ' ' || (i != 0 && str.charAt(i-1) != ' ')) {
                        throw new Exception("Invalid expression");
                        //System.out.println("Invalid expression");
                    }
                    i = i + 4;
                }
            }
        }

        String[]part = str.split(" ");

        boolean opapar = false;
        boolean endpar = false;
        double number;
        String curoperand;
        String lastopen;
        double op1 = 0.001;
        double op2;
        for(int i = 0; i < part.length; i++) {
            //System.out.println("parts " + part[i]);
            if (part[i].equals("(") || part[i].equals("{") || part[i].equals("[")) {
                paren.push(part[i]);
                operators.push(part[i]);
                opafteropen.push(false);
                opapar = false;
            }

            else if (part[i].equals("+") || part[i].equals("-") || part[i].equals("*") || part[i].equals("/") || part[i].equals("^") ||
                    part[i].equals("sin") || part[i].equals("cos") || part[i].equals("log")) {
                operators.push(part[i]);
                opafteropen.push(true);
                opapar = true;
            }
            else if (part[i].equals(")") || part[i].equals("}") || part[i].equals("]")) {
                if (i != 0 && ((part[i - 1].equals("(") || part[i - 1].equals("{") || part[i - 1].equals("[")))) {
                    throw new Exception("Invalid expression");
                    //System.out.println("Invalid expression");
                }
                if (operands.IsEmpty() || operators.IsEmpty()) {
                    throw new Exception("Invalid expression");
                    //System.out.println("Invalid expression");
                }
                endpar = true;
            } else {
                endpar = false;
                try {
                    number = Double.parseDouble(part[i]);
                    //System.out.println("number " + number);
                    operands.push(number);
                } catch (NumberFormatException nfe) {
                    throw new Exception("Invalid expression");
                    //System.out.println("Invalid expression");
                    //nfe.printStackTrace();
                }
            }
            if (false) {
                throw new Exception("Invalid expression");
                //System.out.println("Invalid expression");
                //System.out.println(part[i]);
            } else if(endpar) {
                lastopen = String.valueOf(paren.pop());
                switch (part[i]) {
                    case ")":
                        if (!lastopen.equals("(")) {
                            throw new Exception("Invalid expression");
                            //System.out.println("Invalid expression");
                        }
                        break;
                    case "}":
                        if (!lastopen.equals("{")) {
                            throw new Exception("Invalid expression");
                            //System.out.println("Invalid expression");
                        }
                        break;
                    case "]":
                        if (!lastopen.equals("[")) {
                            throw new Exception("Invalid expression");
                            //System.out.println("Invalid expression");
                        }
                        break;
                }
                endpar = false;

                opapar = (Boolean)opafteropen.pop();
                if (opapar){

                    opafteropen.pop();
                    curoperand = String.valueOf(operators.pop());
                    operators.pop();
                    //System.out.println(curoperand);
                    if(operands.IsEmpty()){
                        throw new Exception("Invalid expression");
                        //System.out.println("Invalid expression");
                    }
                    op2 = new Double(operands.pop().toString());
                    //System.out.println("op2 " + op2);
                    if(curoperand.equals("+") || curoperand.equals("-") || curoperand.equals("*") || curoperand.equals("/") || curoperand.equals("^")){
                        if(operands.IsEmpty()){
                            throw new Exception("Invalid expression");
                            //System.out.println("Invalid expression");
                        }
                        op1 = new Double(operands.pop().toString());
                        //System.out.println(op1);
                    }
                    switch (curoperand){
                        case "+": operands.push(op1+op2);
                            break;
                        case "-": operands.push(op1-op2);
                            break;
                        case "*": operands.push(op1*op2);
                            break;
                        case "/": operands.push(op1/op2);
                            break;
                        case "^": operands.push(Math.pow(op1, op2));
                            break;
                        case "sin": operands.push(Math.sin(op2));
                            break;
                        case "cos": operands.push(Math.cos(op2));
                            break;
                        case "log": operands.push(Math.log(op2));
                            break;
                    }

                    opapar = false;  
                } else{
                    operators.pop();
                }
            }
            //System.out.println(i);
        }
        op2 = new Double(operands.pop().toString());
        return op2;

    }

    public static void main(String[] args)throws IOException
    {
        InFixEvaluator i = new InFixEvaluator();
        try{
            //System.out.println(i.evaluator(args[0]));
            System.out.println(i.evaluator("( 2 ( + 3 ) )"));
            //System.out.println(i.evaluator("( 2 + sin ( 3 ) )  "));
//      Used for testing
//            ( 2 + 3 )
//            ( ( ) 2 + 3 )
//            ( ( 2 ) + 3 )
//            ( ( 2 + ) 3 )
//            ( ( 2 + 3 ) )
//            ( 2 ( ) + 3 )
//            ( 2 ( + ) 3 )
//            ( 2 ( + 3 ) )     
//            ( 2 + ( ) 3 )
//            ( 2 + ( 3 ) )
//            ( 2 + 3 ( ) )
//            ( )
//            ( 2 + 3 ]
//            [ 2 + 3 }
//        ( 2 + [ 3 + 5 ] )
//        { 2 / ( 3 - 3 ) }
//        { 42 + [ 87 / ( { 3 - 9 } * 56 ) ] }
//        ( { sin [ 90 ] } )        fix
//        ( sin ( log ( 3.1 ^ 3 ) ) )
//        ( 3 + 5.8k7o )
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
