package SetOfEquations;
import java.lang.RuntimeException;
public class Matrix{
    int [][] data;
    int m,n;

    public Matrix(int [][] MyMatrix){
        data = (int[][]) MyMatrix.clone();
        m = data.length;
        n = data[0].length;
    }

    public void changeValues(int [][] new_data){
        data = (int[][]) new_data.clone();
    }
    
    public void changeValues(int i,int j,int val){
        data[i][j] = val;
    }

    public int[][] getValues(){
        return (int[][]) data.clone();
    }
    public int[] getDimension(){
        int[] ret = new int[2];
        ret[0] = m;
        ret[1] = n;
        return ret;
    }

    public static Matrix Multiply(Matrix A,Matrix B){
        int lines,columns;
        lines = A.getDimension()[0];
        columns = B.getDimension()[1];

        if(lines != columns)
            throw new RuntimeException();


        int [][] arrayC = new int[lines][columns];
        int [][] arrayA = A.getValues();
        int [][] arrayB = B.getValues();

        int i,j;

        for(i = 0; i < lines;i++)
            for(j = 0; j < lines; j++)
            {
                int sum=0;
                int index;
                for(index = 0; index < lines;index++)
                    sum += arrayA[i][index]*arrayB[index][j];
            }

        return new Matrix(arrayC);
    }

    public static Matrix Add(Matrix A,Matrix B){
        int[] dimension = A.getDimension();
        int[][] arrayC = new int[dimension[0]][dimension[1]];
        int[][] arrayA = A.getValues();
        int[][] arrayB = B.getValues();
        int i,j;
        for(i=0;i<arrayA.length;i++)
            for(j=0;j<arrayB.length;j++)
                arrayC[i][j] = arrayA[i][j] + arrayB[i][j];
        return new Matrix(arrayC); 
    }

//  public int getFirstNorm()
    public int getSecondNorm()
    {
        int [] sums = new int[n];
        int i,j;
        for(i = 0; i < n; i++)
            for(j = 0; j < m;j++)
                sums[i] += Math.abs(data[i][j]);
        int ret; 
        if(sums[0] > sums[1])
            if(sums[0] > sums[2])
                ret = 0;
            else 
                ret = 2;
        else
            if(sums[1] > sums[2])
                ret = 1;
            else
                ret = 2;
        return sums[ret];
    }
//  public int getThirdNorm()

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for(int[] line : data)
        {
            for(int i : line)
                builder.append(i);
            builder.append('\n');
        }       
        return builder.toString();
    }
}
