import java.io.*;
import java.util.*;
import java.lang.*;

class UnionFindUF
{
	private int id[];

	UnionFindUF(int len)
	{
		id = new int[len];
		for(int i=0;i<len;i++)
			id[i] = i;
	}

	public boolean connected(int p, int q)
	{
		return (id[p] == id[q]);
	}

	public void union(int p, int q)
	{
		int len = id.length;
		int temp = id[p];
		for(int i=0;i<len;i++)
		{
			if(id[i] == temp)
				id[i] = id[q];
		}
	}


	public static void main(String[] args)
	{
		UnionFind obj = new UnionFind(10);
		System.out.println(obj.connected(5,7));
		obj.union(5,7);
		System.out.println(obj.connected(5,7));		
	}
}