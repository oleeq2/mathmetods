package SetOfEquations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.Exception;
import java.lang.NumberFormatException;

public class Main
{
    public static void main(String[] args)
    {
        if(args.length < 3) 
        {
            System.out.println("Слишком мало аргументов!");
            return;
        }
        double precision; 
        try
        {
            precision = Double.parseDouble(args[0]);
        }
        catch(Exception ex)
        {
            System.out.println("Неверно задана точность");
            return;
        }

        Matrix A = getMatrixFromFile(args[1]);
        Matrix B = getMatrixFromFile(args[2]);

        if(A == null && B == null)
            return;

        Solver mySolver = new Solver();
        Matrix X;
        try
        {
            X = mySolver.Solve(A,B,precision);
        }
        catch(RuntimeException ex)
        {
            System.out.println("Матрица без диагонального преобладания");
            return;
        }
        Matrix Discrepancy = getDiscrepancy(A,B,X);

        System.out.println(X.toString()+"\n");
        System.out.println(Discrepancy.toString());

    }

    static Matrix getDiscrepancy(Matrix A,Matrix B,Matrix X)
    {
        Matrix ret = null;

        ret = Matrix.Substraction(B,Matrix.Multiply(A,X));

        return ret;
    }

    static Matrix getMatrixFromFile(String filePath)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(filePath)); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }

        int n,m;
        try{
            String str = reader.readLine();
            String[] dimension = str.split("x");
            n = Integer.parseInt(dimension[0]);
            m = Integer.parseInt(dimension[1]);
        }
        catch(Exception ex)
        {
            System.out.println("Ошибка чтения");
            return null;
        }

        double [][] data = new double[n][m];
        int i = 0;

        String read = null;
        do
        {
            try
            {
                if((read = reader.readLine()) == null)
                    continue;
                String[] nums = read.split(" ");
                int j = 0;
                if(nums.length != m)
                {
                    System.out.println("Не прямоугольная матрица. Строка: " + i);
                    return null;
                }
                for(String num : nums)
                    data[i][j++] = Integer.parseInt(num);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                return null;
            }
            i++;
        }while(read != null);

        try{
            reader.close(); 
        }
        catch(IOException ex)
        {
            System.out.println("Ошибка закрытия файла");
        }

        Matrix ret = new Matrix(data);
        return ret;
    }
} 
