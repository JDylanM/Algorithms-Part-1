import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
    private double[] values;

    public PercolationStats(int n, int trials)
    {
        values = new double[trials];
        for(int i=0; i < trials; i++) {
            Percolation current = new Percolation(n);
            while (!current.percolates()) {
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                current.open(row, col);
            }
            StdOut.println(current.numberOfOpenSites());
            values[i]=current.numberOfOpenSites()/n;
        }
    }

    public double mean()
    {
        return 0.0;
    }

    public double stddev()
    {
        return 0.0;
    }

    public double confidenceLo()
    {
        return 0.0;
    }

    public double confidenceHi()
    {
        return 0.0;
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
    }
}
