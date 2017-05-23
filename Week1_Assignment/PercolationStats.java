/****************************
* Written:5/22/2017         *
* Author:Sharique           *
****************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/****************************************************************************************************************
* To perform a series of computational experiments, create a data type PercolationStats with the following API. *
****************************************************************************************************************/
public class PercolationStats
{             
    private double[] sitesNumberPercolation;        //Number of times we need to iterate for percolation 
    
    //Parameterized constructor containing open site's number for each computation. 
    public PercolationStats(int N, int T)
    {
        if(N <= 0 || T <= 0)
        {
            throw new java.lang.IllegalArgumentException("negative number!");
        }

        sitesNumberPercolation = new double[T];
        for(int t = 0; t < T; t++)
        {
        	Percolation percolation = new Percolation(N);
            int count = 0;                          //Count the number of sites open 
            while(!percolation.percolates())
            {   
                //generate random cell i.e. row value in 'i' abd col value in 'j'
                int i = StdRandom.uniform(1, N+1);
                int j = StdRandom.uniform(1, N+1);
                if(!percolation.isOpen(i, j))
                {
                    percolation.open(i, j);     //Open the site with row i and col j
                    count++; 
                }
            }
            sitesNumberPercolation[t] = (double) count / (N * N);
        }
    }

    //Function to calculate the mean value of the threshold value for perlocation
    public double mean()
    {
        return  StdStats.mean(sitesNumberPercolation);
    }

    //Function to calculate the standard deviation value of the threshold value for perlocation
    public double stddev()
    {
        return StdStats.stddev(sitesNumberPercolation);
    }
    
    private double halfInt()
    {
         return 1.96 * stddev() / Math.sqrt(sitesNumberPercolation.length);
    }
    
    public double confidenceLo()
    {
        return  mean() - halfInt();
    }
    
    public double confidenceHi()
    {
        return mean() + halfInt();
    }
    
    public static void main(String[] args)
    {
        PercolationStats ps;
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        ps = new PercolationStats(N, T);
        System.out.println("mean            =" + ps.mean());
        System.out.println("stddev          =" + ps.stddev());
        System.out.println("95% confidence interval =" + ps.confidenceLo() + "," + ps.confidenceHi());
    }
}