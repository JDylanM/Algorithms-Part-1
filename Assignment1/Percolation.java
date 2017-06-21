/*----------------------------------------------------------------
 *  Author:        Dylan
 *  Written:       21/6/2017
 *  Last updated:  21/6/2017
 *
 *  Compilation:   javac-algs4 Percolation.java
 *  Execution:     java-algs4 Percolation
 *
 *  A class for a percolation system. Uses WeightedQuickUnionUF for efficiency.
 *
 *----------------------------------------------------------------*/


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // All sites, false = closed, true = open.
    private boolean[] grid;
    // Grid size
    private int gridSize;
    // Uses the WeightedQuickUnionUF data structure
    private WeightedQuickUnionUF uf;
    // Total open sites
    private int openSites = 0;
    // Top virtual site to use the top/bottom virtual trick
    private int topVirtualSite;
    // Top virtual site to use the top/bottom virtual trick
    private int bottomVirtualSite;
    // total sites
    private int totalSites;

    /**
    * Initializes an empty union–find data structure with {@code n} sites
    * {@code 0} through {@code n-1}. Each site is initially in its own
    * component. Also initializes grid, gridSize, topVirtualSite and
    * bottomVirtualSite
    *
    * @param  n the number of sites
    * @throws IllegalArgumentException if {@code n < 0}
    */
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

    /**
     * Converts a 2-dimensional position to 1-dimensional to match
     * data structure of the WeightedQuickUnionUF
     * @param  row the row number of site in question
     * @param  col the col number of site in question
     * @return
     */
    private int xyTo1D(int row, int col)
    {
        validate(row, col);
        return (row-1) * gridSize + col-1;
    }

    /**
     * Validates the submitted row and col values.
     * @param  row the row number of site in question
     * @param  col the col number of site in question
     * @throws IndexOutOfBoundsException If invalid values
     */
    private void validate(int row, int col)
    {
        if (row <= 0 || row > gridSize) throw new IllegalArgumentException("row index out of bounds");
        if (col <= 0 || col > gridSize) throw new IllegalArgumentException("col index out of bounds");
    }

    /**
      * Uses WeightedQuickUnionUF connect if bottom or top row to
      * corresponding virtual sites
      * @param currentSite the position of currentSite in WeightedQuickUnionUF
      * data structure
      */
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

    /**
      * Uses WeightedQuickUnionUF union to neighboring open sites
      *
      * @param currentSite the position of currentSite in WeightedQuickUnionUF
      * data structure
      */
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

    /**
    * Returns the number openSites.
    *
    * @return the number of components (between {@code 1} and {@code n*n})
    */
    public int numberOfOpenSites()
    {
        return openSites;
    }


    /**
    * Opens the submitted site
    *
    */
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

    /**
    * Returns if the submitted site is open
    *
    * @return boolean
    */
    public boolean isOpen(int row, int col)
    {
        int oneDimensional = xyTo1D(row, col);
        return grid[oneDimensional];
    }

    /**
    * Uses WeighteQuickUnionUF connected method to see if its connected
    * with topVirtualSite
    * @return boolean
    */
    public boolean isFull(int row, int col)
    {
        int oneDimensional = xyTo1D(row, col);
        return uf.connected(oneDimensional, topVirtualSite);
    }

    /**
    * Uses WeighteQuickUnionUF connected method to see if bottomVirtualSite
    * is connected with topVirtualSite
    * @return boolean
    */
    public boolean percolates()
    {
        return uf.connected(topVirtualSite, bottomVirtualSite);
    }

    public static void main(String[] args)
    {
        Percolation test = new Percolation(5);
        test.open(2,1);
        System.out.println(test.isOpen(2,1));
    }
}
