package SetOfEquations;
import java.lang.RuntimeException;
public class Matrix{
    double [][] data;
    int lines,columns;

    public Matrix(double [][] MyMatrix){
        data = (double[][]) MyMatrix.clone(); 
        lines = data.length;
        columns = data[0].length;
   }

  /*  public void changeValues(double [][] new_data){
        data = (double[][]) new_data.clone();
    }
    
    public void changeValues(int i,int j,int val){
        data[i][j] = val;
    }   */

    public double[][] getValues(){
        double[][] ret = new double[lines][columns];
        int i,j;
        for(i=0;i<lines;i++)
            for(j=0;j<columns;j++)
                ret[i][j] = data[i][j];
        return ret;
    }
    public int[] getDimension(){
        int[] ret = new int[2];
        ret[0] = lines;
        ret[1] = columns;
        return ret;
    }

    public static Matrix Multiply(Matrix A,double num)
    {
        int []dim = A.getDimension();
        double [][] arrayA = A.getValues();
        double [][] arrayB = new double[dim[0]][dim[1]];

        int i,j;
        for(i=0;i<dim[0];i++)
            for(j=0;j<dim[1];j++)
                arrayB[i][j] = num*arrayA[i][j];
        return new Matrix(arrayB);
    }

    public static Matrix Multiply(Matrix A,Matrix B){
        int lines,columns;
        int[] dimA,dimB;

        dimA = A.getDimension();
        dimB = B.getDimension();

        lines = dimA[0];
        columns = dimB[1];
        if(dimA[1] != dimB[0])
            throw new RuntimeException();
        double [][] arrayC = new double[lines][columns];
        double [][] arrayA = A.getValues();
        double [][] arrayB = B.getValues();

        int i,j;

        for(i = 0; i < lines;i++)
            for(j = 0; j < columns; j++)
            {
                double sum=0;
                int index;
                for(index = 0; index < lines;index++)
                {
                    sum += arrayA[i][index]*arrayB[index][j];
                }
                arrayC[i][j] = sum;
            }

        return new Matrix(arrayC);
    }

    public static Matrix Substraction(Matrix A,Matrix B)
    {
        return Matrix.Add(A,Multiply(B,-1));
    }
    public static Matrix Add(Matrix A,Matrix B){
        int[] Adim = A.getDimension();
        int[] Bdim = B.getDimension();
        if( Adim[0] != Bdim[0] || Adim[1] != Bdim[1])
        {
            return null;
        }
        double[][] arrayC = new double[Adim[0]][Adim[1]];
        double[][] arrayA = A.getValues();
        double[][] arrayB = B.getValues();
        int i,j;

        for(i=0;i<Adim[0];i++)
            for(j=0;j<Adim[1];j++)
                arrayC[i][j] = arrayA[i][j] + arrayB[i][j];
        return new Matrix(arrayC); 
    }

//  public int getFirstNorm()
//  public int getThirdNorm()
    public double getSecondNorm()
    {
        double [] sums = new double[lines];
        int i,j;
        for(i = 0; i < lines; i++)
        {
            sums[i] = 0;
            for(j = 0; j < columns;j++)
                sums[i] += Math.abs(data[i][j]);
        }
        return getMax(sums);
    }

    double getMax(double[] data)
    {
        double max = data[0];
        int i;
        for(i=0;i<lines;i++)
        {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for(double[] line : data)
        {
            for(double i : line)
                builder.append(i + " ");
            builder.append('\n');
        }       
        return builder.toString();
    }
}
