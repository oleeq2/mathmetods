package SetOfEquations;
import java.lang.RuntimeException;
public class Solver
{
    public Matrix Solve(Matrix A,Matrix B,double precision)
    {
       Matrix X = new Matrix(B.getValues());
       Matrix Current;
       double prevNorm;
       double currentNorm;

       do
       {
           Matrix FactorMatrix = getFactorMatrix(A);
           Matrix FreeFactorMatrix = getFreeFactorMatrix(A,B);

           if(FactorMatrix.getSecondNorm() > 1)
               throw new RuntimeException();
           Current = Matrix.Multiply(FactorMatrix,X);
           Current = Matrix.Add(Current,FreeFactorMatrix);

           prevNorm = X.getSecondNorm();
           currentNorm = Current.getSecondNorm();

           X = Current;

       }while(Math.abs(currentNorm-prevNorm) > precision);
       return X;
    }

    Matrix getFactorMatrix(Matrix A)
    {
        double[][] factorArray = A.getValues();
        int[] dim = A.getDimension();
        int i;

        for(i = 0; i < dim[0]; i++)
        {
            int j;
            double element = factorArray[i][i];
            for(j = 0; j < dim[1]; j++)
            {
                if(j == i)
                {
                    factorArray[i][j] = 0;
                }
                factorArray[i][j] /= -element;
            }
        }
        return new Matrix(factorArray);
    }

    Matrix getFreeFactorMatrix(Matrix A,Matrix B)
    {
        double [][] dataB = B.getValues();
        double [][] dataA = A.getValues();
        int[] dim = B.getDimension();
        int i;

        for(i = 0; i < dim[0]; i++)
        {
            double element = dataA[i][i];
            int j;
            for(j = 0; j < dim[1]; j++)
                dataB[i][j] /= element;
        }

        return new Matrix(dataB);
    }
}
