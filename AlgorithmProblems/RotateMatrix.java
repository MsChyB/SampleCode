import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
	
	/*In this program you are rotating a 2D MxN integer matrix counter-clockwise depending 
	 * on the number of times the user states they want it rotated.In a single 
	 * rotation each number moves one spot to the left as shown below
	 * 
	 * 		a11 a12 a13 a14          a12 a13 a14 a24
	 * 		a21 a22 a23 a24  ------> a11 a23 a33 a34
	 * 		a31 a32 a33 a34          a21 a22 a32 a44
	 * 		a41 a42 a43 a44          a31 a41 a42 a43
	 * 
	 * The inner square rotates and the outer square rotates. The minimum of M and N will 
	 * always be even.
	 * 
	 * Input Format: 
	 * First line contains three space separated integers, M, N and R, where M is the 
	 * number of rows, N is number of columns in matrix, and R is the number of times 
	 * the matrix has to be rotated. Then M lines follow, where each line contains N 
	 * space separated positive integers. These M lines represent the matrix.
	 * 
	 * Sample Input:
	 * 	4 4 1
	 * 	1 2 3 4
	 * 	5 6 7 8
	 * 	9 10 11 12
	 * 	13 14 15 16
	 * 
	 * Sample Output (rotated matrix):
	 * 	2 3 4 8
	 * 	1 7 11 12
	 * 	5 6 10 16
	 * 	9 13 14 15
	 * 
	 * Constraints:
	 * 2 <= M, N <= 300
	 * 1 <= R <= 109 
	 * min(M, N) % 2 == 0 
	 * 1 <= aij <= 108, where i is [1..M] & j is [1..N]
	 */

	
public class RotateMatrix {

    public static void main(String[] args) {
       Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        long numRotations = in.nextLong();
        
        //Enter numbers into matrix and create intermediate matrix "b"
        long a[][] =new long[m][n];
        long b[][] =new long[m][n];
        for(int a_i=0; a_i < m; a_i++)
        {
            for(int a_j=0; a_j < n; a_j++)
            {
                a[a_i][a_j] = in.nextLong();
            }
        }
        
        //make a copy of a as b
        for (int i = 0; i < a.length; i++) 
        {
             System.arraycopy(a[i], 0, b[i], 0, a[i].length);
        }
        long min=(long)Math.min(m,n);
       
        //Numbers needed for circle rotations
        int begin1 =0,end1= m-2,
        	begin2=0,end2=n-2,
        	begin3=m-1,end3=1,
        	begin4=n-1,end4=1;
        
        //Finds the number of rotations needed to make a full 360 circle (this
        //cuts down on rotations needed if number and matrix are extremely large 
        long numIts =(2*m)+(2*n)-4;
        long numForFull = (long)(numRotations/numIts);
        long rotNeedToDo= numRotations-(numForFull*numIts);
        
        long beingReplaced=0,replacing=0;
        
        //This loop is for going through each rectangle in matrix and rotating
        for(int circleNum=0;circleNum<min/2;circleNum++)
        {
        	//This loop goes through the number of rotations each specific rectangle needs
        	for(int it=0;it<rotNeedToDo;it++)
        	{
        		
        		replacing=a[circleNum][circleNum];
        		
        		//for loop to rotate left column of each rectangle
            	for(int j=begin1;j<=end1;j++)
            	{
            		beingReplaced=a[j+1][circleNum];
            		a[j+1][circleNum]=replacing;
                    replacing=beingReplaced;   
            	}
	
	            //for loop to rotate bottom rows of each rectangle
	         	for(int j=begin2;j<=end2;j++)
            	{
            		beingReplaced=a[m-(circleNum+1)][j+1];
            		a[m-(circleNum+1)][j+1]=replacing;
                    replacing=beingReplaced;
            	}
            
	            
	            //for loop to rotate right column of each rectangle
                for(int j=begin3;j>=end3;j--)
                {
                    beingReplaced=a[j-1][n-(circleNum+1)];
                    a[j-1][n-(circleNum+1)]=replacing;
                    replacing=beingReplaced;
                }
                
	            //for loop to rotate top rows of each rectangle
            	for(int j=begin4;j>=end4;j--)
            	{
                    beingReplaced=a[circleNum][j-1];
                    a[circleNum][j-1]=replacing;
                    replacing=beingReplaced;
            	}
        	}
            //get new number for rotations of next inner circle if we did not do all stated 
        	//rotations
            if(rotNeedToDo<numRotations)
            {
                numIts-=8; 
                if(numIts!=0)
                {
                    numForFull = (long)(numRotations/numIts);
                    rotNeedToDo= numRotations-(numForFull*numIts);
                }
                else
                    rotNeedToDo=0;
            }
           
            
           begin1++;
           end1--;
           begin2++;
           end2--;
           begin3--;
           end3++;
           begin4--;
           end4++;
        }
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(a[i][j]+" ");
            }      
            System.out.println();
        }
    }
}