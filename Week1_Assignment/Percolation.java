/**************************
 * Written:5/22/2017      *  
 * Author:Sharique        *  
 *************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/*****************************************************************************************************
 * Percolation class defines the percolation system using an n-by-n grid of sites. Each site is either open or 
 blocked. A full site is an open site that can be connected to an open site in the top row via a chain of 
 neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the 
 bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that
 process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites 
 correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, 
 with full sites conducting. 
 ****************************************************************************************************/
public class Percolation    
{
    private boolean[] status;   //Buffer to mark the status of a cell(open/block)
    private int size;           //It stores the size of the grid
    private WeightedQuickUnionUF uf, ufBottom;      //Union-find data structure using from API
    
    //Parameterized constructor which will initialze the grid
    public Percolation(int N)           
    {
        if(N <= 0)                 //1 based indexing
        {
             throw new java.lang.IllegalArgumentException("negative number!");
            
        }
        
        size = N;
        status = new boolean[N * N + 1];
        uf = new WeightedQuickUnionUF(N * N + 1);       //UnioN-Find structure with size of N*N+1
        ufBottom = new WeightedQuickUnionUF(N * N + 2);
        
        status[0] = true;                               //Since the TOP is always open
        for (int i = 1; i < N*N+1; i++)
        {
            status[i] = false;
        }
    }
    
    //Function responsible tp convert the 2D mapping into 1d w.r.t status[]
    private int two2oneD(int i, int j)
    {
        if(i < 1 ||  i > size || j < 1 || j > size)
        {
            throw new java.lang.IndexOutOfBoundsException("x or y out of bound!");
        }
        return (i-1)*size+j;
    }
    
    //Funtion to open the respective cell    
    public void open(int i, int j)
    {
        if(i < 1 || i > size || j < 1 || j > size)
        {
            throw new java.lang.IndexOutOfBoundsException("x or y out of bound!");
        }
        if(!status[two2oneD(i, j)])
        {
            status[two2oneD(i, j)] = true;      //connect virtual top site and first row sites
            if(i == 1)
            {     
                uf.union(0, two2oneD(i, j));
                ufBottom.union(0, two2oneD(i, j));
            }
            if(i==size)
                ufBottom.union(size*size+1, two2oneD(i, j));
            
            //Check for all 4 connecting sites
            if(i < size && status[two2oneD(i+1, j)])
            {
                uf.union(two2oneD(i, j), two2oneD(i+1, j));
                ufBottom.union(two2oneD(i, j), two2oneD(i+1, j));
            }
            if(i > 1 && status[two2oneD(i-1, j)])
            {
                uf.union(two2oneD(i, j), two2oneD(i-1, j));
                ufBottom.union(two2oneD(i, j), two2oneD(i-1, j));
            }
            if(j < size && status[two2oneD(i, j+1)])
            {
                uf.union(two2oneD(i, j), two2oneD(i, j+1));
                ufBottom.union(two2oneD(i, j), two2oneD(i, j+1));
            }
            if(j > 1 && status[two2oneD(i, j-1)])
            {
                uf.union(two2oneD(i, j), two2oneD(i, j-1));
                ufBottom.union(two2oneD(i, j), two2oneD(i, j-1));
            }
        }
    }

    //Function to check the status of a cell
    public boolean isOpen(int i, int j)
    {
        if (i < 1 || i > size || j < 1 || j > size)
        {
            throw new java.lang.IndexOutOfBoundsException("x or y out of bound!");
        }    
        return status[two2oneD(i, j)];
    }
     
    //is site (row, col) full?
    public boolean isFull(int i, int j)
    {
        if (i < 1 || i > size || j < 1 || j > size)
        {
            throw new java.lang.IndexOutOfBoundsException("x or y out of bound!");
        }   
        return uf.connected(0, two2oneD(i, j));
    }

    //Check the percolation status
    public boolean percolates() 
    {
        return ufBottom.connected(0, size*size+1);
    }

    //Return the number of open cells in the grid
    public int numberOfOpenSites()
    {
        int opensites = 0;
        for(int i=1;i<=size;i++)
        {
            for(int j=1;j<=size;j++)
            {
                if(isOpen(i,j))
                    opensites++;
            }
        }
        return opensites;
    }
}