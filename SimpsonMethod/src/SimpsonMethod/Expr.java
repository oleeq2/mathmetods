package SimpsonMethod;

class Expr implements IExpression
{

    @Override
    public double getValue(double x)
    {
        double ret = Math.pow(x,2);
        return ret;
    }
}