/*----------------------------------------------------------------
 *  Author:        Dylan
 *  Written:       21/6/2017
 *  Last updated:  21/6/2017
 *
 *  Compilation:   javac-algs4 Percolation.java PercolationStats.java
 *  Execution:     java-algs4 Percolation n trials
 *
 *  A class for a percolation stats.
 *
 *----------------------------------------------------------------*/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.lang.Math;

public class PercolationStats {
    private double[] values;
    private int T = 0;

    public PercolationStats(int n, int trials)
    {
        values = new double[trials];
        T = trials;
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
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHi()
    {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.printf("mean                          : %f\n", stats.mean());
        StdOut.printf("stddev                        : %f\n", stats.stddev());
        StdOut.printf("stddev                        : %f\n", stats.confidenceLo());
        StdOut.printf(
            "95%% confidence interval       : [%f, %f]\n"
            , stats.confidenceLo(), stats.confidenceHi()
        );
    }
}
