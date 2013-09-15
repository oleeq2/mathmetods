
class Solver{
    int FactorMatrix[][];
    int FreeMembers[];
    public Solver(int AMatrix[][],int BMatrix[]){
        this.FactorMatrix = AMatrix;
        this.FreeMembers  = BMatrix;
    }

    public void setFactorMatrix(int Matrix[][]){
        this.FactorMatrix = Matrix;
    }
//    public int[][] getFactorMatrix()

    public void setFreeMembers(int[] Members){
        FreeMembers=Members;
    }
    public int[] getFreeMembers(){
        return Arrays.copyOfRange(FreeMembers,0,FreeMembers.length);
    }

}
