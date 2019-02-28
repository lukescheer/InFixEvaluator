import java.util.ArrayList;

/**
 * Created by Utkarsh on 8/30/18.
 * Revised by George Adams on 09/02/18.
 * Revised by Shivaram Gopal on 09/02/18.
 */


public class Stack<T>
{

    private T[] myStack;
    private int n;

    public Stack(int capacity)
    {
        myStack = (T[]) new Object[capacity];
        n = 0;

        //Initialize your stack here
    }

    public boolean IsEmpty(){
        //Write your code here
        //Return whether the stack is empty or not

        return (myStack[0] == null);
    }

    public boolean push(T val){
        //Write your code here
        //Push the new element on the stack
        //If the element was added successfully, return true
        //If the element was not added, return false
        myStack[n] = val;

        if(myStack[n] == null){
            return false;
        }
        n++;
        //remove
        //System.out.println("pushed " + val);
        //////////////////////
        return true;

    }

    public T pop() throws IndexOutOfBoundsException {
        //Write your code here
        // Method should return the top element of the stack
        // This removes the element from the stack
        // Incase the stack is empty, it should throw an error,
        // with the message "Empty Stack"


        try {
            if (IsEmpty()) {
                System.out.println("Empty Stack");
                return null;
            }
            T top = myStack[--n];
            myStack[n] = null;
            //n--;
            //System.out.println("popped " + top);
            return top;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return null;    // add your own return statement
    }

    public int size()
    {
        //Write your code here
        //number of elements currently in the stack

        return (n);               //remove this line and return the appropriate answer
    }
}
