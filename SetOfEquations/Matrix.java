public class Matrix{
    int [][] data;
    boolean isTriangular;
    boolean isChanged;
    
    public Matrix(int [][] MyMatrix){
        data = (int[][])MyMatrix.clone();
        isTriangular = isTriangularSet();
        isChanged = false;
    }

    public void changeValues(int [][] new_data){
        data = (int[][])new_data.clone();
        isChanged = true;
    }
    
    public void changeValues(int i,int j,int val)
    {
        data[i][j] = val;
        isChanged = true;
    }

//  public int getFirstNorm()
//  public int getSecondNorm()
//  public int getThirdNorm()
}
