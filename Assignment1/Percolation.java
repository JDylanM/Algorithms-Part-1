/**
 * Wed Jun 21, 2017
 * The code models a percolation system. By using WeighteQuickUnionUF we can see if it percolates!
 * Grid starts from (1,1) to (n, n)
 */



import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int gridSize;
    private WeightedQuickUnionUF uf;
    
    private void connectToOpenNeighbors(int row, int col) 
    {
        
    }
    
    private int xyTo1D(int row, int col)
    {
        validate(row, col);
        return (row-1) * gridSize + col-1;
    }
    
    private void validate(int row, int col)
    {
        if (row <= 0 || row > gridSize) throw new IndexOutOfBoundsException("row index out of bounds");
        if (col <= 0 || col > gridSize) throw new IndexOutOfBoundsException("col index out of bounds");
    }
    
    public Percolation(int n)
    {
        if(n <= 0) {
            throw new IllegalArgumentException("n must be bigger than 0");
        }
        grid = new boolean[n][n];
        gridSize = n;
        uf = new WeightedQuickUnionUF(n);
    }
    
    public void open(int row, int col)
    {
        validate(row, col);
        grid[row-1][col-1] = true;
        connectToOpenNeighbors(row, col);
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
        Percolation percolation = new Percolation(7);
        System.out.println(percolation.xyTo1D(3,1));
    }
}