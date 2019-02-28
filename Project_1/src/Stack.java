import java.util.ArrayList;

/**
 * Created by Utkarsh on 8/30/18.
 * Revised by George Adams on 09/02/18.
 * Revised by Shivaram Gopal on 09/02/18.
 * Project completed by Luke Scheer on 9/14/18.
 */


public class Stack<T>
{

    private T[] myStack;
    private int n;

    public Stack(int capacity)
    {
        //Initialize the stack here
        myStack = (T[]) new Object[capacity];
        n = 0;
    }

    public boolean IsEmpty(){
        //Return whether the stack is empty or not

        return (myStack[0] == null);
    }

    public boolean push(T val){
        //Push the new element on the stack
        //If the element was added successfully, return true
        //If the element was not added, return false
        myStack[n] = val;

        if(myStack[n] == null){
            return false;
        }
        n++;
        //System.out.println("pushed " + val);
        return true;

    }

    public T pop() throws IndexOutOfBoundsException {
        //Write your code here
        // Method returns the top element of the stack
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
            //System.out.println("popped " + top);
            return top;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return null;    
    }

    public int size()
    {
        return (n);               //returns number of elements in the stack
    }
}
