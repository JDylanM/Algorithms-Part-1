/**
 * Wed Jun 21, 2017
 * The code models a percolation system. By using WeighteQuickUnionUF we can see if it percolates!
 * Grid starts from (1,1) to (n, n)
 */



import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private int gridSize;
    private WeightedQuickUnionUF uf;
    private int openSites = 0;
    
    public int numberOfOpenSites()
    {
        return openSites;
    }
    
    private void connectToOpenNeighbors(int currentSite) 
    {
        int aboveSite = currentSite - gridSize;
        int leftOfSite = currentSite - 1;
        int rightOfSite = currentSite + 1;
        int belowSite = currentSite + gridSize;
        
        if( aboveSite > 0 ) {
            if (grid[aboveSite]) {
                uf.union(currentSite, aboveSite);
            }
        }
        
        if(rightOfSite % gridSize != 0 && rightOfSite < gridSize*gridSize) {
            if (grid[rightOfSite]) {
                uf.union(currentSite, rightOfSite);
            }
        }
        
        if(leftOfSite % gridSize != gridSize-1 && leftOfSite > 0) {
            if (grid[leftOfSite]) {
                uf.union(currentSite, leftOfSite);
            }
        }
        
        if( belowSite < gridSize*gridSize ) {
            if (grid[belowSite]) {
                uf.union(currentSite, belowSite);
            }
        }
            
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
        int totalSites = n*n;
        grid = new boolean[totalSites];
        gridSize = n;
        uf = new WeightedQuickUnionUF(totalSites);
    }
    
    public void open(int row, int col)
    {
        validate(row, col);
        int oneDimensional = xyTo1D(row,col);
        grid[oneDimensional] = true;
        connectToOpenNeighbors(oneDimensional);
        openSites++;
    }
    
    public boolean isOpen(int row, int col)
    {
        int oneDimensional = xyTo1D(row, col);
        return grid[oneDimensional];
    }
    
    public boolean isFull(int row, int col)
    {
        int oneDimensional = xyTo1D(row, col);
        for(int topRowSite=0; topRowSite < gridSize; topRowSite++) {
            if(uf.connected(oneDimensional, topRowSite)) {
                return true;
            }
        }
        return false;
    }
    
    
    public boolean percolates()
    {
        return true;
    }
    
    public static void main(String[] args) 
    {
        Percolation percolation = new Percolation(5);
        System.out.println(percolation.xyTo1D(3,1));
        percolation.open(2, 1);
        percolation.open(3, 2);
        percolation.open(4, 1);
        percolation.open(3, 1);
        percolation.open(1,1);
        System.out.println(percolation.uf.connected(10, 5));
        System.out.println(percolation.uf.connected(10, 11));
        System.out.println(percolation.uf.connected(10, 15));
        System.out.println(percolation.uf.connected(10, 9));
        System.out.println(percolation.isFull(3, 1));
        
    }
}