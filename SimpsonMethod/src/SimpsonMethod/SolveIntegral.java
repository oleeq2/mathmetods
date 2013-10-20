package SimpsonMethod;

public class SolveIntegral
{
    IExpression integrableFunction;
    final int iterationLimit;

    public SolveIntegral(IExpression function,int iterationLimit)
    {
        this.integrableFunction = function;
        this.iterationLimit = iterationLimit;
    }
    
    public double Solve(double a,double b,double precision,double i_step)
    {
        double step = i_step;
        double prevValue;
        double currentValue = getIntegral(a,b,step);
        double RungeError;
        int iter = 0;
        do
        {
            prevValue = currentValue;
            step /= 2;
            currentValue = getIntegral(a,b,step);
            RungeError = Math.abs(prevValue - currentValue)/15; 
            iter++;
        }while(RungeError > precision && iter < iterationLimit);
        return currentValue;
    }

   double getIntegral(double a,double b,double step)
   {
       double i;
       double integralValue = 0;
       for(i = a; i < b; i+= step ) //TODO Condition
       {
           double left  = integrableFunction.getValue(i);
           double med   = 4*integrableFunction.getValue(i + step/2 );
           double right = integrableFunction.getValue(i+step);
           integralValue += (step/6)*(left + med + right);
       }
       return integralValue;
   }
}
