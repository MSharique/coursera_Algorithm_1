import java.util.*;
import java.io.*;
import java.lang.*;

class QuickUnionUF
{
	private int[] id;
	QuickUnionUF(int N)
	{
		id = new int[N];
		for(int i=0;i<N;i++)
			id[i] = i;
	}

	private int root(int num)
	{
		while(num != id[num])
		{
			id[i] = id[id[i]];
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
		id[pRoot] = qRoot;
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