import java.io.*;
import java.util.*;
import java.lang.*;

class WeightedQuickUnionUF
{
	private int[] id;
	private int[] sz;

	WeightedQuickUnionUF(int N)
	{
		id = new int[N];
		sz = new int[N];
		for(int i=0;i<N;i++)
		{
			id[i] = i;
			sz[i] = 1;
		}
	}

	private int root(int num)
	{
		while(num != id[num])
		{
			num = id[num];
		}	
		return num;
	}

	public boolean connected(int p, int q)
	{
		return root(p)==root(q);
	}

	public void union(int p, int q)
	{
		int pRoot = root(p);
		int qRoot = root(q);
		if(pRoot == qRoot) return;
		if(sz[pRoot] < sz[qRoot])
		{
			id[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		}
		else
		{
			id[qRoot] = pRoot;
			sz[qRoot] += sz[pRoot];
		}
	}

	public static void main(String[] args)
	{
		QuickUnionUF obj = new QuickUnionUF(10);
		System.out.println(obj.connected(5,7));
		System.out.println("root of 5: " + obj.root(5));
		System.out.println("root of 7: " + obj.root(7));

		obj.union(5,7);
		
		System.out.println("root of 5: " + obj.root(5));
		System.out.println("root of 7: " + obj.root(7));
		System.out.println(obj.connected(5,7));	
	}
}