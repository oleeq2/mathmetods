package SimpsonMethod;


public class Main
{
    public static void main(String[] args)
    {
        SolveIntegral solver = new SolveIntegral(new Expr(),1000);
        double a,b;
        a = Double.parseDouble(args[0]);
        b = Double.parseDouble(args[1]);
        System.out.println(solver.Solve(a,b,Math.pow(1,-5),Math.pow(1,-10)));
        return;
    }

}
