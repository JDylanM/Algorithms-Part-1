/******************************************************************************
 *  Name:    Dylan
 *
 *  Date:    21/6 2017
 *
 *  Description:  Modeling Percolation using an N-by-N grid and Union-Find data
 *                structures to determine the threshold. woot. woot.
 ******************************************************************************/

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] grid;
    private int gridSize;
    private WeightedQuickUnionUF uf;
    private int openSites = 0;
    private int topVirtualSite;
    private int bottomVirtualSite;
    private int totalSites;

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

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be bigger than 0");
        }
        totalSites = n*n;
        grid = new boolean[totalSites];
        gridSize = n;
        topVirtualSite = n*n;
        bottomVirtualSite = n*n+1;
        // plus 2 because of virtual top/bottom site.
        uf = new WeightedQuickUnionUF(totalSites+2);
    }

    public int numberOfOpenSites()
    {
        return openSites;
    }

    private void connectToVirtualSites(int currentSite) {
        // if top row, connect to virtual top site
        if (currentSite < gridSize) {
            uf.union(currentSite, topVirtualSite);
        }

        // same with bottom row
        if (currentSite >= totalSites - gridSize) {
            uf.union(currentSite, bottomVirtualSite);
        }
    }

    private void connectToOpenNeighbors(int currentSite)
    {
        int aboveSite = currentSite - gridSize;
        int leftOfSite = currentSite - 1;
        int rightOfSite = currentSite + 1;
        int belowSite = currentSite + gridSize;

        if (aboveSite > 0) {
            if (grid[aboveSite]) {
                uf.union(currentSite, aboveSite);
            }
        }

        if (rightOfSite % gridSize != 0 && rightOfSite < totalSites) {
            if (grid[rightOfSite]) {
                uf.union(currentSite, rightOfSite);
            }
        }

        if (leftOfSite % gridSize != gridSize-1 && leftOfSite > 0) {
            if (grid[leftOfSite]) {
                uf.union(currentSite, leftOfSite);
            }
        }

        if (belowSite < totalSites) {
            if (grid[belowSite]) {
                uf.union(currentSite, belowSite);
            }
        }
    }


    public void open(int row, int col)
    {
        validate(row, col);
        int oneDimensional = xyTo1D(row, col);
        if (grid[oneDimensional]) {
            return;
        }
        grid[oneDimensional] = true;
        connectToOpenNeighbors(oneDimensional);
        connectToVirtualSites(oneDimensional);
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
        if (uf.connected(oneDimensional, topVirtualSite)) {
            return true;
        }
        return false;
    }


    public boolean percolates()
    {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }

    public static void main(String[] args)
    {
        Percolation percolation = new Percolation(5);
        System.out.println(percolation.percolates());

    }
}
