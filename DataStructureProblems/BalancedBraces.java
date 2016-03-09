import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
	
	/*
	 * This program takes in a string of braces and returns whether they are balanced or not.
	 * The first argument is the number of test cases followed by strings of braces.
	 * If  
	 * 			6 
	 * 			[]{}() 
	 * 			{[()]} 
	 * 			[{)} 
	 * 			}[]{
	 * 			{[]()}
	 * 			{[]{
	 * is entered the output should be 
	 * 			YES 
	 * 			YES 
	 * 			NO 
	 * 			NO
	 * 			YES
	 * 			NO
	 * since the first two strings of braces are balanced but the last is not. 
	 * 
	 * Be sure to ensure that there are no extra spaces or lines in input.
	 */
public class BalancedBraces {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        HashMap <String,String> matches=new HashMap<String,String>();
	        matches.put("(",")");
	        matches.put("[","]");
	        matches.put("{","}");
	        matches.put("<",">");
        int numTests=Integer.parseInt(in.nextLine());
        for(int i=0;i<numTests;i++)
        {
            Stack<String> stack = new Stack<String>();
            //boolean isBalanced=true;
            String str=in.nextLine();
            
            //first checks to see if length of string is even...if it is not then the braces are not balanced
            if(str.length()%2==0)
            {

                for(int j=0;j<str.length();j++)
                {	
                		//first push first brace onto stack
                        stack.push(String.valueOf(str.charAt(j)));
                        
                        //if brace pushed is not a starting brace ie not in our brace library "matches"
                        //then return "NO" that braces are not balanced
                        if(!matches.containsKey(stack.peek()))
                        {
                        	System.out.println("NO");
                        	break;
                        }
                        //if we are not at the end of the string and the next brace is not a key to our library
                        //then check to see if next brace in sequence is a math to what's in the stack, if it is then
                        //keep popping matches from the stack
                        else if(j!=str.length()-1
                        		&&!matches.containsKey(String.valueOf(str.charAt(j+1)))
                        		&&matches.get(stack.peek()).equals(String.valueOf(str.charAt(j+1))))
                        {
                        	while(!stack.isEmpty()
                        			&&j!=str.length()-1
                            		&&matches.get(stack.peek()).equals(String.valueOf(str.charAt(j+1)))) 		
                           {
                            	stack.pop();
                            	j++;
                           }
                        }
                        //if neither of the other two conditions are met and it turns out the brace is not a key in
                        //our matches library then the brace is a none matching one so we return no
                        else if(j!=str.length()-1&&!matches.containsKey(String.valueOf(str.charAt(j+1))))
                        {
                        	System.out.println("NO");
                        	break;
                        }
                        //check to see if stack is empty and if we have reached the end of the string
                        //if so all braces have been matched and they are balanced so return YES
                        if(stack.isEmpty()&&j==str.length()-1)
                        {
                        	System.out.println("YES");
                        	break;
                        }
                        //if stack is not empty and we have reached the last brace then we have matched 
                        //all possible braces that had matches and the braces are not balanced
                        else if(!stack.isEmpty()&&j==str.length()-1)
                        {
                        	System.out.println("NO");
                        	break;
                        }
                      //at this point if none of the previous conditions are met then we have an opening brace and 
                      //need to add it to our stack next round in the loop
                }
                
            }	
            else
                System.out.println("NO");   
	        
        }
    }
}