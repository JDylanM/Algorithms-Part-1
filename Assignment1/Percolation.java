public class Percolation {
    private int[][] id;
    
    public Percolation(int n)
    {
        id = new int[n][n];
    }
    
    public void open(int row, int col)
    {
        
    }
    
    public boolean isOpen(int row, int col)
    {
        return true;
    }
    
    public boolean isFull(int row, int col)
    {
        return true;
    }
    
    public int numberOfOpenSites()
    {
        return 0;
    }
    
    public boolean percolates()
    {
        return true;
    }
    
    public static void main(String[] args) 
    {
        Percolation percolation = new Percolation(10);
        System.out.println("tjo bre");
    }
}