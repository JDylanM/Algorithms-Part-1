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
            double openSites = (double) current.numberOfOpenSites();
            double totalSites = (double)(n*n);
            double threshold = openSites / totalSites;
            StdOut.println(threshold);
            values[i]=threshold;
        }
    }

    public double mean()
    {
        return StdStats.mean(values);
    }

    public double stddev()
    {
        return StdStats.stddev(values);
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
        StdOut.println(stats.mean());
        StdOut.println(stats.stddev());
    }
}
